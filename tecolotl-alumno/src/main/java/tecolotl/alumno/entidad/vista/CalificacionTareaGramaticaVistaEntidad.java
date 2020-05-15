package tecolotl.alumno.entidad.vista;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Immutable
@Table(schema = "alumno", name = "calificacion_tarea_gramatica")
@NamedQuery(
        name = "CalificacionTareaGramaticaVistaEntidad.busca",
        query = "SELECT ctg FROM CalificacionTareaGramaticaVistaEntidad ctg WHERE ctg.id = :idTarea"
)
public class CalificacionTareaGramaticaVistaEntidad {

    private UUID id;
    private Short puntaje;
    private String respuesta;
    private String palabra;
    private String codigo;
    private String idActividad;
    private Short vuelta;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Short getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Short puntaje) {
        this.puntaje = puntaje;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    @Id
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Column(name = "id_actividad")
    public String getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(String idActividad) {
        this.idActividad = idActividad;
    }

    public Short getVuelta() {
        return vuelta;
    }

    public void setVuelta(Short vuelta) {
        this.vuelta = vuelta;
    }
}
