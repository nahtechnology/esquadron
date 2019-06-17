package tecolotl.administracion.modelo.coordinador;

import tecolotl.administracion.persistencia.entidad.CoordinadorMotivoBloqueoEntidad;
import tecolotl.nucleo.modelo.CatalogoModelo;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;

public class CoordinadorMotivoBloqueoModelo extends CatalogoModelo {

    public CoordinadorMotivoBloqueoModelo() {
    }

    public CoordinadorMotivoBloqueoModelo(CoordinadorMotivoBloqueoEntidad catalagoEntidad) {
        super(catalagoEntidad);
    }

    public CoordinadorMotivoBloqueoModelo(Short clave) {
        super(clave);
    }

    public CoordinadorMotivoBloqueoModelo(Short clave, String valor) {
        super(clave, valor);
    }
}
