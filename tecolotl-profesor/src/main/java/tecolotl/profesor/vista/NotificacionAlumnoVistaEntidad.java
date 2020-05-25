package tecolotl.profesor.vista;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Immutable
@Table(name = "notificacion_alumno", schema = "profesor")
@NamedQuery(
        name = "NotificacionAlumnoVistaEntidad.buscaProfesor",
        query = "SELECT na FROM NotificacionAlumnoVistaEntidad na WHERE na.idProfesor = :idProfesor")
public class NotificacionAlumnoVistaEntidad {

    private UUID id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private UUID idProfesor;
    private Date momento;
    private boolean leido;
    private String nivelLenguaje;

    public NotificacionAlumnoVistaEntidad() {
    }

    @Id
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(name = "apellido_paterno")
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    @Column(name = "apellido_materno")
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    @Column(name = "id_profesor")
    public UUID getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(UUID idProfesor) {
        this.idProfesor = idProfesor;
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

    @Column(name = "nivel_lenguaje")
    public String getNivelLenguaje() {
        return nivelLenguaje;
    }

    public void setNivelLenguaje(String nivelLenguaje) {
        this.nivelLenguaje = nivelLenguaje;
    }
}
