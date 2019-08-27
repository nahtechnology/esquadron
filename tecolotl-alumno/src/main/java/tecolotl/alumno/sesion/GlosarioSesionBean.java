package tecolotl.alumno.sesion;

import tecolotl.alumno.entidad.glosario.GlosarioActividadEntidad;
import tecolotl.alumno.entidad.glosario.TareaGlosarioActividadEntidad;
import tecolotl.alumno.modelo.glosario.GlosarioModelo;
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
 * El glosario es un conjunto de herramientas asignadas a una actividad.
 * El glosario está compuesto por una palabra, imagen y un significado.
 * @author Antonio Francisco Alonso Valerdi
 */
@Stateless
public class GlosarioSesionBean {

    @Inject
    private Logger logger;

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

}
