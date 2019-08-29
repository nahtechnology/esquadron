package tecolotl.web.colaborador;

import tecolotl.alumno.modelo.glosario.GlosarioModelo;
import tecolotl.alumno.sesion.GlosarioSesionBean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.logging.Logger;

@RequestScoped
@Named
public class RelacionarGlosarioActividad {

    @Inject
    private GlosarioSesionBean glosarioSesionBean;

    @Inject
    private Logger logger;

    private String palabra;
    private String idActividad;
    private List<GlosarioModelo> glosarioModeloLista;

    public void busca() {
        logger.info(idActividad);
        logger.info(palabra);
        glosarioModeloLista = glosarioSesionBean.busca(palabra, idActividad);
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public List<GlosarioModelo> getGlosarioModeloLista() {
        return glosarioModeloLista;
    }

    public void setGlosarioModeloLista(List<GlosarioModelo> glosarioModeloLista) {
        this.glosarioModeloLista = glosarioModeloLista;
    }

    public String getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(String idActividad) {
        this.idActividad = idActividad;
    }
}
