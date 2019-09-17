package tecolotl.alumno.modelo;

import tecolotl.alumno.entidad.TareaEntidad;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;

public class TareaModelo {

    private Integer id;
    private Date asignacion;
    private Short reproducciones;

    public TareaModelo() {
    }

    public TareaModelo(Integer id) {
        this.id = id;
    }

    public TareaModelo(TareaEntidad tareaEntidad) {
        this.id = tareaEntidad.getId();
        this.asignacion = tareaEntidad.getAsignacion();
        this.reproducciones = tareaEntidad.getReproducciones();
    }

    @NotNull
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @NotNull
    public Date getAsignacion() {
        return asignacion;
    }

    public void setAsignacion(Date asignacion) {
        this.asignacion = asignacion;
    }

    @NotNull
    @Min(0)
    public Short getReproducciones() {
        return reproducciones;
    }

    public void setReproducciones(Short reproducciones) {
        this.reproducciones = reproducciones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TareaModelo that = (TareaModelo) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TareaModelo.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("asignacion=" + asignacion)
                .add("reproducciones=" + reproducciones)
                .toString();
    }
}
