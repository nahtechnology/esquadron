package tecolotl.alumno.entidad;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tarea", schema = "alumno")
@SequenceGenerator(name = "generador_automatico", sequenceName = "tarea_seq", schema = "alumno")
@NamedQueries({
        @NamedQuery(name = "TareaEntidad.busca", query = "SELECT a FROM TareaEntidad a")/*,
        @NamedQuery(
                name="TareaEntidad.buscaTareas",
                query="SELECT a FROM TareaEntidad a LEFT JOIN FETCH a.tareaGlosarioEntidad x where a.id= :id"
        )*/
})
public class TareaEntidad {
    private Integer id;
    private AlumnoEntidad alumnoEntidad;
    private Date asignacion;
    //private List<TareaGlosarioEntidad> tareaGlosarioEntidadLista;
    //private List<TareaVideoEntidad> tareaVideoEntidadLista;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "generador_automatico")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "id_alumno")
    public AlumnoEntidad getAlumnoEntidad() {
        return alumnoEntidad;
    }

    public void setAlumnoEntidad(AlumnoEntidad alumnoEntidad) {
        this.alumnoEntidad = alumnoEntidad;
    }

    @Basic
    @JoinColumn(name = "asignacion")
    @NotNull
    public Date getAsignacion() {
        return asignacion;
    }

    public void setAsignacion(Date asignacion) {
        this.asignacion = asignacion;
    }

    /*@OneToMany(mappedBy = "tareaEntidad")
    public List<TareaGlosarioEntidad> getTareaGlosarioEntidadLista() {
        return tareaGlosarioEntidadLista;
    }

    public void setTareaGlosarioEntidadLista(List<TareaGlosarioEntidad> tareaGlosarioEntidadLista) {
        this.tareaGlosarioEntidadLista = tareaGlosarioEntidadLista;
    }

    @OneToMany(mappedBy = "")
    public List<TareaVideoEntidad> getTareaVideoEntidadLista() {
        return tareaVideoEntidadLista;
    }

    public void setTareaVideoEntidadLista(List<TareaVideoEntidad> tareaVideoEntidadLista) {
        this.tareaVideoEntidadLista = tareaVideoEntidadLista;
    }*/
}
