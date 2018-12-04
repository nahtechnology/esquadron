package tecolotl.nucleo.persistencia.entidad;

import javax.persistence.*;

@Entity
@Table(name = "administrador", schema = "nucleo")
@NamedQuery(name = "AdministradorEntidad.buscaApodo", query = "SELECT a FROM AdministradorEntidad a WHERE a.apodo = :apodo")
public class AdministradorEntidad {

    private String apodo;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;

    @Id
    @Column(name = "apodo")
    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    @Basic
    @Column(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "apellido_paterno")
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    @Basic
    @Column(name = "apellido_materno")
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }
}
