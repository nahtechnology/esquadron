package tecolotl.profesor.modelo;

import tecolotl.alumno.entidad.vista.BuscaActividadesVistaEntidad;
import tecolotl.profesor.entidad.ActividadEntidad;

public class ActividadModelo {

    private String idActividad;
    private String preguntaDetonadora;
    private String tema;
    private String nivelLenguaje;
    private Integer totalTareas;
    private int codigoNivelLenguaje;
    private String mapamental;

    public ActividadModelo() {
    }

    public ActividadModelo(ActividadEntidad actividadEntidad) {
        this.idActividad = actividadEntidad.getIdActividad();
        this.preguntaDetonadora = actividadEntidad.getPreguntaDetonadora();
        this.tema = actividadEntidad.getTema();
        this.nivelLenguaje = actividadEntidad.getNivelLenguaje();
        totalTareas = actividadEntidad.getTotalTareas();
        this.codigoNivelLenguaje = actividadEntidad.getCodigoNivelLenguaje();
        this.mapamental = actividadEntidad.getMapamental();
    }

    public ActividadModelo(BuscaActividadesVistaEntidad buscaActividadesVistaEntidad) {
        idActividad = buscaActividadesVistaEntidad.getIdActvidad();
        preguntaDetonadora = buscaActividadesVistaEntidad.getPreguntaDetonadora();
        tema = buscaActividadesVistaEntidad.getTema();
        nivelLenguaje = buscaActividadesVistaEntidad.getNivelLenguaje();
        codigoNivelLenguaje = buscaActividadesVistaEntidad.getIdNivelLenguaje();
        mapamental = buscaActividadesVistaEntidad.getMapaMental();
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

    public String getMapamental() {
        return mapamental;
    }

    public void setMapamental(String mapamental) {
        this.mapamental = mapamental;
    }

    public Integer getTotalTareas() {
        return totalTareas;
    }

    public void setTotalTareas(Integer totalTareas) {
        this.totalTareas = totalTareas;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ActividadModelo{");
        sb.append("idActividad='").append(idActividad).append('\'');
        sb.append(", preguntaDetonadora='").append(preguntaDetonadora).append('\'');
        sb.append(", tema='").append(tema).append('\'');
        sb.append(", nivelLenguaje='").append(nivelLenguaje).append('\'');
        sb.append(", totalTareas=").append(totalTareas);
        sb.append(", codigoNivelLenguaje=").append(codigoNivelLenguaje);
        sb.append(", mapamental='").append(mapamental).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
