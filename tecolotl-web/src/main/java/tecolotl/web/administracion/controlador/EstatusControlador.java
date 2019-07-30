package tecolotl.web.administracion.controlador;

import tecolotl.administracion.sesion.EscuelaSesionBean;
import tecolotl.administracion.sesion.LicenciaSesionBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@SessionScoped
@Named
public class EstatusControlador implements Serializable {

    private int escuelas;
    private int licencias;

    @Inject
    private EscuelaSesionBean escuelaSesionBean;

    @Inject
    private LicenciaSesionBean licenciaSesionBean;

    @PostConstruct
    public void init() {
        escuelas = escuelaSesionBean.cuenta().intValue();
        licencias = licenciaSesionBean.cuenta().intValue();
    }

    public int getEscuelas() {
        return escuelas;
    }

    public void setEscuelas(int escuelas) {
        this.escuelas = escuelas;
    }

    public int getLicencias() {
        return licencias;
    }

    public void setLicencias(int licencias) {
        this.licencias = licencias;
    }

}
