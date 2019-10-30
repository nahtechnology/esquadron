package tecolotl.profesor.entidad;

import tecolotl.alumno.entidad.TareaEntidad;
import tecolotl.alumno.entidad.mapamental.TareaMapaMentalActividadEntidad;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "califica_tarea_mapamental", schema = "profesor")
@NamedQueries({
    @NamedQuery(name = "CalificaTareaMapamental.busca", query = "SELECT ctmm FROM CalificaTareaMapamentalEntidad ctmm"),
    @NamedQuery(
        name = "CalificaTareaMapamentalEntidad.califica",
        query = "UPDATE CalificaTareaMapamentalEntidad c SET c.comentario = :comentario, c.puntaje = :puntaje " +
                "WHERE c.calificaTareaMapamentalEntidadPK.tareaEntidad.id = :idTarea AND " +
                "c.calificaTareaMapamentalEntidadPK.cardinalidad = :cardinalidad AND c.calificaTareaMapamentalEntidadPK.vuelta = :vuelta"),
    @NamedQuery(
        name = "CalificaTareaMapamentalEntidad.busca",
        query = "SELECT ctm FROM CalificaTareaMapamentalEntidad ctm JOIN ctm.calificaTareaMapamentalEntidadPK.tareaEntidad t " +
                "WHERE t.id = :idTarea")
})
public class CalificaTareaMapamentalEntidad implements Serializable {

    private CalificaTareaMapamentalEntidadPK calificaTareaMapamentalEntidadPK;
    private String comentario;
    private Short puntaje;
    private Date momento;

    public CalificaTareaMapamentalEntidad() {
    }

    public CalificaTareaMapamentalEntidad(CalificaTareaMapamentalEntidadPK calificaTareaMapamentalEntidadPK) {
        this.calificaTareaMapamentalEntidadPK = calificaTareaMapamentalEntidadPK;
    }

    @EmbeddedId
    public CalificaTareaMapamentalEntidadPK getCalificaTareaMapamentalEntidadPK() {
        return calificaTareaMapamentalEntidadPK;
    }

    public void setCalificaTareaMapamentalEntidadPK(CalificaTareaMapamentalEntidadPK calificaTareaMapamentalEntidadPK) {
        this.calificaTareaMapamentalEntidadPK = calificaTareaMapamentalEntidadPK;
    }

    @NotNull
    @Size(min = 0)
    @Basic
    @Column(name = "comentario")
    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @NotNull
    @Min(0)
    @Basic
    @Column(name = "puntaje")
    public Short getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Short puntaje) {
        this.puntaje = puntaje;
    }

    @Basic
    @Column(name = "momento", insertable = false, updatable = false)
    public Date getMomento() {
        return momento;
    }

    public void setMomento(Date momento) {
        this.momento = momento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CalificaTareaMapamentalEntidad that = (CalificaTareaMapamentalEntidad) o;
        return calificaTareaMapamentalEntidadPK.equals(that.calificaTareaMapamentalEntidadPK) &&
                comentario.equals(that.comentario) &&
                puntaje.equals(that.puntaje) &&
                momento.equals(that.momento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(calificaTareaMapamentalEntidadPK, comentario, puntaje, momento);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CalificaTareaMapamentalEntidad.class.getSimpleName() + "[", "]")
                .add("calificaTareaMapamentalEntidadPK=" + calificaTareaMapamentalEntidadPK)
                .add("comentario='" + comentario + "'")
                .add("puntaje=" + puntaje)
                .add("momento=" + momento)
                .toString();
    }

}
