package tecolotl.administracion.persistencia.entidad;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CoordinadorEntidadPK implements Serializable {

    private EscuelaEntidad escuelaEntidad;
    private String nickname;

    @ManyToOne
    @JoinColumn(name = "escuela_id", referencedColumnName = "clave_centro_trabajo")
    public EscuelaEntidad getEscuelaEntidad() {
        return escuelaEntidad;
    }

    public void setEscuelaEntidad(EscuelaEntidad escuelaEntidad) {
        this.escuelaEntidad = escuelaEntidad;
    }

    @Column(name = "nickname")
    @Size(min = 3, max = 15)
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoordinadorEntidadPK that = (CoordinadorEntidadPK) o;
        return escuelaEntidad.equals(that.escuelaEntidad) &&
                nickname.equals(that.nickname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(escuelaEntidad, nickname);
    }
}
