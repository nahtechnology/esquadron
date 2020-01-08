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

    @NotNull(groups = {PersonaNuevaValidacion.class})
    private Character sexo;

    public PersonaModelo(){
    }

    public PersonaModelo(PersonaEntidad personaEntidad){
        this.nombre = personaEntidad.getNombre();
        this.apellidoPaterno = personaEntidad.getApellidoPaterno();
        this.apellidoMaterno = personaEntidad.getApellidoMaterno();
        this.contrasenia = personaEntidad.getContrasenia();
        this.apodo = personaEntidad.getApodo();
        this.sexo = personaEntidad.getSexo();
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

    public Character getSexo() {
        return sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    @Override
    public int compareTo(PersonaModelo personaModelo){
        return nombre.compareTo(personaModelo.nombre);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonaModelo that = (PersonaModelo) o;
        return Objects.equals(nombre, that.nombre) &&
                Objects.equals(apellidoPaterno, that.apellidoPaterno) &&
                Objects.equals(apellidoMaterno, that.apellidoMaterno) &&
                Objects.equals(apodo, that.apodo) &&
                Arrays.equals(contrasenia, that.contrasenia) &&
                Objects.equals(sexo, that.sexo);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(nombre, apellidoPaterno, apellidoMaterno, apodo, sexo);
        result = 31 * result + Arrays.hashCode(contrasenia);
        return result;
    }

    @Override
    public String toString() {
        return "PersonaModelo{" +
                "nombre='" + nombre + '\'' +
                ", apellidoPaterno='" + apellidoPaterno + '\'' +
                ", apellidoMaterno='" + apellidoMaterno + '\'' +
                ", apodo='" + apodo + '\'' +
                ", contrasenia=" + Arrays.toString(contrasenia) +
                ", sexo=" + sexo +
                '}';
    }
}
