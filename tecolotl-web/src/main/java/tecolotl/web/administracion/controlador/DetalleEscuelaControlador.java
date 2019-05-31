package tecolotl.web.administracion.controlador;


import tecolotl.administracion.modelo.escuela.ContactoModelo;
import tecolotl.administracion.modelo.escuela.EscuelaDetalleModelo;
import tecolotl.administracion.modelo.escuela.LicenciaModelo;
import tecolotl.administracion.modelo.escuela.TipoContactoModelo;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
@Named
public class DetalleEscuelaControlador {

    private EscuelaDetalleModelo escuelaDetalleModelos;
    private List<ContactoModelo> contactoModelo;
    private List<LicenciaModelo> licenciasModelo;
    private List<TipoContactoModelo> tipoContactoModelos;

    @PostConstruct
    public void init() {


    }


}
