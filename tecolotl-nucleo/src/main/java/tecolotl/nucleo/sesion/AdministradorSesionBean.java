package tecolotl.nucleo.sesion;

import tecolotl.nucleo.persistencia.entidad.AdministradorEntidad;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class AdministradorSesionBean {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Recupera el nombre completo del administrador
     * @param apodo Apodo a buscar
     * @return Nombre completo del usuario: Nombre apllido materno y apellido paterno
     */
    public String busca(String apodo) {
        TypedQuery<AdministradorEntidad> typedQuery = entityManager.createNamedQuery("AdministradorEntidad.buscaApodo",AdministradorEntidad.class);
        typedQuery.setParameter("apodo", apodo);
        AdministradorEntidad administradorEntidad = typedQuery.getSingleResult();
        return administradorEntidad.getNombre().concat(" ")
                .concat(administradorEntidad.getApellidoPaterno()).concat(" ")
                .concat(administradorEntidad.getApellidoMaterno());
    }
}
