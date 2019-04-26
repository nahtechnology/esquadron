package tecolotl.alumno.entidad;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "actividad", schema = "alumno")
@SequenceGenerator(name = "generador",sequenceName = "actividad_seq", schema = "alumno")
@NamedQueries({
        @NamedQuery(name = "ActividadEntidad.busca", query = "SELECT a FROM ActividadEntidad a "
        )
})
public class ActividadEntidad {

    private Integer id;
    private String idVideo;
    private Integer tiempo;
    private String preguntaDetonadora;
    private String lenguaje;
    private String trasncipcion;
    private TipoEstudianteEntidad tipoEstudianteEntidad;
    private List<NivelLenguajeEntidad> nivelLenguajeEntidad;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "generador")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "id_video", unique = true)
    @NotNull
    @Size(min = 11, max = 11)
    public String getIdVideo() {
        return idVideo;
    }

    public void setIdVideo(String idVideo) {
        this.idVideo = idVideo;
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

    @Basic
    @Column(name = "transcripcion")
    @NotNull
    @Size(min = 2)
    public String getTrasncipcion() {
        return trasncipcion;
    }

    public void setTrasncipcion(String trasncipcion) {
        this.trasncipcion = trasncipcion;
    }

    @ManyToOne
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