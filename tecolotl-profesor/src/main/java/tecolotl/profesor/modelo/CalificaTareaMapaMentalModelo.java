package tecolotl.profesor.modelo;

import tecolotl.profesor.entidad.CalificaTareaMapamental;

import java.util.Date;
import java.util.StringJoiner;

public class CalificaTareaMapaMentalModelo {

    private String codigo;
    private String idActividad;
    private Integer idTarea;
    private Short cardinalidad;
    private Short vuelta;
    private String comentario;
    private Short puntaje;
    private Date momento;

    public CalificaTareaMapaMentalModelo() {
    }

    public CalificaTareaMapaMentalModelo(String codigo, String idActividad, Integer idTarea, Short cardinalidad, Short vuelta, String comentario, Short opuntaje, Date momento) {
        this.codigo = codigo;
        this.idActividad = idActividad;
        this.idTarea = idTarea;
        this.cardinalidad = cardinalidad;
        this.vuelta = vuelta;
        this.comentario = comentario;
        this.puntaje = opuntaje;
        this.momento = momento;
    }

    public CalificaTareaMapaMentalModelo(CalificaTareaMapamental calificaTareaMapamental){
        this.codigo = calificaTareaMapamental.getTareaMapaMentalActividadEntidad().getTareaMapaMentalActividadEntidadPK().getMapaMentalActividadEntidad().getMapaMentalActividadPK().getMapaMentalEntidad().getMapaMentalEntidadPK().getCodigo();
        this.idActividad = calificaTareaMapamental.getTareaMapaMentalActividadEntidad().getTareaMapaMentalActividadEntidadPK().getMapaMentalActividadEntidad().getMapaMentalActividadPK().getActividadEntidad().getId();
        this.idTarea = calificaTareaMapamental.getTareaMapaMentalActividadEntidad().getTareaMapaMentalActividadEntidadPK().getTareaEntidad().getId();
        this.cardinalidad = calificaTareaMapamental.getTareaMapaMentalActividadEntidad().getTareaMapaMentalActividadEntidadPK().getMapaMentalActividadEntidad().getMapaMentalActividadPK().getMapaMentalEntidad().getMapaMentalEntidadPK().getCardinalidad();
        this.vuelta = calificaTareaMapamental.getTareaMapaMentalActividadEntidad().getTareaMapaMentalActividadEntidadPK().getVuelta();
        this.comentario = calificaTareaMapamental.getComentario();
        this.puntaje = calificaTareaMapamental.getPuntaje();
        this.momento = calificaTareaMapamental.getMomento();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(String idActividad) {
        this.idActividad = idActividad;
    }

    public Integer getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(Integer idTarea) {
        this.idTarea = idTarea;
    }

    public Short getCardinalidad() {
        return cardinalidad;
    }

    public void setCardinalidad(Short cardinalidad) {
        this.cardinalidad = cardinalidad;
    }

    public Short getVuelta() {
        return vuelta;
    }

    public void setVuelta(Short vuelta) {
        this.vuelta = vuelta;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
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
        return new StringJoiner(", ", CalificaTareaMapaMentalModelo.class.getSimpleName() + "[", "]")
                .add("codigo='" + codigo + "'")
                .add("idActividad='" + idActividad + "'")
                .add("idTarea=" + idTarea)
                .add("cardinalidad=" + cardinalidad)
                .add("vuelta=" + vuelta)
                .add("comentario='" + comentario + "'")
                .add("puntaje=" + puntaje)
                .add("momento=" + momento)
                .toString();
    }
}
