package tecolotl.profesor.modelo;

import tecolotl.administracion.modelo.escuela.EscuelaBaseModelo;
import tecolotl.administracion.sesion.LicenciaSesionBean;
import tecolotl.nucleo.modelo.PersonaModelo;
import tecolotl.nucleo.persistencia.entidad.PersonaEntidad;
import tecolotl.profesor.entidad.ProfesorEntidad;
import tecolotl.profesor.validacion.GrupoProfesorValidacion;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class ProfesorModelo extends PersonaModelo {

    @NotNull(message = "ID Profesor no puede ser nulo", groups = {GrupoProfesorValidacion.class})
    private Integer id;

    @NotNull
    @Valid
    private EscuelaBaseModelo escuelaBaseModelo;

    @NotNull
    private List<GrupoModelo> grupoModeloLista;

    @NotNull
    @Size(max = 100)
    private String correoEletronico;

    public ProfesorModelo() {
    }

    public ProfesorModelo(PersonaEntidad personaEntidad) {
        super(personaEntidad);
    }

    public ProfesorModelo(ProfesorEntidad profesorEntidad){
        super(profesorEntidad);
        this.id = profesorEntidad.getId();
        correoEletronico = profesorEntidad.getCorreoEletronico();
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

    public String getCorreoEletronico() {
        return correoEletronico;
    }

    public void setCorreoEletronico(String correoEletronico) {
        this.correoEletronico = correoEletronico;
    }
}
