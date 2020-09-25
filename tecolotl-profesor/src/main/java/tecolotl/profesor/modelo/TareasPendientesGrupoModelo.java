package tecolotl.profesor.modelo;

import tecolotl.alumno.entidad.vista.TareasPendientesGrupoVistaEntidad;

import java.util.UUID;

public class TareasPendientesGrupoModelo {

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

    public TareasPendientesGrupoModelo() {
    }

    public TareasPendientesGrupoModelo(TareasPendientesGrupoVistaEntidad entidad) {
        id = entidad.getId();
        transcripcionTotal = entidad.getTranscripcionTotal();
        transcripcionPendientes = entidad.getTranscripcionPendientes();
        mapamentalTotal = entidad.getMapamentalTotal();
        mapamentalPendientes = entidad.getMapamentalPendientes();
        relacionarTotal = entidad.getRelacionarTotal();
        relacionarPendiente = entidad.getRelacionarPendiente();
        gramaticaTotal = entidad.getGramaticaTotal();
        gramaticaPendiente = entidad.getGramaticaPendiente();
        oracionesTotal = entidad.getOracionesTotal();
        oracionesPendiete = entidad.getOracionesPendiete();
        relacionarOracionTotal = entidad.getRelacionarOracionTotal();
        relacionarOracionPendiete = entidad.getRelacionarOracionPendiete();
        completarTotal = entidad.getCompletarTotal();
        completarPendiente = entidad.getCompletarPendiente();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getMapamentalTotal() {
        return mapamentalTotal;
    }

    public void setMapamentalTotal(int mapamentalTotal) {
        this.mapamentalTotal = mapamentalTotal;
    }

    public int getMapamentalPendientes() {
        return mapamentalPendientes;
    }

    public void setMapamentalPendientes(int mapamentalPendientes) {
        this.mapamentalPendientes = mapamentalPendientes;
    }

    public int getRelacionarTotal() {
        return relacionarTotal;
    }

    public void setRelacionarTotal(int relacionarTotal) {
        this.relacionarTotal = relacionarTotal;
    }

    public int getRelacionarPendiente() {
        return relacionarPendiente;
    }

    public void setRelacionarPendiente(int relacionarPendiente) {
        this.relacionarPendiente = relacionarPendiente;
    }

    public int getGramaticaTotal() {
        return gramaticaTotal;
    }

    public void setGramaticaTotal(int gramaticaTotal) {
        this.gramaticaTotal = gramaticaTotal;
    }

    public int getGramaticaPendiente() {
        return gramaticaPendiente;
    }

    public void setGramaticaPendiente(int gramaticaPendiente) {
        this.gramaticaPendiente = gramaticaPendiente;
    }

    public int getOracionesTotal() {
        return oracionesTotal;
    }

    public void setOracionesTotal(int oracionesTotal) {
        this.oracionesTotal = oracionesTotal;
    }

    public int getOracionesPendiete() {
        return oracionesPendiete;
    }

    public void setOracionesPendiete(int oracionesPendiete) {
        this.oracionesPendiete = oracionesPendiete;
    }

    public int getRelacionarOracionTotal() {
        return relacionarOracionTotal;
    }

    public void setRelacionarOracionTotal(int relacionarOracionTotal) {
        this.relacionarOracionTotal = relacionarOracionTotal;
    }

    public int getRelacionarOracionPendiete() {
        return relacionarOracionPendiete;
    }

    public void setRelacionarOracionPendiete(int relacionarOracionPendiete) {
        this.relacionarOracionPendiete = relacionarOracionPendiete;
    }

    public int getCompletarTotal() {
        return completarTotal;
    }

    public void setCompletarTotal(int completarTotal) {
        this.completarTotal = completarTotal;
    }

    public int getCompletarPendiente() {
        return completarPendiente;
    }

    public void setCompletarPendiente(int completarPendiente) {
        this.completarPendiente = completarPendiente;
    }

    public int getTranscripcionTotal() {
        return transcripcionTotal;
    }

    public void setTranscripcionTotal(int transcripcionTotal) {
        this.transcripcionTotal = transcripcionTotal;
    }

    public int getTranscripcionPendientes() {
        return transcripcionPendientes;
    }

    public void setTranscripcionPendientes(int transcripcionPendientes) {
        this.transcripcionPendientes = transcripcionPendientes;
    }
}
