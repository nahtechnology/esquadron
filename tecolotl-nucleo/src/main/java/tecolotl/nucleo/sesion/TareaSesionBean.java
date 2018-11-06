package tecolotl.nucleo.sesion;

import tecolotl.nucleo.persistencia.entidad.TareaEntidad;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class TareaSesionBean {

    @PersistenceContext
    private EntityManager entityManager;

    public List<TareaEntidad> busca() {
        TypedQuery<TareaEntidad> typedQuery = entityManager.createQuery("SELECT t FROM TareaEntidad t", TareaEntidad.class);
        return typedQuery.getResultList();
    }
}
