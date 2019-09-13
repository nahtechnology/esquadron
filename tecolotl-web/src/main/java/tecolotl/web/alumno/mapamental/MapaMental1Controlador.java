package tecolotl.web.alumno.mapamental;

import tecolotl.alumno.modelo.mapamental.TareaMapaMentalModelo;
import tecolotl.alumno.sesion.MapaMentalSessionBean;
import tecolotl.web.alumno.AlumnoControlador;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@RequestScoped
@Named
public class MapaMental1Controlador {

    @Inject
    private AlumnoControlador alumnoControlador;

    @Inject
    private MapaMentalSessionBean mapaMentalSessionBean;

    private List<TareaMapaMentalModelo> tareaMapaMentalModeloLista;

    @PostConstruct
    public void inicio() {
         tareaMapaMentalModeloLista = mapaMentalSessionBean.busca(alumnoControlador.getTareaActividadModelo().getId(), (short)1);
    }

    public List<TareaMapaMentalModelo> getTareaMapaMentalModeloLista() {
        return tareaMapaMentalModeloLista;
    }

    public void setTareaMapaMentalModeloLista(List<TareaMapaMentalModelo> tareaMapaMentalModeloLista) {
        this.tareaMapaMentalModeloLista = tareaMapaMentalModeloLista;
    }
}
