package tecolotl.web.profesor;

import tecolotl.profesor.modelo.TareaMapaMentalModelo;
import tecolotl.profesor.sesion.CalificaTareaMapaMentalSesionBean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@RequestScoped
@Named
public class ListaMapaMentalControlador {

    @Inject
    private CalificaTareaMapaMentalSesionBean calificaTareaMapaMentalSesionBean;

    private Integer idTarea;
    private Integer idAlumno;
    private List<TareaMapaMentalModelo> tareaMapaMentalModeloLista;

    public void busca() {
        tareaMapaMentalModeloLista = calificaTareaMapaMentalSesionBean.buscaCalificados(idTarea);
    }

    public Integer getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(Integer idTarea) {
        this.idTarea = idTarea;
    }

    public Integer getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Integer idAlumno) {
        this.idAlumno = idAlumno;
    }

    public List<TareaMapaMentalModelo> getTareaMapaMentalModeloLista() {
        return tareaMapaMentalModeloLista;
    }

    public void setTareaMapaMentalModeloLista(List<TareaMapaMentalModelo> tareaMapaMentalModeloLista) {
        this.tareaMapaMentalModeloLista = tareaMapaMentalModeloLista;
    }
}
