package tecolotl.alumno.entidad;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TareaGlosarioEntidadPK implements Serializable {
    private AlumnoEntidad alumnoEntidad;
    private GlosarioEntidad glosarioEntidad;


    @NotNull
    @OneToOne
    @JoinColumn(name = "id_tarea", referencedColumnName = "id")
    public AlumnoEntidad getAlumnoEntidad() {
        return alumnoEntidad;
    }

    public void setAlumnoEntidad(AlumnoEntidad alumnoEntidad) {
        this.alumnoEntidad = alumnoEntidad;
    }

    @NotNull
    @OneToOne
    @JoinColumn(name = "id_glosario", referencedColumnName = "palabra")
    public GlosarioEntidad getGlosarioEntidad() {
        return glosarioEntidad;
    }

    public void setGlosarioEntidad(GlosarioEntidad glosarioEntidad) {
        this.glosarioEntidad = glosarioEntidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TareaGlosarioEntidadPK that = (TareaGlosarioEntidadPK) o;
        return alumnoEntidad.equals(that.alumnoEntidad) &&
                glosarioEntidad.equals(that.glosarioEntidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(alumnoEntidad, glosarioEntidad);
    }
}
