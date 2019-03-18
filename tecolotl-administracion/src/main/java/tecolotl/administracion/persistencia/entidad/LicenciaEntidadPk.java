package tecolotl.administracion.persistencia.entidad;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class LicenciaEntidadPk implements Serializable {

    private Short contador;
    private EscuelaEntidad escuelaEntidad;

    @Basic
    @Column(name = "contador")
    public Short getContador() {
        return contador;
    }

    public void setContador(Short contador) {
        this.contador = contador;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_escuela", columnDefinition = "clave_centro_trabajo")
    public EscuelaEntidad getEscuelaEntidad() {
        return escuelaEntidad;
    }

    public void setEscuelaEntidad(EscuelaEntidad escuelaEntidad) {
        this.escuelaEntidad = escuelaEntidad;
    }
}
