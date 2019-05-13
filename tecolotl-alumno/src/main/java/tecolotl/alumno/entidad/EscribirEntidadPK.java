package tecolotl.alumno.entidad;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Embeddable
public class EscribirEntidadPK implements Serializable {
    private ActividadEntidad actividadEntidad;
    private Short contador;

    @ManyToOne
    @JoinColumn(name = "id_actividad", referencedColumnName = "id")
    public ActividadEntidad getActividadEntidad() {
        return actividadEntidad;
    }

    public void setActividadEntidad(ActividadEntidad actividadEntidad) {
        this.actividadEntidad = actividadEntidad;
    }

    @Basic
    @Column(name = "contador")
    @NotNull
    public Short getContador() {
        return contador;
    }

    public void setContador(Short contador) {
        this.contador = contador;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EscribirEntidadPK that = (EscribirEntidadPK) o;
        return actividadEntidad.equals(that.actividadEntidad) &&
                contador.equals(that.contador);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actividadEntidad, contador);
    }
}
