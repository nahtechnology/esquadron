package tecolotl.nucleo.modelo;

import tecolotl.nucleo.persistencia.entidad.PersonaEntidad;
import tecolotl.nucleo.validacion.PersonaNuevaValidacion;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.Objects;
import java.util.StringJoiner;

public class PersonaModelo implements Comparable<PersonaModelo>{

    @NotNull(groups = {PersonaNuevaValidacion.class})
    @Size(max = 40, groups = {PersonaNuevaValidacion.class})
    private String nombre;

    @NotNull(groups = {PersonaNuevaValidacion.class})
    @Size(max = 50, groups = {PersonaNuevaValidacion.class})
    private String apellidoPaterno;

    @NotNull(groups = {PersonaNuevaValidacion.class})
    @Size(max = 50, groups = {PersonaNuevaValidacion.class})
    private String apellidoMaterno;

    @NotNull(groups = {PersonaNuevaValidacion.class})
    @Size(max = 40, groups = {PersonaNuevaValidacion.class})
    private String apodo;

    @NotNull(groups = {PersonaNuevaValidacion.class})
    private byte[] contrasenia;

    public PersonaModelo(){
    }

    public PersonaModelo(PersonaEntidad personaEntidad){
        this.nombre = personaEntidad.getNombre();
        this.apellidoPaterno = personaEntidad.getApellidoPaterno();
        this.apellidoMaterno = personaEntidad.getApellidoMaterno();
        this.apodo = personaEntidad.getApodo();
        this.contrasenia = personaEntidad.getContrasenia();
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

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public byte[] getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(byte[] contrasenia) {
        this.contrasenia = contrasenia;
    }

    @Override
    public int compareTo(PersonaModelo x){
        return nombre.compareTo(x.nombre);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PersonaModelo.class.getSimpleName() + "[", "]")
                .add("nombre='" + nombre + "'")
                .add("apellidoPaterno='" + apellidoPaterno + "'")
                .add("apellidoMaterno='" + apellidoMaterno + "'")
                .add("apodo='" + apodo + "'")
                .add("contrasenia=" + Arrays.toString(contrasenia))
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonaModelo that = (PersonaModelo) o;
        return nombre.equals(that.nombre) &&
                apellidoPaterno.equals(that.apellidoPaterno) &&
                apellidoMaterno.equals(that.apellidoMaterno) &&
                apodo.equals(that.apodo) &&
                Arrays.equals(contrasenia, that.contrasenia);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(nombre, apellidoPaterno, apellidoMaterno, apodo);
        result = 31 * result + Arrays.hashCode(contrasenia);
        return result;
    }
}
