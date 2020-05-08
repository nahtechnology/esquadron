package tecolotl.alumno.entidad;

import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;

import javax.persistence.*;

@Entity
@Table(name = "nivel_lenguaje", schema = "alumno")
@SequenceGenerator(name = "generador_defecto", sequenceName = "nivel_lenguaje_seq", schema = "alumno")
@NamedQueries(value = {
        @NamedQuery(name = "NivelLenguajeEntidad.busca", query = "SELECT nl FROM NivelLenguajeEntidad nl"),
        @NamedQuery(
                name = "NivelLenguajeEntidad.buscaLlave",
                query = "SELECT nl.clave FROM NivelLenguajeEntidad nl WHERE nl.valor = :valor")
})
public class NivelLenguajeEntidad extends CatalagoEntidad {

    public NivelLenguajeEntidad() {
    }

    public NivelLenguajeEntidad(Short clave) {
        super(clave);
    }

}
