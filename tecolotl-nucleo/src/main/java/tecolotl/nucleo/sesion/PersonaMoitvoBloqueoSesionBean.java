package tecolotl.nucleo.sesion;

import tecolotl.nucleo.modelo.PersonaMotivoBloqueoModelo;
import tecolotl.nucleo.persistencia.entidad.PersonaMotivoBloqueoEntidad;

import javax.ejb.Stateless;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class PersonaMoitvoBloqueoSesionBean extends CatalogoSesionBean<PersonaMotivoBloqueoModelo, PersonaMotivoBloqueoEntidad>{

    public List<PersonaMotivoBloqueoModelo> busca(@NotNull @Size(min = 4, max = 30) String motivoEvitado) {
         return entityManager.createNamedQuery("PersonaMotivoBloqueo.buscaFiltro", PersonaMotivoBloqueoEntidad.class)
                 .setParameter("valor", motivoEvitado).getResultList().stream().map(PersonaMotivoBloqueoModelo::new).collect(Collectors.toList());
    }
}
