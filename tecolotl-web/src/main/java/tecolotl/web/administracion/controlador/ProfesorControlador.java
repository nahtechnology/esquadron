package tecolotl.web.administracion.controlador;

import tecolotl.administracion.modelo.escuela.EscuelaBaseModelo;
import tecolotl.profesor.modelo.ProfesorModelo;
import tecolotl.profesor.sesion.ProfesorSesionBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@ViewScoped
@Named(value = "administracionProfesorControlador")
public class ProfesorControlador implements Serializable {

    @Inject
    private ProfesorSesionBean profesorSesionBean;

    @Inject
    protected Logger logger;

    private String claveCentroTrabajo;
    private List<ProfesorModelo> profesorModeloLista;
    private ProfesorModelo profesorModelo;
    private  Integer eliminar;

    @PostConstruct
    public void init() {
        profesorModelo = new ProfesorModelo();
    }

    public void inicio() {
        profesorModeloLista = profesorSesionBean.buscaPorEscuela(claveCentroTrabajo);
    }

    public void busca(ProfesorModelo profesorModelo) {
        this.profesorModelo = profesorModelo;
    }

    public void inserta(String claveCentroTrabajo) {
        profesorModelo.setEscuelaBaseModelo(new EscuelaBaseModelo(claveCentroTrabajo));
        profesorSesionBean.inserta(profesorModelo);
        profesorModeloLista = profesorSesionBean.buscaPorEscuela(claveCentroTrabajo);
        profesorModelo = new ProfesorModelo();
    }

    public void actualiza() {
        profesorSesionBean.actualiza(profesorModelo);
        profesorModeloLista = profesorSesionBean.buscaPorEscuela(profesorModeloLista.get(0).getEscuelaBaseModelo().getClaveCentroTrabajo());
        profesorModelo = new ProfesorModelo();
    }
    public void elimina(ProfesorModelo profesorModelo){
       eliminar = profesorSesionBean.elimina(profesorModelo.getId());
       logger.info(String.valueOf(eliminar));
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

    public Integer getEliminar() {
        return eliminar;
    }

    public void setEliminar(Integer eliminar) {
        this.eliminar = eliminar;
    }
}
