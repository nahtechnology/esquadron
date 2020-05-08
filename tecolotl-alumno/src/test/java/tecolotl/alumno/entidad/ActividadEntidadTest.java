package tecolotl.alumno.entidad;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import tecolotl.alumno.entidad.glosario.*;
import tecolotl.alumno.entidad.mapamental.*;
import tecolotl.alumno.entidad.relacionar.RelacionarActividadEntidad;
import tecolotl.alumno.entidad.relacionar.RelacionarActividadEntidadPK;
import tecolotl.alumno.entidad.relacionar.TareaRelacionarActividadEntidad;
import tecolotl.alumno.entidad.relacionar.TareaRelacionarActividadEntidadPK;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;
import tecolotl.nucleo.persistencia.entidad.PersonaEntidad;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class ActividadEntidadTest {

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(NivelLenguajeEntidad.class, CatalagoEntidad.class, ActividadEntidad.class,
                        TipoEstudianteEntidad.class, PersonaEntidad.class, TemaEntidad.class, GlosarioActividadEntidad.class,
                        GlosarioActividadEntidadPK.class, TareaGlosarioActividadEntidad.class, TareaGlosarioActividadEntidadPK.class,
                        TareaEntidad.class, AlumnoEntidad.class, PersonaEntidad.class, TareaMapaMentalActividadEntidad.class,
                        TareaMapaMentalActividadEntidadPK.class, TareaRelacionarActividadEntidad.class, TareaRelacionarActividadEntidadPK.class,
                        MapaMentalActividadEntidad.class, MapaMentalActividadEntidadPK.class, MapaMentalEntidad.class, MapaMentalEntidadPK.class,
                        RelacionarActividadEntidad.class, RelacionarActividadEntidadPK.class, GlosarioEntidad.class, GlosarioEntidadPK.class,
                        ClaseGlosarioEntidad.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void busca() {
        TypedQuery<ActividadEntidad> typedQuery = entityManager.createNamedQuery("ActividadEntidad.busca", ActividadEntidad.class);
        List<ActividadEntidad> actividadEntidadLista = typedQuery.getResultList();
        Assert.assertNotNull(actividadEntidadLista);
        Assert.assertFalse(actividadEntidadLista.isEmpty());
        for (ActividadEntidad actividadEntidad : actividadEntidadLista) {
            Assert.assertNotNull(actividadEntidad);
            Assert.assertNotNull(actividadEntidad.getId());
            Assert.assertNotNull(actividadEntidad.getPuntaje());
            Assert.assertNotNull(actividadEntidad.getLenguaje());
            Assert.assertNotNull(actividadEntidad.getPreguntaDetonadora());
            Assert.assertNotNull(actividadEntidad.getTiempo());
            Assert.assertNotNull(actividadEntidad.getTipoEstudianteEntidad().getClave());
            for (NivelLenguajeEntidad nivelLenguajeEntidad : actividadEntidad.getNivelLenguajeEntidad()) {
                Assert.assertNotNull(nivelLenguajeEntidad);
                Assert.assertNotNull(nivelLenguajeEntidad.getValor());
            }
        }
    }

    @Test
    public void buscaTareaNoAsignadas() {
        TypedQuery<ActividadEntidad> typedQuery = entityManager.createNamedQuery("ActividadEntidad.buscaNoAsigandasAlumno", ActividadEntidad.class);
        typedQuery.setParameter("idAlumno", UUID.fromString("0cbaa96c-ba77-408d-b046-56e0fd1ffe56"));
        typedQuery.setParameter("nivelLenguaje", (short)1);
        List<ActividadEntidad> actividadEntidadLista = typedQuery.getResultList();
        assertNotNull(actividadEntidadLista);
        assertFalse(actividadEntidadLista.isEmpty());
        actividadEntidadLista.forEach(actividadEntidad -> {
            assertNotNull(actividadEntidad);
            assertNotNull(actividadEntidad.getId());
            assertNotNull(actividadEntidad.getPreguntaDetonadora());
        });
    }

}
