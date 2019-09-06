package tecolotl.alumno.sesion;

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
import tecolotl.alumno.validacion.ActividadNuevaValidacion;
import tecolotl.alumno.validacion.escribir.EscribirLlavePrimariaValidacion;
import tecolotl.alumno.validacion.glosario.GlosarioNuevoValidacion;
import tecolotl.nucleo.herramienta.ValidadorSessionBean;
import tecolotl.nucleo.modelo.CatalogoModelo;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;
import tecolotl.nucleo.sesion.CatalogoSesionBean;
import tecolotl.nucleo.validacion.CatalogoNuevoValidacion;

import javax.inject.Inject;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class MapaMentalSessionBeanTest {

    @Deployment
    public static Archive<?> createDeployment(){
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackage(MapaMentalEntidad.class.getPackage()).addPackage(GlosarioEntidad.class.getPackage())
                .addPackage(ActividadEntidad.class.getPackage())
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

    @Inject
    private MapaMentalSessionBean mapaMentalSessionBean;
/*
    @Test
    public void buscaActividad() {
        List<MapaMentalBaseModelo> mapaMentalBaseModeloLista = mapaMentalSessionBean.busca("0_1NU60qHWs");
        assertNotNull(mapaMentalBaseModeloLista);
        assertFalse(mapaMentalBaseModeloLista.isEmpty());
        mapaMentalBaseModeloLista.forEach(escribirBaseModelo -> {
            assertNotNull(escribirBaseModelo);
            assertNotNull(escribirBaseModelo.getId());
            assertNotNull(escribirBaseModelo.getPregunta());
        });
    }

    @Test
    public void buscaTarea() {
        List<MapaMentalModelo> mapaMentalModeloLista = mapaMentalSessionBean.busca(1);
        assertNotNull(mapaMentalModeloLista);
        assertFalse(mapaMentalModeloLista.isEmpty());
        mapaMentalModeloLista.forEach(mapaMentalModelo -> {
            assertNotNull(mapaMentalModelo);
            assertNotNull(mapaMentalModelo.getPregunta());
            assertNotNull(mapaMentalModelo.getFechaRespuesta());
            assertNotNull(mapaMentalModelo.getTextoRespuesta());
        });
    }

    @Test
    public void respuesta() {
        MapaMentalModelo escribirModelo = new MapaMentalModelo();
        escribirModelo.setId(2);
        escribirModelo.setTextoRespuesta("Respuesta desde el servicio");
        mapaMentalSessionBean.respuesta(escribirModelo, -21, "JcMtWwiyzpU");
    }

    @Test
    public void agregaNuevo() {
        Integer agregar = mapaMentalSessionBean.agregar("Pregunta detonadora?", "0_1NU60qHWs");
        assertNotNull(agregar);
    }

    @Test
    public void asignaActividad() {
        mapaMentalSessionBean.agregar("DNHmujbuC74", -46);
    }*/

}
