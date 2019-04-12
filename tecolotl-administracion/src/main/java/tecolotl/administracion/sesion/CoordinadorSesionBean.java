package tecolotl.administracion.sesion;

import tecolotl.administracion.modelo.coordinador.CoordinadorModelo;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class CoordinadorSesionBean {

    @PersistenceContext
    private EntityManager entityManager;

    public List<CoordinadorModelo> busca(String claveCentroTrabajo) {
        return null;
    }
}
