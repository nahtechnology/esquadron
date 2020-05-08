package tecolotl.alumno.sesion;

import tecolotl.alumno.entidad.NivelLenguajeEntidad;
import tecolotl.alumno.modelo.NivelLenguajeModelo;
import tecolotl.nucleo.sesion.CatalogoSesionBean;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

@Stateless
public class NivelLenguajeSesionBean extends CatalogoSesionBean<NivelLenguajeModelo, NivelLenguajeEntidad> {

    public Short busca(String valor) {
        TypedQuery<Short> typedQuery = entityManager.createNamedQuery("NivelLenguajeEntidad.buscaLlave", Short.class);
        typedQuery.setParameter("valor", valor);
        return typedQuery.getSingleResult();
    }

}
