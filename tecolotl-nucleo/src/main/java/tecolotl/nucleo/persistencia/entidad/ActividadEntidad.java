package tecolotl.nucleo.persistencia.entidad;

import javax.persistence.*;

@MappedSuperclass
public class ActividadEntidad {

    private short puntaje;
    private int tiempo;
    private String preguntaDetonadora;
    private LenguajeEntidad IdLenguaje;
    private NivelLenguajeEntidad IdNivelLenguaje;

    @Basic
    @Column(name = "puntaje")
    public short getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(short puntaje) {
        this.puntaje = puntaje;
    }

    @Basic
    @Column(name = "tiempo")
    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    @Basic
    @Column(name = "pregunta_detonadora")
    public String getPreguntaDetonadora() {
        return preguntaDetonadora;
    }

    public void setPreguntaDetonadora(String preguntaDetonadora) {
        this.preguntaDetonadora = preguntaDetonadora;
    }

    @Id
    @ManyToOne
    @JoinColumn(name = "id_lenguaje")
    public LenguajeEntidad getIdLenguaje() {
        return IdLenguaje;
    }

    public void setIdLenguaje(LenguajeEntidad idLenguaje) {
        IdLenguaje = idLenguaje;
    }

    @Id
    @ManyToOne
    @JoinColumn(name = "id_nivel_lenguaje")
    public NivelLenguajeEntidad getIdNivelLenguaje() {
        return IdNivelLenguaje;
    }

    public void setIdNivelLenguaje(NivelLenguajeEntidad idNivelLenguaje) {
        IdNivelLenguaje = idNivelLenguaje;
    }
}
