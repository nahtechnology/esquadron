package tecolotl.administracion.dto;

import tecolotl.administracion.persistencia.entidad.MotivoBloqueoEntidad;

import java.util.Objects;

public class MotivoBloqueoDto extends CatalogoDto {

	public MotivoBloqueoDto() {
		super();
	}

	public MotivoBloqueoDto(Short id, String descripcion) {
		super(id, descripcion);
	}

	public MotivoBloqueoDto(MotivoBloqueoEntidad motivoBloqueoEntidad) {
		super(motivoBloqueoEntidad.getId(), motivoBloqueoEntidad.getDescripcion());
	}
	

}
