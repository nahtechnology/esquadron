package tecolotl.alumno.sesion;

import tecolotl.alumno.entidad.*;
import tecolotl.alumno.entidad.vista.TareaAlumnoVistaEntidad;
import tecolotl.alumno.entidad.vista.TareasGrupoVistaEntidad;
import tecolotl.alumno.entidad.vista.TareasResueltasEntidad;
import tecolotl.alumno.modelo.TareaActividadModelo;
import tecolotl.alumno.modelo.TareaAlumnoModelo;
import tecolotl.alumno.modelo.TareaModelo;
import tecolotl.alumno.modelo.vista.TareaResuetasModelo;
import tecolotl.alumno.modelo.vista.TareasGrupoVistaModelo;
import tecolotl.nucleo.herramienta.ValidadorSessionBean;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.*;
import javax.persistence.criteria.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
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
     * Busca los detalles de una tarea por su identificadoe
     * @param idTarea Identificador de la tarea a buscar
     * @return {@link TareaModelo} con los datos encontraods
     */
    public TareaModelo detalles(@NotNull UUID idTarea) {
        return new TareaModelo(entityManager.find(TareaEntidad.class, idTarea));
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
    public List<TareaModelo> busca(@NotNull UUID idAlumno) {
        logger.fine(idAlumno.toString());
        TypedQuery<TareaEntidad> typedQuery = entityManager.createNamedQuery("TareaEntidad.buscaActividad", TareaEntidad.class);
        typedQuery.setParameter("IdAlumno", idAlumno);
        return typedQuery.getResultList().stream().map(TareaModelo::new).collect(Collectors.toList());
    }

    /**
     * Busca las tareas de un alumno y por grupo
     * @param idAlumno Identificador del alumno
     * @param idGrupo Identificador del grupo
     * @return Colección de {@link TareaModelo}
     */
    public List<TareaModelo> busca(@NotNull UUID idAlumno, @NotNull UUID idGrupo) {
        TypedQuery<TareaEntidad> typedQuery = entityManager.createNamedQuery("TareaEntidad.buscaAlumnoGrupo", TareaEntidad.class);
        typedQuery.setParameter("idAlumno", idAlumno);
        typedQuery.setParameter("idGrupo", idGrupo);
        return typedQuery.getResultList().stream().map(TareaModelo::new).collect(Collectors.toList());
    }

    /**
     * Busca todos los tareas asignadas de un alumno, pero tambien agrega el id de la actividad.
     * @param idAlumno Identificador del alumno.
     * @param idGrupo Identificador del grupo.
     * @return Colección de {@link TareaActividadModelo}
     */
    public List<TareaActividadModelo> buscaActividad(@NotNull UUID idAlumno, @NotNull String idGrupo) {
        logger.fine(idAlumno.toString());
        TypedQuery<TareaAlumnoVistaEntidad> typedQuery =
                entityManager.createNamedQuery("TareaAlumnoVistaEntidad.buscaAlumno", TareaAlumnoVistaEntidad.class);
        typedQuery.setParameter("idAlumno",idAlumno);
        typedQuery.setParameter("idGrupo", idGrupo);
        return typedQuery.getResultList().stream().map(TareaActividadModelo::new).collect(Collectors.toList());
    }

    /**
     * Busca todas las tareas de, clasificadas por tarea asignada y con las totales ya realizadas.
     * @param idTarea Identificador de la tarea.
     * @return Colección de {@link TareaResuetasModelo}
     */
    public List<TareaResuetasModelo> tareasResuelta(@NotNull UUID idTarea) {
        logger.fine(idTarea.toString());
        Query query = entityManager.createNativeQuery("SELECT * FROM alumno.busca_tarea(?)", TareasResueltasEntidad.class);
        query.setParameter(1, idTarea);
        return (List<TareaResuetasModelo>) query.getResultList().stream().map(o -> new TareaResuetasModelo((TareasResueltasEntidad)o)).collect(Collectors.toList());
    }

    /**
     * Incrementa el número de reproducciones de una tarea. El valor puedes ser negativo.
     * @param incremento cantidad a incrementar las reproducciones.
     * @param idTarea identificador de la tarea.
     * @return Número de elementos modificados.
     */
    public Integer reproducciones(@NotNull Short incremento, @NotNull UUID idTarea) {
        Query query = entityManager.createNamedQuery("TareaEntidad.aumentaReprodecciones");
        query.setParameter("idTarea", idTarea).setParameter("reproducciones", incremento);
        return query.executeUpdate();
    }

    /**
     * Elimina una tarea por llave primaria
     * @param idTarea dato para eliminar una tarea
     * @return numero de elementos eliminados, 0 en caso de no existir
     */
    public int elimina(@NotNull UUID idTarea){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaDelete<TareaEntidad> tareaEntidadCriteriaDelete = criteriaBuilder.createCriteriaDelete(TareaEntidad.class);
        Root<TareaEntidad> root = tareaEntidadCriteriaDelete.from(TareaEntidad.class);
        tareaEntidadCriteriaDelete.where(criteriaBuilder.equal(root.get("id"),idTarea));
        return entityManager.createQuery(tareaEntidadCriteriaDelete).executeUpdate();
    }

    /**
     * Elimina todas la tareas perteneciente a un aactividad dentro de un grupo.
     * @param idActividad Identificador de la actvidad
     * @param idGrupo Identificadoe del grupo
     * @return Número de elementos elminados.
     */
    public int elimina(@NotNull String idActividad, @NotNull UUID idGrupo) {
        Query query = entityManager.createNamedQuery("TareaEntidad.eliminaGrupo");
        query.setParameter("idActividad", idActividad).setParameter("idGrupo", idGrupo);
        return query.executeUpdate();
    }

    /**
     * Establece las respuesta de la transcripcion.
     * @param respuesta Respuesta del usuario.
     * @param idTarea Identificador de la tarea.
     * @return Número de elementos modificados.
     */
    public Integer respuesta(@NotNull @Size(min = 100) String respuesta, @NotNull UUID idTarea, @NotNull Short calificacion) {
        Query query = entityManager.createNamedQuery("TareaEntidad.respuesta");
        query.setParameter("idTarea", idTarea);
        query.setParameter("respuesta", respuesta);
        query.setParameter("calificacion", calificacion);
        return query.executeUpdate();
    }

    /**
     * Cambia el estatus del la tarea
     * @param idTarea Identificador de la tarea
     * @param activo estus de la tarea, true para saber si aun sigue respondiendo, false en caso contrario.
     * @return Número de elementos modificados.
     */
    public Integer estatus(@NotNull UUID idTarea, boolean activo) {
        Query query = entityManager.createNamedQuery("TareaEntidad.estatusRespondiendo");
        query.setParameter("idTarea", idTarea).setParameter("estatus", activo);
        return query.executeUpdate();
    }

    /**
     * Busca las tareas con calificaciones de un alumno
     * @param idAlumno id del alumno
     * @return lista de tareas del alumno con calificaciones
     */
    public List<TareaAlumnoModelo> buscaCalificaciones(@NotNull UUID idAlumno) {
        Query query = entityManager.createNativeQuery("SELECT * FROM alumno.tareas_alumno(?)", TareaAlumnoEntidad.class);
        query.setParameter(1, idAlumno);
        return ((List<TareaAlumnoEntidad>)query.getResultList()).stream().map(TareaAlumnoModelo::new).collect(Collectors.toList());
    }

    /**
     * Busca todas las activiaades de asignadas de un grupo
     * @param idGrupo Identificador del grupo a buscar
     * @return Colección de Objectos con los datos
     */
    public List<TareasGrupoVistaModelo> buscaPorGrupo(@NotNull UUID idGrupo) {
        List<TareasGrupoVistaModelo> tareasGrupoVistaModeloLista = new ArrayList<>();
        Query query = entityManager.createNamedQuery("TareasGrupoVistaEntidad.busca");
        query.setParameter("idGrupo", idGrupo);
        List<Object[]> lista = query.getResultList();
        lista.forEach(objects -> {
            TareasGrupoVistaModelo tareasGrupoVistaModelo = new TareasGrupoVistaModelo();
            tareasGrupoVistaModelo.setIdVideo((String)objects[0]);
            tareasGrupoVistaModelo.setPreguntaDetonadora((String)objects[1]);
            tareasGrupoVistaModelo.setTotalTareas(((Long)objects[2]).intValue());
            tareasGrupoVistaModeloLista.add(tareasGrupoVistaModelo);
        });
        return tareasGrupoVistaModeloLista;
    }

}
