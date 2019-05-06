package tecolotl.alumno.entidad;

import tecolotl.nucleo.persistencia.entidad.PersonaEntidad;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "alumno", schema = "alumno")
@NamedQueries({
        @NamedQuery(name = "AlumnoEntidad.busca", query = "SELECT a FROM AlumnoEntidad a ")
})
public class AlumnoEntidad extends PersonaEntidad {
    private Integer id;
    private List<GradoEscolarEntidad> gradoEscolarEntidad;

    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @OneToMany
    @JoinColumn(name = "id_grado_escolar")
    public List<GradoEscolarEntidad> getGradoEscolarEntidad() {
        return gradoEscolarEntidad;
    }

    public void setGradoEscolarEntidad(List<GradoEscolarEntidad> gradoEscolarEntidad) {
        this.gradoEscolarEntidad = gradoEscolarEntidad;
    }
}
