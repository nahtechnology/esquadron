package tecolotl.alumno.persistencia.entidad;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class AlumnoEntidadTest {
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Deployment
	public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackage(AlumnoEntidad.class.getPackage())
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	 @PersistenceContext()
	    private EntityManager entityManager;

	    @Test
	    public void busca() {
	        TypedQuery<AlumnoEntidad> typedQuery = entityManager.createNamedQuery("AlumnoEntidad.findAll", AlumnoEntidad.class);
	        List<AlumnoEntidad> listaTarea = typedQuery.getResultList();
	        Assert.assertNotNull(listaTarea);
	        logger.info(String.valueOf(listaTarea.size()));
	        Assert.assertFalse("Lista vacia", listaTarea.isEmpty());
	    }
	    
	    @Test
	    public void buscarId() {
	    	
	    	AlumnoEntidad alumnoEntidad = entityManager.find(AlumnoEntidad.class, 75);
	    	Assert.assertNotNull("Sin Alumno", alumnoEntidad);
	    	Assert.assertTrue("Sin Grado ", alumnoEntidad.getGradoEscolarBean().getGrado() > 0);
	    	Assert.assertNotNull("Sin Grupo", alumnoEntidad.getGradoEscolarBean().getGrupo());
	    	Assert.assertNotNull("Sin Nombre", alumnoEntidad.getNombre());
	    	Assert.assertNotNull("Sin Apellido Paterno", alumnoEntidad.getApellidoPaterno());
	    	Assert.assertNotNull("Sin Apelldio materno", alumnoEntidad.getApellidoMaterno());
	    	Assert.assertNotNull("Sin Apodo", alumnoEntidad.getApodo());
	    	Assert.assertNotNull("Sin Correo Electronico", alumnoEntidad.getCorreoElectronico());
	    	Assert.assertNotNull("Sin Contraseña", alumnoEntidad.getContrasenia());
	    	Assert.assertNotNull("Sin Nivel de Inlges", alumnoEntidad.getNivelIngles());
	    	Assert.assertNotNull("Sin Fecha de Nacimiento", alumnoEntidad.getNacimiento());
	    	Assert.assertNotNull("Sin Correo Electronico Padre", alumnoEntidad.getCorreoPadreFamilia());
	    	Assert.assertNotNull("Sin Contraseña de Padre", alumnoEntidad.getContraseniaPadreFamilia());	
	    }
	    
	    @Test
	    public void login() {
	    	TypedQuery<AlumnoEntidad> typedQuery = entityManager.createNamedQuery("AlumnoEntidad.buscarId", AlumnoEntidad.class);
	         typedQuery.setParameter("nombre", "ESMERALDA");
	         typedQuery.setParameter("password", "ESMERALDA");
	         List<AlumnoEntidad> listaAlumnos = typedQuery.getResultList();
	         Assert.assertNotNull("No se encontro Alumno",listaAlumnos );
	    }
	    public void crear() {

	    }

}
