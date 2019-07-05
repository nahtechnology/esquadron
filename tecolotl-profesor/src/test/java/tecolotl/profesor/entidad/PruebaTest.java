package tecolotl.profesor.entidad;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import tecolotl.administracion.persistencia.entidad.*;
import tecolotl.alumno.entidad.*;
import tecolotl.nucleo.herramienta.LoggerProducer;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;
import tecolotl.nucleo.persistencia.entidad.PersonaEntidad;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Logger;

@RunWith(Arquillian.class)
public class PruebaTest {

    @Deployment
    public static WebArchive createDeployment(){
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(PruebaCipherEntidad.class, PersonaEntidad.class, AlumnoEntidad.class,
                        EscuelaEntidad.class, MotivoBloqueoEntidad.class, CatalagoEntidad.class,
                        ColoniaEntidad.class, MunicipioEntidad.class, ContactoEntidad.class,
                        TipoContactoEntidad.class, ContactoEntidadPK.class, EstadoEntidad.class,
                        TareaEntidad.class, TareaGlosarioActividadEntidad.class, TareaGlosarioActividadEntidadPK.class,
                        GradoEscolarEntidad.class, NivelLenguajeEntidad.class, GlosarioEntidad.class,
                        ActividadEntidad.class, TipoEstudianteEntidad.class,
                        ProfesorEntidad.class, LoggerProducer.class)
                .addPackage(PruebaCipherEntidad.class.getPackage())
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsResource("META-INF/persistence.xml");
    }
    @Inject
    private Logger logger;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void inserta(){
        PruebaCipherEntidad pruebaCipherEntidad = new PruebaCipherEntidad();
        pruebaCipherEntidad.setKey("2ssdfs23kj234k5kj35jkl3l2");
        pruebaCipherEntidad.setPassword("123456");
        entityManager.persist(pruebaCipherEntidad);
        logger.fine("Error de ".concat(pruebaCipherEntidad.toString()));
        Assert.assertNotNull(pruebaCipherEntidad);
        Assert.assertNotNull(pruebaCipherEntidad.getKey());
        Assert.assertNotNull(pruebaCipherEntidad.getPassword());
    }

    @Test
    public void busca(){
        TypedQuery<PruebaCipherEntidad> typedQuery = entityManager.createNamedQuery("PruebaCipherEntidad.BuscaPSWD", PruebaCipherEntidad.class);
        List<PruebaCipherEntidad> pruebaCipherEntidadsLista = typedQuery.getResultList();
        Assert.assertNotNull(pruebaCipherEntidadsLista);
        Assert.assertFalse(pruebaCipherEntidadsLista.isEmpty());
        for (PruebaCipherEntidad pruebaCipherEntidad : pruebaCipherEntidadsLista){
            Assert.assertNotNull(pruebaCipherEntidad);
            Assert.assertNotNull(pruebaCipherEntidad.getId());
            Assert.assertNotNull(pruebaCipherEntidad.getKey());
            Assert.assertNotNull(pruebaCipherEntidad.getPassword());
        }
    }
}
