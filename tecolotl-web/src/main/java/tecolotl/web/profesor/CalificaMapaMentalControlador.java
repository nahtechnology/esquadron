package tecolotl.web.profesor;

import tecolotl.alumno.modelo.mapamental.TareaMapaMentalModelo;
import tecolotl.alumno.sesion.MapaMentalSessionBean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RequestScoped
@Named
public class CalificaMapaMentalControlador {

    @Inject
    private MapaMentalSessionBean mapaMentalSessionBean;

    @Inject
    private Logger logger;

    private Integer idTarea;
    private Map<Short, List<TareaMapaMentalModelo>> mapaMentalMapa;

    public void cargaTareas() {
        mapaMentalMapa = mapaMentalSessionBean.busca(idTarea)
                .stream().collect(Collectors.groupingBy(TareaMapaMentalModelo::getCardinalidad));
        logger.info(mapaMentalMapa.toString());
    }

    public Integer getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(Integer idTarea) {
        this.idTarea = idTarea;
    }

    public Map<Short, List<TareaMapaMentalModelo>> getMapaMentalMapa() {
        return mapaMentalMapa;
    }

    public void setMapaMentalMapa(Map<Short, List<TareaMapaMentalModelo>> mapaMentalMapa) {
        this.mapaMentalMapa = mapaMentalMapa;
    }
}
