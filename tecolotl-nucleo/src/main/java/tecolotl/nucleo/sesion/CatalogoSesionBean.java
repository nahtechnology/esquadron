package tecolotl.nucleo.sesion;

import tecolotl.nucleo.modelo.CatalogoModelo;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CatalogoSesionBean<M extends CatalogoModelo, E extends CatalagoEntidad> {

    @PersistenceContext
    protected EntityManager entityManager;

    @Inject
    private Logger logger;

    private Class<M> catalogoModelo;
    private Class<E> catalogoEntidad;

    @PostConstruct
    protected void init() {
        Type type = getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = null;
        while (parameterizedType == null) {
            if (type instanceof ParameterizedType) {
                parameterizedType = (ParameterizedType)type;
            } else {
                type = ((Class<?>)type).getGenericSuperclass();
            }
        }
        catalogoModelo = (Class<M>) parameterizedType.getActualTypeArguments()[0];
        catalogoEntidad = (Class<E>) parameterizedType.getActualTypeArguments()[1];
    }

    /**
     * Busca un catálogo por si identificado.
     * @param clave Identificador a ser buscado, No puede ser nulo o mínimo a cero.
     * @return {@link M} Con los datos recuperados
     * @throws ReflectiveOperationException En caso de no poder llamar los metodos,
     */
    public M busca(@NotNull @Min(0) Short clave) {
        M m = null;
        logger.fine(clave.toString());
        E e = entityManager.find(catalogoEntidad, clave);
        try {
            m = catalogoModelo.getConstructor(catalogoEntidad).newInstance(e);
        } catch (ReflectiveOperationException ex) {
            logger.log(Level.SEVERE, "No se puede crear el catalogo por:".concat(ex.getMessage()), ex);
        }
        return m;
    }

    /**
     * Busca todos los elementos de una catálogo
     * @return Una colección con los elementos encontrados, coleccion vacia en caso de no existir
     * @throws ReflectiveOperationException En caso de no poder llamar al constructor
     */
    public List<M> busca() {
        List<M> modeloLista = new ArrayList<>();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(catalogoEntidad);
        Root<E> root = criteriaQuery.from(catalogoEntidad);
        criteriaQuery.select(root);
        List<E> catalogoEntidadLista = entityManager.createQuery(criteriaQuery).getResultList();
        try {
            for (E e : catalogoEntidadLista) {
                modeloLista.add(catalogoModelo.getConstructor(catalogoEntidad).newInstance(e));
            }
        } catch (ReflectiveOperationException ex) {
            logger.log(Level.SEVERE, "No se puede crear el catalogo por:".concat(ex.getMessage()), ex);
        }
        return modeloLista;
    }

    /**
     * Actualiza la valor de un elemento dentro de los catálogos
     * @param catalogoModelo Datos a ser actualizados
     * @return Número de elementos modificados.
     */
    public int actualiza(@NotNull @Valid M catalogoModelo) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaUpdate<E> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(catalogoEntidad);
        Root<E> root = criteriaUpdate.from(catalogoEntidad);
        Predicate predicate = criteriaBuilder.equal(root.get("clave"), catalogoModelo.getClave());
        criteriaUpdate.set(root.get("valor"), catalogoModelo.getValor());
        criteriaUpdate.where(predicate);
        return entityManager.createQuery(criteriaUpdate).executeUpdate();
    }

}
