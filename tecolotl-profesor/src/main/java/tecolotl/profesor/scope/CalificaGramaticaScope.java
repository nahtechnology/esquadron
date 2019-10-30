package tecolotl.profesor.scope;

import tecolotl.alumno.entidad.ActividadEntidad;
import tecolotl.alumno.entidad.TareaEntidad;
import tecolotl.alumno.entidad.gramatica.GramaticaEntidad;
import tecolotl.alumno.entidad.gramatica.GramaticaEntidadPK;
import tecolotl.alumno.entidad.gramatica.TareaGramaticaEntidad;
import tecolotl.alumno.entidad.gramatica.TareaGramaticaEntidadPK;
import tecolotl.alumno.modelo.gramatica.GramaticaModelo;
import tecolotl.profesor.entidad.CalificaTareaGramaticaEntidad;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequestScoped
public class CalificaGramaticaScope {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private Logger logger;

    @Resource
    private UserTransaction userTransaction;

    public void inserta(@NotNull @Size List<GramaticaModelo> gramaticaModeloLista, @NotNull Integer idTarea) {
        logger.info(gramaticaModeloLista.toString());
        logger.info(idTarea.toString());
        try {
            userTransaction.begin();
            for (GramaticaModelo gramaticaModelo : gramaticaModeloLista) {
                TareaGramaticaEntidadPK tareaGramaticaEntidadPK = new TareaGramaticaEntidadPK(
                        new TareaEntidad(idTarea),
                        new GramaticaEntidad(new GramaticaEntidadPK(new ActividadEntidad(gramaticaModelo.getActividadModelo().getIdVideo()), gramaticaModelo.getCodigo())),
                        gramaticaModelo.getVuelta()
                );
                CalificaTareaGramaticaEntidad calificaTareaGramaticaEntidad = entityManager.find(CalificaTareaGramaticaEntidad.class, new TareaGramaticaEntidad(tareaGramaticaEntidadPK));
                calificaTareaGramaticaEntidad.setPuntaje(gramaticaModelo.getCalificacion());
            }
            userTransaction.commit();
        } catch (Exception ex) {
            logger.log(Level.SEVERE,"No se puede insertar por la razon:" + ex , ex);
            try {
                userTransaction.rollback();
            } catch (SystemException e) {
                logger.log(Level.SEVERE,"No se puede hacer rollbak por la razon:" + ex, ex);
            }
        }
    }
}
