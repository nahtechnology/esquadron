package tecolotl.administracion.modelo.escuela;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ContactoModelo {

    @NotNull
    @Size(min = 11, max = 110)
    private String nombre;

    @NotNull
    @Size(min = 7, max = 20)
    private String telefono;

    @Size(max = 100)
    private String correoElectronico;


}
