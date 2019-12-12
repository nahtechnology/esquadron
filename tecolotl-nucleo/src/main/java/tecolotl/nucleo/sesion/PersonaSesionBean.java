package tecolotl.nucleo.sesion;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.constraints.NotNull;

@Stateless
public class PersonaSesionBean {

    @PersistenceContext
    private EntityManager entityManager;

    public String rol(@NotNull String apodo) {
        Query query = entityManager.createNativeQuery(
                "SELECT 'alumno' AS rol FROM alumno.alumno a WHERE a.apodo = ? UNION SELECT 'profesor' AS rol FROM profesor.profesor p WHERE p.apodo = ?",
                String.class);
        query.setParameter(1, apodo);
        query.setParameter(2, apodo);
        return (String) query.getSingleResult();
    }

}

