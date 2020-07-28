package tecolotl.profesor.entidad;

import tecolotl.administracion.persistencia.entidad.EscuelaEntidad;
import tecolotl.nucleo.persistencia.entidad.PersonaEntidad;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.StringJoiner;
import java.util.UUID;

@Entity
@Table(schema = "profesor", name = "profesor", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"apodo"})
})
@NamedQueries({
    @NamedQuery(name = "ProfesorEntidad.busca", query = "SELECT p FROM ProfesorEntidad p"),
    @NamedQuery(
        name = "ProfesorEntidad.buscaIdEscuela",
        query = "SELECT p FROM ProfesorEntidad p WHERE p.escuelaEntidad.claveCentroTrabajo = :claveCentroTrabajo"
    ),
    @NamedQuery(
        name = "ProfesorEntidad.buscaDetalle" ,
        query = "SELECT p FROM ProfesorEntidad p JOIN FETCH p.grupoEntidadLista ge WHERE p.id = :id"
    ),
    @NamedQuery(
        name = "ProfesorEntidad.buscaTotalEscuela",
        query = "SELECT COUNT (p) FROM ProfesorEntidad p WHERE p.escuelaEntidad.claveCentroTrabajo = :claveCentroTrabajo"
    ),
    @NamedQuery(
        name = "ProfesorEntidad.buscaId",
        query = "SELECT p FROM ProfesorEntidad p JOIN FETCH p.escuelaEntidad e WHERE p.id = :idProfesor"
    ),
    @NamedQuery(
        name = "ProfesorEntidad.buscaApodo",
        query = "select p from ProfesorEntidad p JOIN FETCH p.escuelaEntidad e WHERE p.apodo = :apodo"
    ),
    @NamedQuery(
        name = "ProfesorEntidad.eliminaProfesor",
        query = "select count (g.id) from GrupoEntidad g where g.profesorEntidad.id = :idProfesor"
    ),
        @NamedQuery(
                name = "ProfesorEntidad.buscaApodoGalaxia",
                query = "SELECT p FROM ProfesorEntidad  p join p.escuelaEntidad e WHERE p.apodo = :apodo AND e.galaxia = :galaxia")
})
public class ProfesorEntidad extends PersonaEntidad {

    private UUID id;
    private String correoEletronico;
    private EscuelaEntidad escuelaEntidad;
    private List<GrupoEntidad> grupoEntidadLista;

    public ProfesorEntidad() {
    }

    public ProfesorEntidad(UUID id) {
        this.id = id;
    }

    @Id
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_escuela")
    public EscuelaEntidad getEscuelaEntidad() {
        return escuelaEntidad;
    }

    public void setEscuelaEntidad(EscuelaEntidad escuelaEntidad) {
        this.escuelaEntidad = escuelaEntidad;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "profesorEntidad")
    public List<GrupoEntidad> getGrupoEntidadLista() {
        return grupoEntidadLista;
    }

    public void setGrupoEntidadLista(List<GrupoEntidad> grupoEntidadLista) {
        this.grupoEntidadLista = grupoEntidadLista;
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

    @PrePersist
    public void creaUUID(){
        this.id = UUID.randomUUID();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ProfesorEntidad.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("correoEletronico='" + correoEletronico + "'")
                .add("escuelaEntidad=" + escuelaEntidad)
                .add("grupoEntidadLista=" + grupoEntidadLista)
                .toString();
    }
}
