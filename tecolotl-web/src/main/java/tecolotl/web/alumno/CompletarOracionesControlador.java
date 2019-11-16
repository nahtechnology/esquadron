package tecolotl.web.alumno;

import tecolotl.alumno.modelo.completar.TareaCompletarModelo;
import tecolotl.alumno.sesion.CompletarSesionBean;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

@Named
@ViewScoped
public class CompletarOracionesControlador implements Serializable {

    @Inject
    private Logger logger;

    @Inject
    private CompletarSesionBean completarSesionBean;

    @Inject
    private AlumnoControlador alumnoControlador;

    private List<TareaCompletarModelo> tareaCompletarModeloLista;

    @PostConstruct
    public void init(){
        tareaCompletarModeloLista = completarSesionBean.busca(alumnoControlador.getTareaActividadModelo().getId());
    }

    public void enviarRespuesta(Integer id) {
        for (TareaCompletarModelo tareaCompletarModelo : tareaCompletarModeloLista) {
            if (tareaCompletarModelo.getId().compareTo(id) == 0) {
                completarSesionBean.respuesta(tareaCompletarModelo, alumnoControlador.getTareaActividadModelo().getId());
                break;
            }
        }
    }

    public String buscaPalabra(Short id) {
        if (id != null) {
            for (TareaCompletarModelo tareaCompletarModelo : tareaCompletarModeloLista) {
                if (tareaCompletarModelo.getCardinalidad().compareTo(id) == 0) {
                    return  tareaCompletarModelo.getOracion().substring(
                            tareaCompletarModelo.getOracion().indexOf("<span class=\"remover\">") + 22,
                            tareaCompletarModelo.getOracion().indexOf("</span>"));
                }
            }
        }
        return null;
    }

    public List<TareaCompletarModelo> getTareaCompletarModeloLista() {
        return tareaCompletarModeloLista;
    }

    public void setTareaCompletarModeloLista(List<TareaCompletarModelo> tareaCompletarModeloLista) {
        this.tareaCompletarModeloLista = tareaCompletarModeloLista;
    }

}

