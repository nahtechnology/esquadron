package tecolotl.web.administracion.controlador;

import tecolotl.administracion.modelo.LicenciaVistaModelo;
import tecolotl.administracion.modelo.escuela.AlumnoTatalesModelo;
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
    private List<AlumnoTatalesModelo> alumnoTatalesModeloLista;

    @Inject
    private LicenciaTiempoSesionBean licenciaTiempoSesionBean;

    @PostConstruct
    public void init() {
        licenciaVistaModeloLista = licenciaTiempoSesionBean.busca(null);
        alumnoTatalesModeloLista = licenciaTiempoSesionBean.buscaAlumnosSobrePasados();
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

    public List<AlumnoTatalesModelo> getAlumnoTatalesModeloLista() {
        return alumnoTatalesModeloLista;
    }

    public void setAlumnoTatalesModeloLista(List<AlumnoTatalesModelo> alumnoTatalesModeloLista) {
        this.alumnoTatalesModeloLista = alumnoTatalesModeloLista;
    }
}
