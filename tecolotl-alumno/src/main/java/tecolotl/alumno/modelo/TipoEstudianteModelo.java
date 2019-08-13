package tecolotl.alumno.modelo;

import tecolotl.nucleo.modelo.CatalogoModelo;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;

public class TipoEstudianteModelo extends CatalogoModelo {
    public TipoEstudianteModelo() {
    }

    public TipoEstudianteModelo(CatalagoEntidad catalagoEntidad) {
        super(catalagoEntidad);
    }

    public TipoEstudianteModelo(Short clave) {
        super(clave);
    }

    public TipoEstudianteModelo(Short clave, String valor) {
        super(clave, valor);
    }
}
