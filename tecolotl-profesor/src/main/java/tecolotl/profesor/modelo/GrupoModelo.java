package tecolotl.profesor.modelo;

import tecolotl.profesor.entidad.GrupoEntidad;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class GrupoModelo {

    @NotNull
    private Integer id;

    @NotNull
    private Short grado;

    @NotNull
    private char grupo;

    @NotNull
    @Valid
    private ProfesorModelo profesorModelo;

    public GrupoModelo() {
    }

    public GrupoModelo(Integer id) {
        this.id = id;
    }

    public GrupoModelo(GrupoEntidad grupoEntidad){
        this.id = grupoEntidad.getId();
        this.grado = grupoEntidad.getGrado();
        this.grupo = grupoEntidad.getGrupo();
        this.profesorModelo = new ProfesorModelo(grupoEntidad.getProfesorEntidad());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getGrado() {
        return grado;
    }

    public void setGrado(Short grado) {
        this.grado = grado;
    }

    public char getGrupo() {
        return grupo;
    }

    public void setGrupo(char grupo) {
        this.grupo = grupo;
    }

    public ProfesorModelo getProfesorModelo() {
        return profesorModelo;
    }

    public void setProfesorModelo(ProfesorModelo profesorModelo) {
        this.profesorModelo = profesorModelo;
    }
}
