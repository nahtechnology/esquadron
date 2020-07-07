package tecolotl.profesor.modelo;

import java.util.UUID;

public class PromedioAlumnoModelo {

    private UUID id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Integer totalTareas;
    private String nivelActual;
    private Integer promedioTranscipcion;
    private Integer promedioPromedioMapamental;
    private Integer promedioRelacionarImagen;
    private Integer promedioGramatica;
    private Integer promedioOraciones;
    private Integer promedioRelacionarOracion;
    private Integer promedioCompletar;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public Integer getTotalTareas() {
        return totalTareas;
    }

    public void setTotalTareas(Integer totalTareas) {
        this.totalTareas = totalTareas;
    }

    public String getNivelActual() {
        return nivelActual;
    }

    public void setNivelActual(String nivelActual) {
        this.nivelActual = nivelActual;
    }

    public Integer getPromedioTranscipcion() {
        return promedioTranscipcion;
    }

    public void setPromedioTranscipcion(Integer promedioTranscipcion) {
        this.promedioTranscipcion = promedioTranscipcion;
    }

    public Integer getPromedioPromedioMapamental() {
        return promedioPromedioMapamental;
    }

    public void setPromedioPromedioMapamental(Integer promedioPromedioMapamental) {
        this.promedioPromedioMapamental = promedioPromedioMapamental;
    }

    public Integer getPromedioRelacionarImagen() {
        return promedioRelacionarImagen;
    }

    public void setPromedioRelacionarImagen(Integer promedioRelacionarImagen) {
        this.promedioRelacionarImagen = promedioRelacionarImagen;
    }

    public Integer getPromedioGramatica() {
        return promedioGramatica;
    }

    public void setPromedioGramatica(Integer promedioGramatica) {
        this.promedioGramatica = promedioGramatica;
    }

    public Integer getPromedioOraciones() {
        return promedioOraciones;
    }

    public void setPromedioOraciones(Integer promedioOraciones) {
        this.promedioOraciones = promedioOraciones;
    }

    public Integer getPromedioRelacionarOracion() {
        return promedioRelacionarOracion;
    }

    public void setPromedioRelacionarOracion(Integer promedioRelacionarOracion) {
        this.promedioRelacionarOracion = promedioRelacionarOracion;
    }

    public Integer getPromedioCompletar() {
        return promedioCompletar;
    }

    public void setPromedioCompletar(Integer promedioCompletar) {
        this.promedioCompletar = promedioCompletar;
    }
}
