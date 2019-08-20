package tecolotl.profesor.sesion;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import tecolotl.administracion.modelo.coordinador.CoordinadorModelo;
import tecolotl.administracion.modelo.direccion.ColoniaModelo;
import tecolotl.administracion.modelo.escuela.ContactoModelo;
import tecolotl.administracion.modelo.escuela.EscuelaBaseModelo;
import tecolotl.administracion.modelo.escuela.EscuelaPoblacionModelo;
import tecolotl.administracion.persistencia.entidad.ColoniaEntidad;
import tecolotl.administracion.sesion.ContactoSesionBean;
import tecolotl.administracion.validacion.direccion.ColoniaNuevaValidacion;
import tecolotl.administracion.validacion.escuela.ContactoLlavePrimariaValidacion;
import tecolotl.alumno.entidad.ActividadEntidad;
import tecolotl.alumno.modelo.AlumnoModelo;
import tecolotl.alumno.sesion.AlumnoSesionBean;
import tecolotl.nucleo.herramienta.EncriptadorContrasenia;
import tecolotl.nucleo.herramienta.ValidadorSessionBean;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;
import tecolotl.nucleo.modelo.CatalogoModelo;
import tecolotl.nucleo.sesion.CatalogoSesionBean;
import tecolotl.nucleo.validacion.CatalogoNuevoValidacion;
import tecolotl.profesor.entidad.GrupoAlumnoEntidad;
import tecolotl.profesor.modelo.GrupoAlumnoModelo;
import tecolotl.profesor.modelo.ProfesorDashboardModelo;
import tecolotl.profesor.modelo.ProfesorModelo;
import tecolotl.profesor.validacion.GrupoProfesorValidacion;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

@RunWith(Arquillian.class)
public class ProfesorSesionBeanTest {

    @Deployment
    public static Archive<?> createDeployment(){
        return ShrinkWrap.create(WebArchive.class, "test.war")
                //nucleo
            .addPackage(ValidadorSessionBean.class.getPackage())
            .addPackage(CatalogoModelo.class.getPackage())
            .addPackage(CatalagoEntidad.class.getPackage())
            .addPackage(CatalogoSesionBean.class.getPackage())
            .addPackage(CatalogoNuevoValidacion.class.getPackage())
                //administracion
            .addPackage(CoordinadorModelo.class.getPackage())
            .addPackage(ColoniaModelo.class.getPackage())
            .addPackage(ContactoModelo.class.getPackage())
            .addPackage(ColoniaEntidad.class.getPackage())
            .addPackage(ContactoSesionBean.class.getPackage())
            .addClass(ColoniaNuevaValidacion.class)
            .addPackage(ContactoLlavePrimariaValidacion.class.getPackage())
                //alumno
            .addPackage(ActividadEntidad.class.getPackage())
            .addPackage(AlumnoModelo.class.getPackage())
            .addPackage(AlumnoSesionBean.class.getPackage())
                //profesor
            .addPackage(GrupoAlumnoEntidad.class.getPackage())
            .addPackage(GrupoAlumnoModelo.class.getPackage())
            .addPackage(GrupoAlumnoSesionBean.class.getPackage())
            .addPackage(GrupoProfesorValidacion.class.getPackage())
            .addAsResource("META-INF/persistence.xml")
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private ProfesorSesionBean profesorSesionBean;

    @Inject
    private EncriptadorContrasenia encriptadorContrasenia;

    @Test
    public void busca(){
        List<ProfesorModelo> profesorModeloLista = profesorSesionBean.busca();
        Assert.assertNotNull(profesorModeloLista);
        Assert.assertFalse(profesorModeloLista.isEmpty());
        for (ProfesorModelo profesorModelo : profesorModeloLista){
            Assert.assertNotNull(profesorModelo);
            Assert.assertNotNull(profesorModelo.getId());
            Assert.assertNotNull(profesorModelo.getEscuelaBaseModelo());
        }
    }


    @Test
    public void buscaContrasenia(){
        ProfesorModelo profesorModelo = profesorSesionBean.busca(-38);
        Assert.assertNotNull(profesorModelo);
        Assert.assertNotNull(profesorModelo.getNombre());
        Assert.assertNotNull(profesorModelo.getContrasenia());
        System.out.println("Ya accediste: ");
        String str1 = encriptadorContrasenia.decrypt(profesorModelo.getContrasenia());
        System.out.println("Cieñit0L1ñd0#/5200*[]{}?¿~!¡€".equals(str1)+"\n Lo lograste!!");
        System.out.println(str1);
    }

    @Test
    public void inserta2(){
        ProfesorModelo profesorModelo = new ProfesorModelo();
        profesorModelo.setNombre("Eliminando");
        profesorModelo.setApellidoPaterno("Reyes");
        profesorModelo.setApellidoMaterno("Sanchez");
        profesorModelo.setApodo("Otro mas");
        profesorModelo.setContrasenia(encriptadorContrasenia.encrypt("Cieñit0L1ñd0#/5200*[]{}"));
        profesorModelo.setEscuelaBaseModelo(new EscuelaBaseModelo("21DBS0029K"));
        profesorSesionBean.inserta(profesorModelo);
        Assert.assertNotNull(profesorModelo);
    }

    @Test
    public void inserta(){
        ProfesorModelo profesorModelo = new ProfesorModelo();
        profesorModelo.setNombre("Jesus");
        profesorModelo.setApellidoPaterno("Reyes");
        profesorModelo.setApellidoMaterno("Sanchez");
        profesorModelo.setApodo("El Juanito");
        try{
            profesorModelo.setContrasenia(encriptadorContrasenia.encrypt("123456"));
        }catch(Exception e){}
        profesorModelo.setEscuelaBaseModelo(new EscuelaBaseModelo("21DBS0029K"));
        profesorSesionBean.inserta(profesorModelo);
        Assert.assertNotNull(profesorModelo);
    }

    @Test
    public void actualiza(){
        ProfesorModelo profesorModelo = new ProfesorModelo(-38);
        profesorModelo.setNombre("Eliminando");
        profesorModelo.setApellidoPaterno("Reyes");
        profesorModelo.setApellidoMaterno("Sanchez");
        profesorModelo.setApodo("Don Levantones");
        profesorModelo.setContrasenia(encriptadorContrasenia.encrypt("Cieñit0L1ñd0#/5200*[]{}?¿~!¡€"));
        EscuelaBaseModelo escuelaBaseModelo = new EscuelaBaseModelo("21DBS0029K");
        escuelaBaseModelo.setNombre("AGUSTIN MELGAR");
        escuelaBaseModelo.setDomicilio("RAFAEL CAÑEDO BENITEX");
        escuelaBaseModelo.setNumeroExterior("12234");
        profesorModelo.setEscuelaBaseModelo(escuelaBaseModelo);

        int elementosModificados = profesorSesionBean.actualiza(profesorModelo);
        Assert.assertFalse(elementosModificados == 0);
    }

    @Test
    public void elimina(){
        Integer profesorEliminado = 0;
        profesorEliminado = profesorSesionBean.elimina(-39);
        Assert.assertNotNull(profesorEliminado);
        Assert.assertFalse(profesorEliminado == 0);
    }

}
