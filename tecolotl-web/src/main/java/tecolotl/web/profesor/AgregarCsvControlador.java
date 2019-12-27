package tecolotl.web.profesor;

import tecolotl.administracion.sesion.LicenciaSesionBean;
import tecolotl.alumno.modelo.AlumnoModelo;
import tecolotl.nucleo.sesion.PersonaSesionBean;
import tecolotl.profesor.sesion.CicloEscolarSessionBean;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@ViewScoped
@Named
public class AgregarCsvControlador implements Serializable {

    @Inject
    private LicenciaSesionBean licenciaSesionBean;

    @Inject
    private ProfesorControlador profesorControlador;

    @Inject
    private PersonaSesionBean personaSesionBean;

    @Inject
    private CicloEscolarSessionBean cicloEscolarSessionBean;

    @Inject
    private Logger logger;

    private int totalAlumno;
    private int alumnosAsignado;
    private List<AlumnoModelo> alumnoModeloLista;
    private List<String> apodos;
    private AlumnoModelo alumnoModelo;

    @PostConstruct
    public void inicio() {
        alumnoModelo = new AlumnoModelo();
        alumnoModeloLista = new ArrayList<>();
        totalAlumno = licenciaSesionBean.busca(new Date(), profesorControlador.getProfesorModelo().getEscuelaBaseModelo().getClaveCentroTrabajo()).getAlumnos();
        alumnosAsignado = cicloEscolarSessionBean.totalAlumnos(profesorControlador.getProfesorModelo().getEscuelaBaseModelo().getClaveCentroTrabajo()).intValue();
        apodos = personaSesionBean.buscaApodo(profesorControlador.getProfesorModelo().getEscuelaBaseModelo().getClaveCentroTrabajo());
    }

    public void agregarLista() {
        logger.info(alumnoModelo.toString());
        alumnoModeloLista.add(alumnoModelo);
        alumnoModelo = new AlumnoModelo();
    }

    public void inserta() {
        logger.info(alumnoModeloLista.toString());
    }

    public int getTotalAlumno() {
        return totalAlumno;
    }

    public void setTotalAlumno(int totalAlumno) {
        this.totalAlumno = totalAlumno;
    }

    public int getAlumnosAsignado() {
        return alumnosAsignado;
    }

    public void setAlumnosAsignado(int alumnosAsignado) {
        this.alumnosAsignado = alumnosAsignado;
    }

    public List<AlumnoModelo> getAlumnoModeloLista() {
        return alumnoModeloLista;
    }

    public void setAlumnoModeloLista(List<AlumnoModelo> alumnoModeloLista) {
        this.alumnoModeloLista = alumnoModeloLista;
    }

    public AlumnoModelo getAlumnoModelo() {
        return alumnoModelo;
    }

    public void setAlumnoModelo(AlumnoModelo alumnoModelo) {
        this.alumnoModelo = alumnoModelo;
    }

    public List<String> getApodos() {
        return apodos;
    }

    public void setApodos(List<String> apodos) {
        this.apodos = apodos;
    }

}
