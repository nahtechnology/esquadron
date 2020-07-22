package tecolotl.administracion.sesion;

import tecolotl.administracion.modelo.LicenciaVistaModelo;
import tecolotl.administracion.persistencia.vista.LicenciaTiempoVista;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class LicenciaTiempoSesionBean {

    @PersistenceContext
    private EntityManager entityManager;

    public List<LicenciaVistaModelo> busca(int tiempo) {
        TypedQuery<LicenciaTiempoVista> typedQuery =
                entityManager.createNamedQuery("LicenciaTiempoVista.buscaMinimoDias", LicenciaTiempoVista.class);
        typedQuery.setParameter("tiempo", tiempo);
        return typedQuery.getResultList().stream().map(LicenciaVistaModelo::new).collect(Collectors.toList());
    }
}
