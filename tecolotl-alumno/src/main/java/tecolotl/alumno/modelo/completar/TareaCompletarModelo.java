package tecolotl.alumno.modelo.completar;

import tecolotl.alumno.entidad.completar.TareaCompletarEntidad;

import java.util.Date;
import java.util.StringJoiner;

public class TareaCompletarModelo extends CompletarModelo {

    private Integer respuesta;
    private Date horaRespuesta;

    public TareaCompletarModelo() {
    }

    public TareaCompletarModelo(Integer id) {
        super(id);
    }

    public TareaCompletarModelo(TareaCompletarEntidad tareaCompletarEntidad) {
        super(tareaCompletarEntidad.getTareaCompletarEntidadPK().getCompletarEntidad());
        respuesta = tareaCompletarEntidad.getRespuesta();
        horaRespuesta = tareaCompletarEntidad.getHora_respuesta();
    }

    public Integer getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(Integer respuesta) {
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
        return new StringJoiner(", ", TareaCompletarModelo.class.getSimpleName() + "[", "]")
                .add("respuesta=" + respuesta)
                .add("horaRespuesta=" + horaRespuesta)
                .add("super=" + super.toString())
                .toString();
    }
}
