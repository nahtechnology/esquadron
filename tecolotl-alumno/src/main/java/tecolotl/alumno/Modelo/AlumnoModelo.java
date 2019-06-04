package tecolotl.alumno.Modelo;

import tecolotl.alumno.entidad.AlumnoEntidad;
import tecolotl.nucleo.modelo.PersonaModelo;
import tecolotl.nucleo.persistencia.entidad.PersonaEntidad;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class AlumnoModelo extends PersonaModelo {

    @NotNull
    private Integer id;
    @NotNull
    private List gradoEscolar;
    @NotNull
    private String nivelLenguaje;
    @NotNull
    private Date nacimiento;
    @NotNull
    private String correoPadreFamilia;
    @NotNull
    private byte[] contrasenia;

    public AlumnoModelo() {
    }

    public AlumnoModelo(PersonaEntidad personaEntidad, Integer id) {
        super(personaEntidad);
        this.id = id;
    }

    public AlumnoModelo(AlumnoEntidad alumnoEntidad) {
        this.id = alumnoEntidad.getId();
        this.gradoEscolar = alumnoEntidad.getGradoEscolarEntidad();
        this.nivelLenguaje = alumnoEntidad.getNivelLenguajeEntidad().getValor();

    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List getGradoEscolar() {
        return gradoEscolar;
    }

    public void setGradoEscolar(List gradoEscolar) {
        this.gradoEscolar = gradoEscolar;
    }

    public String getNivelLenguaje() {
        return nivelLenguaje;
    }

    public void setNivelLenguaje(String nivelLenguaje) {
        this.nivelLenguaje = nivelLenguaje;
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

    @Override
    public byte[] getContrasenia() {
        return contrasenia;
    }

    @Override
    public void setContrasenia(byte[] contrasenia) {
        this.contrasenia = contrasenia;
    }
}
