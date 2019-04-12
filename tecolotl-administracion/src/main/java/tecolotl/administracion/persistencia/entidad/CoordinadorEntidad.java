package tecolotl.administracion.persistencia.entidad;

import tecolotl.nucleo.persistencia.entidad.PersonaEntidad;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "coordinador", schema = "administracion")
@NamedQueries({
        @NamedQuery(
                name = "CoordinadorEntidad.busca",
                query = "SELECT c FROM CoordinadorEntidad c "
        ),
        @NamedQuery(
                name = "CoordinadorEntidad.buscaEscuela",
                query = "SELECT c FROM CoordinadorEntidad c WHERE c.coordinadorEntidadPK.escuelaEntidad.claveCentroTrabajo = :claveCentroTrabajo"
        )
})
public class CoordinadorEntidad extends PersonaEntidad {

    private CoordinadorEntidadPK coordinadorEntidadPK;
    private String correoEletronico;

    @EmbeddedId
    public CoordinadorEntidadPK getCoordinadorEntidadPK() {
        return coordinadorEntidadPK;
    }

    public void setCoordinadorEntidadPK(CoordinadorEntidadPK coordinadorEntidadPK) {
        this.coordinadorEntidadPK = coordinadorEntidadPK;
    }

    @Basic
    @Column(name = "correo_electronico")
    @NotNull
    @Size(max = 100)
    public String getCorreoEletronico() {
        return correoEletronico;
    }

    public void setCorreoEletronico(String correoEletronico) {
        this.correoEletronico = correoEletronico;
    }
}
