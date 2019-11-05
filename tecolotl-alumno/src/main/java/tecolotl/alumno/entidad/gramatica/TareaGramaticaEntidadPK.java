package tecolotl.alumno.entidad.gramatica;

import tecolotl.alumno.entidad.TareaEntidad;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

@Embeddable
public class TareaGramaticaEntidadPK implements Serializable {

    private TareaEntidad tareaEntidad;
    private GramaticaEntidad gramaticaEntidad;
    private Short vuelta;

    public TareaGramaticaEntidadPK() {
    }

    public TareaGramaticaEntidadPK(TareaEntidad tareaEntidad, GramaticaEntidad gramaticaEntidad, Short vuelta) {
        this.tareaEntidad = tareaEntidad;
        this.gramaticaEntidad = gramaticaEntidad;
        this.vuelta = vuelta;
    }

    @JoinColumn(name = "id_tarea")
    @ManyToOne(fetch = FetchType.LAZY)
    public TareaEntidad getTareaEntidad() {
        return tareaEntidad;
    }

    public void setTareaEntidad(TareaEntidad tareaEntidad) {
        this.tareaEntidad = tareaEntidad;
    }

    @JoinColumns(
        value = {
            @JoinColumn(name = "id_actividad", referencedColumnName = "id_actividad"),
            @JoinColumn(name = "codigo", referencedColumnName = "codigo")
        }
    )
    @ManyToOne(fetch = FetchType.LAZY)
    public GramaticaEntidad getGramaticaEntidad() {
        return gramaticaEntidad;
    }

    public void setGramaticaEntidad(GramaticaEntidad gramaticaEntidad) {
        this.gramaticaEntidad = gramaticaEntidad;
    }

    @Basic
    @Column(name = "vuelta")
    public Short getVuelta() {
        return vuelta;
    }

    public void setVuelta(Short vuelta) {
        this.vuelta = vuelta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TareaGramaticaEntidadPK that = (TareaGramaticaEntidadPK) o;
        return tareaEntidad.equals(that.tareaEntidad) &&
                gramaticaEntidad.equals(that.gramaticaEntidad) &&
                vuelta.equals(that.vuelta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tareaEntidad, gramaticaEntidad, vuelta);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TareaGramaticaEntidadPK.class.getSimpleName() + "[", "]")
                .add("tareaEntidad=" + tareaEntidad)
                .add("gramaticaEntidad=" + gramaticaEntidad)
                .add("vuelta=" + vuelta)
                .toString();
    }

}
