package tecolotl.alumno.entidad;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

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
    private List<TareaGlosarioActividadEntidad> tareaGlosarioActividadEntidadList;

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

    @OneToMany(mappedBy = "tareaGlosarioActividadEntidadPK.tareaEntidad")
    public List<TareaGlosarioActividadEntidad> getTareaGlosarioActividadEntidadList() {
        return tareaGlosarioActividadEntidadList;
    }

    public void setTareaGlosarioActividadEntidadList(List<TareaGlosarioActividadEntidad> tareaGlosarioActividadEntidadList) {
        this.tareaGlosarioActividadEntidadList = tareaGlosarioActividadEntidadList;
    }
}
