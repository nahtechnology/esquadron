package tecolotl.web.control.administracion;

import tecolotl.administracion.dto.MotivoBloqueoDto;
import tecolotl.administracion.sesion.MotivoBloqueoSesionBean;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.SortedSet;
import java.util.TreeSet;

@Named
@ViewScoped
public class CatalogoControlador implements Serializable {

    private SortedSet<MotivoBloqueoDto> motivoBloqueos;
    private MotivoBloqueoDto motivoBloqueoModelo;
    private int count;

    @Inject
    private MotivoBloqueoSesionBean motivoBloqueoSesionBean;

    @PostConstruct
    public void init() {
        motivoBloqueos = new TreeSet<>(motivoBloqueoSesionBean.motivoBloque());
        motivoBloqueoModelo = new MotivoBloqueoDto();
    }

    public void inserta() {
        if (motivoBloqueoModelo.getId() == 0) {
            motivoBloqueoSesionBean.inserta(motivoBloqueoModelo.getDescripcion());
            motivoBloqueos.add(motivoBloqueoModelo);
        } else {
            motivoBloqueoSesionBean.actualiza(motivoBloqueoModelo);
            MotivoBloqueoDto motivoBloqueoDtoLocal = ((TreeSet<MotivoBloqueoDto>)motivoBloqueos).floor(motivoBloqueoModelo);
            motivoBloqueoDtoLocal.setDescripcion(motivoBloqueoModelo.getDescripcion());
        }
        motivoBloqueoModelo = new MotivoBloqueoDto();
    }

    public void actualiza(MotivoBloqueoDto motivoBloqueoDto) {
        motivoBloqueoModelo = motivoBloqueoDto;
    }

    public SortedSet<MotivoBloqueoDto> getMotivoBloqueos() {
        return motivoBloqueos;
    }

    public void setMotivoBloqueos(SortedSet<MotivoBloqueoDto> motivoBloqueos) {
        this.motivoBloqueos = motivoBloqueos;
    }

    public MotivoBloqueoDto getMotivoBloqueoModelo() {
        return motivoBloqueoModelo;
    }

    public void setMotivoBloqueoModelo(MotivoBloqueoDto motivoBloqueoModelo) {
        this.motivoBloqueoModelo = motivoBloqueoModelo;
    }
}
