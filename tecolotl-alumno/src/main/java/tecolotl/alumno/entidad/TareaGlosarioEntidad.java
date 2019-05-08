package tecolotl.alumno.entidad;

import com.sun.org.apache.bcel.internal.generic.Select;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tarea_glosario", schema = "alumno")
@NamedQueries({
        @NamedQuery(name = "TareaGlosarioEntidad.busca", query = "SELECT a FROM TareaGlosarioEntidad a"),
        @NamedQuery(name = "TareaGlosarioEntidad.buscaTarea", query = "SELECT b FROM TareaGlosarioEntidad b WHERE b.tareaGlosarioEntidadPK.tareaEntidad.id = :id"),
        @NamedQuery(name = "TareaGlosarioEntidad.buscaGlosario", query = "SELECT c FROM TareaGlosarioEntidad c WHERE c.tareaGlosarioEntidadPK.glosarioEntidad.palabra = :palabra")
})
public class TareaGlosarioEntidad {
    private TareaGlosarioEntidadPK tareaGlosarioEntidadPK;
    private char respuesta;

    @EmbeddedId
    public TareaGlosarioEntidadPK getTareaGlosarioEntidadPK() {
        return tareaGlosarioEntidadPK;
    }

    public void setTareaGlosarioEntidadPK(TareaGlosarioEntidadPK tareaGlosarioEntidadPK) {
        this.tareaGlosarioEntidadPK = tareaGlosarioEntidadPK;
    }

    @Basic
    @Column(name = "respuesta")
    @NotNull
    @Size(max = 32)
    public char getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(char respuesta) {
        this.respuesta = respuesta;
    }
}
