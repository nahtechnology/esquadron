package tecolotl.profesor.entidad;

import tecolotl.alumno.entidad.AlumnoEntidad;
import tecolotl.alumno.entidad.TareaEntidad;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Embeddable
public class GrupoAlumnoTareaEntidadPK implements Serializable {
    private GrupoEntidad grupoEntidad;
    private AlumnoEntidad alumnoEntidad;
    private TareaEntidad tareaEntidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "id_grupo")
    public GrupoEntidad getGrupoEntidad() {
        return grupoEntidad;
    }

    public void setGrupoEntidad(GrupoEntidad grupoEntidad) {
        this.grupoEntidad = grupoEntidad;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "id_alumno")
    public AlumnoEntidad getAlumnoEntidad() {
        return alumnoEntidad;
    }

    public void setAlumnoEntidad(AlumnoEntidad alumnoEntidad) {
        this.alumnoEntidad = alumnoEntidad;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "id_tarea")
    public TareaEntidad getTareaEntidad() {
        return tareaEntidad;
    }

    public void setTareaEntidad(TareaEntidad tareaEntidad) {
        this.tareaEntidad = tareaEntidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrupoAlumnoTareaEntidadPK that = (GrupoAlumnoTareaEntidadPK) o;
        return grupoEntidad.equals(that.grupoEntidad) &&
            alumnoEntidad.equals(that.alumnoEntidad) &&
            tareaEntidad.equals(that.tareaEntidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(grupoEntidad, alumnoEntidad, tareaEntidad);
    }
}
