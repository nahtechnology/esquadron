package tecolotl.profesor.entidad;

import tecolotl.alumno.entidad.gramatica.TareaGramaticaEntidad;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "califica_tarea_gramatica", schema = "profesor")
@NamedQueries({
        @NamedQuery(name = "CalificaTareaGramaticaEntidad.busca", query = "SELECT calTarea FROM CalificaTareaGramaticaEntidad calTarea")
})
public class CalificaTareaGramaticaEntidad implements Serializable {

    private TareaGramaticaEntidad tareaGramaticaEntidad;
    private Short puntaje;
    private Date momento;

    public CalificaTareaGramaticaEntidad() {
    }

    public CalificaTareaGramaticaEntidad(TareaGramaticaEntidad tareaGramaticaEntidad) {
        this.tareaGramaticaEntidad = tareaGramaticaEntidad;
    }

    @Id
    @OneToOne
    @JoinColumns(value = {
            @JoinColumn(name = "id_tarea", referencedColumnName = "id_tarea"),
            @JoinColumn(name = "id_actividad", referencedColumnName = "id_actividad"),
            @JoinColumn(name = "codigo", referencedColumnName = "codigo"),
            @JoinColumn(name = "vuelta", referencedColumnName = "vuelta")
    })
    public TareaGramaticaEntidad getTareaGramaticaEntidad() {
        return tareaGramaticaEntidad;
    }

    public void setTareaGramaticaEntidad(TareaGramaticaEntidad tareaGramaticaEntidad) {
        this.tareaGramaticaEntidad = tareaGramaticaEntidad;
    }

    @Basic
    @NotNull
    @Min(value = 0)
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
        CalificaTareaGramaticaEntidad that = (CalificaTareaGramaticaEntidad) o;
        return tareaGramaticaEntidad.equals(that.tareaGramaticaEntidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tareaGramaticaEntidad);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CalificaTareaGramaticaEntidad.class.getSimpleName() + "[", "]")
                .add("tareaGramaticaEntidad=" + tareaGramaticaEntidad)
                .add("puntaje=" + puntaje)
                .add("momento=" + momento)
                .toString();
    }
}
