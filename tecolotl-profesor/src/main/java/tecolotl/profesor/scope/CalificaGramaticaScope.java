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
import javax.persistence.Query;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;
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

    public void inserta(@NotNull @Size List<GramaticaModelo> gramaticaModeloLista, @NotNull UUID idTarea) {
        logger.info(gramaticaModeloLista.toString());
        logger.info(idTarea.toString());
        try {
            userTransaction.begin();
            for (GramaticaModelo gramaticaModelo : gramaticaModeloLista) {
                Query query = entityManager.createNamedQuery("CalificaTareaGramaticaEntidad.califica");
                query.setParameter("puntaje", gramaticaModelo.getCalificacion())
                        .setParameter("idTarea", idTarea)
                        .setParameter("idActividad", gramaticaModelo.getActividadModelo().getIdVideo())
                        .setParameter("codigo", gramaticaModelo.getCodigo())
                        .setParameter("vuelta", gramaticaModelo.getVuelta());
                query.executeUpdate();
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
