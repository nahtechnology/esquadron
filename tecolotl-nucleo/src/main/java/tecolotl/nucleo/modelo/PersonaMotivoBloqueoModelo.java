package tecolotl.nucleo.modelo;

import tecolotl.nucleo.persistencia.entidad.PersonaMotivoBloqueoEntidad;

public class PersonaMotivoBloqueoModelo extends CatalogoModelo {

    public PersonaMotivoBloqueoModelo() {
    }

    public PersonaMotivoBloqueoModelo(PersonaMotivoBloqueoEntidad personaMotivoBloqueoEntidad) {
        super(personaMotivoBloqueoEntidad);
    }

    public PersonaMotivoBloqueoModelo(Short clave) {
        super(clave);
    }

    public PersonaMotivoBloqueoModelo(Short clave, String valor) {
        super(clave, valor);
    }

}
