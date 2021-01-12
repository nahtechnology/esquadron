package tecolotl.web.administracion.controlador;

import tecolotl.administracion.modelo.escuela.EscuelaBaseModelo;
import tecolotl.profesor.modelo.ProfesorModelo;

import javax.faces.component.UIInput;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.logging.Logger;

@ViewScoped
@Named
public class AgregarControlador implements Serializable {

    private String escuela;
    private EscuelaBaseModelo escuelaBaseModelo;
    private ProfesorModelo profesorModelo;
    private UIInput uiInput;

    @Inject
    private Logger logger;

    public void inicio() {
        escuelaBaseModelo = new EscuelaBaseModelo(escuela);
        profesorModelo = new ProfesorModelo();
    }

    public String agregar() {
        logger.info(profesorModelo.toString());
        return "success";
    }

    public String getEscuela() {
        return escuela;
    }

    public void setEscuela(String escuela) {
        this.escuela = escuela;
    }

    public ProfesorModelo getProfesorModelo() {
        return profesorModelo;
    }

    public void setProfesorModelo(ProfesorModelo profesorModelo) {
        this.profesorModelo = profesorModelo;
    }

    public UIInput getUiInput() {
        return uiInput;
    }

    public void setUiInput(UIInput uiInput) {
        this.uiInput = uiInput;
    }

    public EscuelaBaseModelo getEscuelaBaseModelo() {
        return escuelaBaseModelo;
    }

    public void setEscuelaBaseModelo(EscuelaBaseModelo escuelaBaseModelo) {
        this.escuelaBaseModelo = escuelaBaseModelo;
    }
}
