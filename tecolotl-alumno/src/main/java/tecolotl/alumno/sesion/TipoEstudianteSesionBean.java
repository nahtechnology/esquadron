package tecolotl.alumno.sesion;

import tecolotl.alumno.modelo.TipoEstudianteModelo;
import tecolotl.alumno.entidad.TipoEstudianteEntidad;
import tecolotl.nucleo.sesion.CatalogoSesionBean;

import javax.ejb.Stateless;

@Stateless
public class TipoEstudianteSesionBean extends CatalogoSesionBean<TipoEstudianteModelo, TipoEstudianteEntidad> {
}
