package tecolotl.alumno.entidad.vista;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class TareaAlumnoVistaEntidadTest {

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClass(TareaAlumnoVistaEntidad.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void busca() {
        TypedQuery<TareaAlumnoVistaEntidad> typedQuery =
                entityManager.createNamedQuery("TareaAlumnoVistaEntidad.buscaAlumno", TareaAlumnoVistaEntidad.class);
        typedQuery.setParameter("idAlumno", 1);
        TareaAlumnoVistaEntidad tareaAlumnoVistaEntidad = typedQuery.getSingleResult();
        assertNotNull(tareaAlumnoVistaEntidad);
        assertNotNull(tareaAlumnoVistaEntidad.getTotalTareas());
        assertNotNull(tareaAlumnoVistaEntidad.getGrado());
        assertNotNull(tareaAlumnoVistaEntidad.getGrupo());
        assertNotNull(tareaAlumnoVistaEntidad.getInicio());
        assertNotNull(tareaAlumnoVistaEntidad.getFin());
        assertNotNull(tareaAlumnoVistaEntidad.getNombre());
        assertNotNull(tareaAlumnoVistaEntidad.getApellidoMaterno());
        assertNotNull(tareaAlumnoVistaEntidad.getApellidoPaterno());
    }

}
