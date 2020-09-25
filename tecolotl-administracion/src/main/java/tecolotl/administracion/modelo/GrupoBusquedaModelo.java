package tecolotl.administracion.modelo;

import tecolotl.administracion.persistencia.vista.AlumnoEscuelaVista;

import java.util.Objects;

public class GrupoBusquedaModelo {

    private String idGrupo;
    private Short grado;
    private String grupo;

    public GrupoBusquedaModelo() {
    }

    public GrupoBusquedaModelo(AlumnoEscuelaVista alumnoEscuelaVista) {
        idGrupo = alumnoEscuelaVista.getIdGrupo();
        grado = alumnoEscuelaVista.getGrado();
        grupo = alumnoEscuelaVista.getGrupo();
    }

    public String getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(String idGrupo) {
        this.idGrupo = idGrupo;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrupoBusquedaModelo that = (GrupoBusquedaModelo) o;
        return idGrupo.equals(that.idGrupo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idGrupo);
    }
}
