package tecolotl.alumno.modelo.mapamental;

import tecolotl.alumno.entidad.mapamental.MapaMentalActividadEntidad;
import tecolotl.alumno.entidad.mapamental.MapaMentalEntidad;
import tecolotl.alumno.entidad.mapamental.TareaMapaMentalActividadEntidad;

import java.util.Date;
import java.util.StringJoiner;

public class TareaMapaMentalModelo extends MapaMentalModelo{

    private String respuesta;
    private Date horaRespuesta;
    private Short vuelta;

    public TareaMapaMentalModelo() {
    }

    public TareaMapaMentalModelo(MapaMentalEntidad mapaMentalEntidad) {
        super(mapaMentalEntidad);
    }

    public TareaMapaMentalModelo(MapaMentalActividadEntidad mapaMentalActividadEntidad) {
        super(mapaMentalActividadEntidad);
    }

    public TareaMapaMentalModelo(TareaMapaMentalActividadEntidad tareaMapaMentalActividadEntidad) {
        this(tareaMapaMentalActividadEntidad.getTareaMapaMentalActividadEntidadPK().getMapaMentalActividadEntidad());
        this.respuesta = tareaMapaMentalActividadEntidad.getTextoRespuesta();
        this.horaRespuesta = tareaMapaMentalActividadEntidad.getHoraRespuesta();
        this.vuelta = tareaMapaMentalActividadEntidad.getTareaMapaMentalActividadEntidadPK().getVuelta();
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public Date getHoraRespuesta() {
        return horaRespuesta;
    }

    public void setHoraRespuesta(Date horaRespuesta) {
        this.horaRespuesta = horaRespuesta;
    }

    public Short getVuelta() {
        return vuelta;
    }

    public void setVuelta(Short vuelta) {
        this.vuelta = vuelta;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TareaMapaMentalModelo.class.getSimpleName() + "[", "]")
                .add("respuesta='" + respuesta + "'")
                .add("horaRespuesta=" + horaRespuesta)
                .add("vuelta=" + vuelta)
                .add("super=".concat(super.toString()))
                .toString();
    }

}
