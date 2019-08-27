package tecolotl.alumno.entidad.glosario;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

@Entity
@Table(name = "glosario", schema = "alumno")
@NamedQueries({
        @NamedQuery(name = "GlosarioEntidad.busca", query = "SELECT g FROM GlosarioEntidad g JOIN FETCH g.glosarioEntidadPK.claseGlosarioEntidad cg")
})
public class GlosarioEntidad {

    private GlosarioEntidadPK glosarioEntidadPK;
    private byte[] imagen;
    private String significado;
    private List<GlosarioActividadEntidad> glosarioActividadEntidadLista;

    public GlosarioEntidad() {
    }

    public GlosarioEntidad(GlosarioEntidadPK glosarioEntidadPK) {
        this.glosarioEntidadPK = glosarioEntidadPK;
    }

    @EmbeddedId
    public GlosarioEntidadPK getGlosarioEntidadPK() {
        return glosarioEntidadPK;
    }

    public void setGlosarioEntidadPK(GlosarioEntidadPK glosarioEntidadPK) {
        this.glosarioEntidadPK = glosarioEntidadPK;
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

    @OneToMany(mappedBy = "glosarioActividadEntidadPK.glosarioEntidad", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    public List<GlosarioActividadEntidad> getGlosarioActividadEntidadLista() {
        return glosarioActividadEntidadLista;
    }

    public void setGlosarioActividadEntidadLista(List<GlosarioActividadEntidad> glosarioActividadEntidadLista) {
        this.glosarioActividadEntidadLista = glosarioActividadEntidadLista;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", GlosarioEntidad.class.getSimpleName() + "[", "]")
                .add("glosarioEntidad=" + glosarioEntidadPK.toString())
                .add("palabra='" + glosarioEntidadPK + "'")
                .add("imagen=" + Arrays.toString(imagen))
                .add("significado='" + significado + "'")
                .toString();
    }
}
