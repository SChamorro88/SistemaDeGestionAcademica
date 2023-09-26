package ar.edu.unlam.pb2;

public class Nota {

	private Curso curso;
	private String tipo;
	private Double valor;
	private Materia materia;

	public Nota(String tipo, Double valor, Materia materia) {
		this.tipo = tipo;
		this.valor = valor;
		this.materia = materia;
	}
	
	

	public Nota(Curso curso, String tipo, Double valor, Materia materia) {
		super();
		this.curso = curso;
		this.tipo = tipo;
		this.valor = valor;
		this.materia = materia;
	}



	public String getTipo() {
		return tipo;
	}

	public Double getValor() {
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

	public void setValor(Double valor) {
		this.valor = valor;
	}



	public Curso getCurso() {
		return curso;
	}



	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	
	

}
