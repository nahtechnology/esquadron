package tecolotl.nucleo.persistencia.entidad;

import tecolotl.nucleo.persistence.enumeracion.NivelLenguajeEnumeracion;

import javax.persistence.*;

@Entity
@Table(name = "tarea", schema = "nucleo")
@NamedQueries({
        @NamedQuery(name = "Tarea.busca", query = "FROM TareaEntidad")
})
public class TareaEntidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int puntaje;

    private int tiempo;

    @Column(name = "pregunta_detonadora")
    private String preguntaDetonadora;

    private String video;

    private String transcripcion;

    @Enumerated(EnumType.STRING)
    @Column(name = "nivel_lenguaje")
    private NivelLenguajeEnumeracion nivelLenguaje;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public String getPreguntaDetonadora() {
        return preguntaDetonadora;
    }

    public void setPreguntaDetonadora(String preguntaDetonadora) {
        this.preguntaDetonadora = preguntaDetonadora;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getTranscripcion() {
        return transcripcion;
    }

    public void setTranscripcion(String transcripcion) {
        this.transcripcion = transcripcion;
    }

    public NivelLenguajeEnumeracion getNivelLenguaje() {
        return nivelLenguaje;
    }

    public void setNivelLenguaje(NivelLenguajeEnumeracion nivelLenguaje) {
        this.nivelLenguaje = nivelLenguaje;
    }
}
