package tecolotl.web.administracion.controlador;


import tecolotl.administracion.modelo.escuela.EscuelaDetalleModelo;
import tecolotl.administracion.modelo.escuela.LicenciaModelo;
import tecolotl.administracion.sesion.EscuelaSesionBean;
import tecolotl.administracion.sesion.LicenciaSesionBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@RequestScoped
@Named
public class DetalleEscuelaControlador {

    private List<LicenciaModelo> licenciaModeloLista;
    private EscuelaDetalleModelo escuelaDetalleModelo;

    @Inject
    private EscuelaSesionBean escuelaSesionBean;

    @Inject
    private LicenciaSesionBean licenciaSesionBean;

    @PostConstruct
    public void init() {
        String claveCentroTrabajo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("escuela");
        escuelaDetalleModelo = escuelaSesionBean.busca(claveCentroTrabajo);
        licenciaModeloLista = licenciaSesionBean.busca(claveCentroTrabajo);
    }

    public EscuelaDetalleModelo getEscuelaDetalleModelo() {
        return escuelaDetalleModelo;
    }

    public void setEscuelaDetalleModelo(EscuelaDetalleModelo escuelaDetalleModelo) {
        this.escuelaDetalleModelo = escuelaDetalleModelo;
    }

    public List<LicenciaModelo> getLicenciaModeloLista() {
        return licenciaModeloLista;
    }

    public void setLicenciaModeloLista(List<LicenciaModelo> licenciaModeloLista) {
        this.licenciaModeloLista = licenciaModeloLista;
    }

}
