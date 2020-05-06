package tecolotl.profesor.modelo;

import tecolotl.profesor.entidad.AlumnoTareasNivelEntidad;

import java.util.StringJoiner;
import java.util.UUID;

public class AlumnoTareasNivelModelo {

    private UUID idGrupo;
    private UUID idAlumno;
    private Short nivelLenguajeAlumno;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Float promedio;
    private Integer totalTareas;
    private String  nivelLenguaje;

    public AlumnoTareasNivelModelo() {
    }

    public AlumnoTareasNivelModelo(AlumnoTareasNivelEntidad alumnoTareasNivelEntidad) {
        this.idGrupo = alumnoTareasNivelEntidad.getIdGrupo();
        this.idAlumno = alumnoTareasNivelEntidad.getIdAlumno();
        this.nivelLenguajeAlumno = alumnoTareasNivelEntidad.getNivelLenguajeAlumno();
        this.nombre = alumnoTareasNivelEntidad.getNombre();
        this.apellidoPaterno = alumnoTareasNivelEntidad.getApellidoPaterno();
        this.apellidoMaterno = alumnoTareasNivelEntidad.getApellidoMaterno();
        this.totalTareas = alumnoTareasNivelEntidad.getTotalTareas();
        this.nivelLenguaje = alumnoTareasNivelEntidad.getNivelLenguaje();
    }

    public UUID getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(UUID idGrupo) {
        this.idGrupo = idGrupo;
    }

    public UUID getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(UUID idAlumno) {
        this.idAlumno = idAlumno;
    }

    public Integer getTotalTareas() {
        return totalTareas;
    }

    public void setTotalTareas(Integer totalTareas) {
        this.totalTareas = totalTareas;
    }

    public String getNivelLenguaje() {
        return nivelLenguaje;
    }

    public void setNivelLenguaje(String nivelLenguaje) {
        this.nivelLenguaje = nivelLenguaje;
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

    public Short getNivelLenguajeAlumno() {
        return nivelLenguajeAlumno;
    }

    public void setNivelLenguajeAlumno(Short nivelLenguajeAlumno) {
        this.nivelLenguajeAlumno = nivelLenguajeAlumno;
    }

    public Float getPromedio() {
        return promedio;
    }

    public void setPromedio(Float promedio) {
        this.promedio = promedio;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", AlumnoTareasNivelModelo.class.getSimpleName() + "[", "]")
                .add("idGrupo=" + idGrupo)
                .add("idAlumno=" + idAlumno)
                .add("nivelLenguajeAlumno=" + nivelLenguajeAlumno)
                .add("nombre='" + nombre + "'")
                .add("apellidoPaterno='" + apellidoPaterno + "'")
                .add("apellidoMaterno='" + apellidoMaterno + "'")
                .add("totalTareas=" + totalTareas)
                .add("nivelLenguaje='" + nivelLenguaje + "'")
                .toString();
    }
}
