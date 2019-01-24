package tecolotl.administracion.persistencia.entidad;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "colonia", schema = "administracion")
@NamedQueries({
	@NamedQuery(name = "ColoniaEntidad.buscaCodigoPostal", query = "SELECT c FROM ColoniaEntidad c WHERE c.codigoPostal=:codigoPostal ORDER BY c.nombre" ),
    @NamedQuery(name = "ColoniaEntidad.buscaMunicipoCodigoPostal", query = "SELECT m FROM ColoniaEntidad c LEFT JOIN c.municipio m WHERE c.codigoPostal = :codigoPostal")
})
public class ColoniaEntidad {

    private Integer id;
    private String codigoPostal;
    private String nombre;
    private MunicipioEntidad municipio;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
}
