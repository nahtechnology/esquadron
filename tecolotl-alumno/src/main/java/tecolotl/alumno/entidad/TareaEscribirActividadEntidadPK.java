package tecolotl.alumno.entidad;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

@Embeddable
public class TareaEscribirActividadEntidadPK implements Serializable {

    private EscribirActividadEntidad escribirActividadEntidad;

    public TareaEscribirActividadEntidadPK() {
    }

    public TareaEscribirActividadEntidadPK(EscribirActividadEntidad escribirActividadEntidad) {
        this.escribirActividadEntidad = escribirActividadEntidad;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns(value = {
            @JoinColumn(name = "id_escribir", referencedColumnName = "id_escribir"),
            @JoinColumn(name = "id_actividad", referencedColumnName = "id_actividad")
    })
    public EscribirActividadEntidad getEscribirActividadEntidad() {
        return escribirActividadEntidad;
    }

    public void setEscribirActividadEntidad(EscribirActividadEntidad escribirActividadEntidad) {
        this.escribirActividadEntidad = escribirActividadEntidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TareaEscribirActividadEntidadPK that = (TareaEscribirActividadEntidadPK) o;
        return escribirActividadEntidad.equals(that.escribirActividadEntidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(escribirActividadEntidad);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TareaEscribirActividadEntidadPK.class.getSimpleName() + "[", "]")
                .add("escribirActividadEntidad=" + escribirActividadEntidad)
                .toString();
    }
}
