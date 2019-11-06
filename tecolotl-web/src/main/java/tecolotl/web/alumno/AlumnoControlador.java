package tecolotl.web.alumno;

import tecolotl.alumno.modelo.ActividadModelo;
import tecolotl.alumno.modelo.AlumnoModelo;
import tecolotl.alumno.modelo.TareaActividadModelo;
import tecolotl.alumno.modelo.TareaModelo;
import tecolotl.alumno.modelo.vista.TareaResuetasModelo;
import tecolotl.alumno.sesion.AlumnoSesionBean;
import tecolotl.alumno.sesion.GlosarioSesionBean;
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
    private List<TareaActividadModelo> tareaActvidadModeloLista;
    private TareaActividadModelo tareaActividadModelo;
    private ActividadModelo actividadModeloBibliotecaLibre;

    @PostConstruct
    public void init() {
        alumnoModelo = alumnoSesionBean.busca(2);
        tareaActvidadModeloLista = tareaSesionBean.buscaActividad(2);
    }

    public String seleccion(TareaActividadModelo tareaActividadModelo){
        this.tareaActividadModelo = tareaActividadModelo;
        return "transcript";
    }

    public String actividadBiliotecaLibre(String idActividad) {
        actividadModeloBibliotecaLibre = new ActividadModelo(idActividad);
        return "actividad";
    }

    public AlumnoModelo getAlumnoModelo() {
        return alumnoModelo;
    }

    public void setAlumnoModelo(AlumnoModelo alumnoModelo) {
        this.alumnoModelo = alumnoModelo;
    }

    public List<TareaActividadModelo> getTareaActvidadModeloLista() {
        return tareaActvidadModeloLista;
    }

    public void setTareaActvidadModeloLista(List<TareaActividadModelo> tareaActvidadModeloLista) {
        this.tareaActvidadModeloLista = tareaActvidadModeloLista;
    }

    public TareaActividadModelo getTareaActividadModelo() {
        return tareaActividadModelo;
    }

    public void setTareaActividadModelo(TareaActividadModelo tareaActividadModelo) {
        this.tareaActividadModelo = tareaActividadModelo;
    }

    public ActividadModelo getActividadModeloBibliotecaLibre() {
        return actividadModeloBibliotecaLibre;
    }

    public void setActividadModeloBibliotecaLibre(ActividadModelo actividadModeloBibliotecaLibre) {
        this.actividadModeloBibliotecaLibre = actividadModeloBibliotecaLibre;
    }
}
