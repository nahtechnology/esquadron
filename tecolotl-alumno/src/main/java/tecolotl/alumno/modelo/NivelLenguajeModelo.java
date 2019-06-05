package tecolotl.alumno.modelo;

import tecolotl.alumno.entidad.NivelLenguajeEntidad;
import tecolotl.nucleo.modelo.CatalogoModelo;

public class NivelLenguajeModelo extends CatalogoModelo {

    public NivelLenguajeModelo() {
    }

    public NivelLenguajeModelo(NivelLenguajeEntidad nivelLenguajeEntidad) {
        super(nivelLenguajeEntidad);
    }

    public NivelLenguajeModelo(Short clave) {
        super(clave);
    }

    public NivelLenguajeModelo(Short clave, String valor) {
        super(clave, valor);
    }
}
