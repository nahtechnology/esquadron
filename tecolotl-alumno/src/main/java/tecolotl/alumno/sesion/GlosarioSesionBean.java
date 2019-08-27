package tecolotl.alumno.sesion;

import tecolotl.alumno.entidad.ActividadEntidad;
import tecolotl.alumno.entidad.glosario.*;
import tecolotl.alumno.modelo.glosario.GlosarioModelo;
import tecolotl.alumno.validacion.glosario.GlosarioLlavePrimariaValidacion;
import tecolotl.nucleo.herramienta.ValidadorSessionBean;
import tecolotl.nucleo.validacion.CatalogoLlavePrimariaValidacion;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * El glosario es un conjunto de herramientas asignadas a una actividad.
 * El glosario está compuesto por una palabra, imagen y un significado.
 * @author Antonio Francisco Alonso Valerdi
 */
@Stateless
public class GlosarioSesionBean {

    @Inject
    private Logger logger;

    @Inject
    private ValidadorSessionBean validadorSessionBean;

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Busca todos los glosarios relacionados con una actividad.
     * @param idActividad Identidicador de la actividad.
     * @return Colección de {@link GlosarioModelo}
     */
    public List<GlosarioModelo> busca(@NotNull @Size(min = 11, max = 11) String idActividad) {
        logger.fine(idActividad);
        TypedQuery<GlosarioActividadEntidad> typedQuery = entityManager.createNamedQuery("GlosarioActividadEntidad.buscaActividad", GlosarioActividadEntidad.class);
        typedQuery.setParameter("idActivdad", idActividad);
        List<GlosarioActividadEntidad> glosarioActividadEntidadLista = typedQuery.getResultList();
        logger.finer("Glosario encontrado:" + glosarioActividadEntidadLista.size());
        return glosarioActividadEntidadLista.stream().map(
                glosarioActividadEntidad -> new GlosarioModelo(glosarioActividadEntidad.getGlosarioActividadEntidadPK().getGlosarioEntidad())
        ).collect(Collectors.toList());
    }

    /**
     * Busca todos los glosarios asignados a una tarea la cual corresponde a un alumno.
     * @param idTarea Identificador de la tarea
     * @return Colección de {@link GlosarioModelo}
     */
    public List<GlosarioModelo> busca(@NotNull Integer idTarea) {
        logger.fine(idTarea.toString());
        TypedQuery<TareaGlosarioActividadEntidad> typedQuery = entityManager.createNamedQuery("TareaGlosarioActividadEntidad.buscaTarea", TareaGlosarioActividadEntidad.class);
        typedQuery.setParameter("idTarea", idTarea);
        List<TareaGlosarioActividadEntidad> tareaGlosarioActividadEntidadLista = typedQuery.getResultList();
        logger.finer("Glosario encontrados:"+tareaGlosarioActividadEntidadLista.size());
        return tareaGlosarioActividadEntidadLista.stream().map(tareaGlosarioActividadEntidad ->
            new GlosarioModelo(tareaGlosarioActividadEntidad.getTareaGlosarioActividadEntidadPK().getGlosarioActividadEntidad().getGlosarioActividadEntidadPK().getGlosarioEntidad())
        ).collect(Collectors.toList());
    }

    /**
     * Agreaga un nuevo glosario con su relacion en cascada de la actividad que pertenece.
     * @param glosarioModelo Datos del glosario a insertar.
     * @param idActividad Activdad a la que pertenece.
     */
    public void agregar(@NotNull GlosarioModelo glosarioModelo, @NotNull @Size(min = 11, max = 11) String idActividad) {
        logger.fine(glosarioModelo.toString());
        GlosarioEntidad glosarioEntidad = new GlosarioEntidad(llavePrimaria(glosarioModelo));
        glosarioEntidad.setImagen(glosarioModelo.getImagen());
        glosarioEntidad.setSignificado(glosarioModelo.getSignificado());
        GlosarioActividadEntidad glosarioActividadEntidad = new GlosarioActividadEntidad(llavePrimaria(glosarioEntidad, idActividad));
        glosarioEntidad.setGlosarioActividadEntidadLista(Arrays.asList(glosarioActividadEntidad));
        if (glosarioEntidad.getSignificado() == null || glosarioEntidad.getSignificado().isEmpty()) {
            entityManager.persist(glosarioEntidad);
        } else {
            entityManager.persist(glosarioActividadEntidad);
        }
    }


    protected GlosarioEntidadPK llavePrimaria(GlosarioModelo glosarioModelo) {
        GlosarioEntidadPK glosarioEntidadPK = new GlosarioEntidadPK();
        glosarioEntidadPK.setPalabra(glosarioModelo.getPalabra());
        glosarioEntidadPK.setClaseGlosarioEntidad(new ClaseGlosarioEntidad(glosarioModelo.getClaseGlosarioModelo().getClave()));
        return glosarioEntidadPK;
    }

    protected GlosarioActividadEntidadPK llavePrimaria(GlosarioEntidad glosarioEntidad, String idActividad) {
        GlosarioActividadEntidadPK glosarioActividadEntidadPK = new GlosarioActividadEntidadPK();
        glosarioActividadEntidadPK.setActividadEntidad(new ActividadEntidad(idActividad));
        glosarioActividadEntidadPK.setGlosarioEntidad(glosarioEntidad);
        return glosarioActividadEntidadPK;
    }

}
