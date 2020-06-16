package tecolotl.alumno.sesion;

import tecolotl.alumno.entidad.ActividadEntidad;
import tecolotl.alumno.entidad.TareaEntidad;
import tecolotl.alumno.entidad.mapamental.*;
import tecolotl.alumno.entidad.vista.CalificacionTareaMapamentalEntidadVista;
import tecolotl.alumno.modelo.mapamental.MapaMentalModelo;
import tecolotl.alumno.modelo.mapamental.MapaMentalResueltoModelo;
import tecolotl.alumno.modelo.mapamental.TareaMapaMentalModelo;
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
import java.util.UUID;
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
        TypedQuery<MapaMentalActividadEntidad> typedQuery =
                entityManager.createNamedQuery("MapaMentalActividadEntidad.buscaIdActivdad", MapaMentalActividadEntidad.class);
        typedQuery.setParameter("idActividad", idActividad);
        List<MapaMentalActividadEntidad> mapaMentalActividadEntidadLista = typedQuery.getResultList();
        logger.finer("Escrbir total localizados:".concat(String.valueOf(mapaMentalActividadEntidadLista.size())));
        return mapaMentalActividadEntidadLista.stream().map(MapaMentalModelo::new).collect(Collectors.toList());
    }


    /**
     * Busca todas las preguntas asignadas a una tarea
     * @param idTarea Identificador de la tarea
     * @return Coleción de {@link TareaMapaMentalModelo}
     *
     */
    public List<TareaMapaMentalModelo> busca(@NotNull UUID idTarea) {
        logger.finer(idTarea.toString());
        TypedQuery<TareaMapaMentalActividadEntidad> typedQuery =
                entityManager.createNamedQuery("TareaMapaMentalActividadEntidad.buscaidTarea", TareaMapaMentalActividadEntidad.class);
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
                          @NotNull UUID idTarea,
                          @NotNull @Size(min = 11, max = 11) String idActividad) {
        logger.fine(tareaMapaMentalModelo.toString()); logger.fine(idTarea.toString()); logger.fine(idActividad);
        validadorSessionBean.validacion(tareaMapaMentalModelo, MapaMentalLlavePrimariaValidacion.class);
        TareaMapaMentalActividadEntidad tareaMapaMentalActividadEntidad =
                entityManager.find(TareaMapaMentalActividadEntidad.class, llavePrimaria(tareaMapaMentalModelo, idTarea, idActividad));
        logger.info("Este es el objeto: ");
        logger.info(tareaMapaMentalActividadEntidad.toString());
        tareaMapaMentalActividadEntidad.setTextoRespuesta(tareaMapaMentalModelo.getRespuesta());
    }

    /**
     * Busca los mapa mentales de una tarea y el total de ejercicios resueltos.
     * @param idTarea Identificador de las tareas.
     * @return {@link MapaMentalResueltoModelo} con los datos.
     */
    public List<MapaMentalResueltoModelo> buscaResuelto(@NotNull UUID idTarea) {
        logger.fine(idTarea.toString());
        TypedQuery<CalificacionTareaMapamentalEntidadVista> typedQuery =
                entityManager.createNamedQuery("CalificacionTareaMapamentalEntidadVista.busca", CalificacionTareaMapamentalEntidadVista.class);
        typedQuery.setParameter("idTarea", idTarea);
        return typedQuery.getResultList().stream().map(MapaMentalResueltoModelo::new).collect(Collectors.toList());
    }

    /**
     * Busca las preguntas y respuesta de un mapa mental.
     * @param idTarea Tarea a la que pertenece mapa mental.
     * @param cardinalidad cardinalidad del mapa mental.
     * @return Colección de {@link TareaMapaMentalModelo}
     */
    public List<TareaMapaMentalModelo> busca(@NotNull UUID idTarea, @NotNull Short cardinalidad) {
        logger.fine(idTarea.toString());
        logger.fine(cardinalidad.toString());
        TypedQuery<TareaMapaMentalActividadEntidad> typedQuery = entityManager.createNamedQuery("TareaMapaMentalActividadEntidad.buscaMapaMental", TareaMapaMentalActividadEntidad.class);
        typedQuery.setParameter("idTarea", idTarea).setParameter("cardinalidad", cardinalidad);
        return typedQuery.getResultList().stream().map(TareaMapaMentalModelo::new).collect(Collectors.toList());
    }

    //TODO el parámetro "vuelta" no puede ser null o arrojara un NullPointerException
    private TareaMapaMentalActividadEntidadPK llavePrimaria(TareaMapaMentalModelo tareaMapaMentalModelo, UUID idTarea, String idActividad) {
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
        tareaMapaMentalActividadEntidadPK.setVuelta(tareaMapaMentalModelo.getVuelta());
        return tareaMapaMentalActividadEntidadPK;
    }

}
