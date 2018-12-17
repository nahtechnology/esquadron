package tecolotl.web.control.administracion;

import tecolotl.administracion.dto.CodigoPostal;
import tecolotl.administracion.dto.ColoniaDto;
import tecolotl.administracion.sesion.ColoniaSesionBean;
import tecolotl.administracion.sesion.EscuelaSesionBean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Named
@RequestScoped
public class DireccionControlador {

    @Inject
    private ColoniaSesionBean coloniaSesionBean;

    private String codigoPostal;
    private String estado;
    private String municipio;
    private List<CodigoPostal> codigoPostalList;

    public void busca() {
        ColoniaDto coloniaDto = coloniaSesionBean.busca(codigoPostal);
        estado = coloniaDto.getEstado();
        municipio = coloniaDto.getMunicipio();
        codigoPostalList = coloniaDto.getListaCodigoPostal();
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public List<CodigoPostal> getCodigoPostalList() {
        return codigoPostalList;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public void setCodigoPostalList(List<CodigoPostal> codigoPostalList) {
        this.codigoPostalList = codigoPostalList;
    }
}
