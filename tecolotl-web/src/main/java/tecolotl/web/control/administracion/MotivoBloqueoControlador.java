package tecolotl.web.control.administracion;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import tecolotl.administracion.dto.MotivoBloqueoDto;
import tecolotl.administracion.sesion.EscuelaSesionBean;
import tecolotl.administracion.sesion.MotivoBloqueoSesionBean;

@Named
@RequestScoped
public class MotivoBloqueoControlador {

	@Inject
	private MotivoBloqueoSesionBean motivoBloqueoSesionBean;
	private List<MotivoBloqueoDto> motivoBloqueoLista;

	@PostConstruct
	public void init() {
		motivoBloqueoLista = motivoBloqueoSesionBean.motivoBloque();
	}

	public List<MotivoBloqueoDto> getMotivoBloqueoLista() {
		return motivoBloqueoLista;
	}

	public void setMotivoBloqueoLista(List<MotivoBloqueoDto> motivoBloqueoLista) {
		this.motivoBloqueoLista = motivoBloqueoLista;
	}

}
