package tecolotl.web.controlador;

import tecolotl.administracion.modelo.escuela.EscuelaBaseModelo;
import tecolotl.nucleo.modelo.PersonaModelo;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class BarraNavegacionControlador implements Serializable {

    private PersonaModelo personaModelo;
    private String rol;
    private EscuelaBaseModelo escuelaBaseModelo;

    public void init() {
        personaModelo = new PersonaModelo();
        personaModelo.setNombre("Noldi");
        personaModelo.setApellidoPaterno("vives");
        personaModelo.setApellidoMaterno("Quiñones");
        personaModelo.setApodo("Noldi");
        rol = "administrador";
    }
}
