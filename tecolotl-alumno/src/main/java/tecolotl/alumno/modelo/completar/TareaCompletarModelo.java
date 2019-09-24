package tecolotl.alumno.modelo.completar;

import tecolotl.alumno.entidad.completar.TareaCompletarEntidad;

import java.util.Date;

public class TareaCompletarModelo extends CompletarModelo {

    private String respuesta;
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

}
