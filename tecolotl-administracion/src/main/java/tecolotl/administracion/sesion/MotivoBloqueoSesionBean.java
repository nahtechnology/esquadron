package tecolotl.administracion.sesion;

import tecolotl.administracion.modelo.escuela.MotivoBloqueoModelo;
import tecolotl.administracion.persistencia.entidad.MotivoBloqueoEntidad;
import tecolotl.nucleo.sesion.CatalogoSesionBean;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class MotivoBloqueoSesionBean extends CatalogoSesionBean<MotivoBloqueoModelo, MotivoBloqueoEntidad> {

    /**
     * Busca motivo bloqueo ignorando los motivos pasados como parámetros
     * @param motivoEvitado Motivos a ser ignorados
     * @return Coleción de {@link MotivoBloqueoModelo}
     */
    public List<MotivoBloqueoModelo> busca(String motivoEvitado) {
        return entityManager.createNamedQuery("MotivoBloqueoEntidad.busca", MotivoBloqueoEntidad.class)
                .setParameter("valor", motivoEvitado).getResultList().stream().map(MotivoBloqueoModelo::new).collect(Collectors.toList());
    }

}
