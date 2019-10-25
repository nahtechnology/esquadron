package tecolotl.profesor.modelo;

import tecolotl.profesor.entidad.TareaAlumnoEntidad;

public class TareaAlumnoModelo {

    private int id;
    private Short calificacionMapamental;
    private Short calificacionRelacionarImagen;
    private Short calificacionGramatica;
    private Short calificacionOraciones;
    private Short calificacionRelacionarOraciones;
    private Short calificacionCompletar;

    public TareaAlumnoModelo() {
    }

    public TareaAlumnoModelo(int id) {
        this.id = id;
    }

    public TareaAlumnoModelo(TareaAlumnoEntidad tareaAlumnoEntidad) {
        this.id = tareaAlumnoEntidad.getId();
        this.calificacionMapamental = tareaAlumnoEntidad.getCalificacionMapamental();
        this.calificacionRelacionarImagen = tareaAlumnoEntidad.getCalificacionRelacionarImagen();
        this.calificacionGramatica = tareaAlumnoEntidad.getCalificacionGramatica();
        this.calificacionOraciones = tareaAlumnoEntidad.getCalificacionOraciones();
        this.calificacionRelacionarOraciones = tareaAlumnoEntidad.getCalificacionRelacionarOraciones();
        this.calificacionCompletar = tareaAlumnoEntidad.getCalificacionCompletar();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Short getCalificacionMapamental() {
        return calificacionMapamental;
    }

    public void setCalificacionMapamental(Short calificacionMapamental) {
        this.calificacionMapamental = calificacionMapamental;
    }

    public Short getCalificacionRelacionarImagen() {
        return calificacionRelacionarImagen;
    }

    public void setCalificacionRelacionarImagen(Short calificacionRelacionarImagen) {
        this.calificacionRelacionarImagen = calificacionRelacionarImagen;
    }

    public Short getCalificacionGramatica() {
        return calificacionGramatica;
    }

    public void setCalificacionGramatica(Short calificacionGramatica) {
        this.calificacionGramatica = calificacionGramatica;
    }
    public Short getCalificacionOraciones() {
        return calificacionOraciones;
    }

    public void setCalificacionOraciones(Short calificacionOraciones) {
        this.calificacionOraciones = calificacionOraciones;
    }

    public Short getCalificacionRelacionarOraciones() {
        return calificacionRelacionarOraciones;
    }

    public void setCalificacionRelacionarOraciones(Short calificacionRelacionarOraciones) {
        this.calificacionRelacionarOraciones = calificacionRelacionarOraciones;
    }

    public Short getCalificacionCompletar() {
        return calificacionCompletar;
    }

    public void setCalificacionCompletar(Short calificacionCompletar) {
        this.calificacionCompletar = calificacionCompletar;
    }

}
