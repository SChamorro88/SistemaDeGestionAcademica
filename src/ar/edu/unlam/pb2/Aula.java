package ar.edu.unlam.pb2;

public class Aula {

	private Integer id;
	private Integer cantidadAlumnos;

	public Aula(Integer id, Integer cantidadAlumnos) {
		this.id = id;
		this.cantidadAlumnos = cantidadAlumnos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCantidadAlumnos() {
		return cantidadAlumnos;
	}

	public void setCantidadAlumnos(Integer cantidadAlumnos) {
		this.cantidadAlumnos = cantidadAlumnos;
	}

}
