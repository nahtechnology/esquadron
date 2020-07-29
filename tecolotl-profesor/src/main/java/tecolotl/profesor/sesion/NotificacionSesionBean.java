package tecolotl.profesor.sesion;

import tecolotl.alumno.entidad.AlumnoEntidad;
import tecolotl.profesor.entidad.NotificacionEntidad;
import tecolotl.profesor.entidad.NotificacionEntidadPK;
import tecolotl.profesor.entidad.ProfesorEntidad;
import tecolotl.profesor.modelo.NotificacionModelo;
import tecolotl.profesor.vista.NotificacionAlumnoVistaEntidad;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Stateless
public class NotificacionSesionBean {

    @PersistenceContext
    private EntityManager entityManager;

    public List<NotificacionModelo> busca(@NotNull UUID idProfesor) {
        TypedQuery<NotificacionAlumnoVistaEntidad> typedQuery = entityManager.createNamedQuery("NotificacionAlumnoVistaEntidad.buscaProfesor", NotificacionAlumnoVistaEntidad.class);
        typedQuery.setParameter("idProfesor", idProfesor);
        return typedQuery.getResultList().stream().map(NotificacionModelo::new).collect(Collectors.toList());
    }

    public Long cuenta(@NotNull UUID idProfesor) {
        TypedQuery<Long> typedQuery = entityManager.createNamedQuery("NotificacionEntidad.cuentaProfesor", Long.class);
        typedQuery.setParameter("idProfesor", idProfesor);
        return typedQuery.getSingleResult();
    }

    public int leido(@NotNull UUID idProfesor, @NotNull UUID idAlumno) {
        Query query = entityManager.createNamedQuery("NotificacionEntidad.leidos");
        query.setParameter("notificacionPK", llavePrimaria(idProfesor, idAlumno));
        return query.executeUpdate();
    }

    public int elimina(@NotNull UUID idProfesor, @NotNull UUID idAlumno) {
        Query query = entityManager.createNamedQuery("NotificacionEntidad.elimina");
        query.setParameter("notificacionPK", llavePrimaria(idProfesor, idAlumno));
        return query.executeUpdate();
    }

    private NotificacionEntidadPK llavePrimaria(UUID idProfesor, UUID idAlumno) {
        NotificacionEntidadPK notificacionEntidadPK = new NotificacionEntidadPK();
        notificacionEntidadPK.setAlumnoEntidad(new AlumnoEntidad(idAlumno));
        notificacionEntidadPK.setProfesorEntidad(new ProfesorEntidad(idProfesor));
        return notificacionEntidadPK;
    }
}
