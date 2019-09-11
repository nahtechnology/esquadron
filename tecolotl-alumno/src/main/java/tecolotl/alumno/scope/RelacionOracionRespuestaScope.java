package tecolotl.alumno.scope;

import tecolotl.alumno.modelo.relacionar_oraciones.TareaRelacionarOracionModelo;

import javax.ejb.TransactionAttribute;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.logging.Logger;

@RequestScoped
public class RelacionOracionRespuestaScope {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private Logger logger;

    @TransactionAttribute
    public void respuesta(@NotNull @Size(min = 1) List<TareaRelacionarOracionModelo> tareaRelacionarOracionModeloLista) {

    }
}
