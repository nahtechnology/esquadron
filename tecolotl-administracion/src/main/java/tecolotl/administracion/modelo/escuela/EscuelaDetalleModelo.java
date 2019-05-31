package tecolotl.administracion.modelo.escuela;

import tecolotl.administracion.modelo.direccion.ColoniaModelo;
import tecolotl.administracion.persistencia.entidad.EscuelaEntidad;

public class EscuelaDetalleModelo extends EscuelaBaseModelo {

    private ColoniaModelo coloniaModelo;
    private MotivoBloqueoModelo motivoBloqueoModelo;

    public EscuelaDetalleModelo() {
        coloniaModelo = new ColoniaModelo();
    }

    public EscuelaDetalleModelo(String claveCentroTrabajo) {
        super(claveCentroTrabajo);
    }

    public EscuelaDetalleModelo(EscuelaEntidad escuelaEntidad) {
        super(escuelaEntidad);
        coloniaModelo = new ColoniaModelo(escuelaEntidad.getColoniaEntidad());
        motivoBloqueoModelo = new MotivoBloqueoModelo(escuelaEntidad.getMotivoBloqueoEntidad());
    }

    public ColoniaModelo getColoniaModelo() {
        return coloniaModelo;
    }

    public void setColoniaModelo(ColoniaModelo coloniaModelo) {
        this.coloniaModelo = coloniaModelo;
    }

    public MotivoBloqueoModelo getMotivoBloqueoModelo() {
        return motivoBloqueoModelo;
    }

    public void setMotivoBloqueoModelo(MotivoBloqueoModelo motivoBloqueoModelo) {
        this.motivoBloqueoModelo = motivoBloqueoModelo;
    }
}
