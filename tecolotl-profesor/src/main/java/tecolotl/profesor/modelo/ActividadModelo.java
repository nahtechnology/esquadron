package tecolotl.profesor.modelo;

import tecolotl.profesor.entidad.ActividadEntidad;

public class ActividadModelo {

    private String idActividad;
    private String preguntaDetonadora;
    private String tema;
    private String nivelLenguaje;
    private int codigoNivelLenguaje;

    public ActividadModelo() {
    }

    public ActividadModelo(ActividadEntidad actividadEntidad) {
        this.idActividad = actividadEntidad.getIdActividad();
        this.preguntaDetonadora = actividadEntidad.getPreguntaDetonadora();
        this.tema = actividadEntidad.getTema();
        this.nivelLenguaje = actividadEntidad.getNivelLenguaje();
        this.codigoNivelLenguaje = actividadEntidad.getCodigoNivelLenguaje();
    }

    public String getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(String idActividad) {
        this.idActividad = idActividad;
    }

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

    public String getNivelLenguaje() {
        return nivelLenguaje;
    }

    public void setNivelLenguaje(String nivelLenguaje) {
        this.nivelLenguaje = nivelLenguaje;
    }

    public int getCodigoNivelLenguaje() {
        return codigoNivelLenguaje;
    }

    public void setCodigoNivelLenguaje(int codigoNivelLenguaje) {
        this.codigoNivelLenguaje = codigoNivelLenguaje;
    }
}
