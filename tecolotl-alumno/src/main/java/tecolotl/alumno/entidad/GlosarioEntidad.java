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
        @NamedQuery(name = "GlosarioEntidad.busca", query = "SELECT a FROM GlosarioEntidad a")
})
public class GlosarioEntidad {

    private String palabra;
    private byte[] imagen;
    private String significado;
    private List<AlumnoEntidad> alumnoEntidad;

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

    //Modificar aquí
    @OneToMany
    @JoinColumn(name = "significado")
    @NotNull
    public List<AlumnoEntidad> getAlumnoEntidad() {
        return alumnoEntidad;
    }

    public void setAlumnoEntidad(List<AlumnoEntidad> alumnoEntidad) {
        this.alumnoEntidad = alumnoEntidad;
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

    @Override
    public String toString() {
        return new StringJoiner(", ", GlosarioEntidad.class.getSimpleName() + "[", "]")
                .add("palabra='" + palabra + "'")
                .add("imagen=" + Arrays.toString(imagen))
                .add("significado='" + significado + "'")
                .add("alumnoEntidad=" + alumnoEntidad)
                .toString();
    }
}
