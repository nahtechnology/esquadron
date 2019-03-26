package tecolotl.web.control.administracion;

import tecolotl.web.control.PaginadorControlador;
import tecolotl.web.modelo.administracion.EscuelaModelo;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@ViewScoped
@Named
public class EscuelaDashboardControlador extends PaginadorControlador<EscuelaModelo> implements Serializable {

    @Override
    protected List<EscuelaModelo> getDatos() {
        return null;
    }
}
