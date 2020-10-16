package tecolotl.administracion.persistencia.vista;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import tecolotl.administracion.persistencia.entidad.ContactoEntidad;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class LicenciaTiempoVistaTest {

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackage(ContactoEntidad.class.getPackage())
                .addClass(LicenciaTiempoVista.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void busca() {
        TypedQuery<AlumnoEscuelaVista> typedQuery = entityManager.createNamedQuery("AlumnoEscuelaVista.buscaPorEscuela", AlumnoEscuelaVista.class);
        typedQuery.setParameter("idEscuela", "21PPR0000G");
        List<AlumnoEscuelaVista> alumnoEscuelaVistaLista = typedQuery.getResultList();
        assertNotNull(alumnoEscuelaVistaLista);
        assertFalse(alumnoEscuelaVistaLista.isEmpty());
        for (AlumnoEscuelaVista alumnoEscuelaVista : alumnoEscuelaVistaLista) {
            assertNotNull(alumnoEscuelaVista);
            assertNotNull(alumnoEscuelaVista.getIdGrupo());
            assertNotNull(alumnoEscuelaVista.getGrado());
            assertNotNull(alumnoEscuelaVista.getGrupo());
        }
    }

}
