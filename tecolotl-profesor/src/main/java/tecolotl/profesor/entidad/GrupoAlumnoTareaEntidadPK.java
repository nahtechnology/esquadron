package tecolotl.profesor.entidad;

import tecolotl.alumno.entidad.TareaEntidad;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Embeddable
public class GrupoAlumnoTareaEntidadPK implements Serializable {
    private List<GrupoAlumnoEntidad> grupoAlumnoEntidad;
    private TareaEntidad tareaEntidad;

    @OneToMany(fetch = FetchType.LAZY)
    @NotNull
    public List<GrupoAlumnoEntidad> getGrupoAlumnoEntidad() {
        return grupoAlumnoEntidad;
    }

    public void setGrupoAlumnoEntidad(List<GrupoAlumnoEntidad> grupoAlumnoEntidad) {
        this.grupoAlumnoEntidad = grupoAlumnoEntidad;
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
}
