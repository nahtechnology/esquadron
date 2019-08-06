package tecolotl.alumno.entidad;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.StringJoiner;

@Entity
@Table(name = "escribir", schema = "alumno")
@NamedQueries({
        @NamedQuery(name = "EscribirEntidad.busca", query = "SELECT a FROM EscribirEntidad a")
})
public class EscribirEntidad {
    private Integer id;
    private String pregunta;
    private EscribirActividadEntidad escribirActividadEntidad;

    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "pregunta")
    @NotNull
    @Size(min = 2, max = 100)
    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    @OneToMany
    public EscribirActividadEntidad getEscribirActividadEntidad() {
        return escribirActividadEntidad;
    }

    public void setEscribirActividadEntidad(EscribirActividadEntidad escribirActividadEntidad) {
        this.escribirActividadEntidad = escribirActividadEntidad;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", EscribirEntidad.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("pregunta='" + pregunta + "'")
                .add("escribirActividadEntidad=" + escribirActividadEntidad)
                .toString();
    }
}
