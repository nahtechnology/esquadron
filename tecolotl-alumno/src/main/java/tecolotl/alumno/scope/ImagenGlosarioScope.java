package tecolotl.alumno.scope;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.logging.Logger;

@ApplicationScoped
public class ImagenGlosarioScope {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private Logger logger;

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public byte[] imagen(@NotNull @Size(min = 2, max = 20) String palabra, @NotNull Short idClave) {
        logger.fine(palabra);
        logger.fine(idClave.toString());
        TypedQuery<byte[]> typedQuery = entityManager.createNamedQuery("GlosarioEntidad.buscaImagen", byte[].class);
        typedQuery.setParameter("palabra", palabra).setParameter("clave", idClave);
        return typedQuery.getSingleResult();
    }

}
