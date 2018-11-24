package tecolotl.alumno.sesion;

import java.util.Date;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;

import tecolotl.alumno.persistencia.entidad.AlumnoEntidad;

public class AlumnoSesionBeanTest {
	
	@Deployment
	public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackage(AlumnoSesionBean.class.getPackage())
                .addPackage(AlumnoEntidad.class.getPackage())
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}   
	@Inject
	private AlumnoSesionBean alumnoSesionBean;

	@Test
	public void insertar() {
		Date date = new Date();
		alumnoSesionBean.insertar(5 ,"Guillermo", "Cuahuey","Estrada", "Memo", "cu.es.guillermo@nahtechnology.com", "pass", "a1", date , "guillermo@gamil.com", "pass");
	}

}