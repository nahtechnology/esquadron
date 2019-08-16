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
import tecolotl.alumno.entidad.escribir.EscribirActividadEntidad;
import tecolotl.alumno.entidad.escribir.EscribirEntidad;
import tecolotl.alumno.entidad.escribir.TareaEscribirActividadEntidad;
import tecolotl.alumno.entidad.glosario.*;
import tecolotl.alumno.modelo.ActividadModelo;
import tecolotl.alumno.modelo.escribir.EscribirModelo;
import tecolotl.alumno.modelo.glosario.GlosarioModelo;
import tecolotl.alumno.sesion.ActividadSesionBean;
import tecolotl.alumno.validacion.escribir.EscribirLlavePrimariaValidacion;
import tecolotl.nucleo.herramienta.ValidadorSessionBean;
import tecolotl.nucleo.modelo.CatalogoModelo;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;
import tecolotl.nucleo.sesion.CatalogoSesionBean;
import tecolotl.nucleo.validacion.CatalogoNuevoValidacion;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@RunWith(Arquillian.class)
public class TareaGlosarioActividadEntidadTest {

    @Deployment
    public static Archive<?> createDeployment(){
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackage(EscribirEntidad.class.getPackage()).addPackage(GlosarioEntidad.class.getPackage())
                .addPackage(ActividadEntidad.class.getPackage())
                .addPackage(EscribirModelo.class.getPackage())
                .addPackage(GlosarioModelo.class.getPackage())
                .addPackage(GlosarioModelo.class.getPackage())
                .addPackage(ActividadModelo.class.getPackage())
                .addPackage(ActividadSesionBean.class.getPackage())
                .addPackage(EscribirLlavePrimariaValidacion.class.getPackage())
                .addPackage(ValidadorSessionBean.class.getPackage())
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
        TypedQuery<TareaGlosarioActividadEntidad> typedQuery = entityManager.createNamedQuery("TareaGlosarioActividadEntidad.busca", TareaGlosarioActividadEntidad.class);
        List<TareaGlosarioActividadEntidad> tareaGlosarioEntidadLista = typedQuery.getResultList();
        Assert.assertNotNull(tareaGlosarioEntidadLista);
        Assert.assertFalse(tareaGlosarioEntidadLista.isEmpty());
        for (TareaGlosarioActividadEntidad tareaGlosarioEntidad : tareaGlosarioEntidadLista){
            Assert.assertNotNull(tareaGlosarioEntidad);
            TareaGlosarioActividadEntidadPK tareaGlosarioEntidadPK = tareaGlosarioEntidad.getTareaGlosarioActividadEntidadPK();
            Assert.assertNotNull(tareaGlosarioEntidadPK);
            Assert.assertNotNull(tareaGlosarioEntidadPK.getGlosarioActividadEntidad());
            Assert.assertNotNull(tareaGlosarioEntidadPK.getGlosarioActividadEntidad().getGlosarioActividadEntidadPK().getActividadEntidad());
            Assert.assertNotNull(tareaGlosarioEntidadPK.getGlosarioActividadEntidad().getGlosarioActividadEntidadPK().getGlosarioEntidad());
            Assert.assertNotNull(tareaGlosarioEntidadPK.getTareaEntidad());

        }
    }
}
