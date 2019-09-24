package tecolotl.alumno.cache;


import tecolotl.alumno.modelo.ActividadModelo;
import tecolotl.alumno.modelo.glosario.GlosarioModelo;
import tecolotl.alumno.sesion.ActividadSesionBean;
import tecolotl.alumno.sesion.GlosarioSesionBean;

import javax.annotation.PostConstruct;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.TreeSet;

@Singleton
public class ImagenesGlosarioCache {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private ActividadSesionBean actividadSesionBean;

    @Inject
    private GlosarioSesionBean glosarioSesionBean;

    private TreeSet<GlosarioModelo> glosarioModeloTreeSet;

    @PostConstruct
    public void inicio() {
        List<String> stringLista = buscaActividades();
    }


    @Lock(value = LockType.READ)
    public GlosarioModelo busca(@NotNull String palabra, Short clase) {
        return null;
    }

    @Lock(value = LockType.WRITE)
    public void llena() {

    }

    private List<String> buscaActividades() {
        return entityManager.createQuery("SELECT a.id FROM ActividadEntidad a", String.class).getResultList();
    }


}
