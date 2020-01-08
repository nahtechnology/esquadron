package tecolotl.alumno.sesion;

import tecolotl.alumno.entidad.hablar.TareaHablarEntidad;
import tecolotl.alumno.modelo.hablar.HablarModelo;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Stateless
public class HablarSesionBean {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private Logger logger;

    public List<HablarModelo> busca(@NotNull UUID idTarea) {
        logger.fine(idTarea.toString());
        TypedQuery<TareaHablarEntidad> typedQuery = entityManager.createNamedQuery("TareaHablarEntidad.buscaIdTarea", TareaHablarEntidad.class);
        typedQuery.setParameter("idTarea", idTarea);
        return typedQuery.getResultList().stream().map(HablarModelo::new).collect(Collectors.toList());
    }

}
