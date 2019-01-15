package tecolotl.administracion.sesion;

import tecolotl.administracion.dto.ContactoDto;
import tecolotl.administracion.persistencia.entidad.ContactoEntidad;
import tecolotl.administracion.persistencia.entidad.EscuelaEntidad;
import tecolotl.administracion.persistencia.entidad.TipoContactoEntidad;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ContactoSesionBean {

    @PersistenceContext
    private EntityManager entityManager;

    public ContactoDto inserta(String claveCentroTrabajo, int tipoContacto, String nombre, String telefono) {
        ContactoEntidad contactoEntidad = new ContactoEntidad();
        contactoEntidad.setEscuelaEntidad(new EscuelaEntidad(claveCentroTrabajo));
        contactoEntidad.setNombre(nombre);
        contactoEntidad.setTelefono(telefono);
        contactoEntidad.setTipoContactoEntidad(new TipoContactoEntidad(tipoContacto));
        entityManager.persist(contactoEntidad);
        return new ContactoDto(contactoEntidad);
    }

    /**
     * Elimina un contacno
     * @param claveCentroTrabajo Escuela a remover el contacto
     * @param tipoContacto Tipo de contacto a remover
     */
    public void elimina(String claveCentroTrabajo, int tipoContacto) {

    }
}
