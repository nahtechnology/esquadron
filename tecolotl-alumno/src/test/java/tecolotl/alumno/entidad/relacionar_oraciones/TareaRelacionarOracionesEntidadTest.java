package tecolotl.alumno.entidad.relacionar_oraciones;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import tecolotl.alumno.entidad.*;
import tecolotl.alumno.entidad.completar.CompletarEntidad;
import tecolotl.alumno.entidad.completar.TareaCompletarEntidad;
import tecolotl.alumno.entidad.completar.TareaCompletarEntidadPK;
import tecolotl.alumno.entidad.glosario.*;
import tecolotl.alumno.entidad.mapamental.MapaMentalActividadEntidad;
import tecolotl.alumno.entidad.mapamental.MapaMentalEntidad;
import tecolotl.alumno.entidad.mapamental.TareaMapaMentalActividadEntidad;
import tecolotl.alumno.entidad.mapamental.TareaMapaMentalActividadEntidadPK;
import tecolotl.alumno.entidad.oraciones.OracionesEntidad;
import tecolotl.alumno.entidad.oraciones.OracionesEntidadPK;
import tecolotl.alumno.entidad.oraciones.TareaOracionesEntidad;
import tecolotl.alumno.entidad.oraciones.TareaOracionesEntidadPK;
import tecolotl.nucleo.herramienta.LoggerProducer;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@RunWith(Arquillian.class)
public class TareaRelacionarOracionesEntidadTest {

    @Deployment
    public static Archive<?> createDeployment(){
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(TareaRelacionarOracionesEntidad.class, TareaRelacionarOracionesEntidadPK.class, RelacionarOracionesEntidad.class,
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
        TypedQuery<TareaRelacionarOracionesEntidad> typedQuery = entityManager.createNamedQuery("TareaRelacionarOracionesEntidad.busca", TareaRelacionarOracionesEntidad.class);
        List<TareaRelacionarOracionesEntidad> tareaRelacionarOracionesEntidadLista = typedQuery.getResultList();
        Assert.assertNotNull(tareaRelacionarOracionesEntidadLista);
        Assert.assertFalse(tareaRelacionarOracionesEntidadLista.isEmpty());
        for (TareaRelacionarOracionesEntidad tareaRelacionarOracionesEntidad : tareaRelacionarOracionesEntidadLista){
            Assert.assertNotNull(tareaRelacionarOracionesEntidad);
            Assert.assertNotNull(tareaRelacionarOracionesEntidad.getTareaRelacionarOracionesEntidadPK().getRelacionarOracionesEntidad().getId());
            Assert.assertNotNull(tareaRelacionarOracionesEntidad.getTareaRelacionarOracionesEntidadPK().getTareaEntidad().getId());
            Assert.assertNotNull(tareaRelacionarOracionesEntidad.getRespuesta());
            Assert.assertNotNull(tareaRelacionarOracionesEntidad.getHoraRespuesta());
        }
    }

    @Test
    public void buscaTarea_id(){
        TypedQuery<TareaRelacionarOracionesEntidad> typedQuery = entityManager.createNamedQuery("TareaRelacionarOracionesEntidad.buscaidTarea", TareaRelacionarOracionesEntidad.class);
        typedQuery.setParameter("idTarea", 3);
        List<TareaRelacionarOracionesEntidad> tareaRelacionarOracionesEntidadLista = typedQuery.getResultList();
        Assert.assertNotNull(tareaRelacionarOracionesEntidadLista);
        Assert.assertFalse(tareaRelacionarOracionesEntidadLista.isEmpty());
        for (TareaRelacionarOracionesEntidad tareaRelacionarOracionesEntidad : tareaRelacionarOracionesEntidadLista){
            Assert.assertNotNull(tareaRelacionarOracionesEntidad);
            Assert.assertNotNull(tareaRelacionarOracionesEntidad.getTareaRelacionarOracionesEntidadPK().getTareaEntidad().getId());
            Assert.assertNotNull(tareaRelacionarOracionesEntidad.getTareaRelacionarOracionesEntidadPK().getRelacionarOracionesEntidad().getId());
            Assert.assertNotNull(tareaRelacionarOracionesEntidad.getRespuesta());
            Assert.assertNotNull(tareaRelacionarOracionesEntidad.getHoraRespuesta());
        }
    }
}
