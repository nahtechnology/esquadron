package tecolotl.alumno.entidad;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;
import tecolotl.nucleo.persistencia.entidad.PersonaEntidad;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@RunWith(Arquillian.class)
public class AlumnoEntidadTest {

    @Deployment
    public static Archive<?> createDeployment(){
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(CatalagoEntidad.class, AlumnoEntidad.class, PersonaEntidad.class,
                        /*TareaEntidad.class, TareaGlosarioActividadEntidad.class, TareaGlosarioActividadEntidadPK.class,
                        TipoEstudianteEntidad.class, ActividadEntidad.class, GlosarioEntidad.class,*/
                        NivelLenguajeEntidad.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void busca(){
        TypedQuery<AlumnoEntidad> typedQuery = entityManager.createNamedQuery("AlumnoEntidad.busca", AlumnoEntidad.class);
        List<AlumnoEntidad> alumnoEntidadLista = typedQuery.getResultList();
        Assert.assertNotNull(alumnoEntidadLista);
        Assert.assertFalse(alumnoEntidadLista.isEmpty());
        for (AlumnoEntidad alumnoEntidad : alumnoEntidadLista){
            Assert.assertNotNull(alumnoEntidad);
            Assert.assertNotNull(alumnoEntidad.getId());
            Assert.assertNotNull(alumnoEntidad.getNombre());
            Assert.assertNotNull(alumnoEntidad.getApellidoMaterno());
            Assert.assertNotNull(alumnoEntidad.getApellidoPaterno());
            Assert.assertNotNull(alumnoEntidad.getApodo());
            Assert.assertNotNull(alumnoEntidad.getContrasenia());
        }
    }
}
