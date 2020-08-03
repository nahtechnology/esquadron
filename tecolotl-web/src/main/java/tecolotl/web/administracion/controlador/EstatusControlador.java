package tecolotl.web.administracion.controlador;

import tecolotl.administracion.modelo.LicenciaVistaModelo;
import tecolotl.administracion.sesion.EscuelaSesionBean;
import tecolotl.administracion.sesion.LicenciaSesionBean;
import tecolotl.administracion.sesion.LicenciaTiempoSesionBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@SessionScoped
@Named
public class EstatusControlador implements Serializable {

    private List<LicenciaVistaModelo> licenciaVistaModeloLista;

    @Inject
    private LicenciaTiempoSesionBean licenciaTiempoSesionBean;

    @PostConstruct
    public void init() {
        licenciaVistaModeloLista = licenciaTiempoSesionBean.busca(null);
    }

    public int busca(@NotNull String claveCentroTrabajo) {
        for (LicenciaVistaModelo licenciaVistaModelo : licenciaVistaModeloLista) {
            if (licenciaVistaModelo.getClaveCentroTrabajo().equals(claveCentroTrabajo)) {
                return licenciaVistaModelo.getDias();
            }
        }
        return 0;
    }

    public List<LicenciaVistaModelo> getLicenciaVistaModeloLista() {
        return licenciaVistaModeloLista;
    }

    public void setLicenciaVistaModeloLista(List<LicenciaVistaModelo> licenciaVistaModeloLista) {
        this.licenciaVistaModeloLista = licenciaVistaModeloLista;
    }
}
