package tecolotl.web.alumno;

import tecolotl.alumno.modelo.ActividadModelo;
import tecolotl.alumno.sesion.ActividadSesionBean;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@ConversationScoped
@Named
public class ActividadBibliotecaLibre implements Serializable {

    @Inject
    private ActividadSesionBean actividadSesionBean;

    @Inject
    private Conversation conversation;

    private ActividadModelo actividadModelo;

    public String busca(@NotNull @Size(min = 11, max = 11) String idActividad) {
        conversation.begin();
        System.out.println(conversation.isTransient());
        actividadModelo = new ActividadModelo(idActividad);
        actividadModelo.setTranscripcion(actividadSesionBean.transcripcion(idActividad));
        return "actividad";
    }

    public void inicioConversacion() {
        conversation.begin();
    }

    public void finConversacion() {
        conversation.end();
    }

    public ActividadModelo getActividadModelo() {
        return actividadModelo;
    }

    public void setActividadModelo(ActividadModelo actividadModelo) {
        this.actividadModelo = actividadModelo;
    }
}