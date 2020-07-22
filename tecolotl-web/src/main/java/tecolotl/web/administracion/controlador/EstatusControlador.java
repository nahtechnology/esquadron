package tecolotl.web.administracion.controlador;

import tecolotl.administracion.modelo.LicenciaVistaModelo;
import tecolotl.administracion.sesion.EscuelaSesionBean;
import tecolotl.administracion.sesion.LicenciaSesionBean;
import tecolotl.administracion.sesion.LicenciaTiempoSesionBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@Named
public class EstatusControlador implements Serializable {

    private List<LicenciaVistaModelo> licenciaVistaModeloLista;

    @Inject
    private LicenciaTiempoSesionBean licenciaTiempoSesionBean;

    @PostConstruct
    public void init() {
        licenciaVistaModeloLista = licenciaTiempoSesionBean.busca(30);
    }

    public List<LicenciaVistaModelo> getLicenciaVistaModeloLista() {
        return licenciaVistaModeloLista;
    }

    public void setLicenciaVistaModeloLista(List<LicenciaVistaModelo> licenciaVistaModeloLista) {
        this.licenciaVistaModeloLista = licenciaVistaModeloLista;
    }
}
