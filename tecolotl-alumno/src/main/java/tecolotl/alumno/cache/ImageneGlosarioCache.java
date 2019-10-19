package tecolotl.alumno.cache;


import tecolotl.alumno.modelo.glosario.GlosarioModelo;
import tecolotl.alumno.sesion.GlosarioSesionBean;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
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
/*
    @Schedules({
            @Schedule(year = "*", month = "*", dayOfMonth = "*", dayOfWeek = "*", info = "Update de glosario a las 00:00 hrs todos los dias"),
            @Schedule(hour = "00", minute = "00", second = "00")
    })
    public void actualiza() {
        Long totalGlosario = buscaActividades();
        logger.fine("Total glosario encontrados".concat(totalGlosario.toString()));
        logger.fine("Total glosarios en el diccionario: ".concat(String.valueOf(diccionario.size())));
        if(totalGlosario != diccionario.size()){
            for (int bloque = 0; bloque < totalGlosario; bloque += BLOQUE) {
                List<GlosarioModelo> glosarioModeloLista = glosarioSesionBean.busca(bloque, BLOQUE);
                for (GlosarioModelo glosarioModelo : glosarioModeloLista) {
                    try{
                        if(diccionario.get(new LLaveGlosario(glosarioModelo.getPalabra(),glosarioModelo.getClaseGlosarioModelo().getClave())) == null){
                            diccionario.put(new LLaveGlosario(glosarioModelo), glosarioModelo.getImagen());
                        }
                    }catch(NullPointerException npe){
                        logger.severe("Ocurrió un error al actualizar la memoria: ".concat(npe.getMessage()));
                    }
                }
            }
        }
    }
*/
    private Long buscaActividades() {
        return entityManager.createQuery("SELECT COUNT(g) FROM GlosarioEntidad g", Long.class).getSingleResult();
    }

}
