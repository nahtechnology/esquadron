package tecolotl.alumno.entidad.escribir;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.StringJoiner;

@Entity
@Table(name = "escribir", schema = "alumno")
@SequenceGenerator(name = "generador", schema = "alumnos", sequenceName = "escribir_seq")
@NamedQueries({
        @NamedQuery(
                name = "EscribirEntidad.buscaPorActivdad",
                query = "SELECT e FROM EscribirEntidad e JOIN e.escribirActividadEntidadLista ea WHERE ea.actividadEntidad.id = :idActividad")
})
public class EscribirEntidad {

    private Integer id;
    private String pregunta;
    private List<EscribirActividadEntidad> escribirActividadEntidadLista;

    public EscribirEntidad() {
    }

    public EscribirEntidad(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(generator = "generador", strategy = GenerationType.SEQUENCE)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "pregunta")
    @NotNull
    @Size(min = 2, max = 200)
    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    @OneToMany(mappedBy = "escribirEntidad")
    public List<EscribirActividadEntidad> getEscribirActividadEntidadLista() {
        return escribirActividadEntidadLista;
    }

    public void setEscribirActividadEntidadLista(List<EscribirActividadEntidad> escribirActividadEntidadLista) {
        this.escribirActividadEntidadLista = escribirActividadEntidadLista;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", EscribirEntidad.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("pregunta='" + pregunta + "'")
                .toString();
    }
}
