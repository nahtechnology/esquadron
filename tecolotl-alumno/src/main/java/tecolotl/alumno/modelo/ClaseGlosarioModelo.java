package tecolotl.alumno.modelo;

import tecolotl.nucleo.modelo.CatalogoModelo;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;

public class ClaseGlosarioModelo extends CatalogoModelo {
    public ClaseGlosarioModelo() {
    }

    public ClaseGlosarioModelo(CatalagoEntidad catalagoEntidad) {
        super(catalagoEntidad);
    }

    public ClaseGlosarioModelo(Short clave) {
        super(clave);
    }

    public ClaseGlosarioModelo(Short clave, String valor) {
        super(clave, valor);
    }
}
