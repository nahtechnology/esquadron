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
import tecolotl.alumno.entidad.mapamental.MapaMentalActividadEntidad;
import tecolotl.alumno.entidad.mapamental.MapaMentalEntidad;
import tecolotl.alumno.entidad.mapamental.TareaMapaMentalActividadEntidad;
import tecolotl.alumno.entidad.glosario.*;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;
import tecolotl.nucleo.persistencia.entidad.PersonaEntidad;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@RunWith(Arquillian.class)
public class TareaEntidadTest {
    @Deployment
    public static Archive<?> createDeployment(){
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(AlumnoEntidad.class, PersonaEntidad.class, TareaEntidad.class, TareaGlosarioActividadEntidad.class,
                        TareaGlosarioActividadEntidadPK.class, GlosarioEntidad.class, ActividadEntidad.class,
                        TipoEstudianteEntidad.class, ClaseGlosarioEntidad.class, TareaGlosarioActividadEntidad.class,
                        TareaGlosarioActividadEntidadPK.class, GlosarioActividadEntidad.class, GlosarioActividadEntidadPK.class,
                        TareaMapaMentalActividadEntidad.class, TareaGlosarioActividadEntidadPK.class, MapaMentalActividadEntidad.class,
                        MapaMentalEntidad.class,
                        NivelLenguajeEntidad.class, CatalagoEntidad.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void busca(){
        TypedQuery<TareaEntidad> typedQuery = entityManager.createNamedQuery("TareaEntidad.busca", TareaEntidad.class);
        List<TareaEntidad> tareaEntidadLista = typedQuery.getResultList();
        Assert.assertNotNull(tareaEntidadLista);
        Assert.assertFalse(tareaEntidadLista.isEmpty());
        for (TareaEntidad tareaEntidad : tareaEntidadLista){
            Assert.assertNotNull(tareaEntidad);
            Assert.assertNotNull(tareaEntidad.getId());
            Assert.assertNotNull(tareaEntidad.getAsignacion());
        }
    }

    @Test
    public void eleminaPorgrupo() {
        Query query = entityManager.createNamedQuery("TareaEntidad.eliminaGrupo");
        query.setParameter("idActividad", "1CZ7mBSLH1c");
        int eliminados = query.executeUpdate();
        Assert.assertNotEquals(0, eliminados);
    }

}
