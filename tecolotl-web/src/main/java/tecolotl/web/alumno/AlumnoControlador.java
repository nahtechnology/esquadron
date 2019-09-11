package tecolotl.web.alumno;

import tecolotl.alumno.modelo.AlumnoModelo;
import tecolotl.alumno.modelo.TareaModelo;
import tecolotl.alumno.sesion.AlumnoSesionBean;
import tecolotl.alumno.sesion.TareaSesionBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

@SessionScoped
@Named
public class AlumnoControlador implements Serializable {

    @Inject
    private AlumnoSesionBean alumnoSesionBean;

    @Inject
    private TareaSesionBean tareaSesionBean;

    private AlumnoModelo alumnoModelo;
    private List<TareaModelo> tareaModeloLista;
    private TareaModelo tareaModelo;

    @PostConstruct
    public void init() {
        alumnoModelo = alumnoSesionBean.busca(1);
        tareaModeloLista = tareaSesionBean.busca(1);
        tareaModelo = tareaModeloLista.get(1);
    }

    public void seleccion(TareaModelo tareaModelo){
        this.tareaModelo = tareaModelo;
    }

    public AlumnoModelo getAlumnoModelo() {
        return alumnoModelo;
    }

    public void setAlumnoModelo(AlumnoModelo alumnoModelo) {
        this.alumnoModelo = alumnoModelo;
    }

    public List<TareaModelo> getTareaModeloLista() {
        return tareaModeloLista;
    }

    public void setTareaModeloLista(List<TareaModelo> tareaModeloLista) {
        this.tareaModeloLista = tareaModeloLista;
    }

    public TareaModelo getTareaModelo() {
        return tareaModelo;
    }

    public void setTareaModelo(TareaModelo tareaModelo) {
        this.tareaModelo = tareaModelo;
    }
}
