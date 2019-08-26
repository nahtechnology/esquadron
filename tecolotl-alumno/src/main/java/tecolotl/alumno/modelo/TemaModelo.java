package tecolotl.alumno.modelo;

import tecolotl.alumno.entidad.TemaEntidad;
import tecolotl.nucleo.modelo.CatalogoModelo;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;

public class TemaModelo extends CatalogoModelo {
    public TemaModelo() {
    }

    public TemaModelo(TemaEntidad temaEntidad) {
        super(temaEntidad);
    }

    public TemaModelo(Short clave) {
        super(clave);
    }

    public TemaModelo(Short clave, String valor) {
        super(clave, valor);
    }
}
