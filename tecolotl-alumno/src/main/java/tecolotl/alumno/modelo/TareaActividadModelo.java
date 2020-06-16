package tecolotl.alumno.modelo;

import tecolotl.alumno.entidad.TareaEntidad;
import tecolotl.alumno.entidad.vista.TareaAlumnoVistaEntidad;
import tecolotl.alumno.modelo.TareaModelo;

import java.util.StringJoiner;

public class TareaActividadModelo extends TareaModelo {

    private String idActividad;
    private String preguntaDetonadora;
    private boolean pendiente;

    public TareaActividadModelo() {
    }

    public TareaActividadModelo(TareaAlumnoVistaEntidad tareaAlumnoVistaEntidad) {
        setId(tareaAlumnoVistaEntidad.getId());
        setAsignacion(tareaAlumnoVistaEntidad.getAsignacion());
        idActividad = tareaAlumnoVistaEntidad.getIdActividad();
        preguntaDetonadora = tareaAlumnoVistaEntidad.getPreguntaDetonadora();
        pendiente = tareaAlumnoVistaEntidad.isPendiente();
        setVerRespuesta(tareaAlumnoVistaEntidad.isVerRespuesta());
    }

    public void agregarDatos(TareaModelo tareaModelo) {
        setRespuesta(tareaModelo.getRespuesta());
    }

    public String getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(String idActividad) {
        this.idActividad = idActividad;
    }

    public String getPreguntaDetonadora() {
        return preguntaDetonadora;
    }

    public void setPreguntaDetonadora(String preguntaDetonadora) {
        this.preguntaDetonadora = preguntaDetonadora;
    }

    public boolean isPendiente() {
        return pendiente;
    }

    public void setPendiente(boolean pendiente) {
        this.pendiente = pendiente;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TareaActividadModelo.class.getSimpleName() + "[", "]")
                .add("idActividad='" + idActividad + "'")
                .add("super=".concat(super.toString()))
                .toString();
    }
}
