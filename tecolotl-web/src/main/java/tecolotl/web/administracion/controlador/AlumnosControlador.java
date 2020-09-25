package tecolotl.web.administracion.controlador;

import tecolotl.administracion.modelo.AlumnoBusquedaModelo;
import tecolotl.administracion.modelo.GrupoBusquedaModelo;
import tecolotl.administracion.sesion.BusquedaEscuelaSesionBean;
import tecolotl.alumno.sesion.AlumnoSesionBean;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

@ViewScoped
@Named
public class AlumnosControlador implements Serializable {

    @Inject
    private BusquedaEscuelaSesionBean busquedaEscuelaSesionBean;

    @Inject
    private AlumnoSesionBean alumnoSesionBean;

    @Inject
    private Logger logger;

    private String claveCentroTrabajo;
    private Map<GrupoBusquedaModelo, List<AlumnoBusquedaModelo>> mapa;

    public void inicio() {
        mapa = busquedaEscuelaSesionBean.buscaAlumno(claveCentroTrabajo);
    }

    public void actiliza(String idAlunmo, boolean estatus) {
        alumnoSesionBean.actualiza(UUID.fromString(idAlunmo), estatus);
        for (Map.Entry<GrupoBusquedaModelo, List<AlumnoBusquedaModelo>> entrySet : mapa.entrySet()) {
            for (AlumnoBusquedaModelo alumnoBusquedaModelo : entrySet.getValue()) {
                if (alumnoBusquedaModelo.getIdAlumno().equals(idAlunmo)) {
                    alumnoBusquedaModelo.setEstatus(estatus);
                    break;
                }
            }
        }
    }

    public String getClaveCentroTrabajo() {
        return claveCentroTrabajo;
    }

    public void setClaveCentroTrabajo(String claveCentroTrabajo) {
        this.claveCentroTrabajo = claveCentroTrabajo;
    }

    public Map<GrupoBusquedaModelo, List<AlumnoBusquedaModelo>> getMapa() {
        return mapa;
    }

    public void setMapa(Map<GrupoBusquedaModelo, List<AlumnoBusquedaModelo>> mapa) {
        this.mapa = mapa;
    }
}
