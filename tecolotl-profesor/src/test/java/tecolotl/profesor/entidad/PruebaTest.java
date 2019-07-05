package tecolotl.profesor.entidad;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import tecolotl.administracion.modelo.coordinador.CoordinadorModelo;
import tecolotl.administracion.modelo.direccion.ColoniaModelo;
import tecolotl.administracion.modelo.escuela.ContactoModelo;
import tecolotl.administracion.modelo.escuela.EscuelaPoblacionModelo;
import tecolotl.administracion.persistencia.entidad.*;
import tecolotl.administracion.sesion.ContactoSesionBean;
import tecolotl.administracion.validacion.direccion.ColoniaNuevaValidacion;
import tecolotl.administracion.validacion.escuela.ContactoLlavePrimariaValidacion;
import tecolotl.alumno.entidad.*;
import tecolotl.alumno.sesion.AlumnoSesionBean;
import tecolotl.nucleo.herramienta.LoggerProducer;
import tecolotl.nucleo.herramienta.ValidadorSessionBean;
import tecolotl.nucleo.modelo.CatalogoModelo;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;
import tecolotl.nucleo.persistencia.entidad.PersonaEntidad;
import tecolotl.nucleo.sesion.CatalogoSesionBean;
import tecolotl.nucleo.validacion.CatalogoNuevoValidacion;
import tecolotl.profesor.modelo.GrupoAlumnoModelo;
import tecolotl.profesor.sesion.GrupoAlumnoSesionBean;
import tecolotl.profesor.sesion.ProfesorSesionBean;
import tecolotl.profesor.sesion.PruebaSesionBean;
import tecolotl.profesor.validacion.GrupoProfesorValidacion;

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
                //nucleo
                .addPackage(ValidadorSessionBean.class.getPackage())
                .addPackage(CatalogoModelo.class.getPackage())
                .addPackage(CatalagoEntidad.class.getPackage())
                .addPackage(CatalogoSesionBean.class.getPackage())
                .addPackage(CatalogoNuevoValidacion.class.getPackage())
                //administracion
                .addPackage(CoordinadorModelo.class.getPackage())
                .addPackage(ColoniaModelo.class.getPackage())
                .addPackage(ContactoModelo.class.getPackage())
                .addPackage(ColoniaEntidad.class.getPackage())
                .addPackage(ContactoSesionBean.class.getPackage())
                .addClass(ColoniaNuevaValidacion.class)
                .addPackage(ContactoLlavePrimariaValidacion.class.getPackage())
                //alumno
                .addPackage(ActividadEntidad.class.getPackage())
                .addPackage(tecolotl.alumno.modelo.AlumnoModelo.class.getPackage())
                .addPackage(AlumnoSesionBean.class.getPackage())
                //profesor
                .addPackage(GrupoAlumnoEntidad.class.getPackage())
                .addPackage(GrupoAlumnoModelo.class.getPackage())
                .addPackage(GrupoAlumnoSesionBean.class.getPackage())
                .addPackage(GrupoProfesorValidacion.class.getPackage())
                .addClasses(PruebaCipherEntidad.class, PersonaEntidad.class, AlumnoEntidad.class,
                        EscuelaEntidad.class, MotivoBloqueoEntidad.class, CatalagoEntidad.class,
                        ColoniaEntidad.class, MunicipioEntidad.class, ContactoEntidad.class,
                        TipoContactoEntidad.class, ContactoEntidadPK.class, EstadoEntidad.class,
                        TareaEntidad.class, TareaGlosarioActividadEntidad.class, TareaGlosarioActividadEntidadPK.class,
                        GradoEscolarEntidad.class, NivelLenguajeEntidad.class, GlosarioEntidad.class,
                        ActividadEntidad.class, TipoEstudianteEntidad.class, ProfesorSesionBean.class,
                        ValidadorSessionBean.class, EscuelaPoblacionModelo.class,
                        ProfesorEntidad.class, LoggerProducer.class)
                .addPackage(PruebaCipherEntidad.class.getPackage())
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsResource("META-INF/persistence.xml");
    }
    @Inject
    private Logger logger;

    @Inject
    private PruebaSesionBean pruebaSesionBean;

    @Test
    public void inserta(){
        PruebaCipherEntidad pruebaCipherEntidad = new PruebaCipherEntidad();
        pruebaCipherEntidad.setKey("2ssdfs23kj234k5kj35jkl3l2");
        pruebaCipherEntidad.setPassword("123456");
        pruebaSesionBean.inserta(pruebaCipherEntidad);
        logger.fine("Error de ".concat(pruebaCipherEntidad.toString()));
        Assert.assertNotNull(pruebaCipherEntidad);
        Assert.assertNotNull(pruebaCipherEntidad.getKey());
        Assert.assertNotNull(pruebaCipherEntidad.getPassword());
    }

    /*@Test
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
    }*/
}
