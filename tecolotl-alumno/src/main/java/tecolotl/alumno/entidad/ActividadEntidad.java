package tecolotl.alumno.entidad;

import tecolotl.alumno.entidad.glosario.GlosarioActividadEntidad;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "actividad", schema = "alumno")
@NamedQueries({
        @NamedQuery(
                name = "ActividadEntidad.busca",
                query = "SELECT DISTINCT (a) FROM ActividadEntidad a JOIN FETCH a.tipoEstudianteEntidad JOIN FETCH a.nivelLenguajeEntidad JOIN FETCH a.temaEntidad"),
        @NamedQuery(
                name = "ActividadEntidad.buscaNivelLenguaje",
                query = "SELECT a FROM ActividadEntidad a JOIN FETCH a.tipoEstudianteEntidad JOIN FETCH a.temaEntidad t " +
                        "JOIN FETCH a.nivelLenguajeEntidad nl WHERE nl.valor = :nivelLenguaje"),
        @NamedQuery(
                name = "ActividadEntidad.transcripcion" ,
                query = "SELECT a.trasncripcion FROM ActividadEntidad a WHERE a.id = :idActividad"),
        @NamedQuery(
                name = "ActividadEntidad.elimina",
                query = "DELETE FROM ActividadEntidad a WHERE a.id = :idAvtividad"),
        @NamedQuery(
                name = "ActividadEntidad.buscaBibliotecaLibre",
                query = "SELECT a FROM ActividadEntidad a JOIN FETCH a.nivelLenguajeEntidad nl JOIN FETCH a.temaEntidad t WHERE a.id NOT IN " +
                        "(SELECT tga.tareaGlosarioActividadEntidadPK.glosarioActividadEntidad.glosarioActividadEntidadPK.actividadEntidad.id FROM " +
                        "TareaGlosarioActividadEntidad tga JOIN tga.tareaGlosarioActividadEntidadPK.tareaEntidad t WHERE t.alumnoEntidad.id = :idAlumno) AND " +
                        "a.estatus = TRUE"),
        @NamedQuery(
                name = "ActividadEntidad.buscaNoAsigandasAlumno",
                query = "SELECT NEW ActividadEntidad(a.id, a.preguntaDetonadora) FROM ActividadEntidad a JOIN a.nivelLenguajeEntidad nl WHERE nl.clave = :nivelLenguaje AND a.id NOT IN " +
                        "(SELECT tga.tareaGlosarioActividadEntidadPK.glosarioActividadEntidad.glosarioActividadEntidadPK.actividadEntidad.id FROM TareaGlosarioActividadEntidad tga JOIN " +
                        "tga.tareaGlosarioActividadEntidadPK.tareaEntidad t WHERE t.alumnoEntidad.id = :idAlumno)"),
        @NamedQuery(
                name = "ActividadEntidad.buscaEstatus",
                query = "SELECT new ActividadEntidad(e.id, e.estatus) FROM ActividadEntidad e"),
        @NamedQuery(
                name = "ActividadEntidad.actualizaEstatus",
                query = "UPDATE ActividadEntidad a SET a.estatus = :estatus WHERE a.id = :idVideo"),
        @NamedQuery(
                name = "ActividadEntidad.buscaLenguajePregunta",
                query = "SELECT a FROM ActividadEntidad a JOIN FETCH a.nivelLenguajeEntidad JOIN FETCH a.temaEntidad t " +
                        "WHERE (LOWER(a.lenguaje) LIKE :palabra OR LOWER(a.preguntaDetonadora) LIKE :palabra) AND a.estatus = TRUE ")
})
public class ActividadEntidad {

    private String id;
    private Short puntaje;
    private Integer tiempo;
    private String preguntaDetonadora;
    private String lenguaje;
    private String trasncripcion;
    private TipoEstudianteEntidad tipoEstudianteEntidad;
    private TemaEntidad temaEntidad;
    private Boolean estatus;
    private List<NivelLenguajeEntidad> nivelLenguajeEntidad;
    private List<GlosarioActividadEntidad> glosarioActividadEntidadLista;

