package tecolotl.web.administracion.controlador;


import tecolotl.administracion.modelo.escuela.EscuelaDetalleModelo;
import tecolotl.administracion.modelo.escuela.LicenciaModelo;
import tecolotl.administracion.sesion.EscuelaSesionBean;
import tecolotl.administracion.sesion.LicenciaSesionBean;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

@ViewScoped
@Named
public class DetalleEscuelaControlador implements Serializable {

    private List<LicenciaModelo> licenciaModeloLista;
    private EscuelaDetalleModelo escuelaDetalleModelo;
    private String escuela;

    @Inject
    private Logger logger;

    @Inject
    private EscuelaSesionBean escuelaSesionBean;

    @Inject
    private LicenciaSesionBean licenciaSesionBean;

    public void iniicio() {
        escuelaDetalleModelo = escuelaSesionBean.busca(escuela);
        licenciaModeloLista = licenciaSesionBean.busca(escuela);
    }

    public void bloqueaProfesor() {
        escuelaSesionBean.bloqueProfesor(escuelaDetalleModelo.getClaveCentroTrabajo(), escuelaDetalleModelo.isBloqueoProfesor());
    }

    public void bloqueoAlumno() {
        escuelaSesionBean.bloqueAlumno(escuelaDetalleModelo.getClaveCentroTrabajo(), escuelaDetalleModelo.isBloqueoAlumno());
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

    public String getEscuela() {
        return escuela;
    }

    public void setEscuela(String escuela) {
        this.escuela = escuela;
    }
}
