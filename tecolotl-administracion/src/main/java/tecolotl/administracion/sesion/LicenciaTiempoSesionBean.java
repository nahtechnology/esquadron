package tecolotl.administracion.sesion;

import tecolotl.administracion.modelo.LicenciaVistaModelo;
import tecolotl.administracion.persistencia.vista.LicenciaTiempoVista;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class LicenciaTiempoSesionBean {

    @PersistenceContext
    private EntityManager entityManager;

    public List<LicenciaVistaModelo> busca(Integer tiempo) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<LicenciaTiempoVista> criteriaQuery = criteriaBuilder.createQuery(LicenciaTiempoVista.class);
        Root<LicenciaTiempoVista> root = criteriaQuery.from(LicenciaTiempoVista.class);
        criteriaQuery.select(root);
        if (tiempo != null) {
            Predicate predicate = criteriaBuilder.lt(root.get("dias"), tiempo);
            criteriaQuery.where(predicate);
        }
        return entityManager.createQuery(criteriaQuery).getResultList().stream().map(LicenciaVistaModelo::new).collect(Collectors.toList());
    }
}
