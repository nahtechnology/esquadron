package tecolotl.profesor.modelo;

import tecolotl.profesor.entidad.CalificaTareaMapamentalEntidad;
import tecolotl.profesor.entidad.CalificaTareaMapamentalEntidadPK;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;

public class CalificaTareaMapaMentalModelo {

    private UUID idTarea;
    private Short cardinalidad;
    private Short vuelta;
    private String comentario;
    private Short puntaje;
    private Date momento;

    public CalificaTareaMapaMentalModelo() {
    }

    public CalificaTareaMapaMentalModelo(UUID idTarea, Short cardinalidad, Short vuelta, String comentario, Short opuntaje, Date momento) {
        this.idTarea = idTarea;
        this.cardinalidad = cardinalidad;
        this.vuelta = vuelta;
        this.comentario = comentario;
        this.puntaje = opuntaje;
        this.momento = momento;
    }

    public CalificaTareaMapaMentalModelo(CalificaTareaMapamentalEntidad calificaTareaMapamentalEntidad){
        CalificaTareaMapamentalEntidadPK calificaTareaMapamentalEntidadPK = calificaTareaMapamentalEntidad.getCalificaTareaMapamentalEntidadPK();
        this.idTarea = calificaTareaMapamentalEntidadPK.getTareaEntidad().getId();
        this.cardinalidad = calificaTareaMapamentalEntidadPK.getCardinalidad();
        this.vuelta = calificaTareaMapamentalEntidadPK.getVuelta();
        this.comentario = calificaTareaMapamentalEntidad.getComentario();
        this.puntaje = calificaTareaMapamentalEntidad.getPuntaje();
        this.momento = calificaTareaMapamentalEntidad.getMomento();
    }

    @NotNull
    public UUID getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(UUID idTarea) {
        this.idTarea = idTarea;
    }

    @NotNull
    public Short getCardinalidad() {
        return cardinalidad;
    }

    public void setCardinalidad(Short cardinalidad) {
        this.cardinalidad = cardinalidad;
    }

    @NotNull
    public Short getVuelta() {
        return vuelta;
    }

    public void setVuelta(Short vuelta) {
        this.vuelta = vuelta;
    }

    @NotNull
    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @NotNull
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
                .add("idTarea=" + idTarea)
                .add("cardinalidad=" + cardinalidad)
                .add("vuelta=" + vuelta)
                .add("comentario='" + comentario + "'")
                .add("puntaje=" + puntaje)
                .add("momento=" + momento)
                .toString();
    }
}
