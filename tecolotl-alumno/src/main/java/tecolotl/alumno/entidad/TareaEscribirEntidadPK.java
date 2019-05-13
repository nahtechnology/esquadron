package tecolotl.alumno.entidad;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class TareaEscribirEntidadPK implements Serializable {
    private TareaEntidad tareaEntidad;
    private EscribirEntidad escribirEntidad;
    private Short contadorescribir;

    @OneToOne
    @JoinColumn(name = "id_tarea", referencedColumnName = "id")
    public TareaEntidad getTareaEntidad() {
        return tareaEntidad;
    }

    public void setTareaEntidad(TareaEntidad tareaEntidad) {
        this.tareaEntidad = tareaEntidad;
    }

    @OneToOne
    @JoinColumn(name = "id_escribir", referencedColumnName = "id")
    public EscribirEntidad getEscribirEntidad() {
        return escribirEntidad;
    }

    public void setEscribirEntidad(EscribirEntidad escribirEntidad) {
        this.escribirEntidad = escribirEntidad;
    }

    public Short getContadorescribir() {
        return contadorescribir;
    }

    public void setContadorescribir(Short contadorescribir) {
        this.contadorescribir = contadorescribir;
    }
}
