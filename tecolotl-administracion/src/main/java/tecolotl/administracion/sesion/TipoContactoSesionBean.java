package tecolotl.administracion.sesion;

import tecolotl.administracion.dto.TipoContactoDto;
import tecolotl.administracion.persistencia.entidad.TipoContactoEntidad;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Stateless
public class TipoContactoSesionBean {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Recupera todos los tipos de contacto en una colección de conjunto, este conjunto ya está integrado en un  árbol
     * {@link TreeSet}
     * @return Colección de {@link TipoContactoDto} los tipos de contacto, vacio en caso de no existir
     */
    public List<TipoContactoDto> busca() {
        List<TipoContactoDto> tipoContactoDtolista = new ArrayList<>();
        TypedQuery<TipoContactoEntidad> typedQuery = entityManager.createNamedQuery("TipoContactoEntidad.busca", TipoContactoEntidad.class);
        List<TipoContactoEntidad> tipoContactoEntidadLista = typedQuery.getResultList();
        for (TipoContactoEntidad tipoContactoEntidad :
                tipoContactoEntidadLista) {
            tipoContactoDtolista.add(new TipoContactoDto(tipoContactoEntidad));
        }
        return  tipoContactoDtolista;
    }

}
