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

@Stateless
public class GlosarioSesionBean {

    @Inject
    private Logger logger;

    @PersistenceContext
    private EntityManager entityManager;

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
