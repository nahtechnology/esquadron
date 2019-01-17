package tecolotl.web.control.administracion;

import tecolotl.administracion.dto.TipoContactoDto;
import tecolotl.administracion.sesion.TipoContactoSesionBean;
import tecolotl.web.control.PaginadorControlador;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

@Named
@ViewScoped
public class CatalagoTipoContactoControlador extends PaginadorControlador<TipoContactoDto> implements Serializable {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Inject
    private TipoContactoSesionBean tipoContactoSesionBean;

    private List<TipoContactoDto> tipoContactoDtoLista;
    private TipoContactoDto tipoContactoDtoModelo;

    @PostConstruct
    public void init() {
        tipoContactoDtoLista = tipoContactoSesionBean.busca();
        tipoContactoDtoModelo = new TipoContactoDto();
        setFilasEnPagina(5);
        setTotalFilas(tipoContactoDtoLista.size());
        cargaDatos(0);
    }

    public void inserta() {
        logger.info(tipoContactoDtoModelo.toString());
        if (tipoContactoDtoModelo.getId() == null) {
            tipoContactoSesionBean.inserta(tipoContactoDtoModelo.getDescripcion());
            tipoContactoDtoLista.add(tipoContactoDtoModelo);
            setTotalFilas(tipoContactoDtoLista.size());
        } else {
            tipoContactoSesionBean.actualiza(tipoContactoDtoModelo.getId(), tipoContactoDtoModelo.getDescripcion());
            int actual = tipoContactoDtoLista.indexOf(tipoContactoDtoModelo);
            tipoContactoDtoLista.get(actual).setDescripcion(tipoContactoDtoModelo.getDescripcion());
        }
        tipoContactoDtoModelo = new TipoContactoDto();
        cargaDatos(getHtmlDataTable().getFirst());
    }

    @Override
    protected List<TipoContactoDto> getDatos() {
        return tipoContactoDtoLista;
    }

    public List<TipoContactoDto> getTipoContactoDtoLista() {
        return tipoContactoDtoLista;
    }

    public void setTipoContactoDtoLista(List<TipoContactoDto> tipoContactoDtoLista) {
        this.tipoContactoDtoLista = tipoContactoDtoLista;
    }

    public TipoContactoDto getTipoContactoDtoModelo() {
        return tipoContactoDtoModelo;
    }

    public void setTipoContactoDtoModelo(TipoContactoDto tipoContactoDtoModelo) {
        this.tipoContactoDtoModelo = tipoContactoDtoModelo;
    }
}
