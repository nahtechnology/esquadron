package tecolotl.profesor.sesion;

import tecolotl.administracion.modelo.escuela.EscuelaPoblacionModelo;
import tecolotl.administracion.persistencia.entidad.EscuelaEntidad;
import tecolotl.administracion.validacion.escuela.EscuelaLlavePrimariaValidacion;
import tecolotl.administracion.validacion.escuela.ProfesorValidacion;
import tecolotl.nucleo.herramienta.ValidadorSessionBean;
import tecolotl.nucleo.validacion.PersonaNuevaValidacion;
import tecolotl.profesor.entidad.ProfesorEntidad;
import tecolotl.profesor.modelo.ProfesorDashboardModelo;
import tecolotl.profesor.modelo.ProfesorModelo;
import tecolotl.profesor.validacion.ProfesorNuevoValidacion;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.*;
import javax.persistence.criteria.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Stateless
public class ProfesorSesionBean {

    @PersistenceContext(unitName = "profesor")
    private EntityManager entityManager;

    @Inject
    private Logger logger;

    @Inject
    private ValidadorSessionBean validadorSessionBean;

    /**
     * Inserta un nuevo profesor.
     * @param profesorModelo dato para insertar un profesor.
     */
    public void inserta(@NotNull ProfesorModelo profesorModelo){
        validadorSessionBean.validacion(profesorModelo, ProfesorNuevoValidacion.class, PersonaNuevaValidacion.class, EscuelaLlavePrimariaValidacion.class);
        ProfesorEntidad profesorEntidad = new ProfesorEntidad();
        profesorEntidad.setNombre(profesorModelo.getNombre());
        profesorEntidad.setApellidoPaterno(profesorModelo.getApellidoPaterno());
        profesorEntidad.setApellidoMaterno(profesorModelo.getApellidoMaterno());
        profesorEntidad.setApodo(profesorModelo.getApodo());
        profesorEntidad.setContrasenia(profesorModelo.getContrasenia());
        profesorEntidad.setCorreoEletronico(profesorModelo.getCorreoEletronico());
        profesorEntidad.setEscuelaEntidad(new EscuelaEntidad(profesorModelo.getEscuelaBaseModelo().getClaveCentroTrabajo()));
        entityManager.persist(profesorEntidad);
        profesorModelo.setId(profesorEntidad.getId());
    }

    /**
     * Busca el total de profesores y de alumnos de una escuela.
     * @param claveCentroTrabajo Clave centro de la escuela
     * @return EscuelaPoblacionModelo con los datos
     */
    public EscuelaPoblacionModelo total(@NotNull @Size(min = 10, max = 14) String claveCentroTrabajo) {
        logger.fine(claveCentroTrabajo);
        TypedQuery<Long> typedQuery = entityManager.createNamedQuery("ProfesorEntidad.buscaTotalEscuela", Long.class);
        typedQuery.setParameter("claveCentroTrabajo", claveCentroTrabajo);
        Long totalProfesores = typedQuery.getSingleResult();
        EscuelaPoblacionModelo escuelaPoblacionModelo = new EscuelaPoblacionModelo();
        escuelaPoblacionModelo.setTotalProfesores(totalProfesores.intValue());
        //TODO conteo de los alumnos
        return escuelaPoblacionModelo;
    }

    /**
     * Busca un profesor.
     * @return una lista de todos los profesores.
     */
    public List<ProfesorModelo> busca(){
        TypedQuery<ProfesorEntidad> typedQuery = entityManager.createNamedQuery("ProfesorEntidad.busca", ProfesorEntidad.class);
        List<ProfesorEntidad> profesorEntidadLista = typedQuery.getResultList();
        logger.finer("Numero de profesores encontrados: ".concat(String.valueOf(profesorEntidadLista.size())));
        return profesorEntidadLista.stream().map(ProfesorModelo::new).collect(Collectors.toList());
    }

    /**
     * Método para buscar un profesor por ID.
     * @param Id Identificado del profesor.
     * @return un profesor.
     */
    public ProfesorModelo busca(@NotNull Integer Id){
        ProfesorModelo profesorModelo = new ProfesorModelo(entityManager.find(ProfesorEntidad.class, Id));
        logger.finer("Profesor encontrado por ID: ".concat(profesorModelo.toString()));
        return profesorModelo;
    }

    public Map<Integer, ProfesorDashboardModelo> busca(@NotNull String IdEscuela){
        return null;
    }

    /**
     * Actualiza los datos de un profesor
     * @param profesorModelo datos para poder modificar un profesor
     * @return numero de elementos modificados, 0 en caso de no existir.
     */
    public Integer actualiza(@NotNull ProfesorModelo profesorModelo){
        logger.fine(profesorModelo.toString());
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaUpdate<ProfesorEntidad> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(ProfesorEntidad.class);
        Root<ProfesorEntidad> root = criteriaUpdate.from(ProfesorEntidad.class);
        Predicate predicate = criteriaBuilder.equal(root.get("id"), profesorModelo.getId());
        criteriaUpdate.set(root.get("contrasenia"), profesorModelo.getContrasenia())
                .set(root.get("nombre"), profesorModelo.getNombre())
                .set(root.get("apellidoPaterno"), profesorModelo.getApellidoPaterno())
                .set(root.get("apellidoMaterno"), profesorModelo.getApellidoMaterno())
                .set(root.get("apodo"), profesorModelo.getApodo())
                .set(root.get("correoEletronico"), profesorModelo.getCorreoEletronico());
        criteriaUpdate.where(predicate);
        return entityManager.createQuery(criteriaUpdate).executeUpdate();
    }

    /**
     * Elimina un profesor.
     * @param idProfesor dato para eliminar el profesor.
     * @return numero de elementos modificados, 0 en caso de no existir.
     */
    public Integer elimina(@NotNull Integer idProfesor){
        logger.fine(idProfesor.toString());
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaDelete<ProfesorEntidad> criteriaDelete = criteriaBuilder.createCriteriaDelete(ProfesorEntidad.class);
        Root<ProfesorEntidad> root = criteriaDelete.from(ProfesorEntidad.class);
        criteriaDelete.where(criteriaBuilder.equal(root.get("id"), idProfesor));
        return entityManager.createQuery(criteriaDelete).executeUpdate();
    }


}
