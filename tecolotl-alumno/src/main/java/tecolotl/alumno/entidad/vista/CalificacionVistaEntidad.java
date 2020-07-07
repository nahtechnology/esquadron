package tecolotl.alumno.entidad.vista;

import org.hibernate.annotations.Immutable;
import tecolotl.alumno.entidad.AlumnoEntidad;
import tecolotl.alumno.modelo.AlumnoModelo;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "calificacion", schema = "alumno")
@Immutable
public class CalificacionVistaEntidad {

    private UUID id;
    private AlumnoEntidad alumnoEntidad;
    private Date fechaAsignacion;
    private String idActividad;
    private String nivelLenguaje;
    private Integer calificacionTrancipcion;
    private Short calificacionMapamental;
    private Short calificacionRelacionaImagen;
    private Short calificacionGramatica;
    private Short calificacionGraciones;
    private Short calificacionRelacionarOraciones;
    private Short calificacionCompletar;

    @Id
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_alumno")
    public AlumnoEntidad getAlumnoEntidad() {
        return alumnoEntidad;
    }

    public void setAlumnoEntidad(AlumnoEntidad alumnoEntidad) {
        this.alumnoEntidad = alumnoEntidad;
    }

    @Column(name = "fecha_asignacion")
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

    @Column(name = "nivel_lenguaje")
    public String getNivelLenguaje() {
        return nivelLenguaje;
    }

    public void setNivelLenguaje(String nivelLenguaje) {
        this.nivelLenguaje = nivelLenguaje;
    }

    @Column(name = "calificacion_trancipcion")
    public Integer getCalificacionTrancipcion() {
        return calificacionTrancipcion;
    }

    public void setCalificacionTrancipcion(Integer calificacionTrancipcion) {
        this.calificacionTrancipcion = calificacionTrancipcion;
    }

    @Column(name = "calificacion_mapamental")
    public Short getCalificacionMapamental() {
        return calificacionMapamental;
    }

    public void setCalificacionMapamental(Short calificacionMapamental) {
        this.calificacionMapamental = calificacionMapamental;
    }

    @Column(name = "calificacion_relaciona_imagen")
    public Short getCalificacionRelacionaImagen() {
        return calificacionRelacionaImagen;
    }

    public void setCalificacionRelacionaImagen(Short calificacionRelacionaImagen) {
        this.calificacionRelacionaImagen = calificacionRelacionaImagen;
    }

    @Column(name = "calificacion_gramatica")
    public Short getCalificacionGramatica() {
        return calificacionGramatica;
    }

    public void setCalificacionGramatica(Short calificacionGramatica) {
        this.calificacionGramatica = calificacionGramatica;
    }

    @Column(name = "calificacion_oraciones")
    public Short getCalificacionGraciones() {
        return calificacionGraciones;
    }

    public void setCalificacionGraciones(Short calificacionGraciones) {
        this.calificacionGraciones = calificacionGraciones;
    }

    @Column(name = "calificacion_relacionar_oraciones")
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

}
