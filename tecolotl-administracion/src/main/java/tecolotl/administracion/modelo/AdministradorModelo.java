package tecolotl.administracion.modelo;

import tecolotl.administracion.persistencia.entidad.AdministradorEntidad;
import tecolotl.nucleo.modelo.PersonaModelo;

public class AdministradorModelo extends PersonaModelo {

    public AdministradorModelo() {
    }

    public AdministradorModelo(AdministradorEntidad administradorEntidad) {
        super(administradorEntidad);
    }
}
