package tecolotl.administracion.persistencia.entidad;

import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tipo_contacto", schema = "administracion")
@SequenceGenerator(name = "generador_defecto", sequenceName = "tipo_contacto_seq", schema = "administracion", allocationSize = 1)
@NamedQuery(
        name = "TipoContactoEntidad.busca",
        query = "SELECT tc FROM TipoContactoEntidad tc ORDER BY tc.valor"
)
public class TipoContactoEntidad extends CatalagoEntidad {

    public TipoContactoEntidad() {
    }

    public TipoContactoEntidad(Short clave) {
        super(clave);
    }

}

