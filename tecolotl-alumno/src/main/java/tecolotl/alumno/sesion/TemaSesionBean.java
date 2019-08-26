package tecolotl.alumno.sesion;

import tecolotl.alumno.entidad.TemaEntidad;
import tecolotl.alumno.modelo.TemaModelo;
import tecolotl.nucleo.sesion.CatalogoSesionBean;

import javax.ejb.Stateless;

@Stateless
public class TemaSesionBean extends CatalogoSesionBean<TemaModelo, TemaEntidad> {
}
