package tecolotl.alumno.sesion;

import tecolotl.alumno.entidad.AlumnoEntidad;
import tecolotl.alumno.entidad.AlumnoControlSesionEntidad;
import tecolotl.alumno.modelo.AlumnoControlSesionModelo;
import tecolotl.nucleo.persistencia.entidad.TipoRegistroEntidad;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;

@Stateless
public class ControlSesionSesionBean {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Insertamos un registro en la base datos de cieres de sesion.
     * @param idAlumno Alumno quien inicio sesión.
     * @param tipo tipo de sesion, 1 para inicio y 2 para cierre.
     */
    public void inserta(@NotNull UUID idAlumno, @NotNull Short tipo, @NotNull Date tiempo) {
        TipoRegistroEntidad tipoRegistroEntidad = new TipoRegistroEntidad(tipo);
        AlumnoControlSesionEntidad alumnoControlSesionEntidad = new AlumnoControlSesionEntidad();
        alumnoControlSesionEntidad.setTipoRegistroEntidad(tipoRegistroEntidad);
        alumnoControlSesionEntidad.setAlumnoEntidad(new AlumnoEntidad(idAlumno));
        alumnoControlSesionEntidad.setTiempo(tiempo);
        entityManager.persist(alumnoControlSesionEntidad);
    }

    /**
     * Busca todos los inicio de sesion de de un alumno partiendo de un rango de fecha
     * @param idAlumno Identificador del alumno
     * @param fechaInicio Fecha de inicion a buscar
     * @param fechaFin Fecha fin a buscar
     * @return Colección de alumno control sesion
     */
    public List<AlumnoControlSesionModelo> busca(UUID idAlumno, Date fechaInicio, Date fechaFin, TimeZone timeZone) {
        TypedQuery<AlumnoControlSesionEntidad> typedQuery =
                entityManager.createNamedQuery("AlumnoControlSesionEntidad.buscaAlumno", AlumnoControlSesionEntidad.class);
        typedQuery.setParameter("idAlumno", idAlumno);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(timeZone);
        calendar.setTime(fechaInicio);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.HOUR, 6);
        typedQuery.setParameter("fechaInicio", calendar.getTime());
        calendar.setTime(fechaFin);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.add(Calendar.HOUR, 6);
        typedQuery.setParameter("fechaFin", calendar.getTime());
        return typedQuery.getResultList().stream().map(AlumnoControlSesionModelo::new).collect(Collectors.toList());
    }

}
