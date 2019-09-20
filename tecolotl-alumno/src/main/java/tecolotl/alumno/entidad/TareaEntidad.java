package tecolotl.alumno.entidad;

import tecolotl.alumno.entidad.mapamental.TareaMapaMentalActividadEntidad;
import tecolotl.alumno.entidad.glosario.TareaGlosarioActividadEntidad;
import tecolotl.alumno.entidad.relacionar.TareaRelacionarActividadEntidad;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tarea", schema = "alumno")
@SequenceGenerator(name = "generador_automatico", sequenceName = "tarea_seq", schema = "alumno")
@NamedQueries(value = {
        @NamedQuery(
                name = "TareaEntidad.busca",
                query = "SELECT t FROM TareaEntidad t"
        ),
        @NamedQuery(
                name = "TareaEntidad.buscaActividad",
                query = "SELECT t FROM TareaEntidad t JOIN t.tareaGlosarioActividadEntidadLista tga WHERE " +
                        "t.alumnoEntidad.id = :IdAlumno GROUP BY t"
        ),
        @NamedQuery(
                name = "TareaEntidad.aumentaReprodecciones",
                query = "UPDATE TareaEntidad t SET t.reproducciones = t.reproducciones + :reproducciones WHERE t.id = :idTarea"
        )
})
public class TareaEntidad {

    private Integer id;
    private Short reproducciones;
    private Date asignacion;
    private AlumnoEntidad alumnoEntidad;
    private List<TareaGlosarioActividadEntidad> tareaGlosarioActividadEntidadLista;
    private List<TareaMapaMentalActividadEntidad> tareaMapaMentalActividadEntidadLista;
    private List<TareaRelacionarActividadEntidad> tareaRelacionarActividadEntidadLista;

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
    @Column(name = "reproducciones")
    public Short getReproducciones() {
        return reproducciones;
    }

    public void setReproducciones(Short reproducciones) {
        this.reproducciones = reproducciones;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_alumno")
    public AlumnoEntidad getAlumnoEntidad() {
        return alumnoEntidad;
    }

    public void setAlumnoEntidad(AlumnoEntidad alumnoEntidad) {
        this.alumnoEntidad = alumnoEntidad;
    }

    @OneToMany(mappedBy = "tareaGlosarioActividadEntidadPK.tareaEntidad", cascade = {CascadeType.PERSIST})
    public List<TareaGlosarioActividadEntidad> getTareaGlosarioActividadEntidadLista() {
        return tareaGlosarioActividadEntidadLista;
    }

    public void setTareaGlosarioActividadEntidadLista(List<TareaGlosarioActividadEntidad> tareaGlosarioActividadEntidadLista) {
        this.tareaGlosarioActividadEntidadLista = tareaGlosarioActividadEntidadLista;
    }

    @OneToMany(mappedBy = "tareaMapaMentalActividadEntidadPK.tareaEntidad", cascade = CascadeType.PERSIST)
    public List<TareaMapaMentalActividadEntidad> getTareaMapaMentalActividadEntidadLista() {
        return tareaMapaMentalActividadEntidadLista;
    }

    public void setTareaMapaMentalActividadEntidadLista(List<TareaMapaMentalActividadEntidad> tareaMapaMentalActividadEntidadLista) {
        this.tareaMapaMentalActividadEntidadLista = tareaMapaMentalActividadEntidadLista;
    }

    @OneToMany(mappedBy = "tareaRelacionarActividadEntidadPK.tareaEntidad")
    public List<TareaRelacionarActividadEntidad> getTareaRelacionarActividadEntidadLista() {
        return tareaRelacionarActividadEntidadLista;
    }

    public void setTareaRelacionarActividadEntidadLista(List<TareaRelacionarActividadEntidad> tareaRelacionarActividadEntidadLista) {
        this.tareaRelacionarActividadEntidadLista = tareaRelacionarActividadEntidadLista;
    }
}
