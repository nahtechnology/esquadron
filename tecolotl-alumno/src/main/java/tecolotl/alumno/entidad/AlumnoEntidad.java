package tecolotl.alumno.entidad;

import tecolotl.nucleo.persistencia.entidad.PersonaEntidad;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(name = "alumno", schema = "alumno")
@SequenceGenerator(name = "generador_automatico", schema = "alumno", sequenceName = "alumno.alumno_seq")
@NamedQueries({
        @NamedQuery(name = "AlumnoEntidad.busca", query = "SELECT a FROM AlumnoEntidad a LEFT JOIN FETCH a.nivelLenguajeEntidad nle LEFT JOIN FETCH a.gradoEscolarEntidad gee"),
        @NamedQuery(name = "AlumnoEntidad.actualizaNivel", query = "UPDATE AlumnoEntidad a SET a.nivelLenguajeEntidad.valor =:valor WHERE a.id = :id"),
})
public class AlumnoEntidad extends PersonaEntidad {

    private Integer id;
    private NivelLenguajeEntidad nivelLenguajeEntidad;
    private GradoEscolarEntidad gradoEscolarEntidad;
    private Date nacimiento;
    private String correoPadreFamilia;
    private byte[] contraseniaPadreFamilia;


    public AlumnoEntidad() {
    }

    public AlumnoEntidad(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(generator = "generador_automatico")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @OneToOne
    @JoinColumn(name = "id_nivel_lenguaje")
    public NivelLenguajeEntidad getNivelLenguajeEntidad() {
        return nivelLenguajeEntidad;
    }

    public void setNivelLenguajeEntidad(NivelLenguajeEntidad nivelLenguajeEntidad) {
        this.nivelLenguajeEntidad = nivelLenguajeEntidad;
    }

    @ManyToOne
    @JoinColumn(name = "id_grado_escolar")
    public GradoEscolarEntidad getGradoEscolarEntidad() {
        return gradoEscolarEntidad;
    }

    public void setGradoEscolarEntidad(GradoEscolarEntidad gradoEscolarEntidad) {
        this.gradoEscolarEntidad = gradoEscolarEntidad;
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

    @Basic
    @Column(name = "contrasenia_padre_familia")
    public byte[] getContraseniaPadreFamilia() {
        return contraseniaPadreFamilia;
    }

    public void setContraseniaPadreFamilia(byte[] contraseniaPadreFamilia) {
        this.contraseniaPadreFamilia = contraseniaPadreFamilia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlumnoEntidad that = (AlumnoEntidad) o;
        return id.equals(that.id) &&
                nivelLenguajeEntidad.equals(that.nivelLenguajeEntidad) &&
                gradoEscolarEntidad.equals(that.gradoEscolarEntidad) &&
                nacimiento.equals(that.nacimiento) &&
                correoPadreFamilia.equals(that.correoPadreFamilia) &&
                Arrays.equals(contraseniaPadreFamilia, that.contraseniaPadreFamilia);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, nivelLenguajeEntidad, gradoEscolarEntidad, nacimiento, correoPadreFamilia);
        result = 31 * result + Arrays.hashCode(contraseniaPadreFamilia);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", AlumnoEntidad.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("nivelLenguajeEntidad=" + nivelLenguajeEntidad)
                .add("gradoEscolarEntidad=" + gradoEscolarEntidad)
                .add("nacimiento=" + nacimiento)
                .add("correoPadreFamilia='" + correoPadreFamilia + "'")
                .add("contraseniaPadreFamilia=" + Arrays.toString(contraseniaPadreFamilia))
                .toString();
    }
}
