package tecolotl.administracion.sesion;

import tecolotl.administracion.modelo.direccion.ColoniaModelo;
import tecolotl.administracion.modelo.direccion.DireccionModelo;
import tecolotl.administracion.modelo.direccion.MunicipioModelo;
import tecolotl.administracion.persistencia.entidad.ColoniaEntidad;
import tecolotl.administracion.validacion.direccion.ColoniaNuevaValidacion;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.validation.Validator;
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
     */
    public DireccionModelo busca(@NotNull Integer idColonia) {
        logger.fine("Buscando por id:".concat(idColonia.toString()));
        DireccionModelo direccionModelo = null;
        TypedQuery<ColoniaEntidad> typedQuery = entityManager.createNamedQuery("ColoniaEntidad.buscaMunicipioEstado", ColoniaEntidad.class);
        typedQuery.setParameter("id", idColonia);
        ColoniaEntidad coloniaEntidad = typedQuery.getSingleResult();
        if (coloniaEntidad != null) {
            direccionModelo = new DireccionModelo();
            direccionModelo.setMunicipio(coloniaEntidad.getMunicipio().getNombre());
            direccionModelo.setEstado(coloniaEntidad.getMunicipio().getEstado().getNombre());
        }
        return direccionModelo;
    }

    public DireccionModelo busca(@NotNull String codigoPostal) {
        DireccionModelo direccionModelo = null;
        TypedQuery<ColoniaEntidad> typedQuery = entityManager.createNamedQuery("ColoniaEntidad.buscaCodigoPostal", ColoniaEntidad.class);
        List<ColoniaEntidad> coloniaEntidadLista = typedQuery.getResultList();
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

    public void inserta(@NotNull ColoniaModelo coloniaModelo, @NotNull MunicipioModelo municipioModelo) {
        Set<ConstraintViolation<ColoniaModelo>> constraintViolationConjunto = validator.validate(coloniaModelo, ColoniaNuevaValidacion.class);
        if (!constraintViolationConjunto.isEmpty()) {
            throw new ConstraintViolationException(constraintViolationConjunto);
        }
    }

}