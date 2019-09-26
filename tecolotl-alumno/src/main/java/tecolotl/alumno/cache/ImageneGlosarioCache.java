package tecolotl.alumno.cache;


import tecolotl.alumno.modelo.ActividadModelo;
import tecolotl.alumno.modelo.glosario.GlosarioModelo;
import tecolotl.alumno.sesion.ActividadSesionBean;
import tecolotl.alumno.sesion.GlosarioSesionBean;

import javax.annotation.PostConstruct;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.TreeSet;
import java.util.logging.Logger;

@Singleton
@Startup
public class ImageneGlosarioCache {

    private int BLOQUE;

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private Logger logger;

    @Inject
    private GlosarioSesionBean glosarioSesionBean;

    private Dictionary<LLaveGlosario, byte[]> diccionario;

    @PostConstruct
    public void inicio() {
        BLOQUE = 50;
        logger.finer("Bloque:".concat(String.valueOf(BLOQUE)));
        llena();
    }

    @Lock(value = LockType.READ)
    public byte[] busca(@NotNull @Size(min = 1) String palabra, @NotNull @Min(0) Short clase) {
        logger.fine(palabra);
        logger.fine(clase.toString());
        return diccionario.get(new LLaveGlosario(palabra, clase));
    }

    public void llena() {
        Long totalGlosario = buscaActividades();
        logger.fine("Total glosario encontrados".concat(totalGlosario.toString()));
        diccionario = new Hashtable<>(totalGlosario.intValue());
        for (int bloque = 0; bloque < totalGlosario; bloque += BLOQUE) {
            List<GlosarioModelo> glosarioModeloLista = glosarioSesionBean.busca(bloque, BLOQUE);
            for (GlosarioModelo glosarioModelo : glosarioModeloLista) {
                diccionario.put(new LLaveGlosario(glosarioModelo), glosarioModelo.getImagen());
            }
        }
    }

    private Long buscaActividades() {
        return entityManager.createQuery("SELECT COUNT(g) FROM GlosarioEntidad g", Long.class).getSingleResult();
    }

}
