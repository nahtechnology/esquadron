package tecolotl.profesor.entidad;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "grupo", schema = "profesor")
@SequenceGenerator(name = "generador_secuencia", sequenceName = "grupo_seq", schema = "profesor")
@NamedQueries({
    @NamedQuery(name ="GrupoEntidad.busca", query = "SELECT g FROM GrupoEntidad g"),
    @NamedQuery(
        name = "GrupoEntidad.buscaProfesor",
        query = "SELECT g FROM GrupoEntidad g JOIN FETCH g.cicloEscolarEntidad ce WHERE g.profesorEntidad.id = :idProfesor AND g.cicloEscolarEntidad.activo = TRUE")
})
public class GrupoEntidad {

    private Integer id;
    private Short grado;
    private Character grupo;
    private ProfesorEntidad profesorEntidad;
    private CicloEscolarEntidad cicloEscolarEntidad;

    public GrupoEntidad() {
    }

    public GrupoEntidad(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generador_secuencia")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @NotNull
    @Column(name = "grado")
    public Short getGrado() {
        return grado;
    }

    public void setGrado(Short grado) {
        this.grado = grado;
    }

    @NotNull
    @Basic
    @Column(name = "grupo")
    public Character getGrupo() {
        return grupo;
    }

    public void setGrupo(Character grupo) {
        this.grupo = grupo;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profesor")
    public ProfesorEntidad getProfesorEntidad() {
        return profesorEntidad;
    }

    public void setProfesorEntidad(ProfesorEntidad profesorEntidad) {
        this.profesorEntidad = profesorEntidad;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns(value = {
            @JoinColumn(name = "inicio", referencedColumnName = "inicio"),
            @JoinColumn(name = "fin", referencedColumnName = "fin"),
            @JoinColumn(name = "id_escuela", referencedColumnName = "id_escuela")
    })
    public CicloEscolarEntidad getCicloEscolarEntidad() {
        return cicloEscolarEntidad;
    }

    public void setCicloEscolarEntidad(CicloEscolarEntidad cicloEscolarEntidad) {
        this.cicloEscolarEntidad = cicloEscolarEntidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrupoEntidad that = (GrupoEntidad) o;
        return id.equals(that.id) &&
                grado.equals(that.grado) &&
                grupo.equals(that.grupo) &&
                profesorEntidad.equals(that.profesorEntidad) &&
                cicloEscolarEntidad.equals(that.cicloEscolarEntidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, grado, grupo, profesorEntidad, cicloEscolarEntidad);
    }

    @Override
    public String toString() {
        return "GrupoEntidad{" +
                "id=" + id +
                ", grado=" + grado +
                ", grupo=" + grupo +
                ", profesorEntidad=" + profesorEntidad +
                ", cicloEscolarEntidad=" + cicloEscolarEntidad +
                '}';
    }
}
