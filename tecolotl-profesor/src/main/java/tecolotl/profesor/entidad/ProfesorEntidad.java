package tecolotl.profesor.entidad;

import tecolotl.administracion.persistencia.entidad.EscuelaEntidad;
import tecolotl.nucleo.persistencia.entidad.PersonaEntidad;

import javax.persistence.*;

@Entity
@Table(schema = "profesor", name = "profesor")
@SequenceGenerator(name = "secuencia", schema = "profesor", sequenceName = "profesor_seq")
@NamedQueries({
        @NamedQuery(name = "ProfesorEntidad.busca", query = "SELECT p FROM ProfesorEntidad p")
})
public class ProfesorEntidad extends PersonaEntidad {

    private Integer id;
    private EscuelaEntidad escuelaEntidad;

    @Id
    @GeneratedValue(generator = "secuencia", strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "id_escuela")
    public EscuelaEntidad getEscuelaEntidad() {
        return escuelaEntidad;
    }

    public void setEscuelaEntidad(EscuelaEntidad escuelaEntidad) {
        this.escuelaEntidad = escuelaEntidad;
    }
}
