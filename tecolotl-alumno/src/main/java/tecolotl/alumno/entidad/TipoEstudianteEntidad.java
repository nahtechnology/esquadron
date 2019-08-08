package tecolotl.alumno.entidad;

import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;

import javax.persistence.*;

@Entity
@Table(name = "tipo_estudiante", schema = "alumno")
@SequenceGenerator(name = "generador_defecto", schema = "alumno", sequenceName = "alumno.tipo_estudiante_seq")
@NamedQueries({
        @NamedQuery(name = "TipoEstudianteEntidad.busca", query = "SELECT t FROM TipoEstudianteEntidad t")
})
public class TipoEstudianteEntidad extends CatalagoEntidad {
    public TipoEstudianteEntidad() {
    }
    public TipoEstudianteEntidad(Short clave) {
        super(clave);
    }
}
