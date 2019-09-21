package tecolotl.alumno.modelo.oraciones;

import tecolotl.alumno.entidad.ActividadEntidad;
import tecolotl.alumno.entidad.oraciones.OracionesEntidad;
import tecolotl.alumno.modelo.ActividadModelo;

import java.util.StringJoiner;

public class OracionesModelo {
    private ActividadModelo actividadModelo;
    private String codigo;
    private Short cardinalidad;
    private String oracion;

    public OracionesModelo() {
    }

    public OracionesModelo(OracionesEntidad oracionesEntidad){
        this.actividadModelo = new ActividadModelo(oracionesEntidad.getOracionesEntidadPK().getActividadEntidad());
        this.codigo = oracionesEntidad.getOracionesEntidadPK().getCodigo();
        this.cardinalidad = oracionesEntidad.getOracionesEntidadPK().getCardinalidad();
        this.oracion = oracionesEntidad.getOracion();
    }

    public OracionesModelo(ActividadModelo actividadModelo, String codigo, Short cardinalidad) {
        this.actividadModelo = actividadModelo;
        this.codigo = codigo;
        this.cardinalidad = cardinalidad;
    }

    public ActividadModelo getActividadModelo() {
        return actividadModelo;
    }

    public void setActividadModelo(ActividadModelo actividadModelo) {
        this.actividadModelo = actividadModelo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Short getCardinalidad() {
        return cardinalidad;
    }

    public void setCardinalidad(Short cardinalidad) {
        this.cardinalidad = cardinalidad;
    }

    public String getOracion() {
        return oracion;
    }

    public void setOracion(String oracion) {
        this.oracion = oracion;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", OracionesModelo.class.getSimpleName() + "[", "]")
                .add("actividadModelo=" + actividadModelo)
                .add("codigo='" + codigo + "'")
                .add("cardinalidad=" + cardinalidad)
                .add("oracion='" + oracion + "'")
                .toString();
    }
}
