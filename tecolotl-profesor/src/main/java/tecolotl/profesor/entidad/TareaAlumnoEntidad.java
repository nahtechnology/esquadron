package tecolotl.profesor.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class TareaAlumnoEntidad {

    private int id;
    private Date fechaAsignacion;
    private String idActividad;
    private Short calificacionMapamental;
    private Short calificacionRelacionarImagen;
    private Short calificacionGramatica;
    private Short calificacionOraciones;
    private Short calificacionRelacionarOraciones;
    private Short calificacionCompletar;


    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "calificacion_mapamental")
    public Short getCalificacionMapamental() {
        return calificacionMapamental;
    }

    public void setCalificacionMapamental(Short calificacionMapamental) {
        this.calificacionMapamental = calificacionMapamental;
    }

    @Column(name = "calificacion_relacionar_imagen")
    public Short getCalificacionRelacionarImagen() {
        return calificacionRelacionarImagen;
    }

    public void setCalificacionRelacionarImagen(Short calificacionRelacionarImagen) {
        this.calificacionRelacionarImagen = calificacionRelacionarImagen;
    }

    @Column(name = "calificacion_gramatica")
    public Short getCalificacionGramatica() {
        return calificacionGramatica;
    }

    public void setCalificacionGramatica(Short calificacionGramatica) {
        this.calificacionGramatica = calificacionGramatica;
    }
    @Column(name = "calificacion_oraciones")
    public Short getCalificacionOraciones() {
        return calificacionOraciones;
    }

    public void setCalificacionOraciones(Short calificacionOraciones) {
        this.calificacionOraciones = calificacionOraciones;
    }
    @Column(name = "calificacion_relacionar_oracion")
    public Short getCalificacionRelacionarOraciones() {
        return calificacionRelacionarOraciones;
    }

    public void setCalificacionRelacionarOraciones(Short calificacionRelacionarOraciones) {
        this.calificacionRelacionarOraciones = calificacionRelacionarOraciones;
    }
    @Column(name = "calificacion_completar")
    public Short getCalificacionCompletar() {
        return calificacionCompletar;
    }

    public void setCalificacionCompletar(Short calificacionCompletar) {
        this.calificacionCompletar = calificacionCompletar;
    }
    @Column(name = "fecha_asignacion ")
    public Date getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(Date fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }
    @Column(name = "id_actividad")
    public String getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(String idActividad) {
        this.idActividad = idActividad;
    }
}
