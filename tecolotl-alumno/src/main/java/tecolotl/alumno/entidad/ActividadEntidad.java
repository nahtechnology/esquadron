package tecolotl.alumno.entidad;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "actividad", schema = "alumno")
@NamedQueries({
        @NamedQuery(
                name = "ActividadEntidad.busca",
                query = "SELECT a FROM ActividadEntidad a JOIN FETCH a.tipoEstudianteEntidad JOIN FETCH a.nivelLenguajeEntidad JOIN FETCH a.temaEntidad"
        ),
        @NamedQuery(
                name = "ActividadEntidad.buscaNivelLenguaje",
                query = "SELECT a FROM ActividadEntidad a JOIN FETCH a.tipoEstudianteEntidad JOIN FETCH a.temaEntidad t " +
                        "JOIN FETCH a.nivelLenguajeEntidad nl WHERE nl.valor = :nivelLenguaje"
        ),
        @NamedQuery(
                name = "ActividadEntidad.transcripcion" ,
                query = "SELECT a.trasncripcion FROM ActividadEntidad a WHERE a.id = :idActividad"
        ),
        @NamedQuery(
                name = "ActividadEntidad.elimina",
                query = "DELETE FROM ActividadEntidad a WHERE a.id = :idAvtividad"
        )
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
    private List<NivelLenguajeEntidad> nivelLenguajeEntidad;

    public ActividadEntidad() {
    }

    public ActividadEntidad(String id) {
        this.id = id;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_estudiante")
    public TipoEstudianteEntidad getTipoEstudianteEntidad() {
        return tipoEstudianteEntidad;
    }

    public void setTipoEstudianteEntidad(TipoEstudianteEntidad tipoEstudianteEntidad) {
        this.tipoEstudianteEntidad = tipoEstudianteEntidad;
    }

    @ManyToMany(fetch = FetchType.LAZY)
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
}
