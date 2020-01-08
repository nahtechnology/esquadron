package tecolotl.web.profesor;

import tecolotl.profesor.modelo.TareaMapaMentalModelo;
import tecolotl.profesor.sesion.CalificaTareaMapaMentalSesionBean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.UUID;

@RequestScoped
@Named
public class ListaMapaMentalControlador {

    @Inject
    private CalificaTareaMapaMentalSesionBean calificaTareaMapaMentalSesionBean;

    private UUID idTarea;
    private List<TareaMapaMentalModelo> tareaMapaMentalModeloLista;

    public void busca() {
        tareaMapaMentalModeloLista = calificaTareaMapaMentalSesionBean.buscaCalificados(idTarea);
    }

    public UUID getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(UUID idTarea) {
        this.idTarea = idTarea;
    }

    public List<TareaMapaMentalModelo> getTareaMapaMentalModeloLista() {
        return tareaMapaMentalModeloLista;
    }

    public void setTareaMapaMentalModeloLista(List<TareaMapaMentalModelo> tareaMapaMentalModeloLista) {
        this.tareaMapaMentalModeloLista = tareaMapaMentalModeloLista;
    }
}
