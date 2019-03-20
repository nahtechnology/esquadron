package tecolotl.administracion.modelo;

import java.util.Objects;

public class CoordinadorModelo {

    private String claveCentroTrabajo;
    private String nickname;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String correoElectronico;
    private String contrasenia;

    public CoordinadorModelo() {
    }

    public CoordinadorModelo(String claveCentroTrabajo, String nickname) {
        this.claveCentroTrabajo = claveCentroTrabajo;
        this.nickname = nickname;
    }

    public String getClaveCentroTrabajo() {
        return claveCentroTrabajo;
    }

    public void setClaveCentroTrabajo(String claveCentroTrabajo) {
        this.claveCentroTrabajo = claveCentroTrabajo;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

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
        CoordinadorModelo that = (CoordinadorModelo) o;
        return claveCentroTrabajo.equals(that.claveCentroTrabajo) &&
                nickname.equals(that.nickname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(claveCentroTrabajo, nickname);
    }
}
