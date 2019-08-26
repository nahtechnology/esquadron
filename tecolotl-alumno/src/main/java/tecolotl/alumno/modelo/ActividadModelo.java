package tecolotl.alumno.modelo;

import tecolotl.alumno.entidad.ActividadEntidad;
import tecolotl.alumno.entidad.NivelLenguajeEntidad;
import tecolotl.alumno.validacion.ActividadNuevoValidacion;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class ActividadModelo {

    private String idVideo;
    private Short puntaje;
    private List<NivelLenguajeModelo> nivelLenguajeModeloLista;
    private TipoEstudianteModelo tipoEstudianteModelo;
    private TemaModelo temaModelo;
    private String lenguaje;
    private Integer tiempo;
    private String preguntaDetonadora;
    private String transcripcion;

    public ActividadModelo() {
    }

    public ActividadModelo(String idVideo) {
        this.idVideo = idVideo;
    }

    public ActividadModelo(ActividadEntidad actividadEntidad) {
        this.idVideo = actividadEntidad.getId();
        this.puntaje = actividadEntidad.getPuntaje();
        this.nivelLenguajeModeloLista = new ArrayList<>();
        for (NivelLenguajeEntidad nivelLenguajeEntidad : actividadEntidad.getNivelLenguajeEntidad()) {
            this.nivelLenguajeModeloLista.add(new NivelLenguajeModelo(nivelLenguajeEntidad));
        }
        this.tipoEstudianteModelo = new TipoEstudianteModelo(actividadEntidad.getTipoEstudianteEntidad());
        this.temaModelo = new TemaModelo(actividadEntidad.getTemaEntidad());
        this.lenguaje = actividadEntidad.getLenguaje();
        this.tiempo = actividadEntidad.getTiempo();
        this.preguntaDetonadora = actividadEntidad.getPreguntaDetonadora();
    }

    @NotNull
    @Size(min = 11, max = 11)
    public String getIdVideo() {
        return idVideo;
    }

    public void setIdVideo(String idVideo) {
        this.idVideo = idVideo;
    }

    @NotNull(groups = {ActividadNuevoValidacion.class})
    @Min(value = 1, groups = {ActividadNuevoValidacion.class})
    public Short getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Short puntaje) {
        this.puntaje = puntaje;
    }

    @NotNull(groups = {ActividadNuevoValidacion.class})
    @Size(min = 1, groups = {ActividadNuevoValidacion.class})
    public List<NivelLenguajeModelo> getNivelLenguajeModeloLista() {
        return nivelLenguajeModeloLista;
    }

    public void setNivelLenguajeModeloLista(List<NivelLenguajeModelo> nivelLenguajeModeloLista) {
        this.nivelLenguajeModeloLista = nivelLenguajeModeloLista;
    }

    @NotNull(groups = {ActividadNuevoValidacion.class})
    @Valid
    public TipoEstudianteModelo getTipoEstudianteModelo() {
        return tipoEstudianteModelo;
    }

    public void setTipoEstudianteModelo(TipoEstudianteModelo tipoEstudianteModelo) {
        this.tipoEstudianteModelo = tipoEstudianteModelo;
    }

    @NotNull(groups = {ActividadNuevoValidacion.class})
    @Size(min = 2, max = 200, groups = {ActividadNuevoValidacion.class})
    public String getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

    @NotNull(groups = {ActividadNuevoValidacion.class})
    @Size(min = 2, groups = {ActividadNuevoValidacion.class})
    public String getTranscripcion() {
        return transcripcion;
    }

    public void setTranscripcion(String transcripcion) {
        this.transcripcion = transcripcion;
    }

    @NotNull(groups = {ActividadNuevoValidacion.class})
    @Min(value = 0, groups = {ActividadNuevoValidacion.class})
    public Integer getTiempo() {
        return tiempo;
    }

    public void setTiempo(Integer tiempo) {
        this.tiempo = tiempo;
    }

    @NotNull(groups = {ActividadNuevoValidacion.class})
    @Size(min = 2, max = 100, groups = {ActividadNuevoValidacion.class})
    public String getPreguntaDetonadora() {
        return preguntaDetonadora;
    }

    public void setPreguntaDetonadora(String preguntaDetonadora) {
        this.preguntaDetonadora = preguntaDetonadora;
    }

    @NotNull(groups = {ActividadNuevoValidacion.class})
    @Valid
    public TemaModelo getTemaModelo() {
        return temaModelo;
    }

    public void setTemaModelo(TemaModelo temaModelo) {
        this.temaModelo = temaModelo;
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
