package tecolotl.alumno.sesion;

import java.util.Date;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
	/**
	 * Despues de que se validar datos se agrega un nuevo alumno con todos los parametros relacionados a el.
	 * @param gradoEscolar grupo al que pertenece el alumno
	 * @param nombre nombre(s) del alumno
	 * @param apellidopaterno Apellido paterno del Alumno
	 * @param apellidomaterno Apellido Materno de Alumno es es opcional
	 * @param apodo Nombre opcional del Alumno
	 * @param correoelectronico Correo electronico del Alumno
	 * @param contrasenia Contraseña del alumno de su cuenta
	 * @param nivelingles Rango de ingles segun el estandar cambridge que tiene el alumno
	 * @param nacimiento Fecha de nacimiento del alumno
	 * @param correo_padre_familia correo electronico del padre o tutor del alumno
	 * @param contraseniapadrefamilia Contraseña para el acceso de reportes del Padre o tutor del alumno.
	 */
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
	/**
	 * Busca a un alumno por medio del ID al encontrarlo regresa los valores que estan relacionados con el.
	 * @param id ID esta asignado a un alumno.
	 * @return retorna un objeto con los valores relacionados al ID
	 */
    public AlumnoEntidad buscar (int id) {
    	AlumnoEntidad alumnoEntidad = entityManager.find(AlumnoEntidad.class, id);
    	return alumnoEntidad;
    }
    /**
     * Actualiza el nombre de un alumno, se realiza una busqueda por id y sobre escribe el nombre.
     * @param nombre es el nuevo nombre que se le asignara a un alumno.
     * @param id Id con el que se identifica a un alumno
     */
    public void actualizar(String nombre, int id) {
    	AlumnoEntidad alumnoEntidad = entityManager.find(AlumnoEntidad.class, id);
    	alumnoEntidad.setNombre(nombre);
    }

	/**
	 * Busca un alumno partiendo del apodo y nombre, en caso de que alguno de los valos sea nulo, se regresará un nulo.
	 * Se obtiene un true si y sólo si el apodo y la contrsaseña coincidan con el registro.
	 * @param apodo Apodo con el que identifica el alumno.
	 * @param contrasenia Contraseña del alumno.
	 * @return true en caso de existir la coincidencia, false en cualquier otro caso.
	 */
	public boolean busca(String apodo, String contrasenia) {
		if (apodo == null || contrasenia == null) {
			return false;
		}
		TypedQuery<AlumnoEntidad> typedQuery = entityManager.createNamedQuery("AlumnoEntidad.buscaContrasenia", AlumnoEntidad.class);
		typedQuery.setParameter("apodo", apodo);
		typedQuery.setParameter("contrasenia", contrasenia);
		AlumnoEntidad alumnoEntidad = typedQuery.getSingleResult();
		return alumnoEntidad != null;
	}
}
