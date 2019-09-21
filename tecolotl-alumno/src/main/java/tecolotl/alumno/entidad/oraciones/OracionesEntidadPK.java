package tecolotl.alumno.entidad.oraciones;

import tecolotl.alumno.entidad.ActividadEntidad;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

@Embeddable
public class OracionesEntidadPK implements Serializable {
    private ActividadEntidad actividadEntidad;
    private String codigo;
    private Short cardinalidad;

    public OracionesEntidadPK() {
    }

    public OracionesEntidadPK(ActividadEntidad actividadEntidad, String codigo, Short cardinalidad){
        this.actividadEntidad = actividadEntidad;
        this.codigo = codigo;
        this.cardinalidad = cardinalidad;
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

    @JoinColumn(name = "cardinalidad")
    public Short getCardinalidad() {
        return cardinalidad;
    }

    public void setCardinalidad(Short cardinalidad) {
        this.cardinalidad = cardinalidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        OracionesEntidadPK that = (OracionesEntidadPK) o;
        return actividadEntidad.equals(that.actividadEntidad) &&
                codigo.equals(that.codigo) &&
                cardinalidad.equals(that.cardinalidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actividadEntidad, codigo, cardinalidad);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", OracionesEntidadPK.class.getSimpleName() + "[", "]")
                .add("actividadEntidad=" + actividadEntidad)
                .add("codigo='" + codigo + "'")
                .add("cardinalidad=" + cardinalidad)
                .toString();
    }
}
