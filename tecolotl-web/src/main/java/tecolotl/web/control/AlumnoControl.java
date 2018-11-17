package tecolotl.web.control;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import tecolotl.alumno.persistencia.entidad.AlumnoEntidad;
import tecolotl.alumno.sesion.AlumnoSesionBean;

@RequestScoped
@Named
public class AlumnoControl {

	@Inject
	private AlumnoSesionBean alumnoSesionBean;

	private List<AlumnoEntidad> lista;
	
	@PostConstruct
	public void init() {
		lista = alumnoSesionBean.bucar();
	}

	public List<AlumnoEntidad> getLista() {
		return lista;
	}

	public void setLista(List<AlumnoEntidad> lista) {
		this.lista = lista;
	}
	
	
}
