package tecolotl.alumno.entidad;

import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;

import javax.persistence.*;

@Entity
@Table(name = "clase_glosario", schema = "alumno")
@SequenceGenerator(name = "generadorDefault", schema = "alumno", sequenceName = "clase_glosario_seq")
@NamedQueries(
        @NamedQuery(name = "ClaseGlosarioEntidad.busca", query = "SELECT cg FROM ClaseGlosarioEntidad cg")
)
public class ClaseGlosarioEntidad extends CatalagoEntidad {
    public ClaseGlosarioEntidad() {
    }
    public ClaseGlosarioEntidad(Short clave) {
        super(clave);
    }
}
