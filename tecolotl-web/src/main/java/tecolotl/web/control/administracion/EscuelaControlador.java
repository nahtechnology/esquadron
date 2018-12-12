package tecolotl.web.control.administracion;

import tecolotl.administracion.dto.EscuelaDto;
import tecolotl.administracion.sesion.EscuelaSesionBean;
import tecolotl.web.modelo.administracion.EscuelaModelo;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collection;

@Named
@RequestScoped
public class EscuelaControlador {

    @Inject
    private EscuelaSesionBean escuelaSesionBean;

    private Collection<EscuelaDto> escuelas;
    private EscuelaModelo escuelaModelo;

    @PostConstruct
    public void init() {
        escuelas = escuelaSesionBean.busca();
        escuelaModelo = new EscuelaModelo();
    }

    public void inserta() {
        if (escuelaModelo.getClaveCentroTrabajo() != "111") {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El nombre es incorrecto", "El nombre es incorrecto");
            throw new ValidatorException(facesMessage);
        }
    }

    public Collection<EscuelaDto> getEscuelas() {
        return escuelas;
    }

    public void setEscuelas(Collection<EscuelaDto> escuelas) {
        this.escuelas = escuelas;
    }

    public EscuelaModelo getEscuelaModelo() {
        return escuelaModelo;
    }

    public void setEscuelaModelo(EscuelaModelo escuelaModelo) {
        this.escuelaModelo = escuelaModelo;
    }
}
