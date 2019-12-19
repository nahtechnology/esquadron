package tecolotl.nucleo.sesion;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.constraints.NotNull;
import java.util.logging.Logger;

@Stateless
public class PersonaSesionBean {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private Logger logger;

    /**
     * Recupera el rol al que pertenece un usuario
     * @param apodo Apodo del usuario
     * @return Nombre del rol al que pertenece el usuario
     */
    public String rol(@NotNull String apodo) {
        Query query = entityManager.createNativeQuery(
                "SELECT 'alumno' AS rol FROM alumno.alumno a WHERE a.apodo = ? UNION SELECT 'profesor' AS rol FROM profesor.profesor p WHERE p.apodo = ?");
        query.setParameter(1, apodo);
        query.setParameter(2, apodo);
        return (String) query.getSingleResult();
    }

}

