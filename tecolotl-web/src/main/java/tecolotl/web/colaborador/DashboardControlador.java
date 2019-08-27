package tecolotl.web.colaborador;

import tecolotl.alumno.modelo.ActividadModelo;
import tecolotl.alumno.modelo.NivelLenguajeModelo;
import tecolotl.alumno.modelo.TemaModelo;
import tecolotl.alumno.modelo.TipoEstudianteModelo;
import tecolotl.alumno.sesion.ActividadSesionBean;
import tecolotl.alumno.sesion.NivelLenguajeSesionBean;
import tecolotl.alumno.sesion.TemaSesionBean;
import tecolotl.alumno.sesion.TipoEstudianteSesionBean;
import tecolotl.web.alumno.ActividadesModelo;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.model.CollectionDataModel;
import javax.inject.Inject;
import javax.inject.Named;
import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RequestScoped
@Named("colaboradorDashboardControlador")
public class DashboardControlador  {
    private HtmlDataTable htmlDataTable;
    private CollectionDataModel<ActividadModelo> collectionDataModel;
    private ActividadModelo actividadModelo;
    private List<TipoEstudianteModelo> tipoEstudianteModeloLista;
    private List<TemaModelo> temaModeloLista;
    private List<NivelLenguajeModelo> nivelLenguajeModeloLista;
    private String[] checkBox;


    @Inject
    private ActividadSesionBean actividadSesionBean;

    @Inject
    private TipoEstudianteSesionBean tipoEstudianteSesionBean;

    @Inject
    private TemaSesionBean temaSesionBean;

    @Inject
    private NivelLenguajeSesionBean nivelLenguajeSesionBean;

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
    }

    public void agregarActividad(){
        logger.info(actividadModelo.toString());
        logger.info(actividadModelo.getNivelLenguajeModeloLista().toString());
        for (int i = 0; i < checkBox.length; i++) {
            actividadModelo.getNivelLenguajeModeloLista().add(new NivelLenguajeModelo(Short.parseShort(checkBox[i])));
        }
        actividadSesionBean.inserta(actividadModelo);

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
}
