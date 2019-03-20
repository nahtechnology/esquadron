package tecolotl.administracion.modelo.escuela;

import tecolotl.administracion.modelo.ColoniaModelo;

public class EscuelaDetalleModelo extends EscuelaBaseModelo {

    private ColoniaModelo coloniaModelo;

    public ColoniaModelo getColoniaModelo() {
        return coloniaModelo;
    }

    public void setColoniaModelo(ColoniaModelo coloniaModelo) {
        this.coloniaModelo = coloniaModelo;
    }
}
