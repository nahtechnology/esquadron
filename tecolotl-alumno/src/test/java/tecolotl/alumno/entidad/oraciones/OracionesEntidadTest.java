package tecolotl.alumno.entidad.oraciones;

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
import tecolotl.alumno.entidad.glosario.*;
import tecolotl.nucleo.herramienta.LoggerProducer;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Logger;

@RunWith(Arquillian.class)
public class OracionesEntidadTest {

    @Deployment
    public static Archive<?> createDeployment(){
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(OracionesEntidad.class, OracionesEntidadPK.class, ActividadEntidad.class, TipoEstudianteEntidad.class,
                        NivelLenguajeEntidad.class, ClaseGlosarioEntidad. class, GlosarioEntidad.class, GlosarioActividadEntidad.class,
                        GlosarioEntidadPK.class, GlosarioActividadEntidadPK.class, CatalagoEntidad.class, TemaEntidad.class, LoggerProducer.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private Logger logger;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void busca(){
        TypedQuery<OracionesEntidad> typedQuery = entityManager.createNamedQuery("OracionesEntidad.busca", OracionesEntidad.class);
        List<OracionesEntidad> oracionesEntidadLista = typedQuery.getResultList();
        Assert.assertNotNull(oracionesEntidadLista);
        Assert.assertFalse(oracionesEntidadLista.isEmpty());
        for (OracionesEntidad oracionesEntidad: oracionesEntidadLista){
            Assert.assertNotNull(oracionesEntidad);
            Assert.assertNotNull(oracionesEntidad.getOracionesEntidadPK().getActividadEntidad().getId());
            Assert.assertNotNull(oracionesEntidad.getOracionesEntidadPK().getCodigo());
            Assert.assertNotNull(oracionesEntidad.getOracionesEntidadPK().getCardinalidad());
            Assert.assertNotNull(oracionesEntidad.getOracion());
        }
    }

}
