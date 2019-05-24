package tecolotl.alumno.Modelo;

import tecolotl.alumno.entidad.AlumnoEntidad;
import tecolotl.nucleo.modelo.PersonaModelo;

import java.util.Date;
import java.util.List;

public class AlumnoModelo extends PersonaModelo {

    private Integer id;
    private List gradoEscolar;
    private String nivelLenguaje;
    private Date nacimiento;
    private String correoPadreFamilia;
    private byte[] contrasenia;

    public AlumnoModelo() {
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

    public String getNivelLenguaje() {
        return nivelLenguaje;
    }

    public void setNivelLenguaje(String nivelLenguaje) {
        this.nivelLenguaje = nivelLenguaje;
    }


}
