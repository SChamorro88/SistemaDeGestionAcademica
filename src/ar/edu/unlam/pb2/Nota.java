package ar.edu.unlam.pb2;

public class Nota {

	private String tipo;
	private Integer valor;
	private Materia materia;

	public Nota(String tipo, Integer valor, Materia materia) {
		this.tipo = tipo;
		this.valor = valor;
		this.materia = materia;
	}

	public String getTipo() {
		return tipo;
	}

	public Integer getValor() {
		return valor;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}
	
	
}
