package tecolotl.profesor.entidad;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "grupo", schema = "profesor")
@NamedQueries({
    @NamedQuery(name ="GrupoEntidad.busca", query = "SELECT g FROM GrupoEntidad g"),
    @NamedQuery(
        name = "GrupoEntidad.buscaProfesor",
        query = "SELECT g FROM GrupoEntidad g JOIN FETCH g.cicloEscolarEntidad ce WHERE g.profesorEntidad.id = :idProfesor AND g.cicloEscolarEntidad.activo = TRUE"),
    @NamedQuery(
        name = "GrupoAlumnoEntidad.buscaAlumno",
        query = "SELECT g FROM GrupoEntidad g JOIN g.profesorEntidad"),
    @NamedQuery(
        name = "GrupoEntidad.buscaTotalGrupos",
        query = "SELECT COUNT(ge.id), gae.grupoAlumnoEntidadPK.alumnoEntidad FROM GrupoEntidad ge JOIN ge.grupoAlumnoEntidad gae " +
                "WHERE ge.profesorEntidad.id = :idprofesor GROUP BY ge.id"),
    @NamedQuery(
        name = "GrupoEntidad.buscaCiclioEscolar",
        query = "SELECT g FROM GrupoEntidad g WHERE g.cicloEscolarEntidad.cicloEscolarPK.inicio = :inicio AND " +
                "g.cicloEscolarEntidad.cicloEscolarPK.fin = :fin AND g.cicloEscolarEntidad.cicloEscolarPK.escuelaEntidad.claveCentroTrabajo = :claveCentroTrabajo AND " +
                "g.profesorEntidad.id = :idProfesor"),
    @NamedQuery(
        name = "GrupoEntidad.buscaAlumnoApodo",
        query = "SELECT count(a.id) FROM GrupoEntidad g JOIN g.grupoAlumnoEntidad ga JOIN ga.grupoAlumnoEntidadPK.alumnoEntidad a " +
                "WHERE g.profesorEntidad.escuelaEntidad.claveCentroTrabajo = :claveCentroTrabajo AND a.apodo = :apodo"),
    @NamedQuery(
        name = "GrupoEntidad.buscaCiclioEscolarTotalAlumno",
        query = "SELECT new GrupoEntidad(g.id, g.grado, g.grupo, COUNT(ga.grupoAlumnoEntidadPK.alumnoEntidad.id)) FROM GrupoEntidad g LEFT JOIN g.grupoAlumnoEntidad ga " +
                "WHERE g.cicloEscolarEntidad.cicloEscolarPK.inicio = :inicio AND g.cicloEscolarEntidad.cicloEscolarPK.fin = :fin AND " +
                "g.cicloEscolarEntidad.cicloEscolarPK.escuelaEntidad.claveCentroTrabajo = :claveCentroTrabajo AND g.profesorEntidad.id = :idProfesor GROUP BY g.id, g.grado, g.grupo"),
    @NamedQuery(
        name = "GrupoEntidad.cuentaPorProfesor",
        query = "SELECT g.profesorEntidad.id FROM GrupoEntidad g WHERE g.id = :idGrupo"
    )
})
public class GrupoEntidad {

    private UUID id;
    private Short grado;
    private Character grupo;
    private ProfesorEntidad profesorEntidad;
    private int totalAlumno;
    private CicloEscolarEntidad cicloEscolarEntidad;
    private List<GrupoAlumnoEntidad> grupoAlumnoEntidad;

    public GrupoEntidad() {
    }

    public GrupoEntidad(UUID id) {
        this.id = id;
    }

    public GrupoEntidad(UUID id, Short grado, Character grupo, Long totalAlumno) {
        this.id = id;
        this.grado = grado;
        this.grupo = grupo;
        this.totalAlumno = totalAlumno.intValue();
    }

    @Id
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    @OneToMany(mappedBy = "grupoAlumnoEntidadPK.grupoEntidad")
    public List<GrupoAlumnoEntidad> getGrupoAlumnoEntidad() {
        return grupoAlumnoEntidad;
    }

    public void setGrupoAlumnoEntidad(List<GrupoAlumnoEntidad> grupoAlumnoEntidad) {
        this.grupoAlumnoEntidad = grupoAlumnoEntidad;
    }

    @Transient
    public int getTotalAlumno() {
        return totalAlumno;
    }

    public void setTotalAlumno(int totalAlumno) {
        this.totalAlumno = totalAlumno;
    }

    @PrePersist
    public void creaUUID(){
        this.id = UUID.randomUUID();
    }

}
