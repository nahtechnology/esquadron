package tecolotl.administracion.sesion;

import tecolotl.administracion.persistencia.entidad.ContactoEntidad;
import tecolotl.administracion.persistencia.entidad.ContactoEntidadPK;
import tecolotl.administracion.persistencia.entidad.EscuelaEntidad;
import tecolotl.administracion.persistencia.entidad.TipoContactoEntidad;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

@Stateless
public class ContactoSesionBean {

    @PersistenceContext
    private EntityManager entityManager;

/*    public ContactoDto inserta(String claveCentroTrabajo, Short tipoContacto, String nombre, String telefono) {
        ContactoEntidad contactoEntidad = new ContactoEntidad();
        ContactoEntidadPK contactoEntidadPK = new ContactoEntidadPK();
        contactoEntidadPK.setEscuelaEntidad(new EscuelaEntidad(claveCentroTrabajo));
        contactoEntidadPK.setTipoContactoEntidad(new TipoContactoEntidad(tipoContacto));
        contactoEntidad.setNombre(nombre);
        contactoEntidad.setTelefono(telefono);
        contactoEntidad.setContactoEntidadPK(contactoEntidadPK);
        entityManager.persist(contactoEntidad);
        return new ContactoDto(contactoEntidad);
    }*/

    /**
     * Elimina un contacno
     * @param claveCentroTrabajo Escuela a remover el contacto
     * @param tipoContacto Tipo de contacto a remover
     */
    public void elimina(String claveCentroTrabajo, Short tipoContacto) {
        if (claveCentroTrabajo == null || tipoContacto == null) {
            return;
        }
        ContactoEntidad contactoEntidad = entityManager.find(ContactoEntidad.class, creaLlavePrimaria(claveCentroTrabajo, tipoContacto));
        entityManager.remove(contactoEntidad);
    }

    /**
     * Actualiza los datos de un contacto
     * @param claveCentroTrabajo Escuela a la que pertenece el contacto
     * @param tipoContacto Identificador del ripo de contacto
     * @param nombre Nombre del contacto
     * @param telefono Telefono del contacto
     * @return Número de elementos modificados, cero en caso de no realizar modificaciones
     */
    public void actualiza(String claveCentroTrabajo, Short tipoContacto, String nombre, String telefono) {
        if (claveCentroTrabajo == null || tipoContacto == null) {
            return;
        }
        ContactoEntidad contactoEntidad = entityManager.find(ContactoEntidad.class, creaLlavePrimaria(claveCentroTrabajo, tipoContacto));
        contactoEntidad.setTelefono(telefono);
        contactoEntidad.setNombre(nombre);
    }

    private ContactoEntidadPK creaLlavePrimaria(String claveCentroTrabajo, Short tipoContacto) {
        ContactoEntidadPK contactoEntidadPK = new ContactoEntidadPK();
        contactoEntidadPK.setEscuelaEntidad(new EscuelaEntidad(claveCentroTrabajo));
        contactoEntidadPK.setTipoContactoEntidad(new TipoContactoEntidad(tipoContacto));
        return contactoEntidadPK;
    }
}
