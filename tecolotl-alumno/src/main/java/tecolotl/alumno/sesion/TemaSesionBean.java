package tecolotl.alumno.sesion;

import tecolotl.alumno.entidad.TipoEstudianteEntidad;
import tecolotl.alumno.modelo.TipoEstudianteModelo;
import tecolotl.nucleo.sesion.CatalogoSesionBean;

import javax.ejb.Stateless;

@Stateless
public class TemaSesionBean extends CatalogoSesionBean<TipoEstudianteModelo, TipoEstudianteEntidad> {
}
