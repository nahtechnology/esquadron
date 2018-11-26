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

	/**
	 * 
	 */
	private String estado;
	
	/**
	 * 
	 */
	private String municipio;
	
	/**
	 * 
	 */
	private List<CodigoPostal> listaCodigoPostal;
	
	public ColoniaDto() {
	
	}

	/**
	 * 
	 * @param listaColonias
	 */
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

	/**
	 * 
	 * @author antonio
	 *
	 */
	public class CodigoPostal {
		
		/**
		 * 
		 */
		private int id;
		
		/**
		 * 
		 */
		private String nombre;		
		
		/**
		 * 
		 */
		private String codigoPostal;
		
		
		public CodigoPostal() {
		}
		
		public CodigoPostal(ColoniaEntidad coloniaEntidad) {
			this.id = coloniaEntidad.getId();
			this.nombre = coloniaEntidad.getNombre();
			this.codigoPostal = coloniaEntidad.getCodigoPostal();
		}
		
		
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public String getCodigoPostal() {
			return codigoPostal;
		}
		public void setCodigoPostal(String codigoPostal) {
			this.codigoPostal = codigoPostal;
		}

		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}		
		
	}
}
