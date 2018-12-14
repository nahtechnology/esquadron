package tecolotl.web.control.administracion;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import tecolotl.administracion.dto.MotivoBloqueoDto;
import tecolotl.administracion.sesion.EscuelaSesionBean;

@Named
@RequestScoped
public class MotivoBloqueoControlador {

	@Inject
	private EscuelaSesionBean escuelaSesionBean;
	private List<MotivoBloqueoDto> motivoBloqueoLista;

	@PostConstruct
	public void init() {
		motivoBloqueoLista = escuelaSesionBean.motivoBloque();
	}

	public List<MotivoBloqueoDto> getMotivoBloqueoLista() {
		return motivoBloqueoLista;
	}

	public void setMotivoBloqueoLista(List<MotivoBloqueoDto> motivoBloqueoLista) {
		this.motivoBloqueoLista = motivoBloqueoLista;
	}

}
