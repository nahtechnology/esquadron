package tecolotl.web.profesor.controladores;

import tecolotl.profesor.modelo.ProfesorDashboardModelo;
import tecolotl.profesor.modelo.ProfesorModelo;
import tecolotl.profesor.sesion.ProfesorSesionBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Map;
import java.util.logging.Logger;

@RequestScoped
@Named
public class DashboardProfesorControlador {
    @Inject
    private Logger logger;
    private String apodo;
    private String correo;

    @Inject
    private ProfesorSesionBean profesorSesionBean;
    private ProfesorModelo profesorModelo;
    private int numero;
    private Map<Integer, ProfesorDashboardModelo> profesorDashboardModeloMap;

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Map<Integer, ProfesorDashboardModelo> getProfesorDashboardModeloMap() {
        return profesorDashboardModeloMap;
    }

    public void setProfesorDashboardModeloMap(Map<Integer, ProfesorDashboardModelo> profesorDashboardModeloMap) {
        this.profesorDashboardModeloMap = profesorDashboardModeloMap;
    }

    @PostConstruct
    public void init(){
        logger.info("Construyendo el controlador Dashboard Profesor");
        profesorModelo = profesorSesionBean.buscaID(134);
        profesorDashboardModeloMap = profesorSesionBean.busca("12eqwdqwe23ed");
        numero = 1;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public ProfesorSesionBean getProfesorSesionBean() {
        return profesorSesionBean;
    }

    public void setProfesorSesionBean(ProfesorSesionBean profesorSesionBean) {
        this.profesorSesionBean = profesorSesionBean;
    }

    public ProfesorModelo getProfesorModelo() {
        return profesorModelo;
    }

    public void setProfesorModelo(ProfesorModelo profesorModelo) {
        this.profesorModelo = profesorModelo;
    }
}
