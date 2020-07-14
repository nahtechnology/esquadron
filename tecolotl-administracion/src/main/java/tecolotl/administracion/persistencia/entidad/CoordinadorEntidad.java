package tecolotl.administracion.persistencia.entidad;

import tecolotl.nucleo.persistencia.entidad.PersonaEntidad;
import tecolotl.nucleo.persistencia.entidad.PersonaMotivoBloqueoEntidad;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.StringJoiner;

@Entity
@Table(name = "coordinador", schema = "administracion")
@NamedQueries({
        @NamedQuery(
                name = "CoordinadorEntidad.buscaDetalle",
                query = "SELECT c FROM CoordinadorEntidad c LEFT JOIN FETCH c.personaMotivoBloqueoEntidad pmb WHERE c.coordinadorEntidadPK = :coordinadorEntidadPK"
        ),
        @NamedQuery(
                name = "CoordinadorEntidad.buscaEscuela",
                query = "SELECT c FROM CoordinadorEntidad c WHERE c.coordinadorEntidadPK.escuelaEntidad.claveCentroTrabajo = :claveCentroTrabajo ORDER BY c.apellidoPaterno"
        ),
        @NamedQuery(
                name = "CoordinadorEntidad.cuentaPorEscuela",
                query = "SELECT COUNT (c) FROM CoordinadorEntidad c WHERE c.coordinadorEntidadPK.escuelaEntidad.claveCentroTrabajo = :claveCentroTrabajo"
        ),
        @NamedQuery(
                name = "CoordinadorEntidad.buscaApodo",
                query = "SELECT count (c) FROM CoordinadorEntidad c JOIN c.coordinadorEntidadPK.escuelaEntidad e " +
                        "WHERE e.claveCentroTrabajo = :claveCentroTrabajo AND c.apodo = :apodo"
        ),
        @NamedQuery(
                name = "CoordinadorEntidad.detalleApodo",
                query = "SELECT c FROM CoordinadorEntidad c JOIN c.coordinadorEntidadPK.escuelaEntidad e " +
                        "WHERE e.galaxia = :galaxia AND c.apodo = :apodo"
        )
})
public class CoordinadorEntidad extends PersonaEntidad {

    private CoordinadorEntidadPK coordinadorEntidadPK;
    private String correoEletronico;
    private PersonaMotivoBloqueoEntidad personaMotivoBloqueoEntidad;

    public CoordinadorEntidad() {
    }

    public CoordinadorEntidad(CoordinadorEntidadPK coordinadorEntidadPK) {
        this.coordinadorEntidadPK = coordinadorEntidadPK;
    }

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


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_motivo_bloqueo")
    public PersonaMotivoBloqueoEntidad getPersonaMotivoBloqueoEntidad() {
        return personaMotivoBloqueoEntidad;
    }

    public void setPersonaMotivoBloqueoEntidad(PersonaMotivoBloqueoEntidad personaMotivoBloqueoEntidad) {
        this.personaMotivoBloqueoEntidad = personaMotivoBloqueoEntidad;
    }

    @PrePersist
    public void agregaMotivoBloqueo() {
        personaMotivoBloqueoEntidad = new PersonaMotivoBloqueoEntidad((short)1);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CoordinadorEntidad.class.getSimpleName() + "[", "]")
                .add("coordinadorEntidadPK=" + coordinadorEntidadPK)
                .add("correoEletronico='" + correoEletronico + "'")
                .add("personaMotivoBloqueoEntidad=" + personaMotivoBloqueoEntidad)
                .toString();
    }
}
