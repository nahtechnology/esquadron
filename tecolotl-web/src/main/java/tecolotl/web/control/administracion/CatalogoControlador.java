package tecolotl.web.control.administracion;

import tecolotl.administracion.dto.MotivoBloqueoDto;
import tecolotl.administracion.sesion.MotivoBloqueoSesionBean;
import tecolotl.web.modelo.administracion.MotivoBloqueoModeloDatos;

import javax.annotation.PostConstruct;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

@Named
@ViewScoped
public class CatalogoControlador implements Serializable {

    private Logger logger = Logger.getLogger(getClass().getName());

    private List<MotivoBloqueoDto> motivoBloqueos;
    private HtmlDataTable dataTableMitivoBloqueo;
    private MotivoBloqueoModeloDatos motivoBloqueoModeloDatos;
    private int rowsOnPage;
    private int allRowsCount = 0;
    private MotivoBloqueoDto motivoBloqueoModelo;

    @Inject
    private MotivoBloqueoSesionBean motivoBloqueoSesionBean;

    @PostConstruct
    public void init() {
        motivoBloqueos = motivoBloqueoSesionBean.motivoBloque();
        rowsOnPage = 5;
        allRowsCount = motivoBloqueos.size();
        motivoBloqueoModelo = new MotivoBloqueoDto();
        cargaDatos(0);
    }

    public void inserta() {
        if (motivoBloqueoModelo.getId() == 0) {
            motivoBloqueoSesionBean.inserta(motivoBloqueoModelo.getDescripcion());
            motivoBloqueos.add(motivoBloqueoModelo);
        } else {
            motivoBloqueoSesionBean.actualiza(motivoBloqueoModelo);
            int indice = motivoBloqueos.indexOf(motivoBloqueoModelo);
            motivoBloqueos.set(indice, motivoBloqueoModelo);
        }
        motivoBloqueoModelo = new MotivoBloqueoDto();
    }

    public void actualiza(MotivoBloqueoDto motivoBloqueoDto) {
        motivoBloqueoModelo = motivoBloqueoDto;
    }

    public void vistaAnterior(boolean primera) {
        if (primera) {
            dataTableMitivoBloqueo.setFirst(0);
        } else {
            dataTableMitivoBloqueo.setFirst(dataTableMitivoBloqueo.getFirst() - dataTableMitivoBloqueo.getRows());
        }
        cargaDatos(dataTableMitivoBloqueo.getFirst());
    }

    public void vistaSiguiente(boolean ultima) {
        if (ultima) {
            int totalRows = dataTableMitivoBloqueo.getRowCount();
            int displayRows = dataTableMitivoBloqueo.getRows();
            int full = totalRows / displayRows;
            int modulo = dataTableMitivoBloqueo.getRowCount() % dataTableMitivoBloqueo.getRows();
            if (modulo <= displayRows && modulo > 0) {
                dataTableMitivoBloqueo.setFirst(full * displayRows);
            } else {
                dataTableMitivoBloqueo.setFirst(modulo + (full - 1) * displayRows);
            }
        } else {
            dataTableMitivoBloqueo.setFirst(dataTableMitivoBloqueo.getFirst() + dataTableMitivoBloqueo.getRows());
        }
        cargaDatos(dataTableMitivoBloqueo.getFirst());
    }

    public void cargaDatos(int primero) {
        motivoBloqueoModeloDatos = new MotivoBloqueoModeloDatos(motivoBloqueos.subList(primero, primero + rowsOnPage > motivoBloqueos.size() ? motivoBloqueos.size() : primero + rowsOnPage), allRowsCount, rowsOnPage);
    }

    public List<MotivoBloqueoDto> getMotivoBloqueos() {
        return motivoBloqueos;
    }

    public void setMotivoBloqueos(List<MotivoBloqueoDto> motivoBloqueos) {
        this.motivoBloqueos = motivoBloqueos;
    }

    public MotivoBloqueoDto getMotivoBloqueoModelo() {
        return motivoBloqueoModelo;
    }

    public void setMotivoBloqueoModelo(MotivoBloqueoDto motivoBloqueoModelo) {
        this.motivoBloqueoModelo = motivoBloqueoModelo;
    }

    public HtmlDataTable getDataTableMitivoBloqueo() {
        return dataTableMitivoBloqueo;
    }

    public void setDataTableMitivoBloqueo(HtmlDataTable dataTableMitivoBloqueo) {
        this.dataTableMitivoBloqueo = dataTableMitivoBloqueo;
    }

    public int getRowsOnPage() {
        return rowsOnPage;
    }

    public void setRowsOnPage(int rowsOnPage) {
        this.rowsOnPage = rowsOnPage;
    }

    public int getAllRowsCount() {
        return allRowsCount;
    }

    public void setAllRowsCount(int allRowsCount) {
        this.allRowsCount = allRowsCount;
    }

    public MotivoBloqueoModeloDatos getMotivoBloqueoModeloDatos() {
        return motivoBloqueoModeloDatos;
    }

    public void setMotivoBloqueoModeloDatos(MotivoBloqueoModeloDatos motivoBloqueoModeloDatos) {
        this.motivoBloqueoModeloDatos = motivoBloqueoModeloDatos;
    }
}
