package tecolotl.profesor.modelo;

import tecolotl.profesor.entidad.TareaMapaMentalEntidad;

public class TareaMapaMentalModelo {

    private short cardinalidad;
    private short totalRespuestas;
    private short vuelta;
    private Short puntaje;

    public TareaMapaMentalModelo() {
    }

    public TareaMapaMentalModelo(TareaMapaMentalEntidad tareaMapaMentalEntidad) {
        this.cardinalidad = tareaMapaMentalEntidad.getCardinalidad();
        this.totalRespuestas = tareaMapaMentalEntidad.getTotalRespuestas();
        this.vuelta = tareaMapaMentalEntidad.getVuelta();
        this.puntaje = tareaMapaMentalEntidad.getPuntaje();
    }

    public short getCardinalidad() {
        return cardinalidad;
    }

    public void setCardinalidad(short cardinalidad) {
        this.cardinalidad = cardinalidad;
    }

    public short getTotalRespuestas() {
        return totalRespuestas;
    }

    public void setTotalRespuestas(short totalRespuestas) {
        this.totalRespuestas = totalRespuestas;
    }

    public Short getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Short puntaje) {
        this.puntaje = puntaje;
    }

    public short getVuelta() {
        return vuelta;
    }

    public void setVuelta(short vuelta) {
        this.vuelta = vuelta;
    }
}
