package tecolotl.web.control.administracion;

import tecolotl.administracion.dto.EscuelaDto;
import tecolotl.administracion.sesion.EscuelaSesionBean;
import tecolotl.web.control.PaginadorControlador;

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

    @PostConstruct
    public void init() {
        escuelaDtoLista = new ArrayList<>(escuelaSesionBean.busca());
        setFilasEnPagina(5);
        setTotalFilas(escuelaDtoLista.size());
        cargaDatos(0);
    }

    @Override
    protected List<EscuelaDto> getDatos() {
        return escuelaDtoLista;
    }
}
