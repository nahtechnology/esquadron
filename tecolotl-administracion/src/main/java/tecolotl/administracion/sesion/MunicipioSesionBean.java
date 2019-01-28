package tecolotl.administracion.sesion;

import tecolotl.administracion.dto.MunicipioDto;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class MunicipioSesionBean {

    @PersistenceContext
    private EntityManager entityManager;

}
