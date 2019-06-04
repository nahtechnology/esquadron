package tecolotl.profesor.entidad;

import tecolotl.alumno.entidad.AlumnoEntidad;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "grupo", schema = "profesor")
public class GrupoEntidad {

    private GrupoEntidadPK grupoEntidadPK;
    private List<AlumnoEntidad> alumnoEntidadLista;

    @EmbeddedId
    public GrupoEntidadPK getGrupoEntidadPK() {
        return grupoEntidadPK;
    }

    public void setGrupoEntidadPK(GrupoEntidadPK grupoEntidadPK) {
        this.grupoEntidadPK = grupoEntidadPK;
    }

    @OneToMany
    @JoinColumn(name = "id_alumno")
    public List<AlumnoEntidad> getAlumnoEntidadLista() {
        return alumnoEntidadLista;
    }

    public void setAlumnoEntidadLista(List<AlumnoEntidad> alumnoEntidadLista) {
        this.alumnoEntidadLista = alumnoEntidadLista;
    }
}
