package tecolotl.profesor.modelo;

import tecolotl.administracion.modelo.escuela.EscuelaBaseModelo;
import tecolotl.nucleo.modelo.PersonaModelo;
import tecolotl.profesor.entidad.ProfesorEntidad;
import tecolotl.profesor.validacion.ProfesorLlavePrimaria;
import tecolotl.profesor.validacion.ProfesorNuevoValidacion;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

public class ProfesorModelo extends PersonaModelo {

    @NotNull(groups = {ProfesorLlavePrimaria.class})
    private UUID id;

    @NotNull
    @Valid
    private EscuelaBaseModelo escuelaBaseModelo;

    @NotNull
    private List<GrupoModelo> grupoModeloLista;

    @NotNull(groups = {ProfesorNuevoValidacion.class})
    @Size(max = 100, groups = {ProfesorNuevoValidacion.class})
    private String correoEletronico;

    public ProfesorModelo() {
    }

    public ProfesorModelo(ProfesorEntidad profesorEntidad){
        super(profesorEntidad);
        this.id = profesorEntidad.getId();
        correoEletronico = profesorEntidad.getCorreoEletronico();
        escuelaBaseModelo = new EscuelaBaseModelo(profesorEntidad.getEscuelaEntidad().getClaveCentroTrabajo());
    }

    public ProfesorModelo(ProfesorModelo profesorModelo) {
        id = profesorModelo.getId();
        setApodo(profesorModelo.getApodo());
        setApellidoPaterno(profesorModelo.getApellidoPaterno());
        setApellidoMaterno(profesorModelo.getApellidoMaterno());
        setNombre(profesorModelo.getNombre());
        setCorreoEletronico(profesorModelo.getCorreoEletronico());
        setContrasenia(profesorModelo.getContrasenia());
    }

    public ProfesorModelo(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    @Override
    public String toString() {
        return new StringJoiner(", ", ProfesorModelo.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("escuelaBaseModelo=" + escuelaBaseModelo)
                .add("grupoModeloLista=" + grupoModeloLista)
                .add("correoEletronico='" + correoEletronico + "'")
                .add(super.toString())
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ProfesorModelo that = (ProfesorModelo) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

}
