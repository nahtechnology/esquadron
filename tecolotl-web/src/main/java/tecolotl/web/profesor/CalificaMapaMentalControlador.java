package tecolotl.web.profesor;

import tecolotl.alumno.modelo.mapamental.TareaMapaMentalModelo;
import tecolotl.alumno.sesion.MapaMentalSessionBean;
import tecolotl.profesor.modelo.CalificaTareaMapaMentalModelo;
import tecolotl.profesor.sesion.CalificaTareaMapaMentalSesionBean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.logging.Logger;

@RequestScoped
@Named
public class CalificaMapaMentalControlador {

    @Inject
    private MapaMentalSessionBean mapaMentalSessionBean;

    @Inject
    private CalificaTareaMapaMentalSesionBean calificaTareaMapaMentalSesionBean;

    @Inject
    private Logger logger;

    private Integer idTarea;
    private Short cardinalidad;
    private Short intento;
    private List<TareaMapaMentalModelo> tareaMapaMentalModeloLista;
    private CalificaTareaMapaMentalModelo calificaTareaMapaMentalModelo = new CalificaTareaMapaMentalModelo();

    public void busca() {
        tareaMapaMentalModeloLista = mapaMentalSessionBean.busca(idTarea, cardinalidad);
        calificaTareaMapaMentalModelo = calificaTareaMapaMentalSesionBean.busca(idTarea, cardinalidad, intento);
    }

    public String califica() {
        calificaTareaMapaMentalSesionBean.respuesta(calificaTareaMapaMentalModelo);
        return "tareas";
    }

    public List<TareaMapaMentalModelo> getTareaMapaMentalModeloLista() {
        return tareaMapaMentalModeloLista;
    }

    public void setTareaMapaMentalModeloLista(List<TareaMapaMentalModelo> tareaMapaMentalModeloLista) {
        this.tareaMapaMentalModeloLista = tareaMapaMentalModeloLista;
    }

    public Integer getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(Integer idTarea) {
        this.idTarea = idTarea;
    }

    public Short getCardinalidad() {
        return cardinalidad;
    }

    public void setCardinalidad(Short cardinalidad) {
        this.cardinalidad = cardinalidad;
    }

    public Short getIntento() {
        return intento;
    }

    public void setIntento(Short intento) {
        this.intento = intento;
    }

    public CalificaTareaMapaMentalModelo getCalificaTareaMapaMentalModelo() {
        return calificaTareaMapaMentalModelo;
    }

    public void setCalificaTareaMapaMentalModelo(CalificaTareaMapaMentalModelo calificaTareaMapaMentalModelo) {
        this.calificaTareaMapaMentalModelo = calificaTareaMapaMentalModelo;
    }
}