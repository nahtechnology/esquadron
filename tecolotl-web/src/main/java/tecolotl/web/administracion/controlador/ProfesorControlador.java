package tecolotl.web.administracion.controlador;

import tecolotl.administracion.modelo.escuela.EscuelaBaseModelo;
import tecolotl.profesor.modelo.ProfesorModelo;
import tecolotl.profesor.sesion.ProfesorSesionBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@RequestScoped
@Named(value = "administracionProfesorControlador")
public class ProfesorControlador {

    @Inject
    private ProfesorSesionBean profesorSesionBean;

    @Inject
    protected Logger logger;

    private String claveCentroTrabajo;
    private List<ProfesorModelo> profesorModeloLista;
    private ProfesorModelo profesorModelo;

    @PostConstruct
    public void init() {
        logger.info("contruyendo");
        profesorModelo = new ProfesorModelo();
    }

    public void inicio() {
        profesorModeloLista = profesorSesionBean.buscaPorEscuela(claveCentroTrabajo);
    }

    public void busca(AjaxBehaviorEvent event) {
        logger.info(event.toString());
        //profesorModelo = profesorSesionBean.busca(idProfesor);
    }

    public void llamdaAjax(AjaxBehaviorEvent event) {
        logger.info("llamda Ajax");
    }

    public void inserta(String claveCentroTrabajo) {
        profesorModelo.setEscuelaBaseModelo(new EscuelaBaseModelo(claveCentroTrabajo));
        profesorSesionBean.inserta(profesorModelo);
        profesorModeloLista = profesorSesionBean.buscaPorEscuela(claveCentroTrabajo);
    }

    public void actualiza() {
        profesorSesionBean.actualiza(profesorModelo);
    }

    public String getClaveCentroTrabajo() {
        return claveCentroTrabajo;
    }

    public void setClaveCentroTrabajo(String claveCentroTrabajo) {
        this.claveCentroTrabajo = claveCentroTrabajo;
    }

    public List<ProfesorModelo> getProfesorModeloLista() {
        return profesorModeloLista;
    }

    public void setProfesorModeloLista(List<ProfesorModelo> profesorModeloLista) {
        this.profesorModeloLista = profesorModeloLista;
    }

    public ProfesorModelo getProfesorModelo() {
        return profesorModelo;
    }

    public void setProfesorModelo(ProfesorModelo profesorModelo) {
        this.profesorModelo = profesorModelo;
    }

}
