package tecolotl.administracion.persistencia.vista;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Immutable
@Table(schema = "administracion", name = "alumno_escuela")
@NamedQuery(
        name = "AlumnoEscuelaVista.buscaPorEscuela",
        query = "SELECT a FROM AlumnoEscuelaVista a WHERE a.idEscuela = :idEscuela"
)
public class AlumnoEscuelaVista implements Serializable {

    private String idGrupo;
    private Short grado;
    private String grupo;
    private String idEscuela;
    private String idAlumno;
    private String nombre;
    private String apellidPaterno;
    private String apellidoMaterno;
    private Boolean estatus;

    @Id
    @Column(name = "id_grupo")
    public String getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(String idGrupo) {
        this.idGrupo = idGrupo;
    }

    public Short getGrado() {
        return grado;
    }

    @Column(name = "id_escuela")
    public String getIdEscuela() {
        return idEscuela;
    }

    public void setIdEscuela(String idEscuela) {
        this.idEscuela = idEscuela;
    }

    public void setGrado(Short grado) {
        this.grado = grado;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    @Id
    @Column(name = "id_alumno")
    public String getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(String idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(name = "apellido_paterno")
    public String getApellidPaterno() {
        return apellidPaterno;
    }

    public void setApellidPaterno(String apellidPaterno) {
        this.apellidPaterno = apellidPaterno;
    }

    @Column(name = "apellido_materno")
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public Boolean getEstatus() {
        return estatus;
    }

    public void setEstatus(Boolean estatus) {
        this.estatus = estatus;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AlumnoEscuelaVista{");
        sb.append("idGrupo='").append(idGrupo).append('\'');
        sb.append(", grado=").append(grado);
        sb.append(", grupo='").append(grupo).append('\'');
        sb.append(", idEscuela='").append(idEscuela).append('\'');
        sb.append(", idAlumno='").append(idAlumno).append('\'');
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append(", apellidPaterno='").append(apellidPaterno).append('\'');
        sb.append(", apellidoMaterno='").append(apellidoMaterno).append('\'');
        sb.append(", estatus=").append(estatus);
        sb.append('}');
        return sb.toString();
    }
}
