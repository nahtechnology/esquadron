package tecolotl.alumno.entidad;

import javax.persistence.*;
import java.util.StringJoiner;

@Entity
@Table(name = "glosario_actividad", schema = "alumno")
@NamedQueries(
        @NamedQuery(name="GlosarioActividadEntidad.busca", query= "SELECT ga FROM GlosarioActividadEntidad ga")
)
public class GlosarioActividadEntidad {

    private GlosarioActividadEntidadPK glosarioActividadEntidadPK;

    @EmbeddedId
    public GlosarioActividadEntidadPK getGlosarioActividadEntidadPK() {
        return glosarioActividadEntidadPK;
    }

    public void setGlosarioActividadEntidadPK(GlosarioActividadEntidadPK glosarioActividadEntidadPK) {
        this.glosarioActividadEntidadPK = glosarioActividadEntidadPK;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", GlosarioActividadEntidad.class.getSimpleName() + "[", "]")
                .add("glosarioActividadEntidadPK=" + glosarioActividadEntidadPK)
                .toString();
    }
}
