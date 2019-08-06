package tecolotl.alumno.entidad;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class EscribirActividadEntidad {
    private EscribirActividadEntidadPK escribirActividadEntidadPK;
    private TareaEscribirActividadEntidad tareaEscribirActividadEntidad;

    @EmbeddedId
    public EscribirActividadEntidadPK getEscribirActividadEntidadPK() {
        return escribirActividadEntidadPK;
    }

    public void setEscribirActividadEntidadPK(EscribirActividadEntidadPK escribirActividadEntidadPK) {
        this.escribirActividadEntidadPK = escribirActividadEntidadPK;
    }
}
