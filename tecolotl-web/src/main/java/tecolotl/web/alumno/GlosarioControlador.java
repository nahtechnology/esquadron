package tecolotl.web.alumno;

import tecolotl.alumno.modelo.glosario.GlosarioModelo;
import tecolotl.alumno.sesion.GlosarioSesionBean;
import tecolotl.nucleo.herramienta.AlmacenamientoEnum;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@RequestScoped
@Named
public class GlosarioControlador {

    @Inject
    private GlosarioSesionBean glosarioSesionBean;

    @Inject
    private AlumnoControlador alumnoControlador;

    private List<GlosarioModelo> glosarioModeloLista;
    private String carpeta;

    @PostConstruct
    public void init() {
        glosarioModeloLista = glosarioSesionBean.busca(alumnoControlador.getTareaActividadModelo().getIdActividad());
        carpeta = AlmacenamientoEnum.IMAGENES_GLOSARIO.name().toLowerCase();
    }

    public List<GlosarioModelo> getGlosarioModeloLista() {
        return glosarioModeloLista;
    }

    public void setGlosarioModeloLista(List<GlosarioModelo> glosarioModeloLista) {
        this.glosarioModeloLista = glosarioModeloLista;
    }

    public String getCarpeta() {
        return carpeta;
    }

    public void setCarpeta(String carpeta) {
        this.carpeta = carpeta;
    }
}
