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

@Named
@ViewScoped
public class CatalagoTipoContactoControlador extends PaginadorControlador<TipoContactoDto> implements Serializable {

    @Inject
    private TipoContactoSesionBean tipoContactoSesionBean;

    private List<TipoContactoDto> tipoContactoDtoLista;
    private TipoContactoDto tipoContactoDtoModelo;

    @PostConstruct
    public void init() {
        tipoContactoDtoLista = tipoContactoSesionBean.busca();
        setFilasEnPagina(5);
        setTotalFilas(tipoContactoDtoLista.size());
        cargaDatos(0);
    }

    public void inserta() {

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
