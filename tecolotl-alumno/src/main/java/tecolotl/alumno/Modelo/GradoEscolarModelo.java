package tecolotl.alumno.Modelo;

import tecolotl.alumno.entidad.GradoEscolarEntidad;

import javax.validation.constraints.NotNull;

public class GradoEscolarModelo {

    @NotNull
    private Short id;

    @NotNull
    private String nivel;

    @NotNull
    private Short grado;

    public GradoEscolarModelo(){
    }

    public GradoEscolarModelo(GradoEscolarEntidad gradoEscolarEntidad){
        this.id = gradoEscolarEntidad.getId();
        this.nivel = gradoEscolarEntidad.getNivel();
        this.grado = gradoEscolarEntidad.getGrado();
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public Short getGrado() {
        return grado;
    }

    public void setGrado(Short grado) {
        this.grado = grado;
    }
}
