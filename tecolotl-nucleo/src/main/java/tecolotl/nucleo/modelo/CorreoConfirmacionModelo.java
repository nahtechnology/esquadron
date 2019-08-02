package tecolotl.nucleo.modelo;

import javax.validation.constraints.NotNull;
import java.util.StringJoiner;

public class CorreoConfirmacionModelo {

    private String nombre;
    private String correo;
    private String apodo;
    private String contrasenia;

    @NotNull
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @NotNull
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @NotNull
    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    @NotNull
    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CorreoConfirmacionModelo.class.getSimpleName() + "[", "]")
                .add("nombre='" + nombre + "'")
                .add("correo='" + correo + "'")
                .add("apodo='" + apodo + "'")
                .add("contrasenia='" + contrasenia + "'")
                .toString();
    }
}
