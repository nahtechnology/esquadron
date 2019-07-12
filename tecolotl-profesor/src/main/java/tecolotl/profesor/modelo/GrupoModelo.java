package tecolotl.profesor.modelo;

import tecolotl.profesor.entidad.GrupoEntidad;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.StringJoiner;

public class GrupoModelo {

    @NotNull
    private Integer id;

    @NotNull
    private Short grado;

    @NotNull
    private char grupo;

    @NotNull
    private Date inicio;

    @NotNull
    private Date fin;

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
        this.inicio = grupoEntidad.getInicio();
        this.fin = grupoEntidad.getFin();
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

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public ProfesorModelo getProfesorModelo() {
        return profesorModelo;
    }

    public void setProfesorModelo(ProfesorModelo profesorModelo) {
        this.profesorModelo = profesorModelo;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", GrupoModelo.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("grado=" + grado)
                .add("grupo=" + grupo)
                .add("inicio=" + inicio)
                .add("fin=" + fin)
                .add("profesorModelo=" + profesorModelo)
                .toString();
    }
}
