package tecolotl.alumno.entidad.vista;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Immutable
@Table(name = "tareas_alumno", schema = "alumno")
@NamedQuery(
        name = "TareaAlumnoVistaEntidad.buscaAlumno",
        query = "SELECT tav FROM TareaAlumnoVistaEntidad tav " +
                "WHERE tav.idAlumno = :idAlumno AND tav.idGrupo = :idGrupo ORDER BY tav.pendiente DESC, tav.asignacion DESC ")
public class TareaAlumnoVistaEntidad {

    private UUID id;
    private String idGrupo;
    private Date asignacion;
    private String idActividad;
    private UUID idAlumno;
    private boolean verRespuesta;
    private String preguntaDetonadora;
    private boolean pendiente;

    @Id
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Column(name = "id_grupo")
    public String getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(String idGrupo) {
        this.idGrupo = idGrupo;
    }

    public Date getAsignacion() {
        return asignacion;
    }

    public void setAsignacion(Date asignacion) {
        this.asignacion = asignacion;
    }

    @Column(name = "id_actividad")
    public String getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(String idActividad) {
        this.idActividad = idActividad;
    }

    @Column(name = "pregunta_detonadora")
    public String getPreguntaDetonadora() {
        return preguntaDetonadora;
    }

    public void setPreguntaDetonadora(String preguntaDetonadora) {
        this.preguntaDetonadora = preguntaDetonadora;
    }

    public boolean isPendiente() {
        return pendiente;
    }

    public void setPendiente(boolean pendiente) {
        this.pendiente = pendiente;
    }

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "id_alumno")
    public UUID getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(UUID idAlumno) {
        this.idAlumno = idAlumno;
    }

    @Basic
    @Column(name = "ver_respuesta")
    public boolean isVerRespuesta() {
        return verRespuesta;
    }

    public void setVerRespuesta(boolean verRespuesta) {
        this.verRespuesta = verRespuesta;
    }
}
