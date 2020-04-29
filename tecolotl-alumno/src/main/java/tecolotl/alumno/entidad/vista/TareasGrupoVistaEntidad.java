package tecolotl.alumno.entidad.vista;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Immutable
@Table(name = "tareas_grupo", schema = "alumno")
@NamedQuery(
        name = "TareasGrupoVistaEntidad.busca",
        query = "SELECT tg.idVideo, tg.preguntaDetonadora, COUNT(tg.idVideo) FROM TareasGrupoVistaEntidad tg " +
                "WHERE tg.idGrupo = :idGrupo GROUP BY tg.idVideo, tg.preguntaDetonadora"
)
public class TareasGrupoVistaEntidad {

    private String idVideo;
    private String preguntaDetonadora;
    private UUID idGrupo;
    private UUID id;

    @Id
    @Column(name = "id_video")
    public String getIdVideo() {
        return idVideo;
    }

    public void setIdVideo(String idVideo) {
        this.idVideo = idVideo;
    }

    @Column(name = "pregunta_detonadora")
    public String getPreguntaDetonadora() {
        return preguntaDetonadora;
    }

    public void setPreguntaDetonadora(String preguntaDetonadora) {
        this.preguntaDetonadora = preguntaDetonadora;
    }

    public UUID getId() {
        return id;
    }

    @Column(name = "id_grupo")
    public UUID getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(UUID idGrupo) {
        this.idGrupo = idGrupo;
    }

    @Id
    public void setId(UUID id) {
        this.id = id;
    }
}
