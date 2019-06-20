package tecolotl.administracion.sesion;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import tecolotl.administracion.modelo.coordinador.CoordinadorModelo;
import tecolotl.administracion.modelo.coordinador.CoordinadorMotivoBloqueoModelo;
import tecolotl.administracion.modelo.direccion.ColoniaModelo;
import tecolotl.administracion.modelo.escuela.EscuelaBaseModelo;
import tecolotl.administracion.persistencia.entidad.ColoniaEntidad;
import tecolotl.administracion.validacion.direccion.ColoniaNuevaValidacion;
import tecolotl.administracion.validacion.escuela.ContactoLlavePrimariaValidacion;
import tecolotl.nucleo.herramienta.LoggerProducer;
import tecolotl.nucleo.herramienta.ValidadorSessionBean;
import tecolotl.nucleo.modelo.CatalogoModelo;
import tecolotl.nucleo.modelo.PersonaModelo;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;
import tecolotl.nucleo.persistencia.entidad.PersonaEntidad;
import tecolotl.nucleo.validacion.PersonaNuevaValidacion;

import javax.inject.Inject;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class CoordinadorSesionBeanTest {

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackage(ColoniaEntidad.class.getPackage())
                .addPackage(EscuelaBaseModelo.class.getPackage())
                .addPackage(ColoniaModelo.class.getPackage())
                .addPackage(ContactoLlavePrimariaValidacion.class.getPackage())
                .addPackage(ColoniaNuevaValidacion.class.getPackage())
                .addClasses(CatalagoEntidad.class, CatalogoModelo.class, PersonaEntidad.class,DireccionSesionBean.class,
                        LoggerProducer.class, ColoniaNuevaValidacion.class, CoordinadorSesionBean.class, CoordinadorModelo.class,
                        PersonaModelo.class, ValidadorSessionBean.class, CoordinadorMotivoBloqueoModelo.class, PersonaNuevaValidacion.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private CoordinadorSesionBean coordinadorSesionBean;

    @Test
    @InSequence(value = 2)
    public void buscaFiltrado() {
        List<CoordinadorModelo> coordinadorModeloLista = coordinadorSesionBean.busca("21DBS0029K");
        assertNotNull(coordinadorModeloLista);
        assertFalse(coordinadorModeloLista.isEmpty());
        coordinadorModeloLista.forEach(coordinadorModelo -> {
            assertNotNull(coordinadorModelo);
            assertNotNull(coordinadorModelo.getClaveCentroTrabajo());
            assertNotNull(coordinadorModelo.getContador());
            assertNotNull(coordinadorModelo.getCoordinadorMotivoBloqueoModelo());
            assertNotNull(coordinadorModelo.getCoordinadorMotivoBloqueoModelo().getClave());
            assertNotNull(coordinadorModelo.getNombre());
            assertNotNull(coordinadorModelo.getApellidoPaterno());
            assertNotNull(coordinadorModelo.getApellidoMaterno());
            assertNotNull(coordinadorModelo.getApodo());
            assertNotNull(coordinadorModelo.getContrasenia());
        });
    }

    @Test
    @InSequence(value = 1)
    public void agrega() {
        CoordinadorModelo coordinadorModelo = new CoordinadorModelo();
        coordinadorModelo.setClaveCentroTrabajo("21DBS0029K");
        coordinadorModelo.setCorreoEletronico("edgar@correo.com");
        coordinadorModelo.setNombre("Edgar Fidencio");
        coordinadorModelo.setApellidoPaterno("Aristegui");
        coordinadorModelo.setApellidoMaterno("Zavala");
        coordinadorModelo.setApodo("Fide");
        coordinadorModelo.setContrasenia("contrasenia".getBytes());
        coordinadorSesionBean.agreaga(coordinadorModelo);
        assertNotNull(coordinadorModelo);
    }

    @Test
    @InSequence(value = 3)
    public void cambiaEstatus() {
        CoordinadorModelo coordinadorModelo = new CoordinadorModelo("21DBS0029K", (short)1);
        coordinadorSesionBean.estatus(coordinadorModelo, (short)3);
        assertNotNull(coordinadorModelo);
    }


    @Test
    @InSequence(4)
    public void buscaDetalle() {
        CoordinadorModelo coordinadorModelo = coordinadorSesionBean.busca("21DBS0029K", (short)1);
        assertNotNull(coordinadorModelo);
        assertNotNull(coordinadorModelo.getClaveCentroTrabajo());
        assertNotNull(coordinadorModelo.getContador());
        assertNotNull(coordinadorModelo.getCoordinadorMotivoBloqueoModelo());
        assertNotNull(coordinadorModelo.getCoordinadorMotivoBloqueoModelo().getClave());
        assertNotNull(coordinadorModelo.getCoordinadorMotivoBloqueoModelo().getValor());
        assertNotNull(coordinadorModelo.getNombre());
        assertNotNull(coordinadorModelo.getApellidoPaterno());
        assertNotNull(coordinadorModelo.getApellidoMaterno());
        assertNotNull(coordinadorModelo.getApodo());
        assertNotNull(coordinadorModelo.getContrasenia());
    }

    @Test
    @InSequence(5)
    public void actualiza() {
        CoordinadorModelo coordinadorModelo = new CoordinadorModelo();
        coordinadorModelo.setClaveCentroTrabajo("21DBS0029K");
        coordinadorModelo.setContador((short)1);
        coordinadorModelo.setCorreoEletronico("edgar@correo.comm");
        coordinadorModelo.setNombre("Edgar Fidencioo");
        coordinadorModelo.setApellidoPaterno("Aristeguii");
        coordinadorModelo.setApellidoMaterno("Zavalaa");
        coordinadorModelo.setApodo("Fidee");
        coordinadorModelo.setContrasenia("contraseniaa".getBytes());
        coordinadorSesionBean.actualiza(coordinadorModelo);
        assertNotNull(coordinadorModelo);
    }

    @Test
    @InSequence(6)
    public void elimina() {

    }

}
