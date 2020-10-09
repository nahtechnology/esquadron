package tecolotl.alumno.modelo;

import tecolotl.alumno.entidad.ActividadEntidad;
import tecolotl.alumno.entidad.NivelLenguajeEntidad;
import tecolotl.alumno.entidad.vista.ActividadBuquedaVista;
import tecolotl.alumno.validacion.ActividadNuevaValidacion;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
    private Boolean estatus;

    public ActividadModelo() {
    }

    public ActividadModelo(String idVideo) {
        this.idVideo = idVideo;
    }

    public ActividadModelo(ActividadBuquedaVista actividadBuquedaVista) {
        idVideo = actividadBuquedaVista.getIdVideo();
        lenguaje = actividadBuquedaVista.getLenguaje();
        preguntaDetonadora = actividadBuquedaVista.getPreguntaDetonadora();
        nivelLenguajeModeloLista = new ArrayList<>();
        nivelLenguajeModeloLista.add(new NivelLenguajeModelo((short)1, actividadBuquedaVista.getNivelLenguaje()));
        temaModelo = new TemaModelo((short)1, actividadBuquedaVista.getTema());
        transcripcion = actividadBuquedaVista.getMapaMental();
    }

    public ActividadModelo(ActividadEntidad actividadEntidad) {
        this.idVideo = actividadEntidad.getId();
        this.puntaje = actividadEntidad.getPuntaje();
        if (actividadEntidad.getNivelLenguajeEntidad() != null) {
            this.nivelLenguajeModeloLista = new ArrayList<>();
            for (NivelLenguajeEntidad nivelLenguajeEntidad : actividadEntidad.getNivelLenguajeEntidad()) {
                this.nivelLenguajeModeloLista.add(new NivelLenguajeModelo(nivelLenguajeEntidad));
            }
        }
        this.tipoEstudianteModelo = actividadEntidad.getTipoEstudianteEntidad() == null ? null : new TipoEstudianteModelo(actividadEntidad.getTipoEstudianteEntidad());
        this.temaModelo = actividadEntidad.getTemaEntidad() == null ? null : new TemaModelo(actividadEntidad.getTemaEntidad());
        this.lenguaje = actividadEntidad.getLenguaje();
        this.tiempo = actividadEntidad.getTiempo();
        this.estatus = actividadEntidad.getEstatus();
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

    public Boolean getEstatus() {
        return estatus;
    }

    public void setEstatus(Boolean estatus) {
        this.estatus = estatus;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActividadModelo that = (ActividadModelo) o;
        return idVideo.equals(that.idVideo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVideo);
    }
}
