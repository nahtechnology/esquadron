package tecolotl.administracion.modelo.escuela;

import tecolotl.administracion.persistencia.entidad.TipoContactoEntidad;
import tecolotl.nucleo.modelo.CatalogoModelo;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;

public class TipoContactoModelo extends CatalogoModelo {

    public TipoContactoModelo() {
    }

    public TipoContactoModelo(TipoContactoEntidad tipoContactoEntidad) {
        super(tipoContactoEntidad);
    }

    public TipoContactoModelo(Short clave) {
        super(clave);
    }

    public TipoContactoModelo(Short clave, String valor) {
        super(clave, valor);
    }
}
