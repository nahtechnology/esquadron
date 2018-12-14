package tecolotl.administracion.dto;

import java.util.ArrayList;
import java.util.List;

import tecolotl.administracion.persistencia.entidad.ColoniaEntidad;

/**
 * 
 * @author antonio
 *
 */
public class ColoniaDto {

	private String estado;
	private String municipio;
	private List<CodigoPostal> listaCodigoPostal;
	
	public ColoniaDto() {
	
	}

	public ColoniaDto(List<ColoniaEntidad> listaColonias) {
		estado = listaColonias.get(0).getMunicipio().getEstado().getNombre();
		municipio = listaColonias.get(0).getMunicipio().getNombre();
		listaCodigoPostal = new ArrayList<>();
		for (ColoniaEntidad coloniaEntidad : listaColonias) {
			listaCodigoPostal.add(new CodigoPostal(coloniaEntidad));
		}
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public List<CodigoPostal> getListaCodigoPostal() {
		return listaCodigoPostal;
	}

	public void setListaCodigoPostal(List<CodigoPostal> listaCodigoPostal) {
		this.listaCodigoPostal = listaCodigoPostal;
	}


}
