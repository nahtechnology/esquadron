package tecolotl.profesor.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class TareaAlumnoGrupoEntidad {

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

    @Id
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

    @Column(name = "apellido_paterno")
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    @Column(name = "apellido_materno")
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    @Column(name = "total_respuesta_mapamental")
    public int getTotalRespuestaMapaMental() {
        return totalRespuestaMapaMental;
    }

    public void setTotalRespuestaMapaMental(int totalRespuestaMapaMental) {
        this.totalRespuestaMapaMental = totalRespuestaMapaMental;
    }

    @Column(name = "total_asignadas_mapamental")
    public int getTotalAsignadaMapaMental() {
        return totalAsignadaMapaMental;
    }

    public void setTotalAsignadaMapaMental(int totalAsignadaMapaMental) {
        this.totalAsignadaMapaMental = totalAsignadaMapaMental;
    }

    @Column(name = "total_respuesta_gramatica")
    public int getTotalRespuestaGramatica() {
        return totalRespuestaGramatica;
    }

    public void setTotalRespuestaGramatica(int totalRespuestaGramatica) {
        this.totalRespuestaGramatica = totalRespuestaGramatica;
    }

    @Column(name = "total_asignado_gramatica")
    public int getTotalAsignadaGramatica() {
        return totalAsignadaGramatica;
    }

    public void setTotalAsignadaGramatica(int totalAsignadaGramatica) {
        this.totalAsignadaGramatica = totalAsignadaGramatica;
    }

    @Column(name = "total_respuesta_oraciones")
    public int getTotalRespuestaOraciones() {
        return totalRespuestaOraciones;
    }

    public void setTotalRespuestaOraciones(int totalRespuestaOraciones) {
        this.totalRespuestaOraciones = totalRespuestaOraciones;
    }

    @Column(name = "total_asignado_oraciones")
    public int getTotalAsignadaOraciones() {
        return totalAsignadaOraciones;
    }

    public void setTotalAsignadaOraciones(int totalAsignadaOraciones) {
        this.totalAsignadaOraciones = totalAsignadaOraciones;
    }

    @Column(name = "total_respuesta_relacionar_oracion")
    public int getTotalRespuestaRelacionarOracion() {
        return totalRespuestaRelacionarOracion;
    }

    public void setTotalRespuestaRelacionarOracion(int totalRespuestaRelacionarOracion) {
        this.totalRespuestaRelacionarOracion = totalRespuestaRelacionarOracion;
    }

    @Column(name = "total_asignado_relacionar_oracion")
    public int getTotalAsignadaRelacionarOracion() {
        return totalAsignadaRelacionarOracion;
    }

    public void setTotalAsignadaRelacionarOracion(int totalAsignadaRelacionarOracion) {
        this.totalAsignadaRelacionarOracion = totalAsignadaRelacionarOracion;
    }

    @Column(name = "total_respuesta_completar")
    public int getTotalRespuestaCompletar() {
        return totalRespuestaCompletar;
    }

    public void setTotalRespuestaCompletar(int totalRespuestaCompletar) {
        this.totalRespuestaCompletar = totalRespuestaCompletar;
    }

    @Column(name = "total_asignado_completar")
    public int getTotalAsignadaCompletar() {
        return totalAsignadaCompletar;
    }

    public void setTotalAsignadaCompletar(int totalAsignadaCompletar) {
        this.totalAsignadaCompletar = totalAsignadaCompletar;
    }

}
