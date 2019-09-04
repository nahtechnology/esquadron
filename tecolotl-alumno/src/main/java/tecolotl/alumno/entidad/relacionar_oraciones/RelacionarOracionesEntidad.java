package tecolotl.alumno.entidad.relacionar_oraciones;

import tecolotl.alumno.entidad.ActividadEntidad;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.StringJoiner;

@Entity
@Table(name = "relacionar_oracion", schema = "alumno")
@SequenceGenerator(name = "generador_automatico", sequenceName = "relacionar_oracion_seq", schema = "alumno")
@NamedQueries(
        @NamedQuery(name = "RelacionarOracionesEntidad.busca", query = "SELECT roe FROM RelacionarOracionesEntidad roe")
)
public class RelacionarOracionesEntidad {
    private Integer id;
    private ActividadEntidad actividadEntidad;
    private String pregunta;
    private String respuesta;

    public RelacionarOracionesEntidad() {
    }

    public RelacionarOracionesEntidad(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generador_automatico")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JoinColumn(name = "id_actividad")
    @ManyToOne(fetch = FetchType.LAZY)
    public ActividadEntidad getActividadEntidad() {
        return actividadEntidad;
    }

    public void setActividadEntidad(ActividadEntidad actividadEntidad) {
        this.actividadEntidad = actividadEntidad;
    }

    @NotNull
    @Size(max = 100)
    @Column(name = "pregunta")
    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    @NotNull
    @Size(max = 100)
    @Column(name = "respuesta")
    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", RelacionarOracionesEntidad.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("actividadEntidad=" + actividadEntidad)
                .add("pregunta='" + pregunta + "'")
                .add("respuesta='" + respuesta + "'")
                .toString();
    }
}
