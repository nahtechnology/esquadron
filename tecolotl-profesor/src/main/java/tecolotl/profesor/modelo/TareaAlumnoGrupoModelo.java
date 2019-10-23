package tecolotl.profesor.modelo;

import tecolotl.profesor.entidad.TareaAlumnoGrupoEntidad;

public class TareaAlumnoGrupoModelo {

    private int id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private int totalRespuestaMapaMental;
    private int totalAsignadaMapaMental;
    private int totalRespuestaGramatica;
    private int totalAsignadaGramatica;
    private int totalRespuestaOraciones;
    private int totalAsignadaOraciones;
    private int totalRespuestaRelacionarOracion;
    private int totalAsignadaRelacionarOracion;
    private int totalRespuestaCompletar;
    private int totalAsignadaCompletar;

    public TareaAlumnoGrupoModelo() {
    }

    public TareaAlumnoGrupoModelo(TareaAlumnoGrupoEntidad tareaAlumnoGrupoEntidad) {
        this.id = tareaAlumnoGrupoEntidad.getId();
        this.nombre = tareaAlumnoGrupoEntidad.getNombre();
        this.apellidoPaterno = tareaAlumnoGrupoEntidad.getApellidoPaterno();
        this.apellidoMaterno = tareaAlumnoGrupoEntidad.getApellidoMaterno();
        this.totalRespuestaMapaMental = tareaAlumnoGrupoEntidad.getTotalRespuestaMapaMental();
        this.totalAsignadaMapaMental = tareaAlumnoGrupoEntidad.getTotalAsignadaMapaMental();
        this.totalRespuestaGramatica = tareaAlumnoGrupoEntidad.getTotalRespuestaGramatica();
        this.totalAsignadaGramatica = tareaAlumnoGrupoEntidad.getTotalAsignadaGramatica();
        this.totalRespuestaOraciones = tareaAlumnoGrupoEntidad.getTotalRespuestaOraciones();
        this.totalAsignadaOraciones = tareaAlumnoGrupoEntidad.getTotalAsignadaOraciones();
        this.totalRespuestaRelacionarOracion = tareaAlumnoGrupoEntidad.getTotalRespuestaRelacionarOracion();
        this.totalAsignadaRelacionarOracion = tareaAlumnoGrupoEntidad.getTotalAsignadaRelacionarOracion();
        this.totalRespuestaCompletar = tareaAlumnoGrupoEntidad.getTotalRespuestaCompletar();
        this.totalAsignadaCompletar = tareaAlumnoGrupoEntidad.getTotalAsignadaCompletar();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getTotalRespuestaMapaMental() {
        return totalRespuestaMapaMental;
    }

    public void setTotalRespuestaMapaMental(int totalRespuestaMapaMental) {
        this.totalRespuestaMapaMental = totalRespuestaMapaMental;
    }

    public int getTotalAsignadaMapaMental() {
        return totalAsignadaMapaMental;
    }

    public void setTotalAsignadaMapaMental(int totalAsignadaMapaMental) {
        this.totalAsignadaMapaMental = totalAsignadaMapaMental;
    }

    public int getTotalRespuestaGramatica() {
        return totalRespuestaGramatica;
    }

    public void setTotalRespuestaGramatica(int totalRespuestaGramatica) {
        this.totalRespuestaGramatica = totalRespuestaGramatica;
    }

    public int getTotalAsignadaGramatica() {
        return totalAsignadaGramatica;
    }

    public void setTotalAsignadaGramatica(int totalAsignadaGramatica) {
        this.totalAsignadaGramatica = totalAsignadaGramatica;
    }

    public int getTotalRespuestaOraciones() {
        return totalRespuestaOraciones;
    }

    public void setTotalRespuestaOraciones(int totalRespuestaOraciones) {
        this.totalRespuestaOraciones = totalRespuestaOraciones;
    }

    public int getTotalAsignadaOraciones() {
        return totalAsignadaOraciones;
    }

    public void setTotalAsignadaOraciones(int totalAsignadaOraciones) {
        this.totalAsignadaOraciones = totalAsignadaOraciones;
    }

    public int getTotalRespuestaRelacionarOracion() {
        return totalRespuestaRelacionarOracion;
    }

    public void setTotalRespuestaRelacionarOracion(int totalRespuestaRelacionarOracion) {
        this.totalRespuestaRelacionarOracion = totalRespuestaRelacionarOracion;
    }

    public int getTotalAsignadaRelacionarOracion() {
        return totalAsignadaRelacionarOracion;
    }

    public void setTotalAsignadaRelacionarOracion(int totalAsignadaRelacionarOracion) {
        this.totalAsignadaRelacionarOracion = totalAsignadaRelacionarOracion;
    }

    public int getTotalRespuestaCompletar() {
        return totalRespuestaCompletar;
    }

    public void setTotalRespuestaCompletar(int totalRespuestaCompletar) {
        this.totalRespuestaCompletar = totalRespuestaCompletar;
    }

    public int getTotalAsignadaCompletar() {
        return totalAsignadaCompletar;
    }

    public void setTotalAsignadaCompletar(int totalAsignadaCompletar) {
        this.totalAsignadaCompletar = totalAsignadaCompletar;
    }
}
