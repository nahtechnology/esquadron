package tecolotl.web.alumno;

import tecolotl.alumno.modelo.completar.CompletarModelo;
import tecolotl.alumno.modelo.completar.TareaCompletarModelo;
import tecolotl.alumno.sesion.CompletarSesionBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

@ViewScoped
@Named
public class CompletarOracionesControlador implements Serializable {

    @Inject
    private Logger logger;

    @Inject
    private CompletarSesionBean completarSesionBean;

    @Inject
    private AlumnoControlador alumnoControlador;

    private List<TareaCompletarModelo> tareaCompletarModeloLista;
    private String respeusta;

    @PostConstruct
    public void init(){
        tareaCompletarModeloLista = completarSesionBean.busca(alumnoControlador.getTareaActividadModelo().getId());
    }

    public void enviarRespuesta() {

    }

    public List<TareaCompletarModelo> getTareaCompletarModeloLista() {
        return tareaCompletarModeloLista;
    }

    public void setTareaCompletarModeloLista(List<TareaCompletarModelo> tareaCompletarModeloLista) {
        this.tareaCompletarModeloLista = tareaCompletarModeloLista;
    }

    public String getRespeusta() {
        return respeusta;
    }

    public void setRespeusta(String respeusta) {
        this.respeusta = respeusta;
    }
}
