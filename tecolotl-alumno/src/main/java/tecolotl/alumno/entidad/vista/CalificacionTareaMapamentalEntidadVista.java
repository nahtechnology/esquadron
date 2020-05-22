package tecolotl.alumno.entidad.vista;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Immutable
@Table(schema = "alumno", name = "calificacion_tarea_mapamental")
@NamedQuery(
        name = "CalificacionTareaMapamentalEntidadVista.busca",
        query = "SELECT ctm FROM CalificacionTareaMapamentalEntidadVista ctm WHERE ctm.idTarea = :idTarea"
)
public class CalificacionTareaMapamentalEntidadVista {

    private Short cardinalidad;
    private UUID idTarea;
    private Integer count;
    private Short puntaje;
    private String comentario;

    @Id
    public Short getCardinalidad() {
        return cardinalidad;
    }

    public void setCardinalidad(Short cardinalidad) {
        this.cardinalidad = cardinalidad;
    }

    @Column(name = "id_tarea")
    public UUID getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(UUID idTarea) {
        this.idTarea = idTarea;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Short getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Short puntaje) {
        this.puntaje = puntaje;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
