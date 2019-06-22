package tecolotl.profesor.sesion;

import tecolotl.administracion.persistencia.entidad.EscuelaEntidad;
import tecolotl.administracion.validacion.escuela.ProfesorValidacion;
import tecolotl.nucleo.herramienta.ValidadorSessionBean;
import tecolotl.profesor.entidad.ProfesorEntidad;
import tecolotl.profesor.modelo.ProfesorDashboardModelo;
import tecolotl.profesor.modelo.ProfesorModelo;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Stateless
public class ProfesorSesionBean {

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
        validadorSessionBean.validacion(profesorModelo, ProfesorValidacion.class);
        ProfesorEntidad profesorEntidad = new ProfesorEntidad();
        profesorEntidad.setNombre(profesorModelo.getNombre());
        profesorEntidad.setApellidoPaterno(profesorModelo.getApellidoPaterno());
        profesorEntidad.setApellidoMaterno(profesorModelo.getApellidoMaterno());
        profesorEntidad.setApodo(profesorModelo.getApodo());
        profesorEntidad.setContrasenia(profesorModelo.getContrasenia());
        profesorEntidad.setEscuelaEntidad(new EscuelaEntidad(profesorModelo.getEscuelaBaseModelo().getClaveCentroTrabajo()));
        entityManager.persist(profesorEntidad);
    }

    /**
     * Busca un profesor.
     * @return una lista de todos los profesores.
     */
    public List<ProfesorModelo> busca(){
        TypedQuery<ProfesorEntidad> typedQuery = entityManager.createNamedQuery("ProfesorEntidad.busca", ProfesorEntidad.class);
        List<ProfesorEntidad> profesorEntidadLista = typedQuery.getResultList();
        return profesorEntidadLista.stream().map(ProfesorModelo::new).collect(Collectors.toList());
    }

    /**
     * Busca profesores por escuela
     * @param claveCentrodeTrabajo id de la escuela para listar los profesores.
     * @return Lista de ProfesorModelo.
     */
    public Collection<ProfesorDashboardModelo> busca(@NotNull String claveCentrodeTrabajo){
        TypedQuery<ProfesorEntidad> typedQuery = entityManager.createNamedQuery("ProfesorEntidad.buscaIdEscuela", ProfesorEntidad.class);
        typedQuery.setParameter("claveCentroTrabajo", claveCentrodeTrabajo);
        Map<Integer, ProfesorDashboardModelo> profesorDashboardModeloMapa = typedQuery.getResultList().stream()
                .map(ProfesorDashboardModelo::new).collect(Collectors.toMap(ProfesorDashboardModelo::getId, Function.identity()));
        entityManager.createNamedQuery("ProfesorEntidad.buscaTotalGrupos", Object[].class)
                .setParameter("claveCentroTrabajo", claveCentrodeTrabajo).getResultList().forEach(objects -> {
            profesorDashboardModeloMapa.get(objects[1]).setTotalGrupos(((Long)objects[0]).intValue());
        });
        return profesorDashboardModeloMapa.values();
    }
    /**
     * Actualiza los datos de un profesor
     * @param profesorModelo datos para poder modificar un profesor
     * @return numero de elementos modificados, 0 en caso de no existir.
     */
    public Integer actualiza(@NotNull @Valid ProfesorModelo profesorModelo){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaUpdate<ProfesorEntidad> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(ProfesorEntidad.class);
        Root<ProfesorEntidad> root = criteriaUpdate.from(ProfesorEntidad.class);
        Predicate predicate = criteriaBuilder.equal(root.get("id"), profesorModelo.getId());
        criteriaUpdate.set(root.get("apodo"), profesorModelo.getApodo());
        criteriaUpdate.where(predicate);
        return entityManager.createQuery(criteriaUpdate).executeUpdate();
    }

    /**
     * Elimina un profesor.
     * @param idProfesor dato para eliminar el profesor.
     * @return numero de elementos modificados, 0 en caso de no existir.
     */
    public Integer elimina(@NotNull Integer idProfesor){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaDelete<ProfesorEntidad> criteriaDelete = criteriaBuilder.createCriteriaDelete(ProfesorEntidad.class);
        Root<ProfesorEntidad> root = criteriaDelete.from(ProfesorEntidad.class);
        criteriaDelete.where(criteriaBuilder.equal(root.get("id"), idProfesor));
        return entityManager.createQuery(criteriaDelete).executeUpdate();
    }


    /**
     * CRUD Relacion Profesor-Grupo
     * Se crean las funciones para obtención de la información de la relación de alumno profesor.
     *
     *
     */

    public void insertaRel(){

    }

    public List<ProfesorModelo> buscaRel(){
        List<ProfesorModelo> profesorModeloList = null;
        return profesorModeloList;
    }

    public Integer actualizaRel(){
        return 1;
    }

    public Integer eliminaRel(){
        return 1;
    }
}
