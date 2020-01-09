package tecolotl.alumno.modelo;

import tecolotl.alumno.entidad.ActividadEntidad;
import tecolotl.alumno.entidad.NivelLenguajeEntidad;
import tecolotl.alumno.entidad.gramatica.GramaticaEntidad;
import tecolotl.alumno.validacion.ActividadNuevaValidacion;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@XmlRootElement
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
        this.setTranscripcion(actividadEntidad.getTrasncripcion());
    }

    @NotNull
    @Size(min = 11, max = 11)
    public String getIdVideo() {
        return idVideo;
    }

    public void setIdVideo(String idVideo) {
        this.idVideo = idVideo;
    }

    @NotNull(groups = {ActividadNuevaValidacion.class})
    @Min(value = 1, groups = {ActividadNuevaValidacion.class})
    public Short getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Short puntaje) {
        this.puntaje = puntaje;
    }

    @NotNull(groups = {ActividadNuevaValidacion.class})
    @Size(min = 1, groups = {ActividadNuevaValidacion.class})
    public List<NivelLenguajeModelo> getNivelLenguajeModeloLista() {
        return nivelLenguajeModeloLista;
    }

    public void setNivelLenguajeModeloLista(List<NivelLenguajeModelo> nivelLenguajeModeloLista) {
        this.nivelLenguajeModeloLista = nivelLenguajeModeloLista;
    }

    @NotNull(groups = {ActividadNuevaValidacion.class})
    @Valid
    public TipoEstudianteModelo getTipoEstudianteModelo() {
        return tipoEstudianteModelo;
    }

    public void setTipoEstudianteModelo(TipoEstudianteModelo tipoEstudianteModelo) {
        this.tipoEstudianteModelo = tipoEstudianteModelo;
    }

    @NotNull(groups = {ActividadNuevaValidacion.class})
    @Size(min = 2, max = 200, groups = {ActividadNuevaValidacion.class})
    public String getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

    @NotNull(groups = {ActividadNuevaValidacion.class})
    @Size(min = 2, groups = {ActividadNuevaValidacion.class})
    public String getTranscripcion() {
        return transcripcion;
    }

    public void setTranscripcion(String transcripcion) {
        this.transcripcion = transcripcion;
    }

    @NotNull(groups = {ActividadNuevaValidacion.class})
    @Min(value = 0, groups = {ActividadNuevaValidacion.class})
    public Integer getTiempo() {
        return tiempo;
    }

    public void setTiempo(Integer tiempo) {
        this.tiempo = tiempo;
    }

    @NotNull(groups = {ActividadNuevaValidacion.class})
    @Size(min = 2, max = 100, groups = {ActividadNuevaValidacion.class})
    public String getPreguntaDetonadora() {
        return preguntaDetonadora;
    }

    public void setPreguntaDetonadora(String preguntaDetonadora) {
        this.preguntaDetonadora = preguntaDetonadora;
    }

    @NotNull(groups = {ActividadNuevaValidacion.class})
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
