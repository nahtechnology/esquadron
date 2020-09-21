package tecolotl.profesor.sesion;

import tecolotl.alumno.entidad.AlumnoEntidad;
import tecolotl.profesor.entidad.GrupoAlumnoEntidad;
import tecolotl.profesor.entidad.GrupoAlumnoEntidadPK;
import tecolotl.profesor.entidad.GrupoEntidad;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
@TransactionManagement(value = TransactionManagementType.BEAN)
public class ReasignaAlumnoSesionBean {

    @Inject
    private Logger logger;

    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    private UserTransaction userTransaction;

    public void reasigar(UUID grupoViejo, UUID grupoNuevo, UUID idAlumno) {
        GrupoAlumnoEntidadPK grupoAlumnoEntidadPK = new GrupoAlumnoEntidadPK();
        try {
            userTransaction.begin();
            Query query = entityManager.createNamedQuery("GrupoAlumnoEntidad.elimina");
            query.setParameter("idAlumno", idAlumno).setParameter("idGrupo", grupoViejo);
            query.executeUpdate();
            grupoAlumnoEntidadPK.setAlumnoEntidad(new AlumnoEntidad(idAlumno));
            grupoAlumnoEntidadPK.setGrupoEntidad(new GrupoEntidad(grupoNuevo));
            entityManager.persist(new GrupoAlumnoEntidad(grupoAlumnoEntidadPK));
            query = entityManager.createNamedQuery("TareaEntidad.actualizaGrupo");
            query.setParameter("idAlumno", idAlumno).setParameter("nuevoGrupo", grupoNuevo).setParameter("viejoGrupo", grupoViejo);
            query.executeUpdate();
            userTransaction.commit();
        } catch (Exception ex) {
            try {
                userTransaction.rollback();
            } catch (Exception exception) {
                logger.log(Level.SEVERE, exception.getMessage(), exception);
            }
        }
    }

}
