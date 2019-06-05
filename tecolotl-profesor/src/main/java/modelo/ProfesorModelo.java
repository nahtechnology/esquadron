package modelo;

import tecolotl.administracion.modelo.escuela.EscuelaBaseModelo;
import tecolotl.alumno.Modelo.AlumnoModelo;
import tecolotl.nucleo.modelo.PersonaModelo;
import tecolotl.nucleo.persistencia.entidad.PersonaEntidad;
import tecolotl.profesor.entidad.ProfesorEntidad;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class ProfesorModelo extends PersonaModelo {

    @NotNull
    private Integer id;
    @NotNull
    @Valid
    private EscuelaBaseModelo escuelaBaseModelo;

    public ProfesorModelo() {
    }

    public ProfesorModelo(PersonaEntidad personaEntidad) {
        super(personaEntidad);
    }
    public ProfesorModelo(ProfesorEntidad profesorEntidad){
        this.id = profesorEntidad.getId();
        //this.escuelaBaseModelo = new ProfesorModelo(profesorEntidad.getEscuelaEntidad());
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EscuelaBaseModelo getEscuelaBaseModelo() {
        return escuelaBaseModelo;
    }

    public void setEscuelaBaseModelo(EscuelaBaseModelo escuelaBaseModelo) {
        this.escuelaBaseModelo = escuelaBaseModelo;
    }
}
