package tecolotl.profesor.sesion;

import tecolotl.alumno.entidad.AlumnoEntidad;
import tecolotl.profesor.entidad.GrupoAlumnoEntidad;
import tecolotl.profesor.entidad.GrupoAlumnoEntidadPK;
import tecolotl.profesor.entidad.GrupoEntidad;

import javax.annotation.Resource;
import javax.ejb.Stateful;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.*;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateful
@TransactionManagement(TransactionManagementType.BEAN)
public class HeredaSesionBean {

    @Resource
    private UserTransaction userTransaction;

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private Logger logger;

    public void heresaAlumnos(UUID grupo, List<UUID> alumnoLista) {
        GrupoAlumnoEntidadPK grupoAlumnoEntidadPK = null;
        GrupoAlumnoEntidad grupoAlumnoEntidad = null;
        try {
            userTransaction.begin();
            for (UUID alumno : alumnoLista) {
                grupoAlumnoEntidadPK = new GrupoAlumnoEntidadPK(new GrupoEntidad(grupo), new AlumnoEntidad(alumno));
                grupoAlumnoEntidad = new GrupoAlumnoEntidad(grupoAlumnoEntidadPK);
                entityManager.persist(grupoAlumnoEntidad);
            }
            userTransaction.commit();
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException exception) {
            try {
                userTransaction.rollback();
            } catch (SystemException e) {
                logger.log(Level.SEVERE, e.getMessage(), e);
            }
        }
    }

}
