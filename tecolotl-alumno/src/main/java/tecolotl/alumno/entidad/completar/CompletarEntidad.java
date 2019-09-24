package tecolotl.alumno.entidad.completar;

import tecolotl.alumno.entidad.ActividadEntidad;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.StringJoiner;

@Entity
@Table(name= "completar", schema = "alumno")
@SequenceGenerator(name = "generador_automatico", sequenceName = "completar_seq", schema = "alumno")
@NamedQueries(
        @NamedQuery(name = "CompletarEntidad.busca", query = "SELECT ce FROM CompletarEntidad ce")
)
public class CompletarEntidad {
    private Integer id;
    private String oracion;
    private Short cardinalidad;
    private ActividadEntidad actividadEntidad;

    public CompletarEntidad() {
    }

    public CompletarEntidad(Integer id) {
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

    @NotNull
    @Basic
    @Column(name = "oracion")
    public String getOracion() {
        return oracion;
    }

    public void setOracion(String oracion) {
        this.oracion = oracion;
    }

    @NotNull
    @Column(name = "cardinalidad")
    public Short getCardinalidad() {
        return cardinalidad;
    }

    public void setCardinalidad(Short cardinalidad) {
        this.cardinalidad = cardinalidad;
    }

    @JoinColumn(name = "id_actividad")
    @ManyToOne(fetch = FetchType.LAZY)
    public ActividadEntidad getActividadEntidad() {
        return actividadEntidad;
    }

    public void setActividadEntidad(ActividadEntidad actividadEntidad) {
        this.actividadEntidad = actividadEntidad;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CompletarEntidad.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("oracion='" + oracion + "'")
                .add("cardinalidad=" + cardinalidad)
                .add("actividadEntidad=" + actividadEntidad)
                .toString();
    }
}
