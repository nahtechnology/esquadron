package tecolotl.alumno.entidad.vista;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Immutable
@Table(name = "tareas_pendientes_grupo", schema = "alumno")
@NamedQueries(value = {
        @NamedQuery(
                name = "TareasPendientesGrupoVistaEntidad.buscaPorGrupo",
                query = "SELECT tpg FROM TareasPendientesGrupoVistaEntidad tpg WHERE tpg.id = :idGrupo"
        )
})
public class TareasPendientesGrupoVistaEntidad {

    private UUID id;
    private int transcripcionTotal;
    private int transcripcionPendientes;
    private int mapamentalTotal;
    private int mapamentalPendientes;
    private int relacionarTotal;
    private int relacionarPendiente;
    private int gramaticaTotal;
    private int gramaticaPendiente;
    private int oracionesTotal;
    private int oracionesPendiete;
    private int relacionarOracionTotal;
    private int relacionarOracionPendiete;
    private int completarTotal;
    private int completarPendiente;

    @Id
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Column(name = "transcripcion_total")
    public int getTranscripcionTotal() {
        return transcripcionTotal;
    }

    public void setTranscripcionTotal(int transcripcionTotal) {
        this.transcripcionTotal = transcripcionTotal;
    }

    @Column(name = "transcripcion_pendientes")
    public int getTranscripcionPendientes() {
        return transcripcionPendientes;
    }

    public void setTranscripcionPendientes(int transcripcionPendientes) {
        this.transcripcionPendientes = transcripcionPendientes;
    }

    @Column(name = "mapamental_total")
    public int getMapamentalTotal() {
        return mapamentalTotal;
    }

    public void setMapamentalTotal(int mapamentalTotal) {
        this.mapamentalTotal = mapamentalTotal;
    }

    @Column(name = "mapamental_pendientes")
    public int getMapamentalPendientes() {
        return mapamentalPendientes;
    }

    public void setMapamentalPendientes(int mapamentalPendientes) {
        this.mapamentalPendientes = mapamentalPendientes;
    }

    @Column(name = "relacionar_total")
    public int getRelacionarTotal() {
        return relacionarTotal;
    }

    public void setRelacionarTotal(int relacionarTotal) {
        this.relacionarTotal = relacionarTotal;
    }

    @Column(name = "relacionar_pendiente")
    public int getRelacionarPendiente() {
        return relacionarPendiente;
    }

    public void setRelacionarPendiente(int relacionarPendiente) {
        this.relacionarPendiente = relacionarPendiente;
    }

    @Column(name = "gramatica_total")
    public int getGramaticaTotal() {
        return gramaticaTotal;
    }

    public void setGramaticaTotal(int gramaticaTotal) {
        this.gramaticaTotal = gramaticaTotal;
    }

    @Column(name = "gramatica_pendiente")
    public int getGramaticaPendiente() {
        return gramaticaPendiente;
    }

    public void setGramaticaPendiente(int gramaticaPendiente) {
        this.gramaticaPendiente = gramaticaPendiente;
    }

    @Column(name = "oraciones_total")
    public int getOracionesTotal() {
        return oracionesTotal;
    }

    public void setOracionesTotal(int oracionesTotal) {
        this.oracionesTotal = oracionesTotal;
    }

    @Column(name = "oraciones_pendiete")
    public int getOracionesPendiete() {
        return oracionesPendiete;
    }

    public void setOracionesPendiete(int oracionesPendiete) {
        this.oracionesPendiete = oracionesPendiete;
    }

    @Column(name = "relacionar_oracion_total")
    public int getRelacionarOracionTotal() {
        return relacionarOracionTotal;
    }

    public void setRelacionarOracionTotal(int relacionarOracionTotal) {
        this.relacionarOracionTotal = relacionarOracionTotal;
    }

    @Column(name = "relacionar_oracion_pendiete")
    public int getRelacionarOracionPendiete() {
        return relacionarOracionPendiete;
    }

    public void setRelacionarOracionPendiete(int relacionarOracionPendiete) {
        this.relacionarOracionPendiete = relacionarOracionPendiete;
    }

    @Column(name = "completar_total")
    public int getCompletarTotal() {
        return completarTotal;
    }

    public void setCompletarTotal(int completarTotal) {
        this.completarTotal = completarTotal;
    }

    @Column(name = "completar_pendiente")
    public int getCompletarPendiente() {
        return completarPendiente;
    }

    public void setCompletarPendiente(int completarPendiente) {
        this.completarPendiente = completarPendiente;
    }

}
