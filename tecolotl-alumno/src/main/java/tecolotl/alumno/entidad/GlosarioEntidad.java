package tecolotl.alumno.entidad;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "glosario", schema = "alumno")
@NamedQueries({
        @NamedQuery(name = "GlosarioEntidad.busca", query = "SELECT a FROM GlosarioEntidad a")
})
public class GlosarioEntidad {
    private String palabra;
    private byte[] imagen;
    private String significado;
    private List<ActividadEntidad> actividadEntidad;

    @Id
    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    @Basic
    @Column(name = "imagen")
    @NotNull
    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    @Basic
    @Column(name = "significado")
    @NotNull
    @Size(min = 2, max = 100)
    public String getSignificado() {
        return significado;
    }

    public void setSignificado(String significado) {
        this.significado = significado;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "glosario_actividad", schema = "alumno",
            joinColumns = @JoinColumn(name = "id_glosario"),
            inverseJoinColumns = @JoinColumn(name = "id_actividad")
    )
    public List<ActividadEntidad> getActividadEntidad() {
        return actividadEntidad;
    }

    public void setActividadEntidad(List<ActividadEntidad> actividadEntidad) {
        this.actividadEntidad = actividadEntidad;
    }
}
