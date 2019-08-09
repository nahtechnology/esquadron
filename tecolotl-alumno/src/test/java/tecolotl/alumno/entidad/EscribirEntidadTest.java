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
import tecolotl.nucleo.herramienta.CorreoEnum;
import tecolotl.nucleo.modelo.CatalogoModelo;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;
import tecolotl.nucleo.sesion.CatalogoSesionBean;
import tecolotl.nucleo.validacion.CatalogoNuevoValidacion;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@RunWith(Arquillian.class)
public class EscribirEntidadTest {

    @Deployment
    public static Archive<?> createDeployment(){
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(EscribirEntidad.class, EscribirActividadEntidad.class, EscribirActividadEntidadPK.class,
                        ActividadEntidad.class, NivelLenguajeEntidad.class, TipoEstudianteEntidad.class, CatalagoEntidad.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void busca(){
        TypedQuery<EscribirEntidad> typedQuery = entityManager.createNamedQuery("EscribirEntidad.buscaPorActivdad", EscribirEntidad.class);
        typedQuery.setParameter("idActividad", "JcMtWwiyzpU");
        List<EscribirEntidad> escribirEntidadLista = typedQuery.getResultList();
        Assert.assertNotNull(escribirEntidadLista);
        Assert.assertFalse(escribirEntidadLista.isEmpty());
        for(EscribirEntidad escribirEntidad : escribirEntidadLista){
            Assert.assertNotNull(escribirEntidad);
            Assert.assertNotNull(escribirEntidad.getPregunta());
            Assert.assertNotNull(escribirEntidad.getId());
        }
    }
}
