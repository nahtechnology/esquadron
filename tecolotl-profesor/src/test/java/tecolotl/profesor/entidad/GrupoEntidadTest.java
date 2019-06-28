package tecolotl.profesor.entidad;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import tecolotl.administracion.persistencia.entidad.EscuelaEntidad;
import tecolotl.alumno.entidad.*;
import tecolotl.nucleo.herramienta.LoggerProducer;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;
import tecolotl.nucleo.persistencia.entidad.PersonaEntidad;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@RunWith(Arquillian.class)
public class GrupoEntidadTest {
    @Deployment
    public static WebArchive createDeployment(){
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(ProfesorEntidad.class, PersonaEntidad.class, LoggerProducer.class, CatalagoEntidad.class,
                        GrupoEntidad.class, GrupoEntidad.class, AlumnoEntidad.class, NivelLenguajeEntidad.class,
                        GradoEscolarEntidad.class, TareaEntidad.class, TareaGlosarioActividadEntidad.class, TareaGlosarioActividadEntidadPK.class,
                        GlosarioEntidad.class, ActividadEntidad.class, TipoEstudianteEntidad.class)
                .addPackage(EscuelaEntidad.class.getPackage())
                .addPackage(ProfesorEntidad.class.getPackage())
                .addPackage(GrupoEntidad.class.getPackage())
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void busca(){
        TypedQuery<GrupoEntidad> typedQuery = entityManager.createNamedQuery("GrupoEntidad.busca", GrupoEntidad.class);
        List<GrupoEntidad> grupoEntidadLista = typedQuery.getResultList();
        Assert.assertNotNull(grupoEntidadLista);
        Assert.assertFalse(grupoEntidadLista.isEmpty());
        for(GrupoEntidad grupoEntidad: grupoEntidadLista){
            Assert.assertNotNull(grupoEntidad);
            Assert.assertNotNull(grupoEntidad.getId());
            Assert.assertNotNull(grupoEntidad.getGrado());
            Assert.assertNotNull(grupoEntidad.getGrupo());
            Assert.assertNotNull(grupoEntidad.getInicio());
            Assert.assertNotNull(grupoEntidad.getFin());
            Assert.assertNotNull(grupoEntidad.getProfesorEntidad());
        }
    }
}

