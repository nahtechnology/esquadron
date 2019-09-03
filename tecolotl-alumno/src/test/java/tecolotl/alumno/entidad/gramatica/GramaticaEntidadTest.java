package tecolotl.alumno.entidad.gramatica;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import tecolotl.alumno.entidad.ActividadEntidad;
import tecolotl.alumno.entidad.NivelLenguajeEntidad;
import tecolotl.alumno.entidad.TemaEntidad;
import tecolotl.alumno.entidad.TipoEstudianteEntidad;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@RunWith(Arquillian.class)
public class GramaticaEntidadTest {

    @Deployment
    public static Archive<?> createDeployment(){
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(GramaticaEntidad.class, GramaticaEntidadPK.class, ActividadEntidad.class,
                        NivelLenguajeEntidad.class, TemaEntidad.class, TipoEstudianteEntidad.class,
                        CatalagoEntidad.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void busca(){
        TypedQuery<GramaticaEntidad> typedQuery = entityManager.createNamedQuery("GramaticaEntidad.busca", GramaticaEntidad.class);
        List<GramaticaEntidad> gramaticaEntidadLista = typedQuery.getResultList();
        Assert.assertNotNull(gramaticaEntidadLista);
        Assert.assertFalse(gramaticaEntidadLista.isEmpty());
        for (GramaticaEntidad gramaticaEntidad : gramaticaEntidadLista){
            Assert.assertNotNull(gramaticaEntidad);
            Assert.assertNotNull(gramaticaEntidad.getGramaticaEntidadPK().getActividadEntidad().getId());
            Assert.assertNotNull(gramaticaEntidad.getGramaticaEntidadPK().getCodigo());
            Assert.assertNotNull(gramaticaEntidad.getPalabra());
        }
    }
}
