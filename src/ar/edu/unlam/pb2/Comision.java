package ar.edu.unlam.pb2;

public class Comision {

	private int id;
	private Materia materia;
	private CicloLectivo cicloElectivo;
	private Turnos turno;

	public Comision(int id, Materia materia, CicloLectivo cicloElectivo, Turnos turno) {
		this.id = id;
		this.materia = materia;
		this.cicloElectivo = cicloElectivo;
		this.turno = turno;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public CicloLectivo getCicloElectivo() {
		return cicloElectivo;
	}

	public void setCicloElectivo(CicloLectivo cicloElectivo) {
		this.cicloElectivo = cicloElectivo;
	}

	public Turnos getTurno() {
		return turno;
	}

	public void setTurno(Turnos turno) {
		this.turno = turno;
	}

}
