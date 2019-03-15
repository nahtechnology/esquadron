package tecolotl.administracion.persistencia.entidad;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "coordinador", schema = "administracion")
@NamedQueries({
        @NamedQuery(name = "CoordinadorEntidad.busca", query = "SELECT c FROM CoordinadorEntidad c"),
        @NamedQuery(
                name = "CoordinadorEntidad.buscaEscuela",
                query = "SELECT c FROM CoordinadorEntidad c LEFT JOIN c.coordinadorEntidadPK.escuelaEntidad e WHERE e.claveCentroTrabajo=:claveCentroTrabajo")
})
public class CoordinadorEntidad {

    private CoordinadorEntidadPK coordinadorEntidadPK;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String correoElectronico;
    private String contrasenia;

    @EmbeddedId
    public CoordinadorEntidadPK getCoordinadorEntidadPK() {
        return coordinadorEntidadPK;
    }

    public void setCoordinadorEntidadPK(CoordinadorEntidadPK coordinadorEntidadPK) {
        this.coordinadorEntidadPK = coordinadorEntidadPK;
    }

    @Basic
    @Column(name = "nombre")
    @Size(min = 3, max = 40)
    @NotNull
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "apellido_paterno")
    @Size(min = 3, max = 50)
    @NotNull
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    @Basic
    @Column(name = "apellido_materno")
    @Size(min = 3, max = 50)
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    @Basic
    @Column(name = "correo_electronico")
    @Size(min = 3, max = 120)
    @NotNull
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    @Basic
    @Column(name = "contrasenia")
    @Size(min = 3, max = 120)
    @NotNull
    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoordinadorEntidad that = (CoordinadorEntidad) o;
        return coordinadorEntidadPK.equals(that.coordinadorEntidadPK);
    }

    @Override
    public int hashCode() {
        return coordinadorEntidadPK.hashCode();
    }
}
