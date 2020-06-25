package tecolotl.nucleo.sesion;

import tecolotl.nucleo.modelo.UsuarioSesionModelo;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
public class SesionControlSingleton {

    private Map<String, UsuarioSesionModelo> mapa = new ConcurrentHashMap<>();

    public void inicio(String IdSesion, Integer galaxia, String apodo, UsuarioSesionModelo.Tipo tipo, Date momento, UUID id) {
        UsuarioSesionModelo usuarioSesionModelo = new UsuarioSesionModelo();
        usuarioSesionModelo.setApodo(apodo);
        usuarioSesionModelo.setGalaxia(galaxia);
        usuarioSesionModelo.setMomento(momento);
        usuarioSesionModelo.setTipo(tipo);
        usuarioSesionModelo.setId(id);
        mapa.put(IdSesion, usuarioSesionModelo);
    }

    public UsuarioSesionModelo cierre(String idSesion) {
        UsuarioSesionModelo usuarioSesionModelo = mapa.get(idSesion);
        if (usuarioSesionModelo != null) mapa.remove(usuarioSesionModelo);
        return usuarioSesionModelo;
    }

}
