package tecolotl.alumno.modelo;

import tecolotl.alumno.entidad.TipoEstudianteEntidad;
import tecolotl.nucleo.modelo.CatalogoModelo;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;

public class TipoEstudianteModelo extends CatalogoModelo {
    public TipoEstudianteModelo() {
    }

    public TipoEstudianteModelo(TipoEstudianteEntidad tipoEstudianteEntidad) {
        super(tipoEstudianteEntidad);
    }

    public TipoEstudianteModelo(Short clave) {
        super(clave);
    }

    public TipoEstudianteModelo(Short clave, String valor) {
        super(clave, valor);
    }
}
