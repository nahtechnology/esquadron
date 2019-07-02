package tecolotl.profesor.entidad;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "grupo", schema = "profesor")
@SequenceGenerator(name = "GeneradorATM", schema = "profesor", sequenceName = "grupo_seq")
@NamedQueries({
    @NamedQuery(name ="GrupoEntidad.busca",query = "SELECT g FROM GrupoEntidad g")
})
public class GrupoEntidad {

    private Integer id;
    private Short grado;
    private Character grupo;
    private Date inicio;
    private Date fin;
    private ProfesorEntidad profesorEntidad;
    private List<GrupoAlumnoEntidad> grupoAlumnoEntidadLista;

    public GrupoEntidad() {
    }

    public GrupoEntidad(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(generator = "GeneradorATM", strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @NotNull
    public Short getGrado() {
        return grado;
    }

    public void setGrado(Short grado) {
        this.grado = grado;
    }

    @NotNull
    public Character getGrupo() {
        return grupo;
    }

    public void setGrupo(Character grupo) {
        this.grupo = grupo;
    }

    @NotNull
    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    @NotNull
    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "id_profesor")
    public ProfesorEntidad getProfesorEntidad() {
        return profesorEntidad;
    }

    public void setProfesorEntidad(ProfesorEntidad profesorEntidad) {
        this.profesorEntidad = profesorEntidad;
    }

    @OneToMany(mappedBy = "grupoAlumnoEntidadPK.grupoEntidad")
    public List<GrupoAlumnoEntidad> getGrupoAlumnoEntidadLista() {
        return grupoAlumnoEntidadLista;
    }

    public void setGrupoAlumnoEntidadLista(List<GrupoAlumnoEntidad> grupoAlumnoEntidadLista) {
        this.grupoAlumnoEntidadLista = grupoAlumnoEntidadLista;
    }
}
