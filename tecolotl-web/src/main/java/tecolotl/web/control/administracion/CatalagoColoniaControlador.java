package tecolotl.web.control.administracion;

import tecolotl.administracion.dto.CodigoPostal;
import tecolotl.administracion.dto.ColoniaDto;
import tecolotl.administracion.dto.MunicipioDto;
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
public class CatalagoColoniaControlador extends PaginadorControlador<ColoniaDto> implements Serializable {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Inject
    private ColoniaSesionBean coloniaSesionBean;

    private List<ColoniaDto> coloniaDtoLista;
    private MunicipioDto municipioDto;
    private ColoniaDto coloniaDtoModelo;
    private String codigoPostal;

    @PostConstruct
    public void init() {
        coloniaDtoModelo = new ColoniaDto();
        setFilasEnPagina(5);
    }

    public void busca() {
        coloniaDtoLista = coloniaSesionBean.busca(codigoPostal);
        municipioDto = coloniaSesionBean.buscaMunicipio(codigoPostal);
        setTotalFilas(coloniaDtoLista.size());
        cargaDatos(getHtmlDataTable().getFirst());
    }

    public void inserta() {
        logger.info(coloniaDtoModelo.toString());
        if (coloniaDtoModelo.getId() == null) {
            Integer id = coloniaSesionBean.inserta(coloniaDtoModelo.getNombre(), codigoPostal, municipioDto.getId());
            coloniaDtoModelo.setId(id);
            coloniaDtoModelo.setCodigoPostal(codigoPostal);
            coloniaDtoLista.add(coloniaDtoModelo);
            setTotalFilas(coloniaDtoLista.size());
        } else {
            coloniaSesionBean.actualiza(coloniaDtoModelo.getId(), coloniaDtoModelo.getNombre());
            int indice = coloniaDtoLista.indexOf(coloniaDtoModelo);
            ColoniaDto coloniaDto = coloniaDtoLista.get(indice);
            coloniaDto.setNombre(coloniaDtoModelo.getNombre());
        }
        cargaDatos(getHtmlDataTable().getFirst());
        coloniaDtoModelo = new ColoniaDto();
    }

    @Override
    protected List<ColoniaDto> getDatos() {
        return coloniaDtoLista;
    }

    public List<ColoniaDto> getColoniaDtoLista() {
        return coloniaDtoLista;
    }

    public void setColoniaDtoLista(List<ColoniaDto> coloniaDtoLista) {
        this.coloniaDtoLista = coloniaDtoLista;
    }

    public MunicipioDto getMunicipioDto() {
        return municipioDto;
    }

    public void setMunicipioDto(MunicipioDto municipioDto) {
        this.municipioDto = municipioDto;
    }

    public ColoniaDto getColoniaDtoModelo() {
        return coloniaDtoModelo;
    }

    public void setColoniaDtoModelo(ColoniaDto coloniaDtoModelo) {
        this.coloniaDtoModelo = coloniaDtoModelo;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }
}
