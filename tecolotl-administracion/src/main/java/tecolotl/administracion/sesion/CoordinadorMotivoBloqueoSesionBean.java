package tecolotl.administracion.sesion;

import org.hibernate.validator.constraints.NotEmpty;
import tecolotl.administracion.modelo.coordinador.CoordinadorModelo;
import tecolotl.administracion.modelo.coordinador.CoordinadorMotivoBloqueoModelo;
import tecolotl.administracion.persistencia.entidad.CoordinadorEntidad;
import tecolotl.administracion.persistencia.entidad.CoordinadorMotivoBloqueoEntidad;
import tecolotl.nucleo.sesion.CatalogoSesionBean;

import javax.ejb.Stateless;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class CoordinadorMotivoBloqueoSesionBean extends CatalogoSesionBean<CoordinadorMotivoBloqueoModelo, CoordinadorMotivoBloqueoEntidad> {

    public List<CoordinadorMotivoBloqueoModelo> buscaFiltro(@NotNull @NotEmpty String filtro) {
        return entityManager.createNamedQuery("CoordinadorMotivoBloqueoEntidad.buscaFiltro", CoordinadorMotivoBloqueoEntidad.class).setParameter("tipoCatalogo", filtro)
                .getResultList().stream().map(CoordinadorMotivoBloqueoModelo::new).collect(Collectors.toList());
    }

}
