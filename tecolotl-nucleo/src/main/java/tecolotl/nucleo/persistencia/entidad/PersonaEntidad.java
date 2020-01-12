package tecolotl.nucleo.persistencia.entidad;

import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@MappedSuperclass
public class PersonaEntidad {

    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String apodo;
    private byte[] contrasenia;
    private Character sexo;

    public PersonaEntidad() {
    }

    public PersonaEntidad(String nombre, String apellidoPaterno, String apellidoMaterno, String apodo, byte[] contrasenia) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.apodo = apodo;
        this.contrasenia = contrasenia;
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
    @Size(max = 50)
    @NotNull
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    @Basic
    @Column(name = "apellido_materno")
    @Size(max = 50)
    @NotNull
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    @Basic
    @Column(name = "apodo")
    @Size(min = 4, max = 50)
    @NotNull
    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    @Basic
    @Column(name = "contrasenia")
    @ColumnTransformer(
            read = "pgp_sym_decrypt_bytea(contrasenia, 'sad7f65as7df6sa87f8asd76f87ads6fa98', 'compress-algo=0, cipher-algo=aes256')",
            write = "pgp_sym_encrypt_bytea(?, 'sad7f65as7df6sa87f8asd76f87ads6fa98', 'compress-algo=0, cipher-algo=aes256')"
    )
    @NotNull
    public byte[] getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(byte[] contrasenia) {
        this.contrasenia = contrasenia;
    }

    @Basic
    @Column(name = "sexo")
    @NotNull
    public Character getSexo() {return sexo;}

    public void setSexo(Character sexo) {this.sexo = sexo;}
}
