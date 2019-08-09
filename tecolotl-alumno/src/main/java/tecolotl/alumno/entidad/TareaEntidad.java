package tecolotl.alumno.entidad;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.StringJoiner;

@Entity
@Table(name = "tarea", schema = "alumno")
@SequenceGenerator(name = "generador_automatico", sequenceName = "tarea_seq", schema = "alumno")
@NamedQueries({
        @NamedQuery(name = "TareaEntidad.busca", query = "SELECT t FROM TareaEntidad t"),
})
public class TareaEntidad {

    private Integer id;
    private Date asignacion;
    private TareaGlosarioActividadEntidad tareaGlosarioActividadEntidad;
    private List<TareaEscribirActividadEntidad> tareaEscribirActividadEntidadLista;
    private TareaVideoEntidad tareaVideoEntidad;

    public TareaEntidad() {
    }

    public TareaEntidad(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "generador_automatico")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "asignacion")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    public Date getAsignacion() {
        return asignacion;
    }

    public void setAsignacion(Date asignacion) {
        this.asignacion = asignacion;
    }

    @OneToOne(mappedBy = "tareaGlosarioActividadEntidadPK.tareaEntidad")
    public TareaGlosarioActividadEntidad getTareaGlosarioActividadEntidad() {
        return tareaGlosarioActividadEntidad;
    }

    public void setTareaGlosarioActividadEntidad(TareaGlosarioActividadEntidad tareaGlosarioActividadEntidad) {
        this.tareaGlosarioActividadEntidad = tareaGlosarioActividadEntidad;
    }

    @OneToMany(mappedBy = "tareaEscribirActividadEntidadPK.tareaEntidad")
    public List<TareaEscribirActividadEntidad> getTareaEscribirActividadEntidadLista() {
        return tareaEscribirActividadEntidadLista;
    }

    public void setTareaEscribirActividadEntidadLista(List<TareaEscribirActividadEntidad> tareaEscribirActividadEntidadLista) {
        this.tareaEscribirActividadEntidadLista = tareaEscribirActividadEntidadLista;
    }

    /*
        //TODO Verificar si el Mappedby es correcto.
        @OneToMany(mappedBy = "tareaVideoEntidadPK.tareaEntidad")
        public TareaVideoEntidad getTareaVideoEntidad() {
            return tareaVideoEntidad;
        }

        public void setTareaVideoEntidad(TareaVideoEntidad tareaVideoEntidad) {
            this.tareaVideoEntidad = tareaVideoEntidad;
        }
    */
    @Override
    public String toString() {
        return new StringJoiner(", ", TareaEntidad.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("asignacion=" + asignacion)
                .add("tareaGlosarioActividadEntidad=" + tareaGlosarioActividadEntidad)
                .add("tareaEscribirActividadEntidad=" + tareaEscribirActividadEntidadLista)
                .add("tareaVideoEntidad=" + tareaVideoEntidad)
                .toString();
    }
}
