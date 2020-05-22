package tecolotl.alumno.modelo.mapamental;

import tecolotl.alumno.entidad.vista.CalificacionTareaMapamentalEntidadVista;

public class MapaMentalResueltoModelo {

    private short cardinalidad;
    private Integer totalResueltos;
    private Short calificacion;
    private String comentario;

    public MapaMentalResueltoModelo() {
    }

    public MapaMentalResueltoModelo(short cardinalidad, int totalResueltos) {
        this.cardinalidad = cardinalidad;
        this.totalResueltos = totalResueltos;
    }

    public MapaMentalResueltoModelo(CalificacionTareaMapamentalEntidadVista calificacionTareaMapamentalEntidadVista) {
        cardinalidad = calificacionTareaMapamentalEntidadVista.getCardinalidad();
        totalResueltos = calificacionTareaMapamentalEntidadVista.getCount();
        calificacion = calificacionTareaMapamentalEntidadVista.getPuntaje();
        comentario = calificacionTareaMapamentalEntidadVista.getComentario();
    }

    public short getCardinalidad() {
        return cardinalidad;
    }

    public void setCardinalidad(short cardinalidad) {
        this.cardinalidad = cardinalidad;
    }

    public Integer getTotalResueltos() {
        return totalResueltos;
    }

    public void setTotalResueltos(Integer totalResueltos) {
        this.totalResueltos = totalResueltos;
    }

    public Short getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Short calificacion) {
        this.calificacion = calificacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
