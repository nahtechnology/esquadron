package tecolotl.alumno.scope;

import tecolotl.alumno.entidad.ActividadEntidad;
import tecolotl.alumno.entidad.TareaEntidad;
import tecolotl.alumno.entidad.glosario.ClaseGlosarioEntidad;
import tecolotl.alumno.entidad.glosario.GlosarioEntidad;
import tecolotl.alumno.entidad.glosario.GlosarioEntidadPK;
import tecolotl.alumno.entidad.relacionar.*;
import tecolotl.alumno.modelo.relacionar.RelacionarModelo;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequestScoped
public class RelacionarRespuestaScope {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private Logger logger;

    @Inject
    private UserTransaction userTransaction;

    public void respuesta(@NotNull @Size(min = 1) List<RelacionarModelo> relacionarModeloLista, @NotNull final UUID idTarea) {
        relacionarModeloLista.forEach(relacionarModelo -> logger.fine(relacionarModelo.toString()));
        try{
            userTransaction.begin();
            for (RelacionarModelo relacionarModelo : relacionarModeloLista) {
                Query query = entityManager.createNamedQuery("TareaRelacionarActividadEntidad.responder");
                query.setParameter("idActividad", relacionarModelo.getIdActividad())
                        .setParameter("idTarea", idTarea)
                        .setParameter("palabra", relacionarModelo.getPalabra())
                        .setParameter("claveClase", relacionarModelo.getClaseGlosarioModelo().getClave())
                        .setParameter("respuesta", relacionarModelo.getRespuesta());
                query.executeUpdate();
            }
            userTransaction.commit();
        }catch(Exception e) {
            logger.log(Level.SEVERE, "No se puede insertar datos por: ".concat(e.getMessage()), e);
            try{
                userTransaction.rollback();
            }catch (Exception ex){
                logger.log(Level.SEVERE, "No se pudo hacer rollback por: ".concat(ex.getMessage()), ex);
            }
        }
    }
}
