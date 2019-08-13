package tecolotl.web.alumno;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@SessionScoped
@Named
public class AlumnoControlador implements Serializable {

    /**
     * true = niño, false = niña
     */
    private boolean sexo;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;

    public boolean isSexo() {
        return sexo;
    }

    public void setSexo(boolean sexo) {
        this.sexo = sexo;
    }

}
