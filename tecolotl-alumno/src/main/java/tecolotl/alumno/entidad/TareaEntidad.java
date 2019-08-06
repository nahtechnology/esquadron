package tecolotl.alumno.entidad;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.StringJoiner;

@Entity
@Table(name = "tarea", schema = "alumno")
@SequenceGenerator(name = "generador_automatico", sequenceName = "tarea_seq", schema = "alumno")
@NamedQueries({
        @NamedQuery(name = "TareaEntidad.busca", query = "SELECT a FROM TareaEntidad a"),
        @NamedQuery(
                name="TareaEntidad.buscaPorAlumno",
                query="SELECT t FROM " +
                        "TareaEntidad t INNER JOIN FETCH " +
                        "t.tareaGlosarioActividadEntidadList tgae INNER JOIN FETCH " +
                        "tgae.tareaGlosarioActividadEntidadPK.actividadEntidad a INNER JOIN FETCH " +
                        "tgae.tareaGlosarioActividadEntidadPK.glosarioEntidad WHERE t.alumnoEntidad.id = :idAlumno"
        )
})
public class TareaEntidad {

    private Integer id;
    private AlumnoEntidad alumnoEntidad;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_alumno")
    public AlumnoEntidad getAlumnoEntidad() {
        return alumnoEntidad;
    }

    public void setAlumnoEntidad(AlumnoEntidad alumnoEntidad) {
        this.alumnoEntidad = alumnoEntidad;
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

    //Aquí modificar también
    @OneToMany(mappedBy = "")
    public TareaEscribirActividadEntidad getTareaEscribirActividadEntidad() {
        return tareaEscribirActividadEntidad;
    }

    public void setTareaEscribirActividadEntidad(TareaEscribirActividadEntidad tareaEscribirActividadEntidad) {
        this.tareaEscribirActividadEntidad = tareaEscribirActividadEntidad;
    }

    @OneToMany(mappedBy = "")
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
                .add("alumnoEntidad=" + alumnoEntidad)
                .add("asignacion=" + asignacion)
                .add("tareaGlosarioActividadEntidad=" + tareaGlosarioActividadEntidad)
                .add("tareaEscribirEntidad=" + tareaEscribirActividadEntidad)
                .add("tareaVideoEntidad=" + tareaVideoEntidad)
                .toString();
    }
}
