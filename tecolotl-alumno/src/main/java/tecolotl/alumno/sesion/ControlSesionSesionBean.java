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
import java.util.Date;
import java.util.List;
import java.util.UUID;
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
    public List<AlumnoControlSesionModelo> busca(UUID idAlumno, Date fechaInicio, Date fechaFin) {
        TypedQuery<AlumnoControlSesionEntidad> typedQuery =
                entityManager.createNamedQuery("AlumnoControlSesionEntidad.buscaAlumno", AlumnoControlSesionEntidad.class);
        typedQuery.setParameter("idAlumno", idAlumno);
        typedQuery.setParameter("fechaInicio", fechaInicio);
        typedQuery.setParameter("fechaFin", fechaFin);
        return typedQuery.getResultList().stream().map(AlumnoControlSesionModelo::new).collect(Collectors.toList());
    }

}