    public ActividadEntidad() {
    }

    public ActividadEntidad(String id) {
        this.id = id;
    }

    public ActividadEntidad(String id, Boolean estatus) {
        this.id = id;
        this.estatus = estatus;
    }

    public ActividadEntidad(String id, String preguntaDetonadora) {
        this.id = id;
        this.preguntaDetonadora = preguntaDetonadora;
    }

    public ActividadEntidad(String id, String preguntaDetonadora, List<NivelLenguajeEntidad> nivelLenguajeEntidad) {
        this.id = id;
        this.preguntaDetonadora = preguntaDetonadora;
        this.nivelLenguajeEntidad = nivelLenguajeEntidad;
    }

    @Id
    @Column(name = "id_video")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "puntaje")
    @NotNull
    public Short getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Short puntaje) {
        this.puntaje = puntaje;
    }

    @Basic
    @Column(name = "tiempo")
    @NotNull
    @Min(0)
    public Integer getTiempo() {
        return tiempo;
    }

    public void setTiempo(Integer tiempo) {
        this.tiempo = tiempo;
    }

    @Basic
    @Column(name = "pregunta_detonadora")
    @NotNull
    @Size(min = 2, max = 100)
    public String getPreguntaDetonadora() {
        return preguntaDetonadora;
    }

    public void setPreguntaDetonadora(String preguntaDetonadora) {
        this.preguntaDetonadora = preguntaDetonadora;
    }

    @Basic
    @Column(name = "lenguaje")
    @NotNull
    @Size(min = 2, max = 200)
    public String getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "transcripcion")
    @NotNull
    @Size(min = 2)
    public String getTrasncripcion() {
        return trasncripcion;
    }

    public void setTrasncripcion(String trasncripcion) {
        this.trasncripcion = trasncripcion;
    }

    public Boolean getEstatus() {
        return estatus;
    }

    @Basic
    @Column(name = "estatus")
    public void setEstatus(Boolean estatus) {
        this.estatus = estatus;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_estudiante")
    public TipoEstudianteEntidad getTipoEstudianteEntidad() {
        return tipoEstudianteEntidad;
    }

    public void setTipoEstudianteEntidad(TipoEstudianteEntidad tipoEstudianteEntidad) {
        this.tipoEstudianteEntidad = tipoEstudianteEntidad;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "nivel_lenguaje_actividad",schema = "alumno",
            joinColumns = @JoinColumn(name = "id_actividad"),
            inverseJoinColumns = @JoinColumn(name = "id_nivel_lenguaje")
    )
    public List<NivelLenguajeEntidad> getNivelLenguajeEntidad() {
        return nivelLenguajeEntidad;
    }

    public void setNivelLenguajeEntidad(List<NivelLenguajeEntidad> nivelLenguajeEntidad) {
        this.nivelLenguajeEntidad = nivelLenguajeEntidad;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tema")
    public TemaEntidad getTemaEntidad() {
        return temaEntidad;
    }

    public void setTemaEntidad(TemaEntidad temaEntidad) {
        this.temaEntidad = temaEntidad;
    }

    @OneToMany(mappedBy = "glosarioActividadEntidadPK.actividadEntidad")
    public List<GlosarioActividadEntidad> getGlosarioActividadEntidadLista() {
        return glosarioActividadEntidadLista;
    }

    public void setGlosarioActividadEntidadLista(List<GlosarioActividadEntidad> glosarioActividadEntidadLista) {
        this.glosarioActividadEntidadLista = glosarioActividadEntidadLista;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActividadEntidad that = (ActividadEntidad) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ActividadEntidad.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("puntaje=" + puntaje)
                .add("tiempo=" + tiempo)
                .add("preguntaDetonadora='" + preguntaDetonadora + "'")
                .add("lenguaje='" + lenguaje + "'")
                .add("trasncripcion='" + trasncripcion + "'")
                .add("tipoEstudianteEntidad=" + tipoEstudianteEntidad)
                .add("temaEntidad=" + temaEntidad)
                .add("nivelLenguajeEntidad=" + nivelLenguajeEntidad)
                .toString();
    }
}
