package tecolotl.alumno.sesion;

import tecolotl.alumno.entidad.AlumnoEntidad;
import tecolotl.alumno.entidad.ControlSesionEntidad;
import tecolotl.nucleo.persistencia.entidad.TipoRegistroEntidad;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Stateless
public class ControlSesionSesionBean {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Insertamos un registro en la base datos de cieres de sesion.
     * @param idAlumno Alumno quien inicio sesión.
     * @param tipo tipo de sesion, 1 para inicio y 2 para cierre.
     */
    public void inserta(@NotNull UUID idAlumno, short tipo) {
        TipoRegistroEntidad tipoRegistroEntidad = new TipoRegistroEntidad(tipo);
        ControlSesionEntidad controlSesionEntidad = new ControlSesionEntidad();
        controlSesionEntidad.setTipoRegistroEntidad(tipoRegistroEntidad);
        controlSesionEntidad.setAlumnoEntidad(new AlumnoEntidad(idAlumno));
        entityManager.persist(controlSesionEntidad);
    }

}
