package tecolotl.alumno.modelo.glosario;

import tecolotl.alumno.entidad.glosario.ClaseGlosarioEntidad;
import tecolotl.nucleo.modelo.CatalogoModelo;

public class ClaseGlosarioModelo extends CatalogoModelo {
    public ClaseGlosarioModelo() {
    }

    public ClaseGlosarioModelo(ClaseGlosarioEntidad claseGlosarioEntidad) {
        super(claseGlosarioEntidad);
    }

    public ClaseGlosarioModelo(Short clave) {
        super(clave);
    }

    public ClaseGlosarioModelo(Short clave, String valor) {
        super(clave, valor);
    }
}
