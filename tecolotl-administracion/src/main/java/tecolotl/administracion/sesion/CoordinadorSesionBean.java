package tecolotl.administracion.sesion;

import tecolotl.administracion.modelo.coordinador.CoordinadorModelo;
import tecolotl.administracion.modelo.coordinador.CoordinadorMotivoBloqueoModelo;
import tecolotl.administracion.persistencia.entidad.CoordinadorEntidad;
import tecolotl.administracion.persistencia.entidad.CoordinadorEntidadPK;
import tecolotl.administracion.persistencia.entidad.CoordinadorMotivoBloqueoEntidad;
import tecolotl.administracion.persistencia.entidad.EscuelaEntidad;
import tecolotl.administracion.validacion.escuela.CoordinadorLlavePrimaria;
import tecolotl.nucleo.herramienta.ValidadorSessionBean;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Stateless
public class CoordinadorSesionBean {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private Logger logger;

    @Inject
    private ValidadorSessionBean validadorSessionBean;

    public List<CoordinadorModelo> busca(@NotNull @Size(min = 10, max = 14) String claveCentroTrabajo) {
        return entityManager.createNamedQuery("CoordinadorEntidad.buscaEscuela", CoordinadorEntidad.class)
                .setParameter("claveCentroTrabajo",claveCentroTrabajo).getResultList().stream().map(CoordinadorModelo::new).collect(Collectors.toList());
    }

    public void estatus(@NotNull CoordinadorModelo coordinadorModelo, @NotNull @Valid CoordinadorMotivoBloqueoModelo coordinadorMotivoBloqueoModelo) {
        logger.fine(coordinadorModelo.toString());
        logger.fine(coordinadorMotivoBloqueoModelo.toString());
        validadorSessionBean.validacion(coordinadorModelo, CoordinadorLlavePrimaria.class);
        CoordinadorEntidadPK coordinadorEntidadPK = new CoordinadorEntidadPK(new EscuelaEntidad(coordinadorModelo.getClaveCentroTrabajo()), coordinadorModelo.getContador());
        CoordinadorEntidad coordinadorEntidad = entityManager.find(CoordinadorEntidad.class, coordinadorEntidadPK);
        coordinadorEntidad.setCoordinadorMotivoBloqueoEntidad(new CoordinadorMotivoBloqueoEntidad());
    }
}
