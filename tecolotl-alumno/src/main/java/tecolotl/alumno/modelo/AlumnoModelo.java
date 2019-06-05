package tecolotl.alumno.modelo;

import tecolotl.alumno.entidad.AlumnoEntidad;
import tecolotl.nucleo.modelo.PersonaModelo;
import tecolotl.nucleo.persistencia.entidad.PersonaEntidad;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.StringJoiner;

public class AlumnoModelo extends PersonaModelo {

    @NotNull
    private Integer id;

    @NotNull
    private GradoEscolarModelo gradoEscolarModelo;

    @NotNull
    private NivelLenguajeModelo nivelLenguajeModelo;

    @NotNull
    private Date nacimiento;

    @NotNull
    private String correoPadreFamilia;

    @NotNull
    private String contraseniaPadreFamilia;

    public AlumnoModelo() {
    }

    public AlumnoModelo(PersonaEntidad personaEntidad, Integer id) {
        super(personaEntidad);
        this.id = id;
    }

    public AlumnoModelo(AlumnoEntidad alumnoEntidad) {
        super(alumnoEntidad);
        this.id = alumnoEntidad.getId();
        this.gradoEscolarModelo = new GradoEscolarModelo(alumnoEntidad.getGradoEscolarEntidad());
        this.nivelLenguajeModelo = new NivelLenguajeModelo(alumnoEntidad.getNivelLenguajeEntidad());
        nacimiento = alumnoEntidad.getNacimiento();
        correoPadreFamilia = alumnoEntidad.getCorreoPadreFamilia();
        contraseniaPadreFamilia = alumnoEntidad.getCorreoPadreFamilia();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public GradoEscolarModelo getGradoEscolarModelo() {
        return gradoEscolarModelo;
    }

    public void setGradoEscolarModelo(GradoEscolarModelo gradoEscolarModelo) {
        this.gradoEscolarModelo = gradoEscolarModelo;
    }

    public Date getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getCorreoPadreFamilia() {
        return correoPadreFamilia;
    }

    public void setCorreoPadreFamilia(String correoPadreFamilia) {
        this.correoPadreFamilia = correoPadreFamilia;
    }

    public NivelLenguajeModelo getNivelLenguajeModelo() {
        return nivelLenguajeModelo;
    }

    public void setNivelLenguajeModelo(NivelLenguajeModelo nivelLenguajeModelo) {
        this.nivelLenguajeModelo = nivelLenguajeModelo;
    }

    public String getContraseniaPadreFamilia() {
        return contraseniaPadreFamilia;
    }

    public void setContraseniaPadreFamilia(String contraseniaPadreFamilia) {
        this.contraseniaPadreFamilia = contraseniaPadreFamilia;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", AlumnoModelo.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("gradoEscolarModelo=" + gradoEscolarModelo)
                .add("nivelLenguajeModelo=" + nivelLenguajeModelo)
                .add("nacimiento=" + nacimiento)
                .add("correoPadreFamilia='" + correoPadreFamilia + "'")
                .add("contraseniaPadreFamilia='" + contraseniaPadreFamilia + "'")
                .toString();
    }
}
