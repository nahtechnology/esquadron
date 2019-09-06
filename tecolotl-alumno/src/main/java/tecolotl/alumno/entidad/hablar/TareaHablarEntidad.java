package tecolotl.alumno.entidad.hablar;

import javax.persistence.*;
import java.util.StringJoiner;

@Entity
@Table(name = "tarea_hablar", schema = "alumno")
@NamedQueries({
        @NamedQuery(name = "TareaHablarEntidad.busca", query = "SELECT the FROm TareaHablarEntidad the"),
        @NamedQuery(name = "TareaHablarEntidad.buscaid_tarea", query = "SELECT the FROM TareaHablarEntidad the WHERE the.tareaHablarEntidadPK.tareaEntidad.id = :id_tarea")
}
)
public class TareaHablarEntidad {
    private TareaHablarEntidadPK tareaHablarEntidadPK;

    public TareaHablarEntidad() {
    }

    public TareaHablarEntidad(TareaHablarEntidadPK tareaHablarEntidadPK) {
        this.tareaHablarEntidadPK = tareaHablarEntidadPK;
    }

    @EmbeddedId
    public TareaHablarEntidadPK getTareaHablarEntidadPK() {
        return tareaHablarEntidadPK;
    }

    public void setTareaHablarEntidadPK(TareaHablarEntidadPK tareaHablarEntidadPK) {
        this.tareaHablarEntidadPK = tareaHablarEntidadPK;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TareaHablarEntidad.class.getSimpleName() + "[", "]")
                .add("tareaHablarEntidadPK=" + tareaHablarEntidadPK)
                .toString();
    }
}
