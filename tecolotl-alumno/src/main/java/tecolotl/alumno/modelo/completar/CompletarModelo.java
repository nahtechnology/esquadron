package tecolotl.alumno.modelo.completar;

import tecolotl.alumno.entidad.completar.CompletarEntidad;

import java.util.Objects;
import java.util.StringJoiner;

public class CompletarModelo {

    private Integer id;
    private String oracion;
    private Short cardinalidad;

    public CompletarModelo() {
    }

    public CompletarModelo(Integer id) {
        this.id = id;
    }

    public CompletarModelo(CompletarEntidad completarEntidad) {
        this.id = completarEntidad.getId();
        this.oracion = completarEntidad.getOracion();
        this.cardinalidad = completarEntidad.getCardinalidad();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOracion() {
        return oracion;
    }

    public void setOracion(String oracion) {
        this.oracion = oracion;
    }

    public Short getCardinalidad() {
        return cardinalidad;
    }

    public void setCardinalidad(Short cardinalidad) {
        this.cardinalidad = cardinalidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompletarModelo that = (CompletarModelo) o;
        return id.equals(that.id) &&
                oracion.equals(that.oracion) &&
                cardinalidad.equals(that.cardinalidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, oracion, cardinalidad);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CompletarModelo.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("oracion='" + oracion + "'")
                .add("cardinalidad=" + cardinalidad)
                .toString();
    }
}
