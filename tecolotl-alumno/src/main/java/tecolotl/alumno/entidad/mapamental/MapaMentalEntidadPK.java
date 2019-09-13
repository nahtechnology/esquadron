package tecolotl.alumno.entidad.mapamental;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

@Embeddable
public class MapaMentalEntidadPK implements Serializable {
    private String codigo;
    private Short cardinalidad;

    public MapaMentalEntidadPK() {
    }

    public MapaMentalEntidadPK(String codigo, Short cardinalidad) {
        this.codigo = codigo;
        this.cardinalidad = cardinalidad;
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
        MapaMentalEntidadPK that = (MapaMentalEntidadPK) o;
        return codigo.equals(that.codigo) &&
                cardinalidad.equals(that.cardinalidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, cardinalidad);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MapaMentalEntidadPK.class.getSimpleName() + "[", "]")
                .add("codigo='" + codigo + "'")
                .add("cardinalidad=" + cardinalidad)
                .toString();
    }
}
