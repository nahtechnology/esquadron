package tecolotl.alumno.entidad;

import tecolotl.nucleo.persistencia.entidad.PersonaEntidad;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(name = "alumno", schema = "alumno")
@NamedQueries({
    @NamedQuery(
        name = "AlumnoEntidad.busca",
        query = "SELECT a FROM AlumnoEntidad a LEFT JOIN FETCH a.nivelLenguajeEntidad nle"),
    @NamedQuery(
        name = "AlumnoEntidad.subeNivel",
        query = "UPDATE AlumnoEntidad a SET a.nivelLenguajeEntidad.clave = a.nivelLenguajeEntidad.clave + :nivel WHERE a.id = :idAlumno"),
    @NamedQuery(
        name = "AlumnoEntidad.buscaId",
        query = "SELECT a FROM AlumnoEntidad a LEFT JOIN FETCH a.nivelLenguajeEntidad nle WHERE a.id = :idAlumno"),
    @NamedQuery(
        name = "AlumnoEntidad.existeApodo",
        query = "SELECT count(a.id) FROM AlumnoEntidad a WHERE a.apodo = :apodo"),
    @NamedQuery(
        name = "AlumnoEntidad.buscaApodo",
        query = "SELECT a FROM AlumnoEntidad a WHERE a.apodo = :apodo"),
    @NamedQuery(
        name = "AlumnoEntidad.cambiaEstatus",
        query = "UPDATE AlumnoEntidad a SET a.estatus = :estatus WHERE a.id = :idAlumno")
})
public class AlumnoEntidad extends PersonaEntidad {

    private UUID id;
    private NivelLenguajeEntidad nivelLenguajeEntidad;
    private Date nacimiento;
    private String correoPadreFamilia;
    private Boolean estatus;
    private byte[] contraseniaPadreFamilia;

    public AlumnoEntidad() {
    }

    public AlumnoEntidad(UUID id) {
        this.id = id;
    }

    public AlumnoEntidad(UUID id, String nombre, String apellidoPaterno, String apellidoMaterno) {
        setId(id);
        setNombre(nombre);
        setApellidoPaterno(apellidoPaterno);
        setApellidoMaterno(apellidoMaterno);
    }

    public AlumnoEntidad(UUID id, String nombre, String apellidoPaterno, String apellidoMaterno, String apodo, byte[] contrasenia) {
        super(nombre, apellidoPaterno, apellidoMaterno, apodo, contrasenia);
        this.id = id;
    }

    @Id
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "id_nivel_lenguaje")
    public NivelLenguajeEntidad getNivelLenguajeEntidad() {
        return nivelLenguajeEntidad;
    }

    public void setNivelLenguajeEntidad(NivelLenguajeEntidad nivelLenguajeEntidad) {
        this.nivelLenguajeEntidad = nivelLenguajeEntidad;
    }

    @Basic
    @Column(name = "nacimiento")
    @NotNull
    @Temporal(TemporalType.DATE)
    public Date getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }

    @Basic
    @Column(name = "correo_padre_familia")
    @NotNull
    @Size(min = 10, max = 100)
    public String getCorreoPadreFamilia() {
        return correoPadreFamilia;
    }

    public void setCorreoPadreFamilia(String correoPadreFamilia) {
        this.correoPadreFamilia = correoPadreFamilia;
    }

    @Basic(fetch =  FetchType.LAZY)
    @Column(name = "contrasenia_padre_familia")
    public byte[] getContraseniaPadreFamilia() {
        return contraseniaPadreFamilia;
    }

    public void setContraseniaPadreFamilia(byte[] contraseniaPadreFamilia) {
        this.contraseniaPadreFamilia = contraseniaPadreFamilia;
    }

    @Basic
    @Column(name = "estatus", insertable = false)
    public Boolean getEstatus() {
        return estatus;
    }

    public void setEstatus(Boolean estatus) {
        this.estatus = estatus;
    }

    @PrePersist
    public void creaUUID(){
        this.id = UUID.randomUUID();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlumnoEntidad that = (AlumnoEntidad) o;
        return id.equals(that.id) &&
                nivelLenguajeEntidad.equals(that.nivelLenguajeEntidad) &&
                nacimiento.equals(that.nacimiento) &&
                correoPadreFamilia.equals(that.correoPadreFamilia) &&
                Arrays.equals(contraseniaPadreFamilia, that.contraseniaPadreFamilia);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, nivelLenguajeEntidad, nacimiento, correoPadreFamilia);
        result = 31 * result + Arrays.hashCode(contraseniaPadreFamilia);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", AlumnoEntidad.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("nivelLenguajeEntidad=" + nivelLenguajeEntidad)
                .add("nacimiento=" + nacimiento)
                .add("correoPadreFamilia='" + correoPadreFamilia + "'")
                .add("contraseniaPadreFamilia=" + Arrays.toString(contraseniaPadreFamilia))
                .add("super=" + super.toString())
                .toString();
    }
}
