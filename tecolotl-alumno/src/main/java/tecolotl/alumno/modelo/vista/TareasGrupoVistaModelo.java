package tecolotl.alumno.modelo.vista;

import tecolotl.alumno.entidad.vista.TareasGrupoVistaEntidad;

public class TareasGrupoVistaModelo {

    private String idVideo;
    private String preguntaDetonadora;
    private int totalTareas;

    public TareasGrupoVistaModelo() {
    }

    public TareasGrupoVistaModelo(String idVideo) {
        this.idVideo = idVideo;
    }

    public TareasGrupoVistaModelo(TareasGrupoVistaEntidad tareasGrupoVistaEntidad) {
        this.idVideo = tareasGrupoVistaEntidad.getIdVideo();
        this.preguntaDetonadora = tareasGrupoVistaEntidad.getPreguntaDetonadora();
    }

    public String getIdVideo() {
        return idVideo;
    }

    public void setIdVideo(String idVideo) {
        this.idVideo = idVideo;
    }

    public String getPreguntaDetonadora() {
        return preguntaDetonadora;
    }

    public void setPreguntaDetonadora(String preguntaDetonadora) {
        this.preguntaDetonadora = preguntaDetonadora;
    }

    public int getTotalTareas() {
        return totalTareas;
    }

    public void setTotalTareas(int totalTareas) {
        this.totalTareas = totalTareas;
    }
}
