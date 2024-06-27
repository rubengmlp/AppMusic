package umu.tds.dominio;


public class Cancion {
	private int codigo;
	private String titulo;
	private int numRep;
	private final String url;
	private String interprete;
	private String estilo;

	public Cancion(String titulo, int numRepro, String url, String interprete, String estilo) {
		this.titulo = titulo;
		this.numRep =  numRepro;
		this.url = url;
		this.interprete = interprete;
		this.estilo = estilo;
	}

	public Cancion (String titulo, int numRepro, String url, String interprete) {
		this.titulo = titulo;
		this.numRep =  numRepro;
		this.url = url;
		this.interprete = interprete;
	}
	
	public Cancion (String titulo, String url, String interprete, String estilo) {
		this.titulo = titulo;
		this.url = url;
		this.interprete = interprete;
	}

	public void addReproduccion() {
		this.numRep++;
	}


	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getNumRep() {
		return numRep;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}


	public String getUrl() {
		return url;
	}
	
	public int compareTo(Cancion c) {
		return c.getNumRep() - this.numRep;
	}

	public String getEstilo() {
		return estilo;
	}

	public void setEstilo(String estilo) {
		this.estilo = estilo;
	}

	public void setInterprete(String interprete) {
		this.interprete = interprete;
	}

	public void setNumRep(int numRep) {
		this.numRep = numRep;
	}
	
	public String getInterprete() {
		return interprete;
	}


	
}
