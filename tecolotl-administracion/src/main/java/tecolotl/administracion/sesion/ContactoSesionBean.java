package tecolotl.administracion.sesion;

import tecolotl.administracion.modelo.escuela.ContactoModelo;
import tecolotl.administracion.persistencia.entidad.ContactoEntidad;
import tecolotl.administracion.persistencia.entidad.ContactoEntidadPK;
import tecolotl.administracion.persistencia.entidad.EscuelaEntidad;
import tecolotl.administracion.persistencia.entidad.TipoContactoEntidad;
import tecolotl.administracion.validacion.escuela.ContactoLlavePrimariaValidacion;
import tecolotl.administracion.validacion.escuela.ContactoNuevoValidacion;
import tecolotl.nucleo.herramienta.ValidadorSessionBean;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Manejo de los contactos de una escuela
 * @author Antonio Francisco Alonso Valerdi
 * @since 0.1
 */
@Stateless
public class ContactoSesionBean {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private ValidadorSessionBean validadorSessionBean;

    @Inject
    private Logger logger;

    /**
     * Busca todos los contactos de una escuela.
     * @param claveCentrotrabajo Clave Centro de Trabajo de la escuela a buscar los contactos.
     * @return Coleccióon con los contactos encontrados, vacio en caso de no existir.
     */
    public List<ContactoModelo> busca(@NotNull @Size(min = 10, max = 14) String claveCentrotrabajo) {
        logger.fine("Buscando contacto con la clave centro de trabajo:".concat(claveCentrotrabajo));
        List<ContactoModelo> contactoModeloLista = new ArrayList<>();
        TypedQuery<ContactoEntidad> typedQuery = entityManager.createNamedQuery("ContactoEntidad.buscaCCT", ContactoEntidad.class);
        typedQuery.setParameter("claveCentroTrabajo", claveCentrotrabajo);
        for (ContactoEntidad contactoEntidad : typedQuery.getResultList()) {
            contactoModeloLista.add(new ContactoModelo(contactoEntidad));
        }
        logger.finer("Número de escuelas encontradas:".concat(String.valueOf(contactoModeloLista.size())));
        if (logger.isLoggable(Level.FINEST)) {
            final StringBuilder stringBuilder = new StringBuilder();
            contactoModeloLista.forEach(contactoModelo -> stringBuilder.append(contactoModelo.toString()).append(','));
            logger.finest(stringBuilder.toString());
        }
        return contactoModeloLista;
    }

    /**
     * Inserta una contacto. El número generado se inserta automaticamente
     * @param contactoModelo Datos del nuevo contacto.
     * @exception javax.validation.ConstraintViolationException En caso de que algunos de los campos no sean validos para
     * la insercción, diche excepcion contiene una colección detalla de los campos que no pasaron.
     */
    public void inserta(@NotNull ContactoModelo contactoModelo) {
        validadorSessionBean.validacion(contactoModelo, ContactoNuevoValidacion.class);
        logger.fine(contactoModelo.toString());
        ContactoEntidad contactoEntidad = new ContactoEntidad(creaLlavePrimaria(contactoModelo));
        contactoEntidad.setNombre(contactoModelo.getNombre());
        contactoEntidad.setTelefono(contactoModelo.getTelefono());
        contactoEntidad.setCorreoElectronico(contactoModelo.getCorreoElectronico());
        contactoEntidad.setTipoContactoEntidad(new TipoContactoEntidad(contactoModelo.getTipoContactoModelo().getClave()));
        entityManager.persist(contactoEntidad);
        logger.fine("Contacto guardado:".concat(contactoEntidad.toString()));
        contactoModelo.setContador(contactoEntidad.getContactoEntidadPK().getContador());
    }

    /**
     * Elimina una escuela.
     * @param contactoModelo Datos de la escuela a ser eliminado.
     * @return Número de elementos modificados.
     * @exception javax.validation.ConstraintViolationException En caso de que los elementos: clave centro de trabajo, tipo contacto
     * y contador no existan
     */
    public int elimina(@NotNull ContactoModelo contactoModelo) {
        validadorSessionBean.validacion(contactoModelo, ContactoLlavePrimariaValidacion.class);
        logger.fine(contactoModelo.toString());
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaDelete<ContactoEntidad> criteriaDelete = criteriaBuilder.createCriteriaDelete(ContactoEntidad.class);
        Root<ContactoEntidad> root = criteriaDelete.from(ContactoEntidad.class);
        Predicate predicate = criteriaBuilder.equal(root.get("contactoEntidadPK"), creaLlavePrimaria(contactoModelo));
        criteriaDelete.where(predicate);
        return entityManager.createQuery(criteriaDelete).executeUpdate();
    }

    /**
     * Actualiza los datos de un contacto.
     * @param contactoModelo Datos a ser actualizados.
     * @return Número de elementos modificados.
     */
    public int actualiza(@NotNull @Valid ContactoModelo contactoModelo) {
        logger.fine(contactoModelo.toString());
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaUpdate<ContactoEntidad> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(ContactoEntidad.class);
        Root<ContactoEntidad> root = criteriaUpdate.from(ContactoEntidad.class);
        Predicate predicate = criteriaBuilder.equal(root.get("contactoEntidadPK"), creaLlavePrimaria(contactoModelo));
        criteriaUpdate.set(root.get("nombre"), contactoModelo.getNombre());
        criteriaUpdate.set(root.get("telefono"), contactoModelo.getTelefono());
        criteriaUpdate.set(root.get("correoElectronico"), contactoModelo.getCorreoElectronico());
        criteriaUpdate.set(root.get("tipoContactoEntidad"), new TipoContactoEntidad(contactoModelo.getTipoContactoModelo().getClave()));
        criteriaUpdate.where(predicate);
        return entityManager.createQuery(criteriaUpdate).executeUpdate();
    }

    /**
     * Generamos una llave primaria del tipo {@link ContactoEntidadPK}
     * @param contactoModelo Datos originarios para crear la llave primaria
     * @return Llave primaria
     */
    private ContactoEntidadPK creaLlavePrimaria(ContactoModelo contactoModelo) {
        ContactoEntidadPK contactoEntidadPK = new ContactoEntidadPK();
        contactoEntidadPK.setEscuelaEntidad(new EscuelaEntidad(contactoModelo.getClaveCentroTrabajo()));
        contactoEntidadPK.setContador(contactoModelo.getContador());
        logger.fine(contactoEntidadPK.toString());
        return contactoEntidadPK;
    }
}
