package tecolotl.web.control.administracion;

import tecolotl.administracion.dto.MotivoBloqueoDto;
import tecolotl.administracion.sesion.MotivoBloqueoSesionBean;
import tecolotl.web.control.PaginadorControlador;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

@Named
@ViewScoped
public class CatalogoMotivoBloqueoControlador extends PaginadorControlador<MotivoBloqueoDto> implements Serializable {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Inject
    private MotivoBloqueoSesionBean motivoBloqueoSesionBean;

    private List<MotivoBloqueoDto> motivoBloqueoDtoLista;
    private MotivoBloqueoDto motivoBloqueoDtoModelo;
    private UIComponent uiComponent;
    private ResourceBundle resourceBundle;

    @PostConstruct
    public void init() {
        motivoBloqueoDtoLista = motivoBloqueoSesionBean.busca();
        motivoBloqueoDtoModelo = new MotivoBloqueoDto();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        resourceBundle = facesContext.getApplication().getResourceBundle(facesContext, "escuelacatalogo");
        setTotalFilas(motivoBloqueoDtoLista.size());
        setFilasEnPagina(5);
        cargaDatos(0);
    }

    public void guarda() {
        if (!existe(false)) {
            motivoBloqueoSesionBean.inserta(motivoBloqueoDtoModelo.getDescripcion());
            motivoBloqueoDtoModelo = new MotivoBloqueoDto();
            motivoBloqueoDtoLista = motivoBloqueoSesionBean.busca();
            setTotalFilas(motivoBloqueoDtoLista.size());
            cargaDatos(0);
        }
    }

    public void actualiza() {
        if (!existe(true)) {
            motivoBloqueoSesionBean.actualiza(motivoBloqueoDtoModelo.getId(), motivoBloqueoDtoModelo.getDescripcion());
            MotivoBloqueoDto motivoBloqueoDto = motivoBloqueoDtoLista.get(motivoBloqueoDtoLista.indexOf(motivoBloqueoDtoModelo));
            motivoBloqueoDto.setDescripcion(motivoBloqueoDtoModelo.getDescripcion());
            motivoBloqueoDtoModelo = new MotivoBloqueoDto();
            cargaDatos(0);
        }
    }


    private boolean existe(boolean indice) {
        if (indice) {
            int actual = motivoBloqueoDtoLista.indexOf(motivoBloqueoDtoModelo);
            for (int i = 0; i < motivoBloqueoDtoLista.size(); i++) {
                if (i == actual) continue;
                if (motivoBloqueoDtoLista.get(i).getDescripcion().equalsIgnoreCase(motivoBloqueoDtoModelo.getDescripcion())) {
                    generaMensaje();
                    return true;
                }
            }
            return false;
        } else {
            for (MotivoBloqueoDto motivoBloqueoDto : motivoBloqueoDtoLista) {
                if (motivoBloqueoDto.getDescripcion().equalsIgnoreCase(motivoBloqueoDtoModelo.getDescripcion())) {
                    generaMensaje();
                    return true;
                }
            }
            return false;
        }
    }

    private void generaMensaje() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String mensaje = resourceBundle.getString("modal.block.exist");
        facesContext.addMessage(uiComponent.getClientId(), new FacesMessage(null, mensaje));
        facesContext.validationFailed();
        facesContext.renderResponse();
    }

    public void limpiaModelo() {
        motivoBloqueoDtoModelo = new MotivoBloqueoDto();
    }

    public void nuevoModelo(MotivoBloqueoDto motivoBloqueoDto) {
        motivoBloqueoDtoModelo = new MotivoBloqueoDto(motivoBloqueoDto.getId(), motivoBloqueoDto.getDescripcion());
    }

    @Override
    protected List<MotivoBloqueoDto> getDatos() {
        return motivoBloqueoDtoLista;
    }

    public MotivoBloqueoDto getMotivoBloqueoDtoModelo() {
        return motivoBloqueoDtoModelo;
    }

    public void setMotivoBloqueoDtoModelo(MotivoBloqueoDto motivoBloqueoDtoModelo) {
        this.motivoBloqueoDtoModelo = motivoBloqueoDtoModelo;
    }

    public UIComponent getUiComponent() {
        return uiComponent;
    }

    public void setUiComponent(UIComponent uiComponent) {
        this.uiComponent = uiComponent;
    }
}
