package tecolotl.profesor.modelo;

import tecolotl.profesor.entidad.NotificacionEntidad;
import tecolotl.profesor.vista.NotificacionAlumnoVistaEntidad;

import java.util.Date;
import java.util.UUID;

public class NotificacionModelo {

    private UUID idAlumno;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Date momento;
    private boolean leido;
    private String nivelLenguaje;

    public NotificacionModelo() {
    }

    public NotificacionModelo(NotificacionEntidad notificacionEntidad) {
        idAlumno = notificacionEntidad.getNotificacionEntidadPK().getAlumnoEntidad().getId();
        nombre = notificacionEntidad.getNotificacionEntidadPK().getAlumnoEntidad().getNombre();
        apellidoPaterno = notificacionEntidad.getNotificacionEntidadPK().getAlumnoEntidad().getApellidoPaterno();
        apellidoPaterno = notificacionEntidad.getNotificacionEntidadPK().getAlumnoEntidad().getApellidoMaterno();
        momento = notificacionEntidad.getMomento();
        leido = notificacionEntidad.isLeido();
    }

    public NotificacionModelo(NotificacionAlumnoVistaEntidad entidad) {
        idAlumno = entidad.getId();
        nombre = entidad.getNombre();
        apellidoPaterno = entidad.getApellidoPaterno();
        apellidoMaterno = entidad.getApellidoMaterno();
        momento = entidad.getMomento();
        leido = entidad.isLeido();
        nivelLenguaje = entidad.getNivelLenguaje();
    }

    public UUID getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(UUID idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public Date getMomento() {
        return momento;
    }

    public void setMomento(Date momento) {
        this.momento = momento;
    }

    public boolean isLeido() {
        return leido;
    }

    public void setLeido(boolean leido) {
        this.leido = leido;
    }

    public String getNivelLenguaje() {
        return nivelLenguaje;
    }

    public void setNivelLenguaje(String nivelLenguaje) {
        this.nivelLenguaje = nivelLenguaje;
    }
}
