package tecolotl.administracion.persistencia.entidad;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "colonia", schema = "administracion")
public class ColoniaEntidad {

    private int id;
    private String codigoPostal;
    private String nombre;
    private MunicipioEntidad municipio;

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "codigo_postal")
    @NotNull
    @Size(min = 5, max = 5)
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_municipio")
    public MunicipioEntidad getMunicipio() {
        return municipio;
    }

    public void setMunicipio(MunicipioEntidad municipio) {
        this.municipio = municipio;
    }
}
