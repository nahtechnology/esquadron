package tecolotl.alumno.modelo.mapamental;

public class MapaMentalResueltoModelo {

    private short cardinalidad;
    private int totalResueltos;

    public MapaMentalResueltoModelo() {
    }

    public MapaMentalResueltoModelo(short cardinalidad, int totalResueltos) {
        this.cardinalidad = cardinalidad;
        this.totalResueltos = totalResueltos;
    }

    public short getCardinalidad() {
        return cardinalidad;
    }

    public void setCardinalidad(short cardinalidad) {
        this.cardinalidad = cardinalidad;
    }

    public int getTotalResueltos() {
        return totalResueltos;
    }

    public void setTotalResueltos(int totalResueltos) {
        this.totalResueltos = totalResueltos;
    }

}
