package tecolotl.alumno.entidad.hablar;

import tecolotl.alumno.entidad.ActividadEntidad;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "hablar", schema = "alumno")
@SequenceGenerator(name = "generador", sequenceName = "hablar_seq", schema = "alumno")
@NamedQueries(value = {
        @NamedQuery(name = "HablarEntidad.busca", query = "SELECT h FROM HablarEntidad h")
})
public class HablarEntidad {

    private Integer id;
    private String tarjeta;
    private ActividadEntidad actividadEntidad;

    @Id
    @GeneratedValue(generator = "generador", strategy = GenerationType.SEQUENCE)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "tarjeta")
    @NotNull
    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_actividad")
    public ActividadEntidad getActividadEntidad() {
        return actividadEntidad;
    }

    public void setActividadEntidad(ActividadEntidad actividadEntidad) {
        this.actividadEntidad = actividadEntidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HablarEntidad that = (HablarEntidad) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
