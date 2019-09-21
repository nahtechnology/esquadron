package tecolotl.alumno.modelo.oraciones;

import tecolotl.alumno.entidad.oraciones.TareaOracionesEntidad;
import tecolotl.alumno.modelo.TareaModelo;

import java.util.Date;
import java.util.StringJoiner;

public class TareaOracionesModelo {
    private OracionesModelo oracionesModelo;
    private TareaModelo tareaModelo;
    private String respuesta;
    private Date horaRespuesta;

    public TareaOracionesModelo() {
    }

    public TareaOracionesModelo(TareaOracionesEntidad tareaOracionesEntidad){
        this.oracionesModelo = new OracionesModelo(tareaOracionesEntidad.getTareaOracionesEntidadPK().getOracionesEntidad());
        this.tareaModelo = new TareaModelo(tareaOracionesEntidad.getTareaOracionesEntidadPK().getTareaEntidad());
        this.respuesta = tareaOracionesEntidad.getRespuesta();
        this.horaRespuesta = tareaOracionesEntidad.getHora_Respuesta();
    }

    public TareaOracionesModelo(OracionesModelo oracionesModelo, TareaModelo tareaModelo) {
        this.oracionesModelo = oracionesModelo;
        this.tareaModelo = tareaModelo;
    }

    public OracionesModelo getOracionesModelo() {
        return oracionesModelo;
    }

    public void setOracionesModelo(OracionesModelo oracionesModelo) {
        this.oracionesModelo = oracionesModelo;
    }

    public TareaModelo getTareaModelo() {
        return tareaModelo;
    }

    public void setTareaModelo(TareaModelo tareaModelo) {
        this.tareaModelo = tareaModelo;
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

    @Override
    public String toString() {
        return new StringJoiner(", ", TareaOracionesModelo.class.getSimpleName() + "[", "]")
                .add("oracionesModelo=" + oracionesModelo)
                .add("tareaModelo=" + tareaModelo)
                .add("respuesta='" + respuesta + "'")
                .add("horaRespuesta=" + horaRespuesta)
                .toString();
    }
}
