package tecolotl.alumno.sesion;

import tecolotl.alumno.entidad.glosario.ClaseGlosarioEntidad;
import tecolotl.alumno.modelo.glosario.ClaseGlosarioModelo;
import tecolotl.nucleo.sesion.CatalogoSesionBean;

import javax.ejb.Stateless;

@Stateless
public class ClaseGlosarioSesionBean extends CatalogoSesionBean<ClaseGlosarioModelo, ClaseGlosarioEntidad> {
}
