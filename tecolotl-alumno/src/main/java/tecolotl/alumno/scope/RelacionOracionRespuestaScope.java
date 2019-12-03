package tecolotl.alumno.scope;

import tecolotl.alumno.entidad.TareaEntidad;
import tecolotl.alumno.entidad.relacionar_oraciones.RelacionarOracionesEntidad;
import tecolotl.alumno.entidad.relacionar_oraciones.TareaRelacionarOracionesEntidad;
import tecolotl.alumno.entidad.relacionar_oraciones.TareaRelacionarOracionesEntidadPK;
import tecolotl.alumno.modelo.relacionar_oraciones.TareaRelacionarOracionModelo;
import tecolotl.alumno.validacion.relacionar_oraciones.RelacionarOracionLlavePrimariaValidacion;
import tecolotl.alumno.validacion.relacionar_oraciones.TareaRelacionarOracionRespuestaValidacion;
import tecolotl.nucleo.herramienta.ValidadorSessionBean;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequestScoped
public class RelacionOracionRespuestaScope {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private Logger logger;

    @Inject
    private ValidadorSessionBean validadorSessionBean;

    @Resource
    private UserTransaction userTransaction;

    public void respuesta(@NotNull @Size(min = 1) List<TareaRelacionarOracionModelo> tareaRelacionarOracionModeloLista) {
        logger.fine(String.valueOf(tareaRelacionarOracionModeloLista.size()));
        tareaRelacionarOracionModeloLista.forEach(tareaRelacionarOracionModelo -> {
            validadorSessionBean.validacion(tareaRelacionarOracionModelo, RelacionarOracionLlavePrimariaValidacion.class, TareaRelacionarOracionRespuestaValidacion.class);
        });
        try {
            userTransaction.begin();
            for (TareaRelacionarOracionModelo tareaRelacionarOracionModelo : tareaRelacionarOracionModeloLista) {
                Query query = entityManager.createNamedQuery("TareaRelacionarOracionesEntidad.respuesta");
                query.setParameter("respuesta", tareaRelacionarOracionModelo.getRespuesta()).setParameter("vuelta", tareaRelacionarOracionModelo.getVuelta())
                        .setParameter("idTarea", tareaRelacionarOracionModelo.getIdTarea()).setParameter("idOracionRelacionar", tareaRelacionarOracionModelo.getRelacionarOracionModelo().getId());
                query.executeUpdate();
            }
            userTransaction.commit();
            logger.finer("Se persistieron:".concat(String.valueOf(tareaRelacionarOracionModeloLista.size())));
        } catch (Exception e) {
            logger.log(Level.SEVERE, "No se puede insertar datos por:".concat(e.getMessage()), e);
            try {
                userTransaction.rollback();
            } catch (SystemException ex) {
                logger.log(Level.SEVERE, "No se pudo hcer rollback por:".concat(ex.getMessage()), ex);
            }
        }
    }

    private TareaRelacionarOracionesEntidadPK llavePrimaria(TareaRelacionarOracionModelo tareaRelacionarOracionModelo) {
        TareaRelacionarOracionesEntidadPK tareaRelacionarOracionesEntidadPK = new TareaRelacionarOracionesEntidadPK();
        tareaRelacionarOracionesEntidadPK.setTareaEntidad(new TareaEntidad(tareaRelacionarOracionModelo.getIdTarea()));
        tareaRelacionarOracionesEntidadPK.setRelacionarOracionesEntidad(
                new RelacionarOracionesEntidad(tareaRelacionarOracionModelo.getRelacionarOracionModelo().getId()));
        tareaRelacionarOracionesEntidadPK.setVuelta(tareaRelacionarOracionModelo.getVuelta());
        return tareaRelacionarOracionesEntidadPK;
    }

}
