package tecolotl.web.control.administracion;

import tecolotl.administracion.dto.ColoniaDto;
import tecolotl.administracion.dto.EscuelaBaseDto;
import tecolotl.administracion.dto.EscuelaDetalleDto;
import tecolotl.administracion.dto.EscuelaDto;
import tecolotl.administracion.sesion.EscuelaSesionBean;
import tecolotl.web.control.PaginadorControlador;
import tecolotl.web.modelo.administracion.PaginacionModeloDato;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ViewScoped
@Named
public class EscuelaDashboardControlador extends PaginadorControlador<EscuelaDto> implements Serializable {

    @Inject
    private EscuelaSesionBean escuelaSesionBean;

    private List<EscuelaDto> escuelaDtoLista;
    private List<EscuelaDto> escuelaDtoSubLista;
    private EscuelaBaseDto escuelaBaseDtoModelo;
    private EscuelaDetalleDto escuelaDetalleDtoModelo;
    private List<ColoniaDto> coloniaDtoLista;

    @PostConstruct
    public void init() {
        escuelaDtoLista = new ArrayList<>(escuelaSesionBean.busca());
        escuelaBaseDtoModelo = new EscuelaBaseDto();
        setFilasEnPagina(5);
        setTotalFilas(escuelaDtoLista.size());
        cargaDatos(0);
    }

    public void filtrar() {
        escuelaDtoSubLista = new ArrayList<>();
        for (EscuelaDto escuelaDto : escuelaDtoLista) {
            if (escuelaDto.getNombre().toLowerCase().contains(escuelaBaseDtoModelo.getNombre().toLowerCase())) {
                escuelaDtoSubLista.add(escuelaDto);
            }
        }
        cargaDatos(0);
    }

    @Override
    protected List<EscuelaDto> getDatos() {
        return escuelaDtoSubLista == null ? escuelaDtoLista : escuelaDtoSubLista;
    }

    public EscuelaBaseDto getEscuelaBaseDtoModelo() {
        return escuelaBaseDtoModelo;
    }

    public void setEscuelaBaseDtoModelo(EscuelaBaseDto escuelaBaseDtoModelo) {
        this.escuelaBaseDtoModelo = escuelaBaseDtoModelo;
    }

    public EscuelaDetalleDto getEscuelaDetalleDtoModelo() {
        return escuelaDetalleDtoModelo;
    }

    public void setEscuelaDetalleDtoModelo(EscuelaDetalleDto escuelaDetalleDtoModelo) {
        this.escuelaDetalleDtoModelo = escuelaDetalleDtoModelo;
    }

    public List<ColoniaDto> getColoniaDtoLista() {
        return coloniaDtoLista;
    }

    public void setColoniaDtoLista(List<ColoniaDto> coloniaDtoLista) {
        this.coloniaDtoLista = coloniaDtoLista;
    }
}
