package tecolotl.administracion.sesion;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import tecolotl.administracion.modelo.AdministradorModelo;
import tecolotl.administracion.modelo.coordinador.CoordinadorModelo;
import tecolotl.administracion.modelo.escuela.ContactoModelo;
import tecolotl.administracion.validacion.direccion.ColoniaNuevaValidacion;
import tecolotl.administracion.validacion.escuela.ContactoLlavePrimariaValidacion;
import tecolotl.nucleo.herramienta.ValidadorSessionBean;
import tecolotl.nucleo.modelo.CatalogoModelo;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;
import tecolotl.nucleo.sesion.CatalogoSesionBean;
import tecolotl.nucleo.validacion.CatalogoNuevoValidacion;

import javax.inject.Inject;

@RunWith(Arquillian.class)
public class AdministradorSesionBeanTest {

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                //nucleo
                .addPackage(ValidadorSessionBean.class.getPackage())
                .addPackage(CatalogoModelo.class.getPackage())
                .addPackage(CatalagoEntidad.class.getPackage())
                .addPackage(CatalogoSesionBean.class.getPackage())
                .addPackage(CatalogoNuevoValidacion.class.getPackage())
                //administracion
                .addPackage(CoordinadorModelo.class.getPackage())
                .addPackage(ContactoModelo.class.getPackage())
                .addPackage(ContactoSesionBean.class.getPackage())
                .addClass(AdministradorModelo.class)
                .addClass(ColoniaNuevaValidacion.class)
                .addPackage(ContactoLlavePrimariaValidacion.class.getPackage())
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private AdministradorSesionBean administradorSesionBean;

    @Test
    public void busca() {
        administradorSesionBean.busca("noldi:0", true);
    }

}
