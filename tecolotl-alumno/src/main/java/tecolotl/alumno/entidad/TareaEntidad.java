package tecolotl.alumno.entidad;

import tecolotl.alumno.entidad.escribir.TareaEscribirActividadEntidad;
import tecolotl.alumno.entidad.glosario.TareaGlosarioActividadEntidad;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tarea", schema = "alumno")
@SequenceGenerator(name = "generador_automatico", sequenceName = "tarea_seq", schema = "alumno")
@NamedQueries({
    @NamedQuery(name = "TareaEntidad.busca", query = "SELECT t FROM TareaEntidad t"),
    @NamedQuery(
        name = "TareaEntidad.buscaId",
        query = "SELECT t FROM TareaEntidad t WHERE t.id = :idTarea")
})
public class TareaEntidad {

    private Integer id;
    private Date asignacion;
    private List<TareaGlosarioActividadEntidad> tareaGlosarioActividadEntidadLista;
    private List<TareaEscribirActividadEntidad> tareaEscribirActividadEntidadLista;
    private TareaVideoEntidad tareaVideoEntidad;

    public TareaEntidad() {
    }

    public TareaEntidad(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generador_automatico")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "asignacion", insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getAsignacion() {
        return asignacion;
    }

    public void setAsignacion(Date asignacion) {
        this.asignacion = asignacion;
    }

    @OneToMany(mappedBy = "tareaGlosarioActividadEntidadPK.tareaEntidad", cascade = {CascadeType.PERSIST})
    public List<TareaGlosarioActividadEntidad> getTareaGlosarioActividadEntidadLista() {
        return tareaGlosarioActividadEntidadLista;
    }

    public void setTareaGlosarioActividadEntidadLista(List<TareaGlosarioActividadEntidad> tareaGlosarioActividadEntidadLista) {
        this.tareaGlosarioActividadEntidadLista = tareaGlosarioActividadEntidadLista;
    }

    @OneToMany(mappedBy = "tareaEntidad", cascade = CascadeType.PERSIST)
    public List<TareaEscribirActividadEntidad> getTareaEscribirActividadEntidadLista() {
        return tareaEscribirActividadEntidadLista;
    }

    public void setTareaEscribirActividadEntidadLista(List<TareaEscribirActividadEntidad> tareaEscribirActividadEntidadLista) {
        this.tareaEscribirActividadEntidadLista = tareaEscribirActividadEntidadLista;
    }


}
