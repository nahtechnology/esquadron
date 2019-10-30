package tecolotl.profesor.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TareaMapaMentalEntidad {

    private short cardinalidad;
    private short totalRespuestas;
    private short vuelta;
    private Short puntaje;

    @Id
    public short getCardinalidad() {
        return cardinalidad;
    }

    public void setCardinalidad(short cardinalidad) {
        this.cardinalidad = cardinalidad;
    }

    @Column(name = "total_respuesta")
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
