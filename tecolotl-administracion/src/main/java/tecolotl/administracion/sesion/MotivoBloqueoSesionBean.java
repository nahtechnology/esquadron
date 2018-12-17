package tecolotl.administracion.sesion;

import tecolotl.administracion.dto.MotivoBloqueoDto;
import tecolotl.administracion.persistencia.entidad.MotivoBloqueoEntidad;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Stateless(name = "MotivoBloqueoSesionEJB")
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
}
