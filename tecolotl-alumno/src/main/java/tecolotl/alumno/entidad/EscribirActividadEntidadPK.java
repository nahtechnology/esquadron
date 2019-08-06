package tecolotl.alumno.entidad;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.StringJoiner;

@Embeddable
public class EscribirActividadEntidadPK implements Serializable {
    private EscribirEntidad escribirEntidad;
    private ActividadEntidad actividadEntidad;

    //Modificar Aquí
    public EscribirEntidad getEscribirEntidad() {
        return escribirEntidad;
    }

    public void setEscribirEntidad(EscribirEntidad escribirEntidad) {
        this.escribirEntidad = escribirEntidad;
    }

    public ActividadEntidad getActividadEntidad() {
        return actividadEntidad;
    }

    public void setActividadEntidad(ActividadEntidad actividadEntidad) {
        this.actividadEntidad = actividadEntidad;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", EscribirActividadEntidadPK.class.getSimpleName() + "[", "]")
                .add("escribirEntidad=" + escribirEntidad)
                .add("actividadEntidad=" + actividadEntidad)
                .toString();
    }
}
