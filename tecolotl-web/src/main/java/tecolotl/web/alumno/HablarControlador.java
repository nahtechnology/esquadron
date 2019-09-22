package tecolotl.web.alumno;

import tecolotl.alumno.modelo.hablar.HablarModelo;
import tecolotl.alumno.sesion.HablarSesionBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@RequestScoped
@Named
public class HablarControlador {

    @Inject
    private HablarSesionBean hablarSesionBean;

    @Inject
    private AlumnoControlador alumnoControlador;

    private List<HablarModelo> hablarModeloLista;

    @PostConstruct
    public void inicio() {
        hablarModeloLista = hablarSesionBean.busca(alumnoControlador.getTareaActividadModelo().getId());
    }

    public List<HablarModelo> getHablarModeloLista() {
        return hablarModeloLista;
    }

    public void setHablarModeloLista(List<HablarModelo> hablarModeloLista) {
        this.hablarModeloLista = hablarModeloLista;
    }
}
