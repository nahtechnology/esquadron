package tecolotl.administracion.sesion;

import tecolotl.administracion.dto.ContactoDto;
import tecolotl.administracion.persistencia.entidad.ContactoEntidad;
import tecolotl.administracion.persistencia.entidad.ContactoEntidadPK;
import tecolotl.administracion.persistencia.entidad.EscuelaEntidad;
import tecolotl.administracion.persistencia.entidad.TipoContactoEntidad;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ContactoSesionBean {

    @PersistenceContext
    private EntityManager entityManager;

    public ContactoDto inserta(String claveCentroTrabajo, Short tipoContacto, String nombre, String telefono) {
        ContactoEntidad contactoEntidad = new ContactoEntidad();
        ContactoEntidadPK contactoEntidadPK = new ContactoEntidadPK();
        contactoEntidadPK.setEscuelaEntidad(new EscuelaEntidad(claveCentroTrabajo));
        contactoEntidadPK.setTipoContactoEntidad(new TipoContactoEntidad(tipoContacto));
        contactoEntidad.setNombre(nombre);
        contactoEntidad.setTelefono(telefono);
        contactoEntidad.setContactoEntidadPK(contactoEntidadPK);
        entityManager.persist(contactoEntidad);
        return new ContactoDto(contactoEntidad);
    }

    /**
     * Elimina un contacno
     * @param claveCentroTrabajo Escuela a remover el contacto
     * @param tipoContacto Tipo de contacto a remover
     */
    public void elimina(String claveCentroTrabajo, Short tipoContacto) {
        ContactoEntidadPK contactoEntidadPK = new ContactoEntidadPK();
        contactoEntidadPK.setEscuelaEntidad(new EscuelaEntidad(claveCentroTrabajo));
        contactoEntidadPK.setTipoContactoEntidad(new TipoContactoEntidad(tipoContacto));
        ContactoEntidad contactoEntidad = entityManager.find(ContactoEntidad.class, contactoEntidadPK);
        entityManager.remove(contactoEntidad);
    }
}
