package tecolotl.web.control;

import tecolotl.alumno.sesion.AlumnoSesionBean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
@Named
public class LoginControl {

    private String apodo;
    private String contrasenia;
    private int tipo;

    @Inject
    private AlumnoSesionBean alumnoSesionBean;

    public String validaUsuario() {
        switch (tipo) {
            case 1:
                if(alumnoSesionBean.busca(apodo, contrasenia)) {

                } else {

                }
                break;
            default:
                throw new IllegalArgumentException("Tipo de usuario aun no aceptado:".concat(String.valueOf(tipo)));
        }
        return "login";
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}