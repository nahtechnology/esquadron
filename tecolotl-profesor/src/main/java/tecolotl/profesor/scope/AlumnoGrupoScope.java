package tecolotl.profesor.scope;

import tecolotl.alumno.entidad.AlumnoEntidad;
import tecolotl.alumno.entidad.NivelLenguajeEntidad;
import tecolotl.alumno.modelo.AlumnoModelo;
import tecolotl.profesor.entidad.GrupoAlumnoEntidad;
import tecolotl.profesor.entidad.GrupoAlumnoEntidadPK;
import tecolotl.profesor.entidad.GrupoEntidad;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import javax.validation.constraints.NotNull;
import java.util.UUID;
import java.util.logging.Logger;

@RequestScoped
public class AlumnoGrupoScope {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private Logger logger;

    @Resource
    private UserTransaction userTransaction;

    /**
     * Agrega un alumno y en cascada lo agrega a un grupo
     * @param alumnoModelo Datos del nuevo alumno a ser ingresaod
     * @param idGrupo grupo donde se agrega dicho alumno
     */
    public void inserta(@NotNull AlumnoModelo alumnoModelo, @NotNull UUID idGrupo) {
        AlumnoEntidad alumnoEntidad = new AlumnoEntidad();
        alumnoEntidad.setApodo(alumnoModelo.getApodo());
        alumnoEntidad.setNombre(alumnoModelo.getNombre());
        alumnoEntidad.setApellidoPaterno(alumnoModelo.getApellidoPaterno());
        alumnoEntidad.setApellidoMaterno(alumnoModelo.getApellidoMaterno());
        alumnoEntidad.setNacimiento(alumnoModelo.getNacimiento());
        alumnoEntidad.setSexo(Character.toLowerCase(alumnoModelo.getSexo()));
        NivelLenguajeEntidad nivelLenguajeEntidad = new NivelLenguajeEntidad(alumnoModelo.getNivelLenguajeModelo().getClave());
        alumnoEntidad.setNivelLenguajeEntidad(nivelLenguajeEntidad);
        alumnoEntidad.setContrasenia(alumnoModelo.getContrasenia());
        alumnoEntidad.setContraseniaPadreFamilia(alumnoModelo.getContrasenia());
        alumnoEntidad.setCorreoPadreFamilia("sincorreo@servidor.com");
        GrupoEntidad grupoEntidad = new GrupoEntidad(idGrupo);
        try {
            userTransaction.begin();
            entityManager.persist(alumnoEntidad);
            GrupoAlumnoEntidad grupoAlumnoEntidad = new GrupoAlumnoEntidad(new GrupoAlumnoEntidadPK(grupoEntidad, alumnoEntidad));
            entityManager.persist(grupoAlumnoEntidad);
            alumnoModelo.setId(alumnoEntidad.getId());
            userTransaction.commit();
        } catch (Exception ex) {
            logger.severe("No se puede insertar por la razon:" + ex);
            try {
                userTransaction.rollback();
            } catch (SystemException e) {
                logger.severe("No se puede insertar por la razon:" + ex);
            }
        }
    }
}
