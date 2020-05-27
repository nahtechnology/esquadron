package tecolotl.nucleo.sesion;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
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
        query.setParameter(1, apodo.split(",")[0]);
        query.setParameter(2, apodo.split(",")[0]);
        try {
            return (String) query.getSingleResult();
        } catch (NoResultException exception) {
            return null;
        }

    }

    /**
     * Busca todos los apodos de una escuela
     * @param claveCentroTrabajo Clave dentro de trabajo de la escuela a ser buscado
     * @return Colección de cadena de caracteres {@link String}
     */
    public List<String> buscaApodo(@NotNull @Size(min = 10, max = 14) String claveCentroTrabajo) {
        Query query = entityManager.createNativeQuery("SELECT p.apodo FROM profesor.profesor p WHERE id_escuela = ? UNION " +
                "SELECT a.apodo FROM profesor.grupo g JOIN profesor.grupo_alumno ga ON g.id = ga.id_grupo JOIN alumno.alumno a ON ga.id_alumno = a.id WHERE g.id_escuela = ?");
        query.setParameter(1, claveCentroTrabajo).setParameter(2, claveCentroTrabajo);
        return (List<String>)query.getResultList();
    }



}

