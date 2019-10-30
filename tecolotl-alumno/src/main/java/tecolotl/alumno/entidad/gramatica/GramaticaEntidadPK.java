package tecolotl.alumno.entidad.gramatica;

import tecolotl.alumno.entidad.ActividadEntidad;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

@Embeddable
public class GramaticaEntidadPK implements Serializable{

    private ActividadEntidad actividadEntidad;
    private String codigo;

    public GramaticaEntidadPK() {
    }

    public GramaticaEntidadPK(ActividadEntidad actividadEntidad, String codigo) {
        this.actividadEntidad = actividadEntidad;
        this.codigo = codigo;
    }

    @JoinColumn(name = "id_actividad")
    @ManyToOne(fetch = FetchType.LAZY)
    public ActividadEntidad getActividadEntidad() {
        return actividadEntidad;
    }

    public void setActividadEntidad(ActividadEntidad actividadEntidad) {
        this.actividadEntidad = actividadEntidad;
    }

    @JoinColumn(name = "codigo")
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        GramaticaEntidadPK that = (GramaticaEntidadPK) o;
        return actividadEntidad.equals(that.actividadEntidad) &&
                codigo.equals(that.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actividadEntidad, codigo);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", GramaticaEntidadPK.class.getSimpleName() + "[", "]")
                .add("actividadEntidad=" + actividadEntidad)
                .add("codigo='" + codigo + "'")
                .toString();
    }
}
