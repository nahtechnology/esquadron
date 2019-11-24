package tecolotl.administracion.persistencia.entidad;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.StringJoiner;

@Entity
@Table(name = "colonia", schema = "administracion")
@SequenceGenerator(name = "generador_colonia", schema = "administracion", sequenceName = "colonia_seq", allocationSize = 1)
@NamedQueries({
	@NamedQuery(
        name = "ColoniaEntidad.buscaMunicipioEstado",
        query = "SELECT c FROM ColoniaEntidad c LEFT JOIN FETCH c.municipio m LEFT JOIN FETCH m.estado WHERE c.id=:id ORDER BY c.nombre"
    ),
    @NamedQuery(
        name = "ColoniaEntidad.buscaCodigoPostal",
        query = "SELECT c FROM ColoniaEntidad c WHERE c.codigoPostal =:codigoPostal"
    )
})
public class ColoniaEntidad {

    private Integer id;
    private String codigoPostal;
    private String nombre;
    private MunicipioEntidad municipio;

    public ColoniaEntidad() {
    }

    public ColoniaEntidad(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "generador_colonia")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "codigo_postal")
    @NotNull
    @Size(min = 4, max = 5)
    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    @Basic
    @Column(name = "nombre")
    @NotNull
    @Size(min = 3, max = 70)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_municipio")
    public MunicipioEntidad getMunicipio() {
        return municipio;
    }

    public void setMunicipio(MunicipioEntidad municipio) {
        this.municipio = municipio;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ColoniaEntidad.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("codigoPostal='" + codigoPostal + "'")
                .add("nombre='" + nombre + "'")
                .add("municipio=" + municipio)
                .toString();
    }
}
