package tecolotl.nucleo.persistencia.entidad;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "video", schema = "nucleo")
@NamedQuery(name = "VideoEntidad.busca", query = "SELECT v FROM VideoEntidad v")
public class VideoEntidad extends ActividadEntidad implements Serializable {

    private VideoEntidadPK id;
    private int reproducciones;
    private List<TemaEntidad> temaList;

    @EmbeddedId
    public VideoEntidadPK getId() {
        return id;
    }

    public void setId(VideoEntidadPK id) {
        this.id = id;
    }

    @Basic
    @Column(name = "reproducciones")
    public int getReproducciones() {
        return reproducciones;
    }

    public void setReproducciones(int reproducciones) {
        this.reproducciones = reproducciones;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "actividad_video_tema",
            schema = "nucleo",
            joinColumns = {
                    @JoinColumn(name = "id_video", referencedColumnName = "id"),
                    @JoinColumn(name = "id_nivel_lenguaje", referencedColumnName = "id_nivel_lenguaje"),
                    @JoinColumn(name = "id_lenguaje", referencedColumnName = "id_lenguaje")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "id_tema", referencedColumnName = "id")
            }
    )
    public List<TemaEntidad> getTemaList() {
        return temaList;
    }

    public void setTemaList(List<TemaEntidad> temaList) {
        this.temaList = temaList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VideoEntidad that = (VideoEntidad) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
