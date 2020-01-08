package tecolotl.profesor.modelo;

import tecolotl.profesor.entidad.CalificaTareaGramaticaEntidad;

import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;

public class CalificaTareaGramaticaModelo {

    private UUID idTarea;
    private String idActividad;
    private String codigo;
    private Short vuelta;
    private Short puntaje;
    private Date momento;

    public CalificaTareaGramaticaModelo() {
    }

    public CalificaTareaGramaticaModelo(UUID idTarea, String idActividad, String codigo, Short vuelta, Short puntaje, Date momento) {
        this.idTarea = idTarea;
        this.idActividad = idActividad;
        this.codigo = codigo;
        this.vuelta = vuelta;
        this.puntaje = puntaje;
        this.momento = momento;
    }

    public CalificaTareaGramaticaModelo(CalificaTareaGramaticaEntidad calificaTareaGramaticaEntidad){
        this.idTarea = calificaTareaGramaticaEntidad.getTareaGramaticaEntidad().getTareaGramaticaEntidadPK().getTareaEntidad().getId();
        this.idActividad = calificaTareaGramaticaEntidad.getTareaGramaticaEntidad().getTareaGramaticaEntidadPK().getGramaticaEntidad().getGramaticaEntidadPK().getActividadEntidad().getId();
        this.codigo = calificaTareaGramaticaEntidad.getTareaGramaticaEntidad().getTareaGramaticaEntidadPK().getGramaticaEntidad().getGramaticaEntidadPK().getCodigo();
        this.vuelta = calificaTareaGramaticaEntidad.getTareaGramaticaEntidad().getTareaGramaticaEntidadPK().getVuelta();
        this.puntaje = calificaTareaGramaticaEntidad.getPuntaje();
        this.momento = calificaTareaGramaticaEntidad.getMomento();
    }

    public UUID getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(UUID idTarea) {
        this.idTarea = idTarea;
    }

    public String getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(String idActividad) {
        this.idActividad = idActividad;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Short getVuelta() {
        return vuelta;
    }

    public void setVuelta(Short vuelta) {
        this.vuelta = vuelta;
    }

    public Short getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Short puntaje) {
        this.puntaje = puntaje;
    }

    public Date getMomento() {
        return momento;
    }

    public void setMomento(Date momento) {
        this.momento = momento;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CalificaTareaGramaticaModelo.class.getSimpleName() + "[", "]")
                .add("idTarea=" + idTarea)
                .add("idActividad='" + idActividad + "'")
                .add("codigo='" + codigo + "'")
                .add("vuelta=" + vuelta)
                .add("puntaje=" + puntaje)
                .add("momento=" + momento)
                .toString();
    }
}
