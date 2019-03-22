package tecolotl.administracion.sesion;

import tecolotl.administracion.modelo.direccion.ColoniaModelo;
import tecolotl.administracion.modelo.direccion.DireccionModelo;
import tecolotl.administracion.persistencia.entidad.ColoniaEntidad;
import tecolotl.administracion.persistencia.entidad.MunicipioEntidad;
import tecolotl.administracion.validacion.direccion.ColoniaNuevaValidacion;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@Stateless
public class DireccionSesionBean {

    @Inject
    private Logger logger;

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private Validator validator;

    /**
     * Busca el municipio y el estado al que pertence una colonia
     * @param idColonia Identificador de la colonia a buscar
     * @return {@link DireccionModelo} con los datos localizados, nulo en caso de no existir la colonia
     * @exception EJBException En caso de no existir la colonia
     */
    public DireccionModelo busca(@NotNull Integer idColonia) {
        logger.fine("Buscando por id:".concat(idColonia.toString()));
        DireccionModelo direccionModelo = new DireccionModelo();
        TypedQuery<ColoniaEntidad> typedQuery = entityManager.createNamedQuery("ColoniaEntidad.buscaMunicipioEstado", ColoniaEntidad.class);
        typedQuery.setParameter("id", idColonia);
        ColoniaEntidad coloniaEntidad = typedQuery.getSingleResult();
        direccionModelo.setMunicipio(coloniaEntidad.getMunicipio().getNombre());
        direccionModelo.setEstado(coloniaEntidad.getMunicipio().getEstado().getNombre());
        return direccionModelo;
    }

    /**
     * Busca las colonias que corresponden a un código postal
     * @param codigoPostal Código Postal de las colonias a buscar
     * @return {@link DireccionModelo} con los datos localizados, nulo en caso de no existir la colonia
     */
    public DireccionModelo busca(@NotNull String codigoPostal) {
        logger.fine(codigoPostal);
        DireccionModelo direccionModelo = null;
        TypedQuery<ColoniaEntidad> typedQuery = entityManager.createNamedQuery("ColoniaEntidad.buscaCodigoPostal", ColoniaEntidad.class);
        typedQuery.setParameter("codigoPostal", codigoPostal);
        List<ColoniaEntidad> coloniaEntidadLista = typedQuery.getResultList();
        logger.finer("Elementos encontrados:".concat(String.valueOf(coloniaEntidadLista.size())));
        if (coloniaEntidadLista != null && !coloniaEntidadLista.isEmpty()) {
            direccionModelo = new DireccionModelo();
            List<ColoniaModelo> coloniaModeloLista = new ArrayList<>();
            for (ColoniaEntidad coloniaEntidad : coloniaEntidadLista) {
                coloniaModeloLista.add(new ColoniaModelo(coloniaEntidad));
            }
            direccionModelo.setColoniaModeloCollection(coloniaModeloLista);
        }
        return direccionModelo;
    }

    /**
     * Inserta una colonia.
     * @param coloniaModelo Datos de la colonia a ser insertador.
     * @param idMunicipio Identificador del municipio relacionado a esta colonia.
     * @exception ConstraintViolationException En caso de algunos de los campos no fueron validados para la creación de
     * una nueva colonia.
     */
    public Integer inserta(@NotNull ColoniaModelo coloniaModelo, @NotNull @Min(0) Integer idMunicipio) {
        Set<ConstraintViolation<ColoniaModelo>> constraintViolationConjunto = validator.validate(coloniaModelo, ColoniaNuevaValidacion.class);
        if (!constraintViolationConjunto.isEmpty()) {
            throw new ConstraintViolationException(constraintViolationConjunto);
        }
        logger.fine("Colonia a insertar:".concat(coloniaModelo.toString()).concat(" con municipio:").concat(idMunicipio.toString()));
        ColoniaEntidad coloniaEntidad = new ColoniaEntidad(coloniaModelo.getId());
        coloniaEntidad.setNombre(coloniaModelo.getNombre());
        coloniaEntidad.setCodigoPostal(coloniaModelo.getCodigoPostal());
        coloniaEntidad.setMunicipio(new MunicipioEntidad(idMunicipio));
        entityManager.persist(coloniaEntidad);
        return coloniaEntidad.getId();
    }

    /**
     * Actualiza los datos de una colonia.
     * @param coloniaModelo Datos de la colonia a actualizar.
     * @return Número de elementos modificados, cero en caso de no existir.
     */
    public int actualiza(@NotNull @Valid ColoniaModelo coloniaModelo) {
        logger.fine("Actualiza:".concat(coloniaModelo.toString()));
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaUpdate<ColoniaEntidad> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(ColoniaEntidad.class);
        Root<ColoniaEntidad> root = criteriaUpdate.from(ColoniaEntidad.class);
        Predicate predicate = criteriaBuilder.equal(root.get("id"), coloniaModelo.getId());
        criteriaUpdate.set(root.get("codigoPostal"), coloniaModelo.getCodigoPostal());
        criteriaUpdate.set(root.get("nombre"), coloniaModelo.getNombre());
        criteriaUpdate.where(predicate);
        return entityManager.createQuery(criteriaUpdate).executeUpdate();
    }


}