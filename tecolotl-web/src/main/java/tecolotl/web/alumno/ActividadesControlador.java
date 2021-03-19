package tecolotl.web.alumno;

import tecolotl.alumno.modelo.TareaActividadModelo;
import tecolotl.alumno.sesion.TareaSesionBean;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

@ViewScoped
@Named
public class ActividadesControlador implements Serializable {
    @Inject
    private AlumnoControlador alumnoControlador;

    @Inject
    private TareaSesionBean tareaSesionBean;

    @Inject
    private Logger logger;

    private List<TareaActividadModelo> tareaActividadModeloLista;

    @PostConstruct
    public void inicio(){
        tareaActividadModeloLista = tareaSesionBean.buscaActividad(alumnoControlador.getAlumnoModelo().getId(), alumnoControlador.getDetalleAlumno2Modelo() == null ? "" : alumnoControlador.getDetalleAlumno2Modelo().getIdGrupo());
        logger.info("View Scoped - Progreso Tareas: ".concat(tareaActividadModeloLista.toString()));
    }

    public List<TareaActividadModelo> getTareaActividadModeloLista() {
        return tareaActividadModeloLista;
    }
}
