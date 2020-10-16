package tecolotl.alumno.entidad.vista;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;

@Entity
@Immutable
@Table(schema = "alumno", name = "actividad_buqueda")
@NamedQuery(
        name = "ActividadBuquedaVista.buscaPreguntaLenguaje",
        query = "SELECT ab FROM ActividadBuquedaVista ab WHERE " +
                "LOWER(ab.preguntaDetonadora) LIKE :palabra OR LOWER(ab.lenguaje) LIKE :palabra"
)
public class ActividadBuquedaVista {

    private String idVideo;
    private String preguntaDetonadora;
    private String lenguaje;
    private String nivelLenguaje;
    private String tema;
    private String mapaMental;

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

    public String getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

    @Column(name = "nivel_lenguaje")
    public String getNivelLenguaje() {
        return nivelLenguaje;
    }

    public void setNivelLenguaje(String nivelLenguaje) {
        this.nivelLenguaje = nivelLenguaje;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    @Column(name = "mapa_mental")
    public String getMapaMental() {
        return mapaMental;
    }

    public void setMapaMental(String mapaMental) {
        this.mapaMental = mapaMental;
    }
}
