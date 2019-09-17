package tecolotl.web.alumno;

import tecolotl.alumno.modelo.mapamental.MapaMentalResueltoModelo;
import tecolotl.alumno.sesion.MapaMentalSessionBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@RequestScoped
@Named
public class SeleccionMapaMentalControlador {

    @Inject
    private AlumnoControlador alumnoControlador;

    @Inject
    private MapaMentalSessionBean mapaMentalSessionBean;

    private List<MapaMentalResueltoModelo> mapaMentalResueltoModeloLista;

    @PostConstruct
    public void inicio() {
        mapaMentalResueltoModeloLista = mapaMentalSessionBean.buscaResuelto(alumnoControlador.getTareaActividadModelo().getId());
    }

    public List<MapaMentalResueltoModelo> getMapaMentalResueltoModeloLista() {
        return mapaMentalResueltoModeloLista;
    }

    public void setMapaMentalResueltoModeloLista(List<MapaMentalResueltoModelo> mapaMentalResueltoModeloLista) {
        this.mapaMentalResueltoModeloLista = mapaMentalResueltoModeloLista;
    }
}
