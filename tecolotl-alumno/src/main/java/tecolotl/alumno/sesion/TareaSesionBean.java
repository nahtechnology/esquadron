package tecolotl.alumno.sesion;

import tecolotl.alumno.entidad.*;
import tecolotl.alumno.entidad.vista.TareasResueltasEntidad;
import tecolotl.alumno.modelo.TareaModelo;
import tecolotl.alumno.modelo.vista.TareaResuetasModelo;
import tecolotl.nucleo.herramienta.ValidadorSessionBean;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.*;
import javax.persistence.criteria.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Stateless
public class TareaSesionBean implements Serializable {

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
    public boolean inserta(@NotNull Integer idAlumno,
                           @NotNull Integer idGrupo,
                           @NotNull @Size(min = 11, max = 11) String idActividad){
        StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("TareaEntidad.agregarTarea");
        storedProcedureQuery.setParameter("grupo", idGrupo);
        storedProcedureQuery.setParameter("alumno", idAlumno);
        storedProcedureQuery.setParameter("actividad", idActividad);
        return storedProcedureQuery.execute();
    }

    /**
     * Busca todas las tareas.
     * @return todas las tareas encontradas.
     */
    public List<TareaModelo> busca(){
        TypedQuery<TareaEntidad> typedQuery = entityManager.createNamedQuery("TareaEntidad.busca", TareaEntidad.class);
        List<TareaEntidad> tareaEntidadLista = typedQuery.getResultList();
        return tareaEntidadLista.stream().map(TareaModelo::new).collect(Collectors.toList());
    }

    /**
     * Busca todas la tareas asignadas a un alumno.
     * @param idAlumno Identificador del alumno
     * @return Colección de {@link TareaModelo}
     */
    public List<TareaModelo> busca(@NotNull Integer idAlumno) {
        logger.fine(idAlumno.toString());
        TypedQuery<TareaEntidad> typedQuery = entityManager.createNamedQuery("TareaEntidad.buscaActividad", TareaEntidad.class);
        typedQuery.setParameter("IdAlumno", idAlumno);
        return typedQuery.getResultList().stream().map(TareaModelo::new).collect(Collectors.toList());
    }

    public List<TareaResuetasModelo> tareasResuelta(@NotNull Integer idTarea) {
        logger.fine(idTarea.toString());
        Query query = entityManager.createNativeQuery("SELECT * FROM alumno.busca_tarea(?)", TareasResueltasEntidad.class);
        query.setParameter(1, idTarea);
        return (List<TareaResuetasModelo>) query.getResultList().stream().map(o -> new TareaResuetasModelo((TareasResueltasEntidad)o)).collect(Collectors.toList());
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
