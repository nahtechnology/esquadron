package tecolotl.web.administracion.controlador;

import tecolotl.profesor.modelo.CicloEscolarModelo;
import tecolotl.profesor.modelo.GrupoModelo;
import tecolotl.profesor.sesion.CicloEscolarSessionBean;
import tecolotl.profesor.sesion.GrupoSesionBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@ViewScoped
@Named
public class GrupoControlador implements Serializable {

    @Inject
    private CicloEscolarSessionBean cicloEscolarSessionBean;

    @Inject
    private GrupoSesionBean grupoSesionBean;

    @Inject
    private Logger logger;

    private Map<Integer, CicloEscolarModelo> cicloEscolarModeloMapa;
    private List<GrupoModelo> grupoModeloLista;
    private GrupoModelo grupoModelo;
    private Integer cicloEscolar;
    private String profesor;
    private String claveCentroTrabajo;

    private HtmlInputText htmlInputText;

    @PostConstruct
    public void init() {
        logger.info("construyendo");
    }

    public void inicio() {
        cicloEscolarModeloMapa = cicloEscolarSessionBean.busca(claveCentroTrabajo, true, UUID.fromString(profesor))
                .stream().collect(Collectors.toMap(c -> c.hashCode(), Function.identity()));
        grupoModelo = new GrupoModelo();
    }

    public void busca() {
        if (cicloEscolar != null) {
            CicloEscolarModelo cicloEscolarModelo = cicloEscolarModeloMapa.get(cicloEscolar);
            logger.info(profesor);
            grupoModeloLista = grupoSesionBean.busca(cicloEscolarModelo.getInicio(), cicloEscolarModelo.getFin(), claveCentroTrabajo, UUID.fromString(profesor));
        } else {
            grupoModeloLista = null;
        }
    }

    public void agrega() {
        logger.info(grupoModelo.toString());
        if (cicloEscolarModeloMapa.containsKey(grupoModelo.hashCode())) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(
                    htmlInputText.getClientId(),
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "El grupo: " + grupoModelo.getGrado().toString() + " " + grupoModelo.getGrupo().toString() + " ya existe", null));
        } else {
            logger.info("guardando");
        }
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public String getClaveCentroTrabajo() {
        return claveCentroTrabajo;
    }

    public void setClaveCentroTrabajo(String claveCentroTrabajo) {
        this.claveCentroTrabajo = claveCentroTrabajo;
    }

    public Integer getCicloEscolar() {
        return cicloEscolar;
    }

    public void setCicloEscolar(Integer cicloEscolar) {
        this.cicloEscolar = cicloEscolar;
    }

    public Map<Integer, CicloEscolarModelo> getCicloEscolarModeloMapa() {
        return cicloEscolarModeloMapa;
    }

    public void setCicloEscolarModeloMapa(Map<Integer, CicloEscolarModelo> cicloEscolarModeloMapa) {
        this.cicloEscolarModeloMapa = cicloEscolarModeloMapa;
    }

    public List<GrupoModelo> getGrupoModeloLista() {
        return grupoModeloLista;
    }

    public void setGrupoModeloLista(List<GrupoModelo> grupoModeloLista) {
        this.grupoModeloLista = grupoModeloLista;
    }

    public GrupoModelo getGrupoModelo() {
        return grupoModelo;
    }

    public void setGrupoModelo(GrupoModelo grupoModelo) {
        this.grupoModelo = grupoModelo;
    }

    public HtmlInputText getHtmlInputText() {
        return htmlInputText;
    }

    public void setHtmlInputText(HtmlInputText htmlInputText) {
        this.htmlInputText = htmlInputText;
    }
}
