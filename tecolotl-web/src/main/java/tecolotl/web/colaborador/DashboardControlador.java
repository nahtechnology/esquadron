package tecolotl.web.colaborador;

import tecolotl.alumno.modelo.ActividadModelo;
import tecolotl.alumno.modelo.NivelLenguajeModelo;
import tecolotl.alumno.modelo.TemaModelo;
import tecolotl.alumno.modelo.TipoEstudianteModelo;
import tecolotl.alumno.modelo.glosario.GlosarioModelo;
import tecolotl.alumno.sesion.*;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.model.CollectionDataModel;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@ViewScoped
@Named("colaboradorDashboardControlador")
public class DashboardControlador implements Serializable {
    private HtmlDataTable htmlDataTable;
    private CollectionDataModel<ActividadModelo> collectionDataModel;
    private ActividadModelo actividadModelo;
    private List<TipoEstudianteModelo> tipoEstudianteModeloLista;
    private List<TemaModelo> temaModeloLista;
    private List<NivelLenguajeModelo> nivelLenguajeModeloLista;
    private String[] checkBox;
    private List<GlosarioModelo> glosarioModeloLista;
    private HtmlInputText htmlInputText;


    @Inject
    private ActividadSesionBean actividadSesionBean;

    @Inject
    private TipoEstudianteSesionBean tipoEstudianteSesionBean;

    @Inject
    private TemaSesionBean temaSesionBean;

    @Inject
    private NivelLenguajeSesionBean nivelLenguajeSesionBean;

    @Inject
    private GlosarioSesionBean glosarioSesionBean;

    @Inject
    private Logger logger;

    @PostConstruct
    public void  init(){
        collectionDataModel = new CollectionDataModel<>(actividadSesionBean.busca());
        tipoEstudianteModeloLista = tipoEstudianteSesionBean.busca();
        temaModeloLista = temaSesionBean.busca();
        nivelLenguajeModeloLista = nivelLenguajeSesionBean.busca();
        actividadModelo = new ActividadModelo();
        actividadModelo.setTipoEstudianteModelo(new TipoEstudianteModelo());
        actividadModelo.setTemaModelo(new TemaModelo());
        actividadModelo.setNivelLenguajeModeloLista(new ArrayList<>());
        for (ActividadModelo actividadModelo :
                (List<ActividadModelo>)collectionDataModel.getWrappedData()) {
            logger.info(actividadModelo.toString());
        }
        glosarioModeloLista = glosarioSesionBean.busca("Cgp644Walyk");
    }

    public void agregarActividad(){
        logger.info(actividadModelo.toString());
        logger.info(actividadModelo.getNivelLenguajeModeloLista().toString());
        ActividadModelo ac = ((List<ActividadModelo>)collectionDataModel.getWrappedData()).stream().filter(actividadModelo -> actividadModelo.getIdVideo().equals(actividadModelo.getIdVideo())).findAny().orElse(null);
        if (ac != null){
            FacesContext facesContext = FacesContext.getCurrentInstance();
            FacesMessage  facesMessage = new FacesMessage();
            facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
            facesMessage.setSummary("Ya existe la actividad: ".concat(actividadModelo.getIdVideo()));
            facesMessage.setDetail("Ya existe la actividad: ".concat(actividadModelo.getIdVideo()));
            facesContext.addMessage(htmlInputText.getClientId(facesContext),facesMessage);
        }else{
            for (int i = 0; i < checkBox.length; i++) {
                actividadModelo.getNivelLenguajeModeloLista().add(new NivelLenguajeModelo(Short.parseShort(checkBox[i])));
            }
            actividadSesionBean.agrega(actividadModelo);
            ((List<ActividadModelo>)collectionDataModel.getWrappedData()).add(actividadModelo);
        }

    }

    public HtmlDataTable getHtmlDataTable() {
        return htmlDataTable;
    }

    public void setHtmlDataTable(HtmlDataTable htmlDataTable) {
        this.htmlDataTable = htmlDataTable;
    }

    public CollectionDataModel<ActividadModelo> getCollectionDataModel() {
        return collectionDataModel;
    }

    public void setCollectionDataModel(CollectionDataModel<ActividadModelo> collectionDataModel) {
        this.collectionDataModel = collectionDataModel;
    }

    public ActividadModelo getActividadModelo() {
        return actividadModelo;
    }

    public void setActividadModelo(ActividadModelo actividadModelo) {
        this.actividadModelo = actividadModelo;
    }

    public List<TipoEstudianteModelo> getTipoEstudianteModeloLista() {
        return tipoEstudianteModeloLista;
    }

    public void setTipoEstudianteModeloLista(List<TipoEstudianteModelo> tipoEstudianteModeloLista) {
        this.tipoEstudianteModeloLista = tipoEstudianteModeloLista;
    }

    public List<TemaModelo> getTemaModeloLista() {
        return temaModeloLista;
    }

    public void setTemaModeloLista(List<TemaModelo> temaModeloLista) {
        this.temaModeloLista = temaModeloLista;
    }

    public List<NivelLenguajeModelo> getNivelLenguajeModeloLista() {
        return nivelLenguajeModeloLista;
    }

    public void setNivelLenguajeModeloLista(List<NivelLenguajeModelo> nivelLenguajeModeloLista) {
        this.nivelLenguajeModeloLista = nivelLenguajeModeloLista;
    }

    public String[] getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(String[] checkBox) {
        this.checkBox = checkBox;
    }

    public HtmlInputText getHtmlInputText() {
        return htmlInputText;
    }

    public void setHtmlInputText(HtmlInputText htmlInputText) {
        this.htmlInputText = htmlInputText;
    }

    public List<GlosarioModelo> getGlosarioModeloLista() {
        return glosarioModeloLista;
    }

    public void setGlosarioModeloLista(List<GlosarioModelo> glosarioModeloLista) {
        this.glosarioModeloLista = glosarioModeloLista;
    }
}
