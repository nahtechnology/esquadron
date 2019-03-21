package tecolotl.administracion.modelo.escuela;

import tecolotl.administracion.modelo.direccion.ColoniaModelo;
import tecolotl.administracion.persistencia.entidad.EscuelaEntidad;

public class EscuelaDetalleModelo extends EscuelaBaseModelo {

    private ColoniaModelo coloniaModelo;

    public EscuelaDetalleModelo() {
    }

    public EscuelaDetalleModelo(String claveCentroTrabajo) {
        super(claveCentroTrabajo);
    }

    public EscuelaDetalleModelo(EscuelaEntidad escuelaEntidad) {
        super(escuelaEntidad);
        coloniaModelo = new ColoniaModelo(escuelaEntidad.getColoniaEntidad());
    }

    public ColoniaModelo getColoniaModelo() {
        return coloniaModelo;
    }

    public void setColoniaModelo(ColoniaModelo coloniaModelo) {
        this.coloniaModelo = coloniaModelo;
    }
}
