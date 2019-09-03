package tecolotl.alumno.sesion;

import tecolotl.alumno.entidad.ActividadEntidad;
import tecolotl.alumno.entidad.glosario.TareaGlosarioActividadEntidad;
import tecolotl.alumno.entidad.gramatica.GramaticaEntidad;
import tecolotl.alumno.entidad.gramatica.GramaticaEntidadPK;
import tecolotl.alumno.entidad.gramatica.TareaGramaticaEntidad;
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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Stateless
public class GramaticaSesionBean {
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
     * @param id_actividad ID_Actividad a buscar relacionada con Gramatica
     * @return una coleccion de {@link GramaticaModelo}
     */
    public List<GramaticaModelo> busca(@NotNull @Size(min = 11, max = 11)String id_actividad){
        TypedQuery<GramaticaEntidad> typedQuery = entityManager.createNamedQuery("GramaticaEntidad.buscaID", GramaticaEntidad.class);
        typedQuery.setParameter("idActividad",id_actividad);
        List<GramaticaEntidad> gramaticaEntidadLista = typedQuery.getResultList();
        return gramaticaEntidadLista.stream().map(GramaticaModelo::new).collect(Collectors.toList());
    }

    /**
     * Busca todas las tareas de gramatica que tengan el mismo ID_Actividad
     * @param id_actividad ID_Actividad a buscar en TareaGramatica
     * @return una colección de {@link GramaticaModelo}
     */
    public List<GramaticaModelo> buscaTarea(@NotNull @Size(min = 11, max = 11)String id_actividad){
        TypedQuery<TareaGramaticaEntidad> typedQuery = entityManager.createNamedQuery("GramaticaEntidad.buscaTareas", TareaGramaticaEntidad.class);
        typedQuery.setParameter("idActividad", id_actividad);
        List<TareaGramaticaEntidad> tareaGramaticaEntidadLista = typedQuery.getResultList();
        return tareaGramaticaEntidadLista.stream().map(tareaGramaticaEntidad -> new GramaticaModelo(tareaGramaticaEntidad.getTareaGramaticaEntidadPK().getGramaticaEntidad())).collect(Collectors.toList());
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

}
