package tecolotl.alumno.modelo;

import tecolotl.alumno.modelo.TareaModelo;

import java.util.StringJoiner;

public class TareaActividadModelo extends TareaModelo {

    private String idActividad;

    public String getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(String idActividad) {
        this.idActividad = idActividad;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TareaActividadModelo.class.getSimpleName() + "[", "]")
                .add("idActividad='" + idActividad + "'")
                .add("super=".concat(super.toString()))
                .toString();
    }
}
