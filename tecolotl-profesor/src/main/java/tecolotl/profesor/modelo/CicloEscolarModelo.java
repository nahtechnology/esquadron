package tecolotl.profesor.modelo;

import tecolotl.profesor.entidad.CicloEscolarEntidad;
import tecolotl.profesor.validacion.CicloEscolarLlavePrimariaValidacion;
import tecolotl.profesor.validacion.GrupoNuevoValidacion;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;

public class CicloEscolarModelo {

    private Date inicio;
    private Date fin;
    private String descripcion;
    private Boolean activo;
    private String idEscuela;
    private Long totalGrupo;

    public CicloEscolarModelo() {
    }

    public CicloEscolarModelo(Date inicio, Date fin, String idEscuela) {
        this.inicio = inicio;
        this.fin = fin;
        this.idEscuela = idEscuela;
    }

    public CicloEscolarModelo(CicloEscolarEntidad cicloEscolarEntidad) {
        this.inicio = cicloEscolarEntidad.getCicloEscolarPK().getInicio();
        this.fin = cicloEscolarEntidad.getCicloEscolarPK().getFin();
        this.descripcion = cicloEscolarEntidad.getDescripcion();
        this.activo = cicloEscolarEntidad.getActivo();
        this.idEscuela = cicloEscolarEntidad.getCicloEscolarPK().getEscuelaEntidad().getClaveCentroTrabajo();
        this.totalGrupo = cicloEscolarEntidad.getTotalGrupo();
    }

    @NotNull(groups = {CicloEscolarLlavePrimariaValidacion.class, GrupoNuevoValidacion.class})
    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    @NotNull(groups = {CicloEscolarLlavePrimariaValidacion.class, GrupoNuevoValidacion.class})
    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    @NotNull
    @Size(max = 200)
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @NotNull(groups = {CicloEscolarLlavePrimariaValidacion.class, GrupoNuevoValidacion.class})
    @Size(min = 10, max = 14, groups = {CicloEscolarLlavePrimariaValidacion.class})
    public String getIdEscuela() {
        return idEscuela;
    }

    public void setIdEscuela(String idEscuela) {
        this.idEscuela = idEscuela;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Boolean getActivo() {
        return activo;
    }

    public Long getTotalGrupo() {
        return totalGrupo;
    }

    public void setTotalGrupo(Long totalGrupo) {
        this.totalGrupo = totalGrupo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CicloEscolarModelo that = (CicloEscolarModelo) o;
        return inicio.equals(that.inicio) &&
                fin.equals(that.fin) &&
                idEscuela.equals(that.idEscuela);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inicio, fin, idEscuela);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CicloEscolarModelo.class.getSimpleName() + "[", "]")
                .add("inicio=" + inicio)
                .add("fin=" + fin)
                .add("descripcion='" + descripcion + "'")
                .add("activo=" + activo)
                .add("idEscuela='" + idEscuela + "'")
                .toString();
    }
}
