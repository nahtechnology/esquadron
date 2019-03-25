package tecolotl.administracion.modelo.escuela;

import tecolotl.administracion.persistencia.entidad.MotivoBloqueoEntidad;
import tecolotl.nucleo.modelo.CatalogoModelo;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;

public class MotivoBloqueoModelo extends CatalogoModelo {

    public MotivoBloqueoModelo() {
    }

    public MotivoBloqueoModelo(MotivoBloqueoEntidad motivoBloqueoEntidad) {
        super(motivoBloqueoEntidad);
    }

    public MotivoBloqueoModelo(Short clave) {
        super(clave);
    }
}
