package tecolotl.administracion.modelo.escuela;

import tecolotl.administracion.persistencia.entidad.EscuelaEntidad;
import tecolotl.administracion.validacion.escuela.EscuelaLlavePrimariaValidacion;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.StringJoiner;

public class EscuelaBaseModelo implements Comparable<EscuelaBaseModelo> {

    @NotNull(message = "Clave centro de trabajo no puede ser nulo", groups = {EscuelaLlavePrimariaValidacion.class})
    @Size(min = 10, max = 14)
    private String claveCentroTrabajo;

    @NotNull
    @Size(min = 4, max = 70)
    private String nombre;

    private Integer galaxia;
    private boolean bloqueoAlumno;
    private boolean bloqueoProfesor;
    private Integer descargables;

    public EscuelaBaseModelo() {
    }

    public EscuelaBaseModelo(String claveCentroTrabajo) {
        this.claveCentroTrabajo = claveCentroTrabajo;
    }

    public EscuelaBaseModelo(EscuelaEntidad escuelaEntidad) {
        claveCentroTrabajo = escuelaEntidad.getClaveCentroTrabajo();
        nombre = escuelaEntidad.getNombre();
        galaxia = escuelaEntidad.getGalaxia();
        bloqueoAlumno = escuelaEntidad.isBloqueoAlumno();
        bloqueoProfesor = escuelaEntidad.isBloqueoProfesor();
        descargables = escuelaEntidad.getDescargables();
    }

    @Override
    public int compareTo(EscuelaBaseModelo o) {
        return claveCentroTrabajo.compareTo(o.claveCentroTrabajo);
    }

    public boolean isBloqueoAlumno() {
        return bloqueoAlumno;
    }

    public void setBloqueoAlumno(boolean bloqueoAlumno) {
        this.bloqueoAlumno = bloqueoAlumno;
    }

    public boolean isBloqueoProfesor() {
        return bloqueoProfesor;
    }

    public void setBloqueoProfesor(boolean bloqueoProfesor) {
        this.bloqueoProfesor = bloqueoProfesor;
    }

    public String getClaveCentroTrabajo() {
        return claveCentroTrabajo;
    }

    public void setClaveCentroTrabajo(String claveCentroTrabajo) {
        this.claveCentroTrabajo = claveCentroTrabajo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getGalaxia() {
        return galaxia;
    }

    public void setGalaxia(Integer galaxia) {
        this.galaxia = galaxia;
    }

    public Integer getDescargables() {
        return descargables;
    }

    public void setDescargables(Integer descargables) {
        this.descargables = descargables;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EscuelaBaseModelo that = (EscuelaBaseModelo) o;
        return claveCentroTrabajo.equals(that.claveCentroTrabajo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(claveCentroTrabajo);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", EscuelaBaseModelo.class.getSimpleName() + "[", "]")
                .add("claveCentroTrabajo='" + claveCentroTrabajo + "'")
                .add("nombre='" + nombre + "'")
                .toString();
    }
}
