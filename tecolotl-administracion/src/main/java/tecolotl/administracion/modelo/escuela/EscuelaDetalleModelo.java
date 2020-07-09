package tecolotl.administracion.modelo.escuela;

import tecolotl.administracion.persistencia.entidad.EscuelaEntidad;

import java.util.StringJoiner;

public class EscuelaDetalleModelo extends EscuelaBaseModelo {

    private MotivoBloqueoModelo motivoBloqueoModelo;

    public EscuelaDetalleModelo() {
    }

    public EscuelaDetalleModelo(String claveCentroTrabajo) {
        super(claveCentroTrabajo);
    }

    public EscuelaDetalleModelo(EscuelaEntidad escuelaEntidad) {
        super(escuelaEntidad);
        motivoBloqueoModelo = new MotivoBloqueoModelo(escuelaEntidad.getMotivoBloqueoEntidad());
    }

    public MotivoBloqueoModelo getMotivoBloqueoModelo() {
        return motivoBloqueoModelo;
    }

    public void setMotivoBloqueoModelo(MotivoBloqueoModelo motivoBloqueoModelo) {
        this.motivoBloqueoModelo = motivoBloqueoModelo;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", EscuelaDetalleModelo.class.getSimpleName() + "[", "]")
                .add("motivoBloqueoModelo=" + motivoBloqueoModelo)
                .add("EscuelaBaseModelo:" + super.toString())
                .toString();
    }
}
