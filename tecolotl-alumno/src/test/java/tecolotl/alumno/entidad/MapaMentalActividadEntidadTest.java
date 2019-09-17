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
import tecolotl.alumno.entidad.completar.CompletarEntidad;
import tecolotl.alumno.entidad.completar.TareaCompletarEntidad;
import tecolotl.alumno.entidad.completar.TareaCompletarEntidadPK;
import tecolotl.alumno.entidad.glosario.*;
import tecolotl.alumno.entidad.hablar.HablarEntidad;
import tecolotl.alumno.entidad.hablar.TareaHablarEntidad;
import tecolotl.alumno.entidad.hablar.TareaHablarEntidadPK;
import tecolotl.alumno.entidad.mapamental.*;
import tecolotl.alumno.entidad.oraciones.OracionesEntidad;
import tecolotl.alumno.entidad.oraciones.OracionesEntidadPK;
import tecolotl.alumno.entidad.oraciones.TareaOracionesEntidad;
import tecolotl.alumno.entidad.oraciones.TareaOracionesEntidadPK;
import tecolotl.nucleo.herramienta.LoggerProducer;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;
import tecolotl.nucleo.persistencia.entidad.PersonaEntidad;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@RunWith(Arquillian.class)
public class MapaMentalActividadEntidadTest {

    @Deployment
    public static Archive<?> createDeployment(){
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(MapaMentalActividadEntidad.class, MapaMentalActividadEntidadPK.class,
                        MapaMentalEntidad.class, MapaMentalEntidadPK.class, MapaMentalActividadEntidad.class, MapaMentalActividadEntidadPK.class,
                        TareaMapaMentalActividadEntidad.class, TareaMapaMentalActividadEntidadPK.class,
                        TareaHablarEntidad.class, TareaHablarEntidadPK.class, AlumnoEntidad.class,
                        PersonaEntidad.class, HablarEntidad.class,
                        TareaCompletarEntidad.class, TareaCompletarEntidadPK.class, CompletarEntidad.class,
                        TareaOracionesEntidad.class, TareaOracionesEntidadPK.class, TareaEntidad.class, OracionesEntidad.class,
                        OracionesEntidadPK.class, ActividadEntidad.class, NivelLenguajeEntidad.class, TipoEstudianteEntidad.class,
                        ClaseGlosarioEntidad.class, GlosarioEntidad.class, GlosarioEntidadPK.class, GlosarioActividadEntidad.class,
                        GlosarioActividadEntidadPK.class, TemaEntidad.class, CatalagoEntidad.class, LoggerProducer.class,
                        TareaMapaMentalActividadEntidad.class, TareaMapaMentalActividadEntidadPK.class, MapaMentalEntidad.class,
                        MapaMentalActividadEntidad.class, TareaGlosarioActividadEntidad.class, TareaGlosarioActividadEntidadPK.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void busca(){
        TypedQuery<MapaMentalActividadEntidad> typedQuery = entityManager.createNamedQuery("MapaMentalActividadEntidad.busca", MapaMentalActividadEntidad.class);
        List<MapaMentalActividadEntidad> mapaMentalActividadEntidadLista = typedQuery.getResultList();
        Assert.assertNotNull(mapaMentalActividadEntidadLista);
        Assert.assertFalse(mapaMentalActividadEntidadLista.isEmpty());
        for(MapaMentalActividadEntidad mapaMentalActividadEntidad: mapaMentalActividadEntidadLista){
            Assert.assertNotNull(mapaMentalActividadEntidad);
            Assert.assertNotNull(mapaMentalActividadEntidad.getMapaMentalActividadPK().getMapaMentalEntidad().getMapaMentalEntidadPK().getCodigo());
            Assert.assertNotNull(mapaMentalActividadEntidad.getMapaMentalActividadPK().getMapaMentalEntidad().getMapaMentalEntidadPK().getCardinalidad());
            Assert.assertNotNull(mapaMentalActividadEntidad.getMapaMentalActividadPK().getActividadEntidad().getId());
        }
    }
}
