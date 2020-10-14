package tecolotl.profesor.sesion;

import tecolotl.nucleo.persistencia.entidad.TipoRegistroEntidad;
import tecolotl.profesor.entidad.ProfesorControlSesionEntidad;
import tecolotl.profesor.entidad.ProfesorEntidad;
import tecolotl.profesor.modelo.ProfesorSesionControlModelo;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Stateless
public class ProfesorSesionControlSessionBean {

    @PersistenceContext
    private EntityManager entityManager;

    public void inserta(@NotNull UUID idProfesor, @NotNull Short tipo, Date tiempo) {
        ProfesorControlSesionEntidad profesorControlSesionEntidad = new ProfesorControlSesionEntidad();
        profesorControlSesionEntidad.setTiempo(tiempo == null ? new Date() : tiempo);
        profesorControlSesionEntidad.setProfesorEntidad(new ProfesorEntidad(idProfesor));
        profesorControlSesionEntidad.setTipoRegistroEntidad(new TipoRegistroEntidad(tipo));
        entityManager.persist(profesorControlSesionEntidad);
    }

    public List<ProfesorSesionControlModelo> busca(@NotNull UUID idProfesor, @NotNull Date fechaInicio, @NotNull Date fechaFin) {
        TypedQuery<ProfesorControlSesionEntidad> typedQuery =
                entityManager.createNamedQuery("ProfesorControlSesionEntidad.buscaFecha", ProfesorControlSesionEntidad.class);
        typedQuery.setParameter("idProfesor", idProfesor);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaInicio);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        typedQuery.setParameter("fechaInicio", calendar.getTime());
        calendar.setTime(fechaFin);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        typedQuery.setParameter("fechaFin", calendar.getTime());
        return typedQuery.getResultList().stream().map(ProfesorSesionControlModelo::new).collect(Collectors.toList());
    }

}
