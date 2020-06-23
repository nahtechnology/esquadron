package tecolotl.nucleo.persistencia.entidad;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tipo_registro", schema = "nucleo")
@SequenceGenerator(name = "secuencia_tipo", schema = "nucleo", sequenceName = "tipo_registro_sec")
public class TipoRegistroEntidad extends CatalagoEntidad {

    public TipoRegistroEntidad() {
    }

    public TipoRegistroEntidad(Short clave) {
        super(clave);
    }

}
