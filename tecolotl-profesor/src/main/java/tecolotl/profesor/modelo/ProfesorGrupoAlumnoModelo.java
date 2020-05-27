package tecolotl.profesor.modelo;

import java.util.UUID;

public class ProfesorGrupoAlumnoModelo {

    private UUID id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String claveCemtroTrabajo;
    private int totalGrupo;
    private int totalAlumno;

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

    public String getClaveCemtroTrabajo() {
        return claveCemtroTrabajo;
    }

    public void setClaveCemtroTrabajo(String claveCemtroTrabajo) {
        this.claveCemtroTrabajo = claveCemtroTrabajo;
    }

    public int getTotalGrupo() {
        return totalGrupo;
    }

    public void setTotalGrupo(int totalGrupo) {
        this.totalGrupo = totalGrupo;
    }

    public int getTotalAlumno() {
        return totalAlumno;
    }

    public void setTotalAlumno(int totalAlumno) {
        this.totalAlumno = totalAlumno;
    }
}
