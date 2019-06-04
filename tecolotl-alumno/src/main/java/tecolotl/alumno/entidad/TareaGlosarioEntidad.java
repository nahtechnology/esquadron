package tecolotl.alumno.entidad;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tarea_glosario", schema = "alumno")
@NamedQueries({
        @NamedQuery(name = "TareaGlosarioEntidad.busca", query = "SELECT a FROM TareaGlosarioEntidad a")
        /*@NamedQuery(name = "TareaGlosarioEntidad.buscaTarea", query = "SELECT b FROM TareaGlosarioEntidad b WHERE b.tareaGlosarioEntidadPK.tareaEntidad.id = :id"),
        @NamedQuery(name = "TareaGlosarioEntidad.buscaGlosario", query = "SELECT c FROM TareaGlosarioEntidad c WHERE c.tareaGlosarioEntidadPK.glosarioEntidad.palabra = :palabra")*/
})
public class TareaGlosarioEntidad {

    private TareaGlosarioEntidadPK tareaGlosarioEntidadPK;
    private TareaEntidad tareaEntidad;
    private GlosarioEntidad glosarioEntidad;
    private char respuesta;

    @EmbeddedId
    public TareaGlosarioEntidadPK getTareaGlosarioEntidadPK() {
        return tareaGlosarioEntidadPK;
    }

    public void setTareaGlosarioEntidadPK(TareaGlosarioEntidadPK tareaGlosarioEntidadPK) {
        this.tareaGlosarioEntidadPK = tareaGlosarioEntidadPK;
    }

    @ManyToOne
    @MapsId("tareaEntidad")
    @JoinColumn(name = "id_tarea")
    public TareaEntidad getTareaEntidad() {
        return tareaEntidad;
    }

    public void setTareaEntidad(TareaEntidad tareaEntidad) {
        this.tareaEntidad = tareaEntidad;
    }

    @OneToOne
    @MapsId("glosarioEntidad")
    @JoinColumn(name = "id_glosario")
    public GlosarioEntidad getGlosarioEntidad() {
        return glosarioEntidad;
    }

    public void setGlosarioEntidad(GlosarioEntidad glosarioEntidad) {
        this.glosarioEntidad = glosarioEntidad;
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
