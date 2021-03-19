package tecolotl.web.alumno;

import tecolotl.alumno.modelo.TareaActividadModelo;
import tecolotl.alumno.sesion.TareaSesionBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.logging.Logger;

@RequestScoped
@Named
public class ActividadesRequestControlador {
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
        logger.info("RequestScoped - Progreso Tareas: ".concat(tareaActividadModeloLista.toString()));
    }

    public List<TareaActividadModelo> getTareaActividadModeloLista() {
        return tareaActividadModeloLista;
    }
}
