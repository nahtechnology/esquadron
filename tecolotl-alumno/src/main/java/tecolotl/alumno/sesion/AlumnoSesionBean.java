package tecolotl.alumno.sesion;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tecolotl.alumno.persistencia.entidad.AlumnoEntidad;
import tecolotl.alumno.persistencia.entidad.GradoEscolarEntidad;

/**
 * Session Bean implementation class AlumnoSesionBean
 */
@Stateless
@LocalBean
public class AlumnoSesionBean {

	@PersistenceContext
    private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<AlumnoEntidad> bucar() {
		Query query = entityManager.createQuery("SELECT a FROM AlumnoEntidad a");
		return (List<AlumnoEntidad>)query.getResultList();
	}
	
	
	public void insertar (int gradoEscolar, String nombre, String apellidopaterno, String apellidomaterno, String apodo, String correoelectronico, String contrasenia,String nivelingles, Date nacimiento,String correo_padre_familia,String contraseniapadrefamilia) {
		AlumnoEntidad alumnoEntidad = new AlumnoEntidad();
		GradoEscolarEntidad gradoEscolarEntidad = new GradoEscolarEntidad();
		gradoEscolarEntidad.setId(gradoEscolar);
		alumnoEntidad.setGradoEscolarBean(gradoEscolarEntidad);
		 alumnoEntidad.setApellidoPaterno(apellidopaterno);
		 alumnoEntidad.setApellidoMaterno(apellidomaterno);
		 alumnoEntidad.setNombre(nombre);
		 alumnoEntidad.setApodo(apodo);
		 alumnoEntidad.setCorreoElectronico(correoelectronico);
		 alumnoEntidad.setContrasenia(contrasenia);
		 alumnoEntidad.setNacimiento(nacimiento);
		 alumnoEntidad.setCorreoPadreFamilia(correo_padre_familia);
		 alumnoEntidad.setContraseniaPadreFamilia(contraseniapadrefamilia);
		 entityManager.persist(alumnoEntidad);	
	}
    public AlumnoEntidad buscar (int id) {
    	AlumnoEntidad alumnoEntidad = entityManager.find(AlumnoEntidad.class, id);
    	return alumnoEntidad;
    	
    }
    
    public void actualizar(String nombre, int id) {
    	AlumnoEntidad alumnoEntidad = entityManager.find(AlumnoEntidad.class, id);
    	alumnoEntidad.setNombre(nombre);
    	
    }
}
