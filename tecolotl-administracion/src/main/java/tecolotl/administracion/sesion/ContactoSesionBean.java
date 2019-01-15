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

    public ContactoDto insertar(String claveCentroTrabajo, int tipoContracto, String nombre, String telefono) {
        ContactoEntidad contactoEntidad = new ContactoEntidad();
        contactoEntidad.setEscuelaEntidad(new EscuelaEntidad(claveCentroTrabajo));
        contactoEntidad.setNombre(nombre);
        contactoEntidad.setTelefono(telefono);
        contactoEntidad.setTipoContactoEntidad(new TipoContactoEntidad(tipoContracto));
        entityManager.persist(contactoEntidad);
        return new ContactoDto(contactoEntidad);
    }
}
