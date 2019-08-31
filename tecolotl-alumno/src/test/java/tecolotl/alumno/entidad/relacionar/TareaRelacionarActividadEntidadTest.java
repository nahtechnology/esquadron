package tecolotl.alumno.entidad.relacionar;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import tecolotl.alumno.entidad.ActividadEntidad;
import tecolotl.alumno.entidad.glosario.GlosarioEntidad;
import tecolotl.alumno.entidad.mapamental.MapaMentalEntidad;
import tecolotl.alumno.modelo.ActividadModelo;
import tecolotl.alumno.modelo.glosario.GlosarioModelo;
import tecolotl.alumno.modelo.mapamental.MapaMentalModelo;
import tecolotl.alumno.sesion.ActividadSesionBean;
import tecolotl.alumno.validacion.ActividadNuevaValidacion;
import tecolotl.alumno.validacion.escribir.EscribirLlavePrimariaValidacion;
import tecolotl.alumno.validacion.glosario.GlosarioNuevoValidacion;
import tecolotl.nucleo.herramienta.ValidadorSessionBean;
import tecolotl.nucleo.modelo.CatalogoModelo;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;
import tecolotl.nucleo.sesion.CatalogoSesionBean;
import tecolotl.nucleo.validacion.CatalogoNuevoValidacion;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class TareaRelacionarActividadEntidadTest {

    @Deployment
    public static Archive<?> createDeployment(){
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackage(MapaMentalEntidad.class.getPackage()).addPackage(GlosarioEntidad.class.getPackage())
                .addPackage(ActividadEntidad.class.getPackage())
                .addPackage(RelacionarEntidad.class.getPackage())
                .addPackage(MapaMentalModelo.class.getPackage())
                .addPackage(GlosarioModelo.class.getPackage())
                .addPackage(ActividadModelo.class.getPackage())
                .addPackage(ActividadSesionBean.class.getPackage())
                .addPackage(EscribirLlavePrimariaValidacion.class.getPackage())
                .addPackage(ValidadorSessionBean.class.getPackage())
                .addPackage(GlosarioNuevoValidacion.class.getPackage())
                .addPackage(ActividadNuevaValidacion.class.getPackage())
                .addPackage(CatalogoNuevoValidacion.class.getPackage())
                .addPackage(CatalagoEntidad.class.getPackage())
                .addPackage(CatalogoSesionBean.class.getPackage())
                .addPackage(CatalogoModelo.class.getPackage())
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @PersistenceContext
    private EntityManager entityManager;


    @Test
    public void busca() {
        TypedQuery<TareaRelacionarActividadEntidad> typedQuery =
                entityManager.createNamedQuery("TareaRelacionarActividadEntidad.busca", TareaRelacionarActividadEntidad.class);
        List<TareaRelacionarActividadEntidad> tareaRelacionarActividadEntidadLista = typedQuery.getResultList();
        assertNotNull(tareaRelacionarActividadEntidadLista);
        assertFalse(tareaRelacionarActividadEntidadLista.isEmpty());
        tareaRelacionarActividadEntidadLista.forEach(tareaRelacionarActividadEntidad -> {
            assertNotNull(tareaRelacionarActividadEntidad);
            assertNotNull(tareaRelacionarActividadEntidad.getTareaRelacionarActividadEntidadPK().getTareaEntidad().getId());
            assertNotNull(tareaRelacionarActividadEntidad.getTareaRelacionarActividadEntidadPK().getRelacionarActividadEntidad());
            assertNotNull(tareaRelacionarActividadEntidad.getTareaRelacionarActividadEntidadPK().getRelacionarActividadEntidad().getRelacionarActividadEntidadPK().getRelacionarEntidad());
            assertNotNull(tareaRelacionarActividadEntidad.getTareaRelacionarActividadEntidadPK().getRelacionarActividadEntidad().getRelacionarActividadEntidadPK().getRelacionarEntidad().getCodigo());
            assertNotNull(tareaRelacionarActividadEntidad.getTareaRelacionarActividadEntidadPK().getRelacionarActividadEntidad().getRelacionarActividadEntidadPK().getActividadEntidad().getId());
        });
    }

}
