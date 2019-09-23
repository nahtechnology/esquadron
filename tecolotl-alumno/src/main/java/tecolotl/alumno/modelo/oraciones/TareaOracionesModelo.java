package tecolotl.alumno.modelo.oraciones;

import tecolotl.alumno.entidad.oraciones.TareaOracionesEntidad;
import tecolotl.alumno.modelo.TareaModelo;

import java.util.Date;
import java.util.StringJoiner;

public class TareaOracionesModelo {
    private OracionesModelo oracionesModelo;
    private Short respuesta;
    private Date horaRespuesta;

    public TareaOracionesModelo() {
    }

    public TareaOracionesModelo(TareaOracionesEntidad tareaOracionesEntidad){
        this.oracionesModelo = new OracionesModelo(tareaOracionesEntidad.getTareaOracionesEntidadPK().getOracionesEntidad());
        this.respuesta = tareaOracionesEntidad.getRespuesta();
        this.horaRespuesta = tareaOracionesEntidad.getHoraRespuesta();
    }

    public TareaOracionesModelo(OracionesModelo oracionesModelo, TareaModelo tareaModelo) {
        this.oracionesModelo = oracionesModelo;
    }

    public OracionesModelo getOracionesModelo() {
        return oracionesModelo;
    }

    public void setOracionesModelo(OracionesModelo oracionesModelo) {
        this.oracionesModelo = oracionesModelo;
    }

    public Short getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(Short respuesta) {
        this.respuesta = respuesta;
    }

    public Date getHoraRespuesta() {
        return horaRespuesta;
    }

    public void setHoraRespuesta(Date horaRespuesta) {
        this.horaRespuesta = horaRespuesta;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TareaOracionesModelo.class.getSimpleName() + "[", "]")
                .add("oracionesModelo=" + oracionesModelo)
                .add("respuesta='" + respuesta + "'")
                .add("horaRespuesta=" + horaRespuesta)
                .toString();
    }
}
