package tecolotl.alumno.sesion;

import tecolotl.alumno.entidad.ActividadEntidad;
import tecolotl.alumno.entidad.TareaEntidad;
import tecolotl.alumno.entidad.mapamental.*;
import tecolotl.alumno.modelo.mapamental.MapaMentalModelo;
import tecolotl.alumno.modelo.mapamental.TareaMapaMentalModelo;
import tecolotl.alumno.validacion.mapamental.EscribirLlavePrimariaValidacion;
import tecolotl.alumno.validacion.mapamental.EscribirRespuestaValidacion;
import tecolotl.alumno.validacion.mapamental.MapaMentalLlavePrimariaValidacion;
import tecolotl.nucleo.herramienta.ValidadorSessionBean;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
     * @return coleción de {@link MapaMentalModelo}
     */
    public List<MapaMentalModelo> busca(@NotNull @Size(min = 11, max = 11) String idActividad) {
        logger.fine("Buscando por:".concat(idActividad));
        TypedQuery<MapaMentalActividadEntidad> typedQuery = entityManager.createNamedQuery("MapaMentalActividadEntidad.buscaIdActivdad", MapaMentalActividadEntidad.class);
        typedQuery.setParameter("idActividad", idActividad);
        List<MapaMentalActividadEntidad> mapaMentalActividadEntidadLista = typedQuery.getResultList();
        logger.finer("Escrbir total localizados:".concat(String.valueOf(mapaMentalActividadEntidadLista.size())));
        return mapaMentalActividadEntidadLista.stream().map(MapaMentalModelo::new).collect(Collectors.toList());
    }


    /**
     * Busca todas las preguntas asignadas a una tarea
     * @param idTarea Identificador de la tarea
     * @return Coleción de {@link MapaMentalModelo}
     *
     */
    public List<MapaMentalModelo> busca(@NotNull Integer idTarea) {
        logger.finer(idTarea.toString());
        TypedQuery<TareaMapaMentalActividadEntidad> typedQuery = entityManager.createNamedQuery("TareaMapaMentalActividadEntidad.buscaidTarea", TareaMapaMentalActividadEntidad.class);
        typedQuery.setParameter("idTarea", idTarea);
        List<TareaMapaMentalActividadEntidad> tareaMapaMentalActividadEntidadLista = typedQuery.getResultList();
        logger.finer("Tarea escribir encontrados:"+ tareaMapaMentalActividadEntidadLista.size());
        return tareaMapaMentalActividadEntidadLista.stream().map(TareaMapaMentalModelo::new).collect(Collectors.toList());
    }

    /**
     * Escribe la respuesta a una pregunta
     * @param tareaMapaMentalModelo Modelo con los datos: id pregunta y la respuesta
     * @param idTarea Identificador de la tarea
     * @param idActividad Identificador de la actvidad
     */
    public void respuesta(@NotNull TareaMapaMentalModelo tareaMapaMentalModelo,
                         @NotNull Integer idTarea,
                         @NotNull @Size(min = 11, max = 11) String idActividad) {
        logger.fine(tareaMapaMentalModelo.toString()); logger.fine(idTarea.toString()); logger.fine(idActividad);
        validadorSessionBean.validacion(tareaMapaMentalModelo, MapaMentalLlavePrimariaValidacion.class);
        TareaMapaMentalActividadEntidad tareaMapaMentalActividadEntidad =
                entityManager.find(TareaMapaMentalActividadEntidad.class, llavePrimaria(tareaMapaMentalModelo, idTarea, idActividad));
        tareaMapaMentalActividadEntidad.setTextoRespuesta(tareaMapaMentalModelo.getRespuesta());
    }

    private TareaMapaMentalActividadEntidadPK llavePrimaria(TareaMapaMentalModelo tareaMapaMentalModelo, Integer idTarea, String idActividad) {
        TareaMapaMentalActividadEntidadPK tareaMapaMentalActividadEntidadPK = new TareaMapaMentalActividadEntidadPK();
        tareaMapaMentalActividadEntidadPK.setTareaEntidad(new TareaEntidad(idTarea));
        MapaMentalEntidadPK mapaMentalEntidadPK = new MapaMentalEntidadPK();
        mapaMentalEntidadPK.setCodigo(tareaMapaMentalModelo.getCodigo());
        mapaMentalEntidadPK.setCardinalidad(tareaMapaMentalModelo.getCardinalidad());
        tareaMapaMentalActividadEntidadPK.setMapaMentalActividadEntidad(
                new MapaMentalActividadEntidad(
                        new MapaMentalActividadEntidadPK(new MapaMentalEntidad(mapaMentalEntidadPK), new ActividadEntidad(idActividad))
                )
        );
        return tareaMapaMentalActividadEntidadPK;
    }
/*
    /**
     * Agrega una oregunta de un mapa mental a una actividad
     * @param pregunta Pregunta detonadora
     * @param idActividad Identificador de la actividad
     * @return Identificador del mapa mental generado
     *
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
     *
    public void agregar(@NotNull @Size(min = 11, max = 11) String idActividad, @NotNull Integer idMapaMental) {
        logger.fine(idActividad);
        logger.fine(idMapaMental.toString());
        MapaMentalActividadEntidad mapaMentalActividadEntidad =
                new MapaMentalActividadEntidad(new MapaMentalEntidad(idMapaMental), new ActividadEntidad(idActividad));
        entityManager.persist(mapaMentalActividadEntidad);
    }*/
}
