package tecolotl.alumno.entidad;

import tecolotl.alumno.entidad.escribir.TareaEscribirActividadEntidad;

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
    //    private TareaGlosarioActividadEntidad tareaGlosarioActividadEntidad;
    private List<TareaEscribirActividadEntidad> tareaEscribirActividadEntidadLista;
//    private TareaVideoEntidad tareaVideoEntidad;

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

    /*
        @OneToOne(mappedBy = "tareaGlosarioActividadEntidadPK.tareaEntidad")
        public TareaGlosarioActividadEntidad getTareaGlosarioActividadEntidad() {
            return tareaGlosarioActividadEntidad;
        }

        public void setTareaGlosarioActividadEntidad(TareaGlosarioActividadEntidad tareaGlosarioActividadEntidad) {
            this.tareaGlosarioActividadEntidad = tareaGlosarioActividadEntidad;
        }
    */
    @OneToMany(mappedBy = "tareaEntidad", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    public List<TareaEscribirActividadEntidad> getTareaEscribirActividadEntidadLista() {
        return tareaEscribirActividadEntidadLista;
    }

    public void setTareaEscribirActividadEntidadLista(List<TareaEscribirActividadEntidad> tareaEscribirActividadEntidadLista) {
        this.tareaEscribirActividadEntidadLista = tareaEscribirActividadEntidadLista;
    }

}
