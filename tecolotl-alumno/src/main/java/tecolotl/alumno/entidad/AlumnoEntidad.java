package tecolotl.alumno.entidad;

import tecolotl.nucleo.persistencia.entidad.PersonaEntidad;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "alumno", schema = "alumno")
@SequenceGenerator(name = "generador_automatico", schema = "alumno", sequenceName = "alumno.alumno_seq")
@NamedQueries({
        @NamedQuery(name = "AlumnoEntidad.busca", query = "SELECT a FROM AlumnoEntidad a LEFT JOIN FETCH a.nivelLenguajeEntidad b")

})
public class AlumnoEntidad extends PersonaEntidad {
    private Integer id;
    private NivelLenguajeEntidad nivelLenguajeEntidad;
    private List<GradoEscolarEntidad> gradoEscolarEntidad;
    private Date nacimiento;
    private String correoPadreFamilia;
    private byte[] contraseniaPadreFamilia;

    @Id
    @GeneratedValue
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

    @OneToMany
    @JoinColumn(name = "id_grado_escolar")
    public List<GradoEscolarEntidad> getGradoEscolarEntidad() {
        return gradoEscolarEntidad;
    }

    public void setGradoEscolarEntidad(List<GradoEscolarEntidad> gradoEscolarEntidad) {
        this.gradoEscolarEntidad = gradoEscolarEntidad;
    }
    @Basic
    @Column(name = "nacimiento")
    @NotNull
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
}
