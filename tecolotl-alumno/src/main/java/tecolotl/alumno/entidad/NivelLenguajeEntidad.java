package tecolotl.alumno.entidad;

import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "nivel_lenguaje", schema = "alumno")
@SequenceGenerator(name = "generador_defecto", sequenceName = "nivel_lenguaje_seq", schema = "alumno")
@NamedQuery(name = "NivelLenguajeEntidad.busca", query = "SELECT nl FROM NivelLenguajeEntidad nl")
public class NivelLenguajeEntidad extends CatalagoEntidad {

    public NivelLenguajeEntidad() {
    }

    public NivelLenguajeEntidad(Short clave) {
        super(clave);
    }

}
