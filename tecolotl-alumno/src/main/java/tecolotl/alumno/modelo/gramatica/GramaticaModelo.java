package tecolotl.alumno.modelo.gramatica;

import tecolotl.alumno.entidad.gramatica.GramaticaEntidad;
import tecolotl.alumno.modelo.ActividadModelo;

import java.util.StringJoiner;

public class GramaticaModelo {
    private ActividadModelo actividadModelo;
    private String codigo;
    private String palabra;

    public GramaticaModelo() {
    }


    public GramaticaModelo(GramaticaEntidad gramaticaEntidad){
        this.actividadModelo = new ActividadModelo(gramaticaEntidad.getGramaticaEntidadPK().getActividadEntidad().getId());
        this.codigo = gramaticaEntidad.getGramaticaEntidadPK().getCodigo();
        this.palabra = gramaticaEntidad.getPalabra();
    }

    public ActividadModelo getActividadModelo() {
        return actividadModelo;
    }

    public void setActividadModelo(ActividadModelo actividadModelo) {
        this.actividadModelo = actividadModelo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", GramaticaModelo.class.getSimpleName() + "[", "]")
                .add("actividadModelo=" + actividadModelo)
                .add("codigo='" + codigo + "'")
                .add("palabra='" + palabra + "'")
                .toString();
    }
}
