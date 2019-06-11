package tecolotl.web.administracion.controlador;


import tecolotl.administracion.modelo.direccion.DireccionModelo;
import tecolotl.administracion.modelo.escuela.ContactoModelo;
import tecolotl.administracion.modelo.escuela.EscuelaDetalleModelo;
import tecolotl.administracion.modelo.escuela.LicenciaModelo;
import tecolotl.administracion.modelo.escuela.TipoContactoModelo;
import tecolotl.administracion.sesion.DireccionSesionBean;
import tecolotl.administracion.sesion.EscuelaSesionBean;
import tecolotl.administracion.sesion.LicenciaSesionBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
@Named
public class DetalleEscuelaControlador {

    private List<LicenciaModelo> licenciaModeloLista;
    private EscuelaDetalleModelo escuelaDetalleModelo;
    private DireccionModelo direccionModelo;

    @Inject
    private EscuelaSesionBean escuelaSesionBean;

    @Inject
    private DireccionSesionBean direccionSesionBean;

    @Inject
    private LicenciaSesionBean licenciaSesionBean;

    @PostConstruct
    public void init() {
        String claveCentroTrabajo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("escuela");
        escuelaDetalleModelo = escuelaSesionBean.busca(claveCentroTrabajo);
        direccionModelo = direccionSesionBean.busca(escuelaDetalleModelo.getColoniaModelo().getId());
        licenciaModeloLista = licenciaSesionBean.busca(claveCentroTrabajo);
    }

    public EscuelaDetalleModelo getEscuelaDetalleModelo() {
        return escuelaDetalleModelo;
    }

    public void setEscuelaDetalleModelo(EscuelaDetalleModelo escuelaDetalleModelo) {
        this.escuelaDetalleModelo = escuelaDetalleModelo;
    }

    public DireccionModelo getDireccionModelo() {
        return direccionModelo;
    }

    public List<LicenciaModelo> getLicenciaModeloLista() {
        return licenciaModeloLista;
    }

    public void setLicenciaModeloLista(List<LicenciaModelo> licenciaModeloLista) {
        this.licenciaModeloLista = licenciaModeloLista;
    }

    public void setDireccionModelo(DireccionModelo direccionModelo) {
        this.direccionModelo = direccionModelo;
    }

}
