package tecolotl.administracion.modelo.coordinador;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CoordinadorModelo {

    private String claveCentroTrabajo;
    private Short contador;
    private String correoEletronico;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String apodo;
    private byte[] contrasenia;

    @NotNull
    @Size(min = 10, max = 14)
    public String getClaveCentroTrabajo() {
        return claveCentroTrabajo;
    }

    public void setClaveCentroTrabajo(String claveCentroTrabajo) {
        this.claveCentroTrabajo = claveCentroTrabajo;
    }

    @NotNull
    @Min(1)
    public Short getContador() {
        return contador;
    }

    public void setContador(Short contador) {
        this.contador = contador;
    }

    @NotNull
    @Size(max = 100, min = 3)
    public String getCorreoEletronico() {
        return correoEletronico;
    }

    public void setCorreoEletronico(String correoEletronico) {
        this.correoEletronico = correoEletronico;
    }

    @Size(min = 3, max = 40)
    @NotNull
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Size(min = 4, max = 50)
    @NotNull
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    @Size(min = 4, max = 50)
    @NotNull
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    @Size(min = 4, max = 50)
    @NotNull
    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    @NotNull
    public byte[] getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(byte[] contrasenia) {
        this.contrasenia = contrasenia;
    }
}
