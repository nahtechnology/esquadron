package tecolotl.web.alumno;

import tecolotl.alumno.sesion.AlumnoSesionBean;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@SessionScoped
@Named
public class AlumnoControlador implements Serializable {

    /**
     * true = niño, false = niña
     */
    private AlumnoSesionBean alumnoSesionBean;

    private boolean sexo;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isSexo() {
        return sexo;
    }

    public void setSexo(boolean sexo) {
        this.sexo = sexo;
    }

}
