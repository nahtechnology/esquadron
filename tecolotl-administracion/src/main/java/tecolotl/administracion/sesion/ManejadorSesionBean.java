package tecolotl.administracion.sesion;

import tecolotl.administracion.modelo.UsuarioSesionModelo;

import javax.ejb.Singleton;
import java.util.concurrent.ConcurrentMap;

@Singleton
public class ManejadorSesionBean {

    ConcurrentMap<String, UsuarioSesionModelo> concurrentMap;

    public void inicion() {

    }

}
