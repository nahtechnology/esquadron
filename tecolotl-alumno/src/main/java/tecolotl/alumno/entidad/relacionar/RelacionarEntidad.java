package tecolotl.alumno.entidad.relacionar;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "relacionar", schema = "alumno")
@NamedQueries(value = {
        @NamedQuery(name = "RelacionarEntidad.busca", query = "SELECT r FROM RelacionarEntidad r"),
        @NamedQuery(
                name = "RelacionarEntidad.buscaNoImagen",
                query = "SELECT new RelacionarEntidad(r.codigo, r.palabra) FROM RelacionarEntidad r"
        ),
        @NamedQuery(
                name = "RelacionarEntidad.buscaNoPalabra",
                query = "SELECT new RelacionarEntidad(r.codigo, r.imagen) FROM RelacionarEntidad r"
        )
})
public class RelacionarEntidad {

    private String codigo;
    private String palabra;
    private byte[] imagen;

    public RelacionarEntidad() {
    }

    public RelacionarEntidad(String codigo) {
        this.codigo = codigo;
    }

    public RelacionarEntidad(String codigo, String palabra) {
        this.codigo = codigo;
        this.palabra = palabra;
    }

    public RelacionarEntidad(String codigo, byte[] imagen) {
        this.codigo = codigo;
        this.imagen = imagen;
    }

    @Id
    @Column(name = "codigo", insertable = false, updatable = false)
    @Size(min = 32, max = 32)
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Basic
    @Column(name = "palabra")
    @NotNull
    @Size(min = 2, max = 25)
    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "imagen")
    @NotNull
    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RelacionarEntidad that = (RelacionarEntidad) o;
        return codigo.equals(that.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", RelacionarEntidad.class.getSimpleName() + "[", "]")
                .add("codigo='" + codigo + "'")
                .add("palabra='" + palabra + "'")
                .add("imagen=" + Arrays.toString(imagen))
                .toString();
    }
}
