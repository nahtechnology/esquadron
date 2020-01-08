package tecolotl.alumno.scope;

import tecolotl.alumno.entidad.ActividadEntidad;
import tecolotl.alumno.entidad.TareaEntidad;
import tecolotl.alumno.entidad.oraciones.OracionesEntidad;
import tecolotl.alumno.entidad.oraciones.OracionesEntidadPK;
import tecolotl.alumno.entidad.oraciones.TareaOracionesEntidad;
import tecolotl.alumno.entidad.oraciones.TareaOracionesEntidadPK;
import tecolotl.alumno.modelo.oraciones.TareaOracionesModelo;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequestScoped
public class OracionesRespuestaScope {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private Logger logger;

    @Inject
    private UserTransaction userTransaction;

    public void respuesta(@NotNull @Size(min = 1) List<TareaOracionesModelo> tareaOracionesModeloLista,@NotNull UUID idTarea) {
        logger.finer(tareaOracionesModeloLista.toString());
        try{
            userTransaction.begin();
            tareaOracionesModeloLista.forEach(tareaOracionesModelo -> {
                TareaOracionesEntidad tareaOracionesEntidad = entityManager.find(TareaOracionesEntidad.class, llaveprimaria(tareaOracionesModelo, idTarea));
                tareaOracionesEntidad.setRespuesta(tareaOracionesModelo.getRespuesta());
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

    private TareaOracionesEntidadPK llaveprimaria(TareaOracionesModelo tareaOracionesModelo, UUID idTarea){
        OracionesEntidadPK oracionesEntidadPK = new OracionesEntidadPK(
                new ActividadEntidad(
                        tareaOracionesModelo.getOracionesModelo().getActividadModelo().getIdVideo()),
                        tareaOracionesModelo.getOracionesModelo().getCodigo(),
                        tareaOracionesModelo.getOracionesModelo().getCardinalidad()
                );
        OracionesEntidad oracionesEntidad = new OracionesEntidad(oracionesEntidadPK);
        TareaEntidad tareaEntidad = new TareaEntidad(idTarea);
        return new TareaOracionesEntidadPK(oracionesEntidad, tareaEntidad);
    }
}
