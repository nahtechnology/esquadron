package tecolotl.profesor.entidad;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ActividadEntidad {

    private String idActividad;
    private String preguntaDetonadora;
    private String tema;
    private String nivelLenguaje;
    private int codigoNivelLenguaje;
    private String mapamental;

    @Id
    @Column(name = "id_actividad")
    public String getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(String idActividad) {
        this.idActividad = idActividad;
    }

    @Basic
    @Column(name = "pregunta_detonadora")
    public String getPreguntaDetonadora() {
        return preguntaDetonadora;
    }

    public void setPreguntaDetonadora(String preguntaDetonadora) {
        this.preguntaDetonadora = preguntaDetonadora;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    @Column(name = "nivel_lenguaje")
    public String getNivelLenguaje() {
        return nivelLenguaje;
    }

    public void setNivelLenguaje(String nivelLenguaje) {
        this.nivelLenguaje = nivelLenguaje;
    }

    @Column(name = "codigo_nivel_lenguaje")
    public int getCodigoNivelLenguaje() {
        return codigoNivelLenguaje;
    }

    public void setCodigoNivelLenguaje(int codigoNivelLenguaje) {
        this.codigoNivelLenguaje = codigoNivelLenguaje;
    }

    @Column(name = "mapa_mental")
    public String getMapamental() {
        return mapamental;
    }

    public void setMapamental(String mapamental) {
        this.mapamental = mapamental;
    }
}
