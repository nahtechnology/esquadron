package tecolotl.alumno.scope;

import tecolotl.alumno.entidad.ActividadEntidad;
import tecolotl.alumno.entidad.TareaEntidad;
import tecolotl.alumno.entidad.relacionar.*;
import tecolotl.alumno.modelo.relacionar.RelacionarModelo;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
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

    public void respuesta(@NotNull @Size(min = 1) List<RelacionarModelo> relacionarModeloLista, @NotNull final Integer idTarea) {
        relacionarModeloLista.forEach(relacionarModelo -> logger.fine(relacionarModelo.toString()));
        try{
            userTransaction.begin();
            relacionarModeloLista.forEach(relacionarModelo -> {
                TareaRelacionarActividadEntidad tareaRelacionarActividadEntidad =
                        entityManager.find(TareaRelacionarActividadEntidad.class,
                                new TareaRelacionarActividadEntidadPK(
                                        new RelacionarActividadEntidad(
                                                new RelacionarActividadEntidadPK(new RelacionarEntidad(relacionarModelo.getCodigo()),
                                                        new ActividadEntidad(relacionarModelo.getIdActividad()))), new TareaEntidad(idTarea)));
                tareaRelacionarActividadEntidad.setRespuesta(relacionarModelo.getRespuesta());
            });
            userTransaction.commit();
        }catch(Exception e){
            logger.log(Level.SEVERE, "No se puede insertar datos por: ".concat(e.getMessage()), e);
            try{
                userTransaction.rollback();
            }catch (Exception ex){
                logger.log(Level.SEVERE, "No se pudo hacer rollback por: ".concat(ex.getMessage()), ex);
            }
        }
    }
}