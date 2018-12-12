package tecolotl.web.modelo.administracion;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EscuelaModelo {

    @NotNull
    @Size(min = 10, max = 10)
    private String claveCentroTrabajo;

    public String getClaveCentroTrabajo() {
        return claveCentroTrabajo;
    }

    public void setClaveCentroTrabajo(String claveCentroTrabajo) {
        this.claveCentroTrabajo = claveCentroTrabajo;
    }
}
