package tecolotl.profesor.sesion;

import tecolotl.administracion.modelo.escuela.EscuelaBaseModelo;
import tecolotl.administracion.modelo.escuela.EscuelaPoblacionModelo;
import tecolotl.administracion.persistencia.entidad.EscuelaEntidad;
import tecolotl.administracion.validacion.escuela.EscuelaLlavePrimariaValidacion;
import tecolotl.nucleo.herramienta.ValidadorSessionBean;
import tecolotl.nucleo.validacion.PersonaNuevaValidacion;
import tecolotl.profesor.entidad.ProfesorEntidad;
import tecolotl.profesor.modelo.ProfesorModelo;
import tecolotl.profesor.validacion.ProfesorNuevoValidacion;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Stateless
public class ProfesorSesionBean implements Serializable {

    @PersistenceContext
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
        profesorEntidad.setSexo(profesorModelo.getSexo());
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
        Query query = entityManager.createNativeQuery(
                "SELECT sum(grupo.total_alumno) FROM (SELECT count(ga.id_grupo) AS total_alumno FROM profesor.profesor AS p LEFT JOIN profesor.grupo AS g ON p.id = g.id_profesor " +
                "LEFT JOIN profesor.ciclo_escolar ce ON g.inicio = ce.inicio and g.fin = ce.fin and g.id_escuela = ce.id_escuela JOIN profesor.grupo_alumno ga ON g.id = ga.id_grupo " +
                "WHERE p.id_escuela = ? AND ce.activo = TRUE GROUP BY p.id) AS grupo");
        query.setParameter(1, claveCentroTrabajo);
        Object resp = query.getSingleResult();
        escuelaPoblacionModelo.setTotalAlumnos( resp == null ? 0 : ((Number)resp).intValue());
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
     * @param idProfesor Identificado del profesor.
     * @return un profesor.
     */
    public ProfesorModelo busca(@NotNull UUID idProfesor){
        return new ProfesorModelo(entityManager.find(ProfesorEntidad.class, idProfesor));
    }

    /**
     * Busca los detalles  de un profesor por apodo
     * @param apodo Apodo con el que buscar
     * @return {@link ProfesorModelo} con los datos encontrados
     */

    public ProfesorModelo busca(@NotNull String apodo, boolean galaxia) {
        TypedQuery<ProfesorEntidad> typedQuery = entityManager.createNamedQuery("ProfesorEntidad.buscaApodo", ProfesorEntidad.class);
        typedQuery.setParameter("apodo", galaxia ? apodo.split(",")[0] : apodo);
        ProfesorEntidad profesorEntidad = typedQuery.getSingleResult();
        ProfesorModelo profesorModelo = new ProfesorModelo(profesorEntidad);
        profesorModelo.setEscuelaBaseModelo(new EscuelaBaseModelo(profesorEntidad.getEscuelaEntidad().getClaveCentroTrabajo()));
        return profesorModelo;
    }

    /**
     * Actualiza los datos de un profesor
     * @param profesorModelo datos para poder modificar un profesor
     */
    public void actualiza(@NotNull ProfesorModelo profesorModelo){
        logger.fine(profesorModelo.toString());
        ProfesorEntidad profesorEntidad = entityManager.find(ProfesorEntidad.class,profesorModelo.getId());
        profesorEntidad.setContrasenia(profesorModelo.getContrasenia());
        profesorEntidad.setNombre(profesorModelo.getNombre());
        profesorEntidad.setApellidoPaterno(profesorModelo.getApellidoPaterno());
        profesorEntidad.setApellidoMaterno(profesorModelo.getApellidoMaterno());
        //profesorEntidad.setApodo(profesorModelo.getApodo());
        profesorEntidad.setSexo(profesorModelo.getSexo());
        profesorEntidad.setCorreoEletronico(profesorModelo.getCorreoEletronico());

    }

    /**
     * Elimina un profesor.
     * @param idProfesor dato para eliminar el profesor.
     * @return numero de elementos modificados, 0 en caso de no existir.
     */
    public Integer elimina(@NotNull UUID idProfesor){
        logger.fine(idProfesor.toString());
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        TypedQuery<Long> typedQuery = entityManager.createNamedQuery("ProfesorEntidad.eliminaProfesor", Long.class);
        typedQuery.setParameter("idProfesor", idProfesor);
        if (typedQuery.getSingleResult() == 0){
            CriteriaDelete<ProfesorEntidad> criteriaDelete = criteriaBuilder.createCriteriaDelete(ProfesorEntidad.class);
            Root<ProfesorEntidad> root = criteriaDelete.from(ProfesorEntidad.class);
            criteriaDelete.where(criteriaBuilder.equal(root.get("id"), idProfesor));
            return entityManager.createQuery(criteriaDelete).executeUpdate();
        }else {
            return 1;
        }
    }

    /**
     * Valida si el profesor tiene asginado el grupo asignado
     * @param claveCentroTrabajo Clave centro de trabajo de la escuela.
     * @return Coleción de {@link ProfesorModelo}
     */
    public List<ProfesorModelo> buscaPorEscuela(@NotNull String claveCentroTrabajo) {
        TypedQuery<ProfesorEntidad> typedQuery = entityManager.createNamedQuery("ProfesorEntidad.buscaIdEscuela", ProfesorEntidad.class);
        typedQuery.setParameter("claveCentroTrabajo", claveCentroTrabajo);
        return typedQuery.getResultList().stream().map(ProfesorModelo::new).collect(Collectors.toList());
    }

}
