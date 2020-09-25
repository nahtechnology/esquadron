package tecolotl.alumno.modelo.vista;

import java.util.Date;

public class DetalleAlumno2Modelo {

    private String idEscuela;
    private Date inicio;
    private Date fin;
    private String descripcion;
    private String idGrupo;
    private Short grado;
    private String grupo;

    public String getIdEscuela() {
        return idEscuela;
    }

    public void setIdEscuela(String idEscuela) {
        this.idEscuela = idEscuela;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
    public String toString() {
        final StringBuilder sb = new StringBuilder("DetalleAlumno2Modelo{");
        sb.append("idEscuela='").append(idEscuela).append('\'');
        sb.append(", inicio=").append(inicio);
        sb.append(", fin=").append(fin);
        sb.append(", descripcion='").append(descripcion).append('\'');
        sb.append(", idGrupo='").append(idGrupo).append('\'');
        sb.append(", grado=").append(grado);
        sb.append(", grupo='").append(grupo).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
