package tecolotl.profesor.modelo;

import java.util.UUID;

public class CalificacionPendientesModelo {

    private UUID idGrupo;
    private UUID idProfesor;
    private Short grado;
    private String grupo;
    private Integer tareasAsignadas;
    private Integer pendientesMapaMental;
    private Integer pendientesGramatica;
    private Float promedio;

    public UUID getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(UUID idGrupo) {
        this.idGrupo = idGrupo;
    }

    public UUID getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(UUID idProfesor) {
        this.idProfesor = idProfesor;
    }

    public Short getGrado() {
        return grado;
    }

    public void setGrado(Short grado) {
        this.grado = grado;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public Integer getTareasAsignadas() {
        return tareasAsignadas;
    }

    public void setTareasAsignadas(Integer tareasAsignadas) {
        this.tareasAsignadas = tareasAsignadas;
    }

    public Integer getPendientesMapaMental() {
        return pendientesMapaMental;
    }

    public void setPendientesMapaMental(Integer pendientesMapaMental) {
        this.pendientesMapaMental = pendientesMapaMental;
    }

    public Integer getPendientesGramatica() {
        return pendientesGramatica;
    }

    public void setPendientesGramatica(Integer pendientesGramatica) {
        this.pendientesGramatica = pendientesGramatica;
    }

    public Float getPromedio() {
        return promedio;
    }

    public void setPromedio(Float promedio) {
        this.promedio = promedio;
    }
}
