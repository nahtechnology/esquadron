package tecolotl.administracion.sesion;

import tecolotl.administracion.dto.ColoniaDto;
import tecolotl.administracion.dto.EstadoDto;
import tecolotl.administracion.dto.MunicipioDto;
import tecolotl.administracion.persistencia.entidad.ColoniaEntidad;
import tecolotl.administracion.persistencia.entidad.EstadoEntidad;
import tecolotl.administracion.persistencia.entidad.MunicipioEntidad;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless(name = "ColoniaSesionEJB")
public class ColoniaSesionBean {

    private Logger logger = Logger.getLogger(getClass().getName());

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Busca todas las colonias correspondientes a un código postal, en caso de que el parámetro sea nulo regresará nulo.
     * En caso de de no existir el código postal regresa una lista vacia
     * @param codigoPostal Código Postal a ser buscado
     * @return Un objeto con los datos al que pertenece el código postal
     */
    public List<ColoniaDto> busca(String codigoPostal) {
        if (codigoPostal == null) {
            return null;
        }
        TypedQuery<ColoniaEntidad> typedQuery = entityManager.createNamedQuery("ColoniaEntidad.buscaCodigoPostal", ColoniaEntidad.class);
        typedQuery.setParameter("codigoPostal", codigoPostal);
        List<ColoniaDto> coloniaDtoLista = new ArrayList<>();
        for (ColoniaEntidad coloniaEntidad :
                typedQuery.getResultList()) {
            coloniaDtoLista.add(new ColoniaDto(coloniaEntidad));
        }
        return coloniaDtoLista;
    }

    /**
     * Actualiza el nombre de una colonia, en caso de ser algún parámetro nulo o si el identificador de la colonia no existe
     * no se inserta dicho de valor
     * @param id Identificador de una colonia
     * @param nombre Nombre de la colonia a actualizar
     */
    public void actualiza(Integer id, String nombre) {
        if (id == null || nombre == null) {
            return;
        }
        ColoniaEntidad coloniaEntidad = entityManager.find(ColoniaEntidad.class, id);
        if (coloniaEntidad == null) {
            return;
        }
        coloniaEntidad.setNombre(nombre);
    }

    /**
     * Inserta una colonia, en caso de que algún dato sea nulo no se inserta el valor
     * @param nombre Nombre de la colonia
     * @param codigoPostal Código postal a la que pertenece dicha colonia
     * @param idMunicipio Identificador del municipio al que pertenece
     * @return
     */
    public Integer inserta(String nombre, String codigoPostal, Integer idMunicipio) {
        if (codigoPostal == null || nombre == null || idMunicipio == null) {
            return null;
        }
        ColoniaEntidad coloniaEntidad = new ColoniaEntidad();
        MunicipioEntidad municipioEntidad = new MunicipioEntidad(idMunicipio);
        coloniaEntidad.setMunicipio(municipioEntidad);
        coloniaEntidad.setNombre(nombre);
        coloniaEntidad.setCodigoPostal(codigoPostal);
        entityManager.persist(coloniaEntidad);
        return coloniaEntidad.getId();
    }

    /**
     * Busca el municipio en el que pertenece un código postal, en caso de no existir el municipio se regresa nulo, en caso
     * que el código postal sea nulo se recupera nulo
     * @param codigoPostal Código postal a buscar
     * @return {@link MunicipioDto} con los datos encontrados
     * @since 0.1
     */
    public MunicipioDto buscaMunicipio(String codigoPostal) {
        if (codigoPostal == null) {
            return null;
        }
        TypedQuery<MunicipioEntidad> typedQuery = entityManager.createNamedQuery("ColoniaEntidad.buscaMunicipoCodigoPostal", MunicipioEntidad.class);
        typedQuery.setParameter("codigoPostal", codigoPostal);
        typedQuery.setMaxResults(1);
        MunicipioEntidad municipioEntidad = typedQuery.getSingleResult();
        return municipioEntidad == null ? null : new MunicipioDto(municipioEntidad);
    }

    /**
     * Busca el estado al que pertene un código postal, en caso de que parametro sea nulo, re recupera nulo
     * @param codigoPostal Código postal para buscar
     * @return {@link EstadoDto} con los datos contrado o nulo en caso de no encontrarse
     */
    public EstadoDto buscaEstado(String codigoPostal) {
        if (codigoPostal == null) {
            return null;
        }
        TypedQuery<EstadoEntidad> typedQuery = entityManager.createNamedQuery("ColoniaEntidad.buscaEstadoPostal", EstadoEntidad.class);
        typedQuery.setParameter("codigoPostal", codigoPostal);
        typedQuery.setMaxResults(1);
        EstadoEntidad estadoEntidad = typedQuery.getSingleResult();
        return estadoEntidad == null ? null : new EstadoDto(estadoEntidad);
    }
}