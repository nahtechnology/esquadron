package tecolotl.administracion.sesion;


import tecolotl.administracion.modelo.AdministradorModelo;
import tecolotl.administracion.persistencia.entidad.AdministradorEntidad;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

@Stateless
public class AdministradorSesionBean {

    @PersistenceContext
    private EntityManager entityManager;

    public AdministradorModelo busca(@NotNull String apodo, boolean galaxia) {
        TypedQuery<AdministradorEntidad> typedQuery = entityManager.createNamedQuery("AdministradorEntidad.buscaApodo", AdministradorEntidad.class);
        typedQuery.setParameter("apodo", galaxia ? apodo.split(",")[0] : apodo );
        return new AdministradorModelo(typedQuery.getSingleResult());
    }
}
