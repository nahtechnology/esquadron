package tecolotl.alumno.modelo;

import tecolotl.alumno.entidad.GradoEscolarEntidad;
import tecolotl.nucleo.modelo.CatalogoModelo;

public class GradoEscolarModelo {

    private Short id;
    private String nivel;
    private Short grado;

    public GradoEscolarModelo(GradoEscolarEntidad gradoEscolarEntidad) {
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
