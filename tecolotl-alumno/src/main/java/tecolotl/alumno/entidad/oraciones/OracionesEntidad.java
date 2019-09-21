package tecolotl.alumno.entidad.oraciones;


import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.StringJoiner;

@Entity
@Table(name = "oraciones", schema = "alumno")
@NamedQueries(
        value = {
                @NamedQuery(name = "OracionesEntidad.busca", query = "SELECT oe from OracionesEntidad oe")
        }
)
public class OracionesEntidad {
    private OracionesEntidadPK oracionesEntidadPK;
    private String oracion;

    public OracionesEntidad() {
    }

    public OracionesEntidad(OracionesEntidadPK oracionesEntidadPK) {
        this.oracionesEntidadPK = oracionesEntidadPK;
    }

    @EmbeddedId
    public OracionesEntidadPK getOracionesEntidadPK() {
        return oracionesEntidadPK;
    }

    public void setOracionesEntidadPK(OracionesEntidadPK oracionesEntidadPK) {
        this.oracionesEntidadPK = oracionesEntidadPK;
    }

    @NotNull
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "oracion")
    public String getOracion() {
        return oracion;
    }

    public void setOracion(String oracion) {
        this.oracion = oracion;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", OracionesEntidad.class.getSimpleName() + "[", "]")
                .add("oracionesEntidadPK=" + oracionesEntidadPK)
                .add("oracion='" + oracion + "'")
                .toString();
    }
}
