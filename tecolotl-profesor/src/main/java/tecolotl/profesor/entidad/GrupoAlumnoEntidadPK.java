package tecolotl.profesor.entidad;

import tecolotl.alumno.entidad.AlumnoEntidad;
import tecolotl.alumno.entidad.TareaEntidad;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

@Embeddable
public class GrupoAlumnoEntidadPK implements Serializable {

    private GrupoEntidad grupoEntidad;
    private AlumnoEntidad alumnoEntidad;

    public GrupoAlumnoEntidadPK() {
    }

    public GrupoAlumnoEntidadPK(GrupoEntidad grupoEntidad, AlumnoEntidad alumnoEntidad) {
        this.grupoEntidad = grupoEntidad;
        this.alumnoEntidad = alumnoEntidad;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "id_grupo")
    public GrupoEntidad getGrupoEntidad() {
        return grupoEntidad;
    }

    public void setGrupoEntidad(GrupoEntidad grupoEntidad) {
        this.grupoEntidad = grupoEntidad;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @NotNull
    @JoinColumn(name = "id_alumno")
    public AlumnoEntidad getAlumnoEntidad() {
        return alumnoEntidad;
    }

    public void setAlumnoEntidad(AlumnoEntidad alumnoEntidad) {
        this.alumnoEntidad = alumnoEntidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrupoAlumnoEntidadPK that = (GrupoAlumnoEntidadPK) o;
        return grupoEntidad.equals(that.grupoEntidad) &&
                alumnoEntidad.equals(that.alumnoEntidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(grupoEntidad, alumnoEntidad);
    }

    @Override
    public String toString() {
        return "GrupoAlumnoEntidadPK{" +
                "grupoEntidad=" + grupoEntidad +
                ", alumnoEntidad=" + alumnoEntidad +
                '}';
    }

}
