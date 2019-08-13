package tecolotl.alumno.modelo;

import java.util.List;
import java.util.StringJoiner;

public class ActividadModelo {
    private String idVideo;
    private Short puntaje;
    private List<NivelLenguajeModelo> nivelLenguajeModeloLista;
    private TipoEstudianteModelo tipoEstudianteModelo;
    private String lenguaje;
    private Integer tiempo;
    private String preguntaDetonadora;
    private String transcripcion;

    public String getIdVideo() {
        return idVideo;
    }

    public void setIdVideo(String idVideo) {
        this.idVideo = idVideo;
    }

    public Short getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Short puntaje) {
        this.puntaje = puntaje;
    }

    public List<NivelLenguajeModelo> getNivelLenguajeModeloLista() {
        return nivelLenguajeModeloLista;
    }

    public void setNivelLenguajeModeloLista(List<NivelLenguajeModelo> nivelLenguajeModeloLista) {
        this.nivelLenguajeModeloLista = nivelLenguajeModeloLista;
    }

    public TipoEstudianteModelo getTipoEstudianteModelo() {
        return tipoEstudianteModelo;
    }

    public void setTipoEstudianteModelo(TipoEstudianteModelo tipoEstudianteModelo) {
        this.tipoEstudianteModelo = tipoEstudianteModelo;
    }

    public String getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

    public String getTranscripcion() {
        return transcripcion;
    }

    public void setTranscripcion(String transcripcion) {
        this.transcripcion = transcripcion;
    }

    public Integer getTiempo() {
        return tiempo;
    }

    public void setTiempo(Integer tiempo) {
        this.tiempo = tiempo;
    }

    public String getPreguntaDetonadora() {
        return preguntaDetonadora;
    }

    public void setPreguntaDetonadora(String preguntaDetonadora) {
        this.preguntaDetonadora = preguntaDetonadora;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ActividadModelo.class.getSimpleName() + "[", "]")
                .add("idVideo='" + idVideo + "'")
                .add("puntaje=" + puntaje)
                .add("nivelLenguajeModeloLista=" + nivelLenguajeModeloLista)
                .add("tipoEstudianteModelo=" + tipoEstudianteModelo)
                .add("lenguaje='" + lenguaje + "'")
                .add("tiempo=" + tiempo)
                .add("preguntaDetonadora='" + preguntaDetonadora + "'")
                .add("lenguaje='" + lenguaje + "'")
                .add("transcripcion='" + transcripcion + "'")
                .toString();
    }
}
