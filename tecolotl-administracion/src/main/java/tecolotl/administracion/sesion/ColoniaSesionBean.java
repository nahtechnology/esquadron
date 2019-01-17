package tecolotl.administracion.sesion;

import tecolotl.administracion.dto.ColoniaDto;
import tecolotl.administracion.persistencia.entidad.ColoniaEntidad;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless(name = "ColoniaSesionEJB")
public class ColoniaSesionBean {

    private Logger logger = Logger.getLogger(getClass().getName());

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Busca todas las colonias correspondientes a un código postal, en caso de que el parametro sea nulo regresará nulo.
     * En caso de de noexistir el codigo postal regresa un objecto vacio
     * @param codigoPostal Código Postal a ser buscado
     * @return Un objeto con los datos al que pertenece el código postal
     */
    public ColoniaDto busca(String codigoPostal) {
        if (codigoPostal == null) {
            return null;
        }
        TypedQuery<ColoniaEntidad> typedQuery = entityManager.createNamedQuery("ColoniaEntidad.buscaCodigoPostal", ColoniaEntidad.class);
        typedQuery.setParameter("codigoPostal", codigoPostal);
        if (logger.isLoggable(Level.FINE)) {
            ColoniaDto coloniaDto = new ColoniaDto(typedQuery.getResultList());
            logger.fine(coloniaDto.toString());
            return coloniaDto;
        } else {
            return new ColoniaDto(typedQuery.getResultList());
        }
    }

    public void actualiza(Integer id, String nombre) {
        if (id == null || nombre == null) {
            return;
        }
        ColoniaEntidad coloniaEntidad = entityManager.find(ColoniaEntidad.class, id);
        coloniaEntidad.setNombre(nombre);
    }
}