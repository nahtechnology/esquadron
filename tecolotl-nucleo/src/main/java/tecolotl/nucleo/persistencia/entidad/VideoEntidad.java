package tecolotl.nucleo.persistencia.entidad;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "video", schema = "nucleo")
@NamedQuery(name = "VideoEntidad.busca", query = "SELECT v FROM VideoEntidad v")
@IdClass(VideoEntidadPK.class)
public class VideoEntidad extends ActividadEntidad{

    private int id;
    private int reproducciones;
    private List<TemaEntidad> temaList;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}
