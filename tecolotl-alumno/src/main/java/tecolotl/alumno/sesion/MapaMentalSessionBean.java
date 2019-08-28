package tecolotl.alumno.sesion;

import tecolotl.alumno.entidad.ActividadEntidad;
import tecolotl.alumno.entidad.mapamental.MapaMentalActividadEntidad;
import tecolotl.alumno.entidad.mapamental.MapaMentalEntidad;
import tecolotl.alumno.entidad.mapamental.TareaEscribirActividadEntidad;
import tecolotl.alumno.modelo.mapamental.MapaMentalBaseModelo;
import tecolotl.alumno.modelo.mapamental.MapaMentalModelo;
import tecolotl.alumno.validacion.escribir.EscribirLlavePrimariaValidacion;
import tecolotl.alumno.validacion.escribir.EscribirRespuestaValidacion;
import tecolotl.nucleo.herramienta.ValidadorSessionBean;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Escribir son preguntas abiertas las cuales deben tener una respuesta por alumno. Están asignadas por actividad y por tarea
 * @author Antonio Francisco Alonso Valerdi
 * @since 0.1
 */
@Stateless
public class MapaMentalSessionBean {

    @Inject
    private Logger logger;

    @Inject
    private ValidadorSessionBean validadorSessionBean;

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Busca todos las preguntas por actividad
     * @param idActividad Identificador de la actividad
     * @return coleción de {@link MapaMentalBaseModelo}
     */
    public List<MapaMentalBaseModelo> busca(@NotNull @Size(min = 11, max = 11) String idActividad) {
        logger.fine("Buscando por:".concat(idActividad));
        TypedQuery<MapaMentalActividadEntidad> typedQuery = entityManager.createNamedQuery("EscribirActividadEntidad.buscaActivdad", MapaMentalActividadEntidad.class);
        typedQuery.setParameter("idActividad", idActividad);
        List<MapaMentalActividadEntidad> mapaMentalActividadEntidadLista = typedQuery.getResultList();
        logger.finer("Escrbir total localizados:".concat(String.valueOf(mapaMentalActividadEntidadLista.size())));
        return mapaMentalActividadEntidadLista.stream().map(ea -> new MapaMentalBaseModelo(ea.getMapaMentalEntidad())).collect(Collectors.toList());
    }

    /**
     * Busca mapa mental por palabra reservada y que no este en dicha actividad.
     * @param idActividad Actividad a ser ignorada.
     * @param pregunta Palabra a buscar en al pregunta.
     * @return coleción de {@link MapaMentalBaseModelo}
     */
    public List<MapaMentalBaseModelo> busca(@NotNull @Size(min = 11, max = 11) String idActividad,
                                            @NotNull @Size(max = 30) String pregunta) {
        logger.fine(idActividad);
        logger.fine(pregunta);
        TypedQuery<MapaMentalActividadEntidad> typedQuery = entityManager.createNamedQuery("MapaMentalActividadEntidad.buscaPreguntaNoActividad", MapaMentalActividadEntidad.class);
        typedQuery.setParameter("idActividad", idActividad);
        typedQuery.setParameter("pregunta","%".concat(pregunta).concat("%"));
        List<MapaMentalActividadEntidad> mapaMentalActividadEntidadLista = typedQuery.getResultList();
        return mapaMentalActividadEntidadLista.stream().map(mapaMentalActividadEntidad -> new MapaMentalBaseModelo(mapaMentalActividadEntidad.getMapaMentalEntidad())).collect(Collectors.toList());
    }

    /**
     * Busca todas las preguntas asignadas a una tarea
     * @param idTarea Identificador de la tarea
     * @return Coleción de {@link MapaMentalModelo}
     */
    public List<MapaMentalModelo> busca(@NotNull Integer idTarea) {
        logger.finer(idTarea.toString());
        TypedQuery<TareaEscribirActividadEntidad> typedQuery = entityManager.createNamedQuery("TareaEscribirActividadEntidad.buscaTarea", TareaEscribirActividadEntidad.class);
        typedQuery.setParameter("idTarea", idTarea);
        List<TareaEscribirActividadEntidad> tareaEscribirActividadEntidadLista = typedQuery.getResultList();
        logger.finer("Tarea escribir encontrados:"+tareaEscribirActividadEntidadLista.size());
        List<MapaMentalModelo> escribirModeloLista = new ArrayList<>();
        for (TareaEscribirActividadEntidad tareaEscribirActividadEntidad : tareaEscribirActividadEntidadLista) {
            logger.finer(tareaEscribirActividadEntidad.toString());
            escribirModeloLista.add(new MapaMentalModelo(tareaEscribirActividadEntidad));
        }
        return escribirModeloLista;
    }

    /**
     * Escribe la respuesta a una pregunta
     * @param escribirModelo Modelo con los datos: id pregunta y la respuesta
     * @param idTarea Identificador de la tarea
     * @param idActividad Identificador de la actvidad
     */
    public void respuesta(@NotNull MapaMentalModelo escribirModelo,
                         @NotNull Integer idTarea,
                         @NotNull @Size(min = 11, max = 11) String idActividad) {
        logger.fine(escribirModelo.toString()); logger.fine(idTarea.toString()); logger.fine(idActividad);
        validadorSessionBean.validacion(escribirModelo, EscribirLlavePrimariaValidacion.class, EscribirRespuestaValidacion.class);
        TareaEscribirActividadEntidad tareaEscribirActividadEntidad = entityManager.createNamedQuery("TareaEscribirActividadEntidad.busca", TareaEscribirActividadEntidad.class)
                .setParameter("idTarea", idTarea).setParameter("idActividad", idActividad).setParameter("idEscribir", escribirModelo.getId())
                .getSingleResult();
        tareaEscribirActividadEntidad.setTextRespuesta(escribirModelo.getTextoRespuesta());
    }

    /**
     * Agrega una oregunta de un mapa mental a una actividad
     * @param pregunta Pregunta detonadora
     * @param idActividad Identificador de la actividad
     * @return Identificador del mapa mental generado
     */
    public Integer agregar(@NotNull @Size(max = 100, min = 2) String pregunta, @NotNull @Size(min = 11, max = 11) String idActividad) {
        logger.fine(pregunta);
        logger.fine(idActividad);
        MapaMentalEntidad mapaMentalEntidad = new MapaMentalEntidad();
        mapaMentalEntidad.setPregunta(pregunta);
        MapaMentalActividadEntidad mapaMentalActividadEntidad =
                new MapaMentalActividadEntidad(mapaMentalEntidad, new ActividadEntidad(idActividad));
        mapaMentalEntidad.setMapaMentalActividadEntidadLista(Arrays.asList(mapaMentalActividadEntidad));
        entityManager.persist(mapaMentalEntidad);
        return mapaMentalEntidad.getId();
    }

    /**
     * Asinga una pregunta detonadora a una actividad.
     * @param idActividad Identificador de la actividad.
     * @param idMapaMental Identificador del mapa mental.
     */
    public void agregar(@NotNull @Size(min = 11, max = 11) String idActividad, @NotNull Integer idMapaMental) {
        logger.fine(idActividad);
        logger.fine(idMapaMental.toString());
        MapaMentalActividadEntidad mapaMentalActividadEntidad =
                new MapaMentalActividadEntidad(new MapaMentalEntidad(idMapaMental), new ActividadEntidad(idActividad));
        entityManager.persist(mapaMentalActividadEntidad);
    }
}
