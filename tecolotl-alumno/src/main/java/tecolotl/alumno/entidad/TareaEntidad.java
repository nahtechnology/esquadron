package tecolotl.alumno.entidad;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
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
    private TareaEscribirActividadEntidad tareaEscribirActividadEntidad;
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

    //TODO Verificar si el mappedBy es correcto.
    @OneToMany(mappedBy = "tareaEscribirActividadPK.tareaEntidad")
    public TareaEscribirActividadEntidad getTareaEscribirActividadEntidad() {
        return tareaEscribirActividadEntidad;
    }

    public void setTareaEscribirActividadEntidad(TareaEscribirActividadEntidad tareaEscribirActividadEntidad) {
        this.tareaEscribirActividadEntidad = tareaEscribirActividadEntidad;
    }

    //TODO Verificar si el Mappedby es correcto.
    @OneToMany(mappedBy = "tareaVideoEntidadPK.tareaEntidad")
    public TareaVideoEntidad getTareaVideoEntidad() {
        return tareaVideoEntidad;
    }

    public void setTareaVideoEntidad(TareaVideoEntidad tareaVideoEntidad) {
        this.tareaVideoEntidad = tareaVideoEntidad;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TareaEntidad.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("asignacion=" + asignacion)
                .add("tareaGlosarioActividadEntidad=" + tareaGlosarioActividadEntidad)
                .add("tareaEscribirActividadEntidad=" + tareaEscribirActividadEntidad)
                .add("tareaVideoEntidad=" + tareaVideoEntidad)
                .toString();
    }
}
