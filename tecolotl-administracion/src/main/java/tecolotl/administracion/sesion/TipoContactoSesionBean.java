package tecolotl.administracion.sesion;

import tecolotl.administracion.dto.TipoContactoDto;
import tecolotl.administracion.persistencia.entidad.TipoContactoEntidad;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class TipoContactoSesionBean {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Recupera todos los tipos de contacto en una colección de conjunto, este conjunto ya está integrado en un  árbol
     * @return Colección de {@link TipoContactoDto} los tipos de contacto, vacio en caso de no existir
     */
    public List<TipoContactoDto> busca() {
        List<TipoContactoDto> tipoContactoDtolista = new ArrayList<>();
        TypedQuery<TipoContactoEntidad> typedQuery = entityManager.createNamedQuery("TipoContactoEntidad.busca", TipoContactoEntidad.class);
        typedQuery.setParameter("descripcion", "Sin tipo");
        List<TipoContactoEntidad> tipoContactoEntidadLista = typedQuery.getResultList();
        for (TipoContactoEntidad tipoContactoEntidad :
                tipoContactoEntidadLista) {
            tipoContactoDtolista.add(new TipoContactoDto(tipoContactoEntidad));
        }
        return  tipoContactoDtolista;
    }

    /**
     * Actualiza un Tipo de Contacto, en caso de que alguno de los campos sean nulos, no se realiza la dicha operación
     * @param id Llave primaria del tipo de contacto
     * @param descripcion Datos a actualizar
     * @return {@link TipoContactoDto} con los datos actualizados, nulo en caso de no actualizar
     */
    public TipoContactoDto actualiza(Short id, String descripcion) {
        if (id == null || descripcion == null) {
            return null;
        }
        TipoContactoEntidad tipoContactoEntidad = entityManager.find(TipoContactoEntidad.class, id);
        tipoContactoEntidad.setDescripcion(descripcion);
        return new TipoContactoDto(tipoContactoEntidad);
    }

    /**
     * Inserta un tipo de contacto, en caso de ser nulo no se inserta la información
     * @param descripcion Tipo de contacto a ser insertado
     * @return {@link TipoContactoDto} Con los datos insertados
     */
    public TipoContactoDto inserta(String descripcion) {
        if (descripcion == null) {
            return null;
        }
        TipoContactoEntidad tipoContactoEntidad = new TipoContactoEntidad();
        tipoContactoEntidad.setDescripcion(descripcion);
        entityManager.persist(tipoContactoEntidad);
        return new TipoContactoDto(tipoContactoEntidad);
    }

}
