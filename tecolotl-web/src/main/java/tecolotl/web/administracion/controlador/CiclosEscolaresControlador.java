package tecolotl.web.administracion.controlador;

import tecolotl.administracion.modelo.escuela.EscuelaBaseModelo;
import tecolotl.profesor.modelo.CicloEscolarModelo;
import tecolotl.profesor.sesion.CicloEscolarSessionBean;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@ViewScoped
@Named
public class CiclosEscolaresControlador implements Serializable {

    @Inject
    private CicloEscolarSessionBean cicloEscolarSessionBean;

    @Inject
    private Logger logger;

    private List<CicloEscolarModelo> cicloEscolarModeloLista;
    private CicloEscolarModelo cicloEscolarModelo;
    private String claveCentroTrabajo;
    private EscuelaBaseModelo escuelaBaseModelo;
    private Long totalGrupo;
    private HtmlInputText htmlInputText;
    private int cicloEscolarViejo;
    private int cicloEscolarNuevo;

    @PostConstruct
    public void buscaCiclosEscolares() {
        cicloEscolarModelo = new CicloEscolarModelo();
    }

    public void inicia() {
        cicloEscolarModelo.setIdEscuela(claveCentroTrabajo);
        cicloEscolarModeloLista = cicloEscolarSessionBean.busca(claveCentroTrabajo);
        escuelaBaseModelo = new EscuelaBaseModelo(claveCentroTrabajo);
    }

    public void inserta() {
    	Optional<CicloEscolarModelo> cicloEscolarModeloOpttional = 
    			cicloEscolarModeloLista.stream().filter(cicloEscolar -> cicloEscolar.equals(cicloEscolarModelo)).findFirst();
    	if (cicloEscolarModeloOpttional.isPresent()) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
    		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Este ciclo escolar ya existe", "Este ciclo escolar ya existe");
    		facesContext.addMessage(htmlInputText.getClientId(facesContext), facesMessage);
		} else {
	        cicloEscolarModelo.setIdEscuela(escuelaBaseModelo.getClaveCentroTrabajo());
	        cicloEscolarSessionBean.inserta(cicloEscolarModelo);
	        cicloEscolarModeloLista = cicloEscolarSessionBean.busca(escuelaBaseModelo.getClaveCentroTrabajo());
	        renuevaModelo();
		}
    }

    public void activa() {
        cicloEscolarModelo.setActivo(!cicloEscolarModelo.getActivo());
        cicloEscolarSessionBean.actualiza(cicloEscolarModelo);
    }

    public void actualiza() {
        cicloEscolarSessionBean.actualiza(cicloEscolarModelo);
        cicloEscolarModeloLista = cicloEscolarSessionBean.busca(escuelaBaseModelo.getClaveCentroTrabajo());
        renuevaModelo();
    }

    public void elimina() {
        cicloEscolarSessionBean.elimina(cicloEscolarModelo);
        cicloEscolarModeloLista.remove(cicloEscolarModelo);
        renuevaModelo();
    }

    public List<CicloEscolarModelo> busca(boolean grupos) {
        return cicloEscolarModeloLista.stream().filter(ciclo -> grupos == (ciclo.getTotalGrupo() != 0)).collect(Collectors.toList());
    }

    public void hereda() {
        cicloEscolarSessionBean.hereda(busca(cicloEscolarViejo), busca(cicloEscolarNuevo), escuelaBaseModelo.getClaveCentroTrabajo());
    }

    public void buscaTotalGrupo() {
        totalGrupo = cicloEscolarSessionBean.totalGrupo(cicloEscolarModelo);
    }

    public CicloEscolarModelo busca(int hashCode) {
        for (CicloEscolarModelo cicloEscolarModeloLocal : cicloEscolarModeloLista) {
            if (cicloEscolarModeloLocal.hashCode() == hashCode) {
                return cicloEscolarModeloLocal;
            }
        }
        return null;
    }

    public void renuevaModelo() {
        cicloEscolarModelo = new CicloEscolarModelo();
    }

    public List<CicloEscolarModelo> getCicloEscolarModeloLista() {
        return cicloEscolarModeloLista;
    }

    public void setCicloEscolarModeloLista(List<CicloEscolarModelo> cicloEscolarModeloLista) {
        this.cicloEscolarModeloLista = cicloEscolarModeloLista;
    }

    public CicloEscolarModelo getCicloEscolarModelo() {
        return cicloEscolarModelo;
    }

    public void setCicloEscolarModelo(CicloEscolarModelo cicloEscolarModelo) {
        this.cicloEscolarModelo = cicloEscolarModelo;
    }

    public String getClaveCentroTrabajo() {
        return claveCentroTrabajo;
    }

    public void setClaveCentroTrabajo(String claveCentroTrabajo) {
        this.claveCentroTrabajo = claveCentroTrabajo;
    }

    public Long getTotalGrupo() {
        return totalGrupo;
    }

    public void setTotalGrupo(Long totalGrupo) {
        this.totalGrupo = totalGrupo;
    }

    public EscuelaBaseModelo getEscuelaBaseModelo() {
        return escuelaBaseModelo;
    }

    public void setEscuelaBaseModelo(EscuelaBaseModelo escuelaBaseModelo) {
        this.escuelaBaseModelo = escuelaBaseModelo;
    }

	public HtmlInputText getHtmlInputText() {
		return htmlInputText;
	}

	public void setHtmlInputText(HtmlInputText htmlInputText) {
		this.htmlInputText = htmlInputText;
	}

    public int getCicloEscolarViejo() {
        return cicloEscolarViejo;
    }

    public void setCicloEscolarViejo(int cicloEscolarViejo) {
        this.cicloEscolarViejo = cicloEscolarViejo;
    }

    public int getCicloEscolarNuevo() {
        return cicloEscolarNuevo;
    }

    public void setCicloEscolarNuevo(int cicloEscolarNuevo) {
        this.cicloEscolarNuevo = cicloEscolarNuevo;
    }
}
