package tecolotl.administracion.modelo.escuela;

import tecolotl.nucleo.modelo.CatalogoModelo;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;

public class MotivoBloqueoModelo extends CatalogoModelo {

    public MotivoBloqueoModelo() {
    }

    public MotivoBloqueoModelo(CatalagoEntidad catalagoEntidad) {
        super(catalagoEntidad);
    }

    public MotivoBloqueoModelo(Short clave) {
        super(clave);
    }
}
