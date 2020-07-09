package tecolotl.alumno.sesion;

import tecolotl.alumno.entidad.ActividadEntidad;
import tecolotl.alumno.entidad.TareaEntidad;
import tecolotl.alumno.entidad.glosario.TareaGlosarioActividadEntidad;
import tecolotl.alumno.entidad.gramatica.GramaticaEntidad;
import tecolotl.alumno.entidad.gramatica.GramaticaEntidadPK;
import tecolotl.alumno.entidad.gramatica.TareaGramaticaEntidad;
import tecolotl.alumno.entidad.gramatica.TareaGramaticaEntidadPK;
import tecolotl.alumno.entidad.vista.CalificacionTareaGramaticaVistaEntidad;
import tecolotl.alumno.modelo.ActividadModelo;
import tecolotl.alumno.modelo.glosario.GlosarioModelo;
import tecolotl.alumno.modelo.gramatica.GramaticaModelo;
import tecolotl.nucleo.herramienta.ValidadorSessionBean;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Stateless
public class GramaticaSesionBean implements Serializable {

    @Inject
    private Logger logger;

    @Inject
    private ValidadorSessionBean validadorSessionBean;

    @PersistenceContext
    private EntityManager entityManager;


    /**
     * Busca todas las tarea de gramatica
     * @return coleccion de {@link GlosarioModelo}
     */
    public List<GramaticaModelo> busca(){
        TypedQuery<GramaticaEntidad> typedQuery = entityManager.createNamedQuery("GramaticaEntidad.busca",GramaticaEntidad.class);
        return typedQuery.getResultList().stream().map(GramaticaModelo::new).collect(Collectors.toList());
    }

    /**
     * Busca todas las actividades de gramatica
     * @param idActividad ID_Actividad a buscar relacionada con Gramatica
     * @return una coleccion de {@link GramaticaModelo}
     */
    public List<GramaticaModelo> busca(@NotNull @Size(min = 11, max = 11) String idActividad){
        TypedQuery<GramaticaEntidad> typedQuery = entityManager.createNamedQuery("GramaticaEntidad.buscaActvidad", GramaticaEntidad.class);
        typedQuery.setParameter("idActividad", idActividad);
        List<GramaticaEntidad> gramaticaEntidadLista = typedQuery.getResultList();
        return gramaticaEntidadLista.stream().map(GramaticaModelo::new).collect(Collectors.toList());
    }

    /**
     * Busca todas las tareas de gramatica que tengan el mismo ID_Actividad
     * @param idTarea ID_Actividad a buscar en TareaGramatica
     * @return una colección de {@link GramaticaModelo}
     */
    public List<GramaticaModelo> busca(@NotNull UUID idTarea){
        TypedQuery<CalificacionTareaGramaticaVistaEntidad> typedQuery =
                entityManager.createNamedQuery("CalificacionTareaGramaticaVistaEntidad.busca", CalificacionTareaGramaticaVistaEntidad.class);
        typedQuery.setParameter("idTarea", idTarea);
        return typedQuery.getResultList().stream().map(GramaticaModelo::new).collect(Collectors.toList());
    }

    /**
     * Aplica la respuesta de una gramatica
     * @param gramaticaModelo Datos de gramatica
     * @param idTarea Identidificador de la tarea
     */
    public void respuesta(@NotNull GramaticaModelo gramaticaModelo, @NotNull UUID idTarea) {
        TareaGramaticaEntidad tareaGramaticaEntidad = entityManager.find(TareaGramaticaEntidad.class, llavePrimaria(gramaticaModelo, idTarea));
        tareaGramaticaEntidad.setRespuesta(gramaticaModelo.getRespuesta());
    }

    /**
     * Inserta una nueva actividad del tipo {@link GramaticaEntidad}
     * @param gramaticaModelo el modelo de datos para agregar la nueva actividad.
     */
    public void inserta(@NotNull GramaticaModelo gramaticaModelo){
        GramaticaEntidadPK gramaticaEntidadPK = new GramaticaEntidadPK();
        gramaticaEntidadPK.setActividadEntidad(new ActividadEntidad(gramaticaModelo.getActividadModelo().getIdVideo()));
        gramaticaEntidadPK.setCodigo(gramaticaModelo.getCodigo());
        GramaticaEntidad gramaticaEntidad = new GramaticaEntidad(gramaticaEntidadPK);
        gramaticaEntidad.setPalabra(gramaticaModelo.getPalabra());
        entityManager.persist(gramaticaEntidad);
    }

    private TareaGramaticaEntidadPK llavePrimaria(GramaticaModelo gramaticaModelo, UUID idTarea) {
        TareaGramaticaEntidadPK tareaGramaticaEntidadPK = new TareaGramaticaEntidadPK();
        tareaGramaticaEntidadPK.setTareaEntidad(new TareaEntidad(idTarea));
        GramaticaEntidadPK gramaticaEntidadPK = new GramaticaEntidadPK();
        gramaticaEntidadPK.setCodigo(gramaticaModelo.getCodigo());
        gramaticaEntidadPK.setActividadEntidad(new ActividadEntidad(gramaticaModelo.getActividadModelo().getIdVideo()));
        tareaGramaticaEntidadPK.setVuelta(gramaticaModelo.getVuelta());
        tareaGramaticaEntidadPK.setGramaticaEntidad(new GramaticaEntidad(gramaticaEntidadPK));
        return tareaGramaticaEntidadPK;
    }
}
