package tecolotl.administracion.sesion;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import tecolotl.administracion.modelo.escuela.ContactoModelo;
import tecolotl.administracion.modelo.escuela.EscuelaBaseModelo;
import tecolotl.administracion.modelo.escuela.TipoContactoModelo;
import tecolotl.administracion.persistencia.entidad.CoordinadorEntidad;
import tecolotl.administracion.persistencia.entidad.EscuelaEntidad;
import tecolotl.administracion.validacion.direccion.ColoniaNuevaValidacion;
import tecolotl.administracion.validacion.escuela.ContactoLlavePrimariaValidacion;
import tecolotl.administracion.validacion.escuela.ContactoNuevoValidacion;
import tecolotl.nucleo.herramienta.LoggerProducer;
import tecolotl.nucleo.herramienta.ValidadorSessionBean;
import tecolotl.nucleo.modelo.CatalogoModelo;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;
import tecolotl.nucleo.persistencia.entidad.PersonaEntidad;
import tecolotl.nucleo.sesion.CatalogoSesionBean;

import javax.inject.Inject;
import java.util.List;

@RunWith(Arquillian.class)
public class ContactoSesionBeanTest {

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(ContactoSesionBean.class, ContactoModelo.class, LoggerProducer.class, ValidadorSessionBean.class,
                        CatalagoEntidad.class, CoordinadorEntidad.class, PersonaEntidad.class, ContactoLlavePrimariaValidacion.class,
                        ContactoNuevoValidacion.class, ColoniaNuevaValidacion.class)
                .addPackage(EscuelaSesionBean.class.getPackage())
                .addPackage(EscuelaEntidad.class.getPackage())
                .addPackage(EscuelaBaseModelo.class.getPackage())
                .addPackage(CatalogoModelo.class.getPackage())
                .addPackage(MotivoBloqueoSesionBean.class.getPackage())
                .addPackage(CatalogoSesionBean.class.getPackage())
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private ContactoSesionBean contactoSesionBean;

    @Test
    public void inserta() {
        ContactoModelo contactoModelo = new ContactoModelo();
        contactoModelo.setClaveCentroTrabajo("0000000000");
        contactoModelo.setTipoContactoModelo(new TipoContactoModelo((short)1));
        contactoModelo.setNombre("Jose Ismael Gozalez Sontecomani");
        contactoModelo.setTelefono("2226348142");
        contactoModelo.setCorreoElectronico("correo@servidor.com");
        contactoSesionBean.inserta(contactoModelo);
    }

    @Test
    public void busca() {
        List<ContactoModelo> contactoModeloLista = contactoSesionBean.busca("0000000000");
        Assert.assertNotNull(contactoModeloLista);
        Assert.assertFalse(contactoModeloLista.isEmpty());
        for (ContactoModelo contactoModelo : contactoModeloLista) {
            Assert.assertNotNull(contactoModelo);
            Assert.assertNotNull(contactoModelo.getClaveCentroTrabajo());
            Assert.assertNotNull(contactoModelo.getTipoContactoModelo());
            Assert.assertNotNull(contactoModelo.getTipoContactoModelo().getValor());
            Assert.assertNotNull(contactoModelo.getContador());
            Assert.assertNotNull(contactoModelo.getCorreoElectronico());
            Assert.assertNotNull(contactoModelo.getNombre());
            Assert.assertNotNull(contactoModelo.getTelefono());
        }
    }

    @Test
    public void elimina() {
        ContactoModelo contactoModelo = new ContactoModelo();
        contactoModelo.setTipoContactoModelo(new TipoContactoModelo((short)1));
        contactoModelo.setContador((short)1);
        contactoModelo.setClaveCentroTrabajo("0000000000");
        int modificados = contactoSesionBean.elimina(contactoModelo);
        Assert.assertFalse(modificados == 0);
    }

    @Test
    public void actualiza() {
        ContactoModelo contactoModelo = new ContactoModelo();
        contactoModelo.setTipoContactoModelo(new TipoContactoModelo((short)1));
        contactoModelo.setContador((short)2);
        contactoModelo.setClaveCentroTrabajo("0000000000");
        contactoModelo.setNombre("Jose Emanuel lopez");
        contactoModelo.setTelefono("2229338844");
        contactoModelo.setCorreoElectronico("correo2@servidor.com");
        int modificados = contactoSesionBean.actualiza(contactoModelo);
        Assert.assertFalse(modificados == 0);
    }
}
