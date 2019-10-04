package tecolotl.alumno.cache;

import tecolotl.alumno.modelo.relacionar.RelacionarOriginalModelo;
import tecolotl.alumno.sesion.RelacionarSesionBean;

import javax.annotation.PostConstruct;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Logger;

@Singleton
@Startup
public class RelacionarImagenCache {

    private int BLOQUE;

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private Logger logger;

    @Inject
    private RelacionarSesionBean relacionarSesionBean;

    private Dictionary<String, byte[]> diccionario;

    @PostConstruct
    public void init(){
        BLOQUE = 50;
        logger.finer("Bloque: ".concat(String.valueOf(BLOQUE)));
        llena();
    }

    @Lock(value = LockType.READ)
    public byte[] busca(@NotNull @Size(min = 32, max = 32) String codigo){
        logger.fine("Codigo: ".concat(codigo));
        return diccionario.get(codigo);
    }

    public void llena(){
        Long totalRelacionar = buscaDatos();
        logger.fine("Total RelacionarModelo encontrados: ".concat(totalRelacionar.toString()));
        diccionario = new Hashtable<>(totalRelacionar.intValue());
        for (int bloque = 0; bloque < totalRelacionar; bloque+= BLOQUE){
            List<RelacionarOriginalModelo> relacionarModeloLista = relacionarSesionBean.busca(bloque, BLOQUE);
            for (RelacionarOriginalModelo relacionarOriginalModelo: relacionarModeloLista){
                diccionario.put(relacionarOriginalModelo.getCodigo(), relacionarOriginalModelo.getImagen() );
            }
        }
    }

    private Long buscaDatos(){
        return entityManager.createQuery("select count(re.codigo) from RelacionarEntidad re", Long.class).getSingleResult();
    }
}
