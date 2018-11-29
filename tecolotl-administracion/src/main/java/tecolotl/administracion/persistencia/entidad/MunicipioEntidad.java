package tecolotl.administracion.persistencia.entidad;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "municipio", schema = "administracion")
@NamedQuery(name = "MunicipioEntidad.busca", query = "SELECT m FROM MunicipioEntidad m")
public class MunicipioEntidad {

    private int id;
    private String nombre;
    private EstadoEntidad estado;

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "nombre")
    @NotNull
    @Size(min = 3, max = 50)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_estado", referencedColumnName = "id")
    public EstadoEntidad getEstado() {
        return estado;
    }

    public void setEstado(EstadoEntidad estado) {
        this.estado = estado;
    }
}
