package tecolotl.alumno.sesion;

import tecolotl.alumno.entidad.*;
import tecolotl.alumno.modelo.TareaModelo;
import tecolotl.nucleo.herramienta.ValidadorSessionBean;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Stateless
public class TareaSesionBean {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private ValidadorSessionBean validadorSessionBean;

    @Inject
    private Logger logger;

    /**
     * Inserta una tarea.
     * @param idAlumno datos de la tarea.
     */
    public Integer inserta(@NotNull Integer idAlumno){
        TareaEntidad tareaEntidad = new TareaEntidad();
        tareaEntidad.setAsignacion(new Date());
        entityManager.persist(tareaEntidad);
        return tareaEntidad.getId();
    }

    public Integer inserta(@NotNull TareaModelo tareaModelo, @NotNull String idActividad) {
        logger.fine(tareaModelo.toString());
        validadorSessionBean.validacion(tareaModelo);
        TareaEntidad tareaEntidad = new TareaEntidad();
        tareaEntidad.setTareaEscribirActividadEntidadLista(
            tareaModelo.getEscribirBaseModeloLista().stream().map(escribirBaseModelo -> {
                TareaEscribirActividadEntidad tareaEscribirActividadEntidad = new TareaEscribirActividadEntidad();
                TareaEscribirActividadEntidadPK tareaEscribirActividadEntidadPK = new TareaEscribirActividadEntidadPK();
                EscribirActividadEntidad escribirActividadEntidad = new EscribirActividadEntidad();
                EscribirActividadEntidadPK escribirActividadEntidadPK = new EscribirActividadEntidadPK();
                escribirActividadEntidadPK.setActividadEntidad(new ActividadEntidad(idActividad));
                escribirActividadEntidadPK.setEscribirEntidad(new EscribirEntidad(escribirBaseModelo.getId()));
                escribirActividadEntidad.setEscribirActividadEntidadPK(escribirActividadEntidadPK);
                tareaEscribirActividadEntidadPK.setEscribirActividadEntidad(escribirActividadEntidad);
                tareaEscribirActividadEntidad.setTareaEscribirActividadEntidadPK(tareaEscribirActividadEntidadPK);
                return tareaEscribirActividadEntidad;
            }).collect(Collectors.toList())
        );
        entityManager.persist(tareaEntidad);
        return tareaEntidad.getId();
    }

    /**
     * Busca uan tarea.
     * @return todas las tareas encontradas.
     */
    public List<TareaModelo> busca(){
        TypedQuery<TareaEntidad> typedQuery = entityManager.createNamedQuery("TareaEntidad.busca", TareaEntidad.class);
        List<TareaEntidad> tareaEntidadLista = typedQuery.getResultList();
        return tareaEntidadLista.stream().map(TareaModelo::new).collect(Collectors.toList());
    }

    /**
     * Busca todas las tareas de un alumno
     * @param idAlumno Identificador del alumno
     * @return
     */
/*    public List<TareaModelo> busca(@NotNull Integer idAlumno){
        TypedQuery<TareaEntidad> typedQuery = entityManager.createNamedQuery("TareaEntidad.buscaPorAlumno", TareaEntidad.class);
        typedQuery.setParameter("idAlumno", idAlumno);
        return typedQuery.getResultList().stream().map(TareaModelo::new).collect(Collectors.toList());
    }*/

    /**
     * Busca una traea por llave primaria
     * @param id dato para buscar una tarea
     * @return retorna la tarea encontrada.
     */
    /*public TareaModelo busca(@NotNull @Min(1) Integer id){
        return new TareaModelo(entityManager.find(TareaEntidad.class, id));
    }*/

    /**
     * Modifica una tarea
     * @param idTarea dato para modificar la tarea.
     * @return numero de elementos modificados, 0 en caso de no existir.
     */
    public Integer actualiza(@NotNull @Valid Integer idTarea){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaUpdate<TareaEntidad> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(TareaEntidad.class);
        Root<TareaEntidad> root = criteriaUpdate.from(TareaEntidad.class);
        Predicate predicate = criteriaBuilder.equal(root.get("id"), idTarea);
        //criteriaUpdate.set(root.get("alumnoEntidad"), tareaModelo.getAlumnoModelo());
        criteriaUpdate.set(root.get("asignacion"), new Date());
        criteriaUpdate.where(predicate);
        return entityManager.createQuery(criteriaUpdate).executeUpdate();
    }

    /**
     * Elimina una tarea por llave primaria
     * @param id dato para eliminar una tarea
     * @return numero de elementos eliminados, 0 en caso de no existir
     */
    public int elimina(@NotNull @Min(1) Integer id){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaDelete<TareaEntidad> tareaEntidadCriteriaDelete = criteriaBuilder.createCriteriaDelete(TareaEntidad.class);
        Root<TareaEntidad> root = tareaEntidadCriteriaDelete.from(TareaEntidad.class);
        tareaEntidadCriteriaDelete.where(criteriaBuilder.equal(root.get("id"),id));
        return entityManager.createQuery(tareaEntidadCriteriaDelete).executeUpdate();
    }

}
