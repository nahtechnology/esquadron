package tecolotl.web.alumno.bibliotecalibre;

import tecolotl.alumno.modelo.relacionar.RelacionarModelo;
import tecolotl.alumno.sesion.RelacionarSesionBean;
import tecolotl.web.alumno.AlumnoControlador;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@RequestScoped
@Named
public class RelacionarBiblitecaLibreControler {

    @Inject
    private AlumnoControlador alumnoControlador;

    @Inject
    private RelacionarSesionBean relacionarSesionBean;

    private List<RelacionarModelo> relacionarModeloLista;

    @PostConstruct
    public void inicio() {
        relacionarModeloLista = relacionarSesionBean.bibliotecaLibre(alumnoControlador.getActividadModeloBibliotecaLibre().getIdVideo());
    }

    public List<RelacionarModelo> getRelacionarModeloLista() {
        return relacionarModeloLista;
    }

    public void setRelacionarModeloLista(List<RelacionarModelo> relacionarModeloLista) {
        this.relacionarModeloLista = relacionarModeloLista;
    }
}
