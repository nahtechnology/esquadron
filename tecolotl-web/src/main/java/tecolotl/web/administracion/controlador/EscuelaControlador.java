package tecolotl.web.administracion.controlador;

import tecolotl.administracion.modelo.direccion.DireccionModelo;
import tecolotl.administracion.modelo.escuela.EscuelaDetalleModelo;
import tecolotl.administracion.sesion.DireccionSesionBean;
import tecolotl.administracion.sesion.EscuelaSesionBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RequestScoped
@Named
public class EscuelaControlador {

    @Valid
    private EscuelaDetalleModelo escuelaDetalleModelo;

    private DireccionModelo direccionModelo;

    @NotNull
    private String codigoPostal;

    @Inject
    private EscuelaSesionBean escuelaSesionBean;

    @Inject
    private DireccionSesionBean direccionSesionBean;

    @PostConstruct
    public void init() {
        escuelaDetalleModelo = new EscuelaDetalleModelo();
    }

    public void buscaColonias(AjaxBehaviorEvent ajaxBehaviorEvent) {
        direccionModelo = direccionSesionBean.busca(codigoPostal);
        DireccionModelo direccionModelo1 = direccionSesionBean.busca(direccionModelo.getColoniaModeloLista().get(0).getId());
        direccionModelo.setEstado(direccionModelo1.getEstado());
        direccionModelo.setMunicipio(direccionModelo1.getMunicipio());
    }

    public void inserta() {
        escuelaSesionBean.inserta(escuelaDetalleModelo);
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

    public void setDireccionModelo(DireccionModelo direccionModelo) {
        this.direccionModelo = direccionModelo;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }
}