package tecolotl.web.control.administracion;

import tecolotl.administracion.dto.CodigoPostal;
import tecolotl.administracion.dto.ColoniaDto;
import tecolotl.administracion.sesion.ColoniaSesionBean;
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
public class CatalagoColoniaControlador extends PaginadorControlador<CodigoPostal> implements Serializable {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Inject
    private ColoniaSesionBean coloniaSesionBean;

    private ColoniaDto coloniaDto;
    private CodigoPostal codigoPostalModelo;
    private String codigoPostal;

    @PostConstruct
    public void init() {
        coloniaDto = new ColoniaDto();
        codigoPostalModelo = new CodigoPostal();
        setFilasEnPagina(5);
    }

    public void busca() {
        coloniaDto = coloniaSesionBean.busca(codigoPostal);
        setTotalFilas(coloniaDto.getListaCodigoPostal().size());
        cargaDatos(getHtmlDataTable().getFirst());
    }

    @Override
    protected List<CodigoPostal> getDatos() {
        return coloniaDto.getListaCodigoPostal();
    }

    public ColoniaDto getColoniaDto() {
        return coloniaDto;
    }

    public void setColoniaDto(ColoniaDto coloniaDto) {
        this.coloniaDto = coloniaDto;
    }

    public CodigoPostal getCodigoPostalModelo() {
        return codigoPostalModelo;
    }

    public void setCodigoPostalModelo(CodigoPostal codigoPostalModelo) {
        this.codigoPostalModelo = codigoPostalModelo;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }
}
