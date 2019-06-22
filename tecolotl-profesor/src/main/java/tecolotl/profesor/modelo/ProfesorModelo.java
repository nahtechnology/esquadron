package tecolotl.profesor.modelo;

import tecolotl.administracion.modelo.escuela.EscuelaBaseModelo;
import tecolotl.administracion.sesion.LicenciaSesionBean;
import tecolotl.nucleo.modelo.PersonaModelo;
import tecolotl.nucleo.persistencia.entidad.PersonaEntidad;
import tecolotl.profesor.entidad.ProfesorEntidad;
import tecolotl.profesor.validacion.GrupoProfesorValidacion;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ProfesorModelo extends PersonaModelo {

    @NotNull(message = "ID Profesor no puede ser nulo", groups = {GrupoProfesorValidacion.class})
    private Integer id;

    @NotNull
    @Valid
    private EscuelaBaseModelo escuelaBaseModelo;

    @NotNull
    private List<GrupoModelo> grupoModeloLista;

    public ProfesorModelo() {
    }

    public ProfesorModelo(PersonaEntidad personaEntidad) {
        super(personaEntidad);
    }
    public ProfesorModelo(ProfesorEntidad profesorEntidad){
        super(profesorEntidad);
        this.id = profesorEntidad.getId();
    }

    public ProfesorModelo(Integer id) {
        this.id = id;
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

    public List<GrupoModelo> getGrupoModeloLista() {
        return grupoModeloLista;
    }

    public void setGrupoModeloLista(List<GrupoModelo> grupoModeloLista) {
        this.grupoModeloLista = grupoModeloLista;
    }
}
