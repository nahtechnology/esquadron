package tecolotl.alumno.modelo.gramatica;

import tecolotl.alumno.entidad.gramatica.GramaticaEntidad;
import tecolotl.alumno.entidad.gramatica.TareaGramaticaEntidad;
import tecolotl.alumno.modelo.ActividadModelo;

import java.util.Date;
import java.util.StringJoiner;

public class GramaticaModelo {

    private ActividadModelo actividadModelo;
    private String codigo;
    private String palabra;
    private String respuesta;
    private Date horaRespuesta;

    public GramaticaModelo() {
    }

    public GramaticaModelo(GramaticaEntidad gramaticaEntidad){
        this.codigo = gramaticaEntidad.getGramaticaEntidadPK().getCodigo();
        this.palabra = gramaticaEntidad.getPalabra();
    }

    public GramaticaModelo(TareaGramaticaEntidad tareaGramaticaEntidad) {
        this(tareaGramaticaEntidad.getTareaGramaticaEntidadPK().getGramaticaEntidad());
        this.respuesta = tareaGramaticaEntidad.getRespuesta();
        this.horaRespuesta = tareaGramaticaEntidad.getHoraRespuesta();
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

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public Date getHoraRespuesta() {
        return horaRespuesta;
    }

    public void setHoraRespuesta(Date horaRespuesta) {
        this.horaRespuesta = horaRespuesta;
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
