package tecolotl.alumno.entidad;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

@Entity
@Table(name = "glosario", schema = "alumno")
@NamedQueries({
        @NamedQuery(name = "GlosarioEntidad.busca", query = "SELECT g FROM GlosarioEntidad g")
})
public class GlosarioEntidad {
    private ClaseGlosarioEntidad claseGlosarioEntidad;
    private String palabra;
    private byte[] imagen;
    private String significado;

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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_clase_glosario")
    @NotNull
    public ClaseGlosarioEntidad getClaseGlosarioEntidad() {
        return claseGlosarioEntidad;
    }

    public void setClaseGlosarioEntidad(ClaseGlosarioEntidad claseGlosarioEntidad) {
        this.claseGlosarioEntidad = claseGlosarioEntidad;
    }


    @Override
    public String toString() {
        return new StringJoiner(", ", GlosarioEntidad.class.getSimpleName() + "[", "]")
                .add("claseGlosarioEntidad=" + claseGlosarioEntidad)
                .add("palabra='" + palabra + "'")
                .add("imagen=" + Arrays.toString(imagen))
                .add("significado='" + significado + "'")
                .toString();
    }
}
