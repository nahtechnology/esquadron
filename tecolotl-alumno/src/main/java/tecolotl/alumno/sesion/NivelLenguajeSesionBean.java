package tecolotl.alumno.sesion;

import tecolotl.alumno.entidad.NivelLenguajeEntidad;
import tecolotl.alumno.modelo.NivelLenguajeModelo;
import tecolotl.nucleo.sesion.CatalogoSesionBean;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

@Stateless
public class NivelLenguajeSesionBean extends CatalogoSesionBean<NivelLenguajeModelo, NivelLenguajeEntidad> {

    public Integer busca(String valor) {
        TypedQuery<Integer> typedQuery = entityManager.createNamedQuery("NivelLenguajeEntidad.buscaLlave", Integer.class);
        typedQuery.setParameter("valor", valor);
        return typedQuery.getSingleResult();
    }

}
