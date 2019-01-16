package tecolotl.administracion.sesion;

import tecolotl.administracion.dto.MotivoBloqueoDto;
import tecolotl.administracion.persistencia.entidad.MotivoBloqueoEntidad;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class MotivoBloqueoSesionBean {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Recupera el conjunto completo de los motivos de bloqueo
     * @return Una colección de {@link MotivoBloqueoDto}
     */
    public List<MotivoBloqueoDto> motivoBloque() {
        TypedQuery<MotivoBloqueoEntidad> typedQuery = entityManager.createNamedQuery("MotivoBloqueoEntidad.busca", MotivoBloqueoEntidad.class);
        List<MotivoBloqueoDto> motivoBloqueoDtoLista = new ArrayList<>();
        for (MotivoBloqueoEntidad motivoBloqueoEntidad : typedQuery.getResultList()) {
            motivoBloqueoDtoLista.add(new MotivoBloqueoDto(motivoBloqueoEntidad));
        }
        return motivoBloqueoDtoLista;
    }

    /**
     * Actualiza un motivo de bloqueo
     * @param id Identificador del motivo de bloqueo
     * @param descripcion Nueva descripción del motivo de bloque
     * @return {@link MotivoBloqueoDto} con los datos actualizado
     */
    public MotivoBloqueoDto actualiza(Short id, String descripcion) {
        MotivoBloqueoEntidad motivoBloqueoEntidad = entityManager.find(MotivoBloqueoEntidad.class, id);
        motivoBloqueoEntidad.setDescripcion(descripcion);
        return new MotivoBloqueoDto(motivoBloqueoEntidad);
    }

    /**
     * Actualiza un motivo de bloqueo
     * @param motivoBloqueoDto Objeto con los datos para la actualización
     */
    public void actualiza(MotivoBloqueoDto motivoBloqueoDto) {
        MotivoBloqueoEntidad motivoBloqueoEntidad = entityManager.find(MotivoBloqueoEntidad.class, motivoBloqueoDto.getId());
        motivoBloqueoEntidad.setDescripcion(motivoBloqueoDto.getDescripcion());
    }

    /**
     * Inserta un nuevo motivo de bloqueo
     * @param descripcion Descripción del motivo de bloque
     * @return {@link MotivoBloqueoDto} con los datos insertados
     */
    public MotivoBloqueoDto inserta(String descripcion) {
        MotivoBloqueoEntidad motivoBloqueoEntidad = new MotivoBloqueoEntidad();
        motivoBloqueoEntidad.setDescripcion(descripcion);
        entityManager.persist(motivoBloqueoEntidad);
        return new MotivoBloqueoDto(motivoBloqueoEntidad);
    }
}
