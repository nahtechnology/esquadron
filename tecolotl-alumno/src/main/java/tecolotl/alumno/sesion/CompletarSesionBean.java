package tecolotl.alumno.sesion;

import tecolotl.alumno.entidad.completar.TareaCompletarEntidad;
import tecolotl.alumno.modelo.completar.TareaCompletarModelo;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Ejercicio para las cuales son oraciones a las cuales se les remueve una palabra para ser respondido.
 */
@Stateless
public class CompletarSesionBean {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private Logger logger;

    /**
     * Busca tadas las tareas asignadad a una tarea
     * @param idTarea Identificaodr de la tarea
     * @return Colección de {@link TareaCompletarModelo}
     */
    public List<TareaCompletarModelo> busca(@NotNull UUID idTarea) {
        logger.fine(idTarea.toString());
        TypedQuery<TareaCompletarEntidad> typedQuery = entityManager.createNamedQuery("TareaCompletarEntidad.buscaidTarea", TareaCompletarEntidad.class);
        typedQuery.setParameter("idTarea", idTarea);
        return typedQuery.getResultList().stream().map(TareaCompletarModelo::new).collect(Collectors.toList());
    }


    public int respuesta(@NotNull TareaCompletarModelo tareaCompletarModelo, @NotNull UUID idTarea) {
        logger.fine(tareaCompletarModelo.toString());
        Query query = entityManager.createNamedQuery("TareaCompletarEntidad.respuesta");
        query.setParameter("idTarea", idTarea);
        query.setParameter("idCompletar", tareaCompletarModelo.getId());
        query.setParameter("vuelta", tareaCompletarModelo.getVuelta());
        query.setParameter("respuesta", tareaCompletarModelo.getRespuesta());
        return query.executeUpdate();
    }
}
