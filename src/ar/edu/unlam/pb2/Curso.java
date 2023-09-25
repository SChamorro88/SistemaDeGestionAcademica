package ar.edu.unlam.pb2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Curso {
	private Integer id;
	private LocalDate fechaCurso;
	private Turnos turno;
	private Integer cupoMaximo;
	private Integer cupoActual;
	private Materia materia;
	private CicloLectivo cicloLectivo;
	private Set<Docente> docentesAsignados;
	private List<Materia> materiasAsociadas;
	private List<Alumno> alumnosInscritos;
	private Set<Materia> correlativasRequeridas;

	public Curso(Integer id, LocalDate fechaCurso, Turnos turno, int cupoMaximo) {
		this.id = id;
		this.fechaCurso = fechaCurso;
		this.turno = turno;
		this.cupoMaximo = cupoMaximo;
		this.cupoMaximo = cupoMaximo;
		this.alumnosInscritos = new ArrayList<Alumno>();
		this.correlativasRequeridas = new HashSet<Materia>();
		this.docentesAsignados = new HashSet<Docente>();
		this.materiasAsociadas = new ArrayList<Materia>();
		this.alumnosInscritos = new ArrayList<>();
		this.correlativasRequeridas = new HashSet<>();
	}

	public Curso(int id, Materia materia, CicloLectivo cicloLectivo, Turnos turno, Integer cupoMaximo) {
		this.id = id;
		this.materia = materia;
		this.cicloLectivo = cicloLectivo;
		this.turno = turno;
		this.cupoMaximo = cupoMaximo;
		this.cupoActual = 0;
		this.alumnosInscritos = new ArrayList<Alumno>();
		this.correlativasRequeridas = new HashSet<Materia>();
		this.docentesAsignados = new HashSet<Docente>();
		this.materiasAsociadas = new ArrayList<Materia>();
	}

	public Integer getCupoActual() {
		return cupoActual;
	}

	public void setCupoActual(Integer cupoActual) {
		this.cupoActual = cupoActual;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getFechaCurso() {
		return fechaCurso;
	}

	public void setFechaCurso(LocalDate fechaCurso) {
		this.fechaCurso = fechaCurso;
	}

	public Turnos getTurno() {
		return turno;
	}

	public void setTurno(Turnos turno) {
		this.turno = turno;
	}

	public int getCupoMaximo() {
		return cupoMaximo;
	}

	public void setCupoMaximo(int cupoMaximo) {
		this.cupoMaximo = cupoMaximo;
	}

	public List<Alumno> getAlumnosInscritos() {
		return alumnosInscritos;
	}

	public void setAlumnosInscritos(List<Alumno> alumnosInscritos) {
		this.alumnosInscritos = alumnosInscritos;
	}

	public Set<Materia> getCorrelativas() {
		return correlativasRequeridas;
	}

	public Set<Materia> getCorrelativasRequeridas() {
		return correlativasRequeridas;
	}

	public void setCorrelativasRequeridas(Set<Materia> correlativasRequeridas) {
		this.correlativasRequeridas = correlativasRequeridas;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public CicloLectivo getCicloLectivo() {
		return cicloLectivo;
	}

	public void setCicloLectivo(CicloLectivo cicloLectivo) {
		this.cicloLectivo = cicloLectivo;
	}

	public void setCupoMaximo(Integer cupoMaximo) {
		this.cupoMaximo = cupoMaximo;
	}

	// ----------------------------------------------------------------------

	public Set<Docente> getDocentesAsignados() {
		return docentesAsignados;
	}

	public void setDocentesAsignados(Set<Docente> docentesAsignados) {
		this.docentesAsignados = docentesAsignados;
	}

	public List<Materia> getMateriasAsociadas() {
		return materiasAsociadas;
	}

	public void setMateriasAsociadas(List<Materia> materiasAsociadas) {
		this.materiasAsociadas = materiasAsociadas;
	}

	// -----------------------------------------------------------------------------------
	public void asociarMateriaACurso(Materia materia) {
		materiasAsociadas.add(materia);

	}

	public void agregarCorrelativa(Materia correlativa) {
		correlativasRequeridas.add(correlativa);
	}

	public void agregarAlumnoInscrito(Alumno alumno) {
		if (alumno != null) {
			if (cupoActual < cupoMaximo) {
				alumnosInscritos.add(alumno);
				cupoActual++;
			}
		}

	}

	public boolean estaInscrito(Alumno alumno) {

		return alumnosInscritos.contains(alumno);
	}

	public void asignarDocente(Docente docente) {
		if (docente != null) {
			Integer numeroDocentes = docentesAsignados.size();
			Integer alumnosIncritos = cupoActual;

			int docentesNecesarios = (alumnosIncritos / 20) + 1;

			if (numeroDocentes < docentesNecesarios) {
				docentesAsignados.add(docente);
				docente.asignarCurso(this);
			}

		}
	}

	public boolean tieneProfesor(Docente docente) {
		return docentesAsignados.contains(docente);
	}

	public void incrementarCupoActual() {
		cupoActual++;
	}

	// ---------------------------------------------------------------------

	public void registrarNota(Integer id_curso, Integer id_alumno, Nota nota) {
		Alumno alumno = buscarAlumnoPorId(id_alumno);

		// 1-verificar el rango de las notas
		if (nota.getValor() < 1 || nota.getValor() > 10) {
			throw new IllegalArgumentException("La nota debe estar entre 1 y 10");
		}

		// 2- verificar notas mayores o iguales a 7 y correlativas aprobadas
		if (nota.getValor() >= 7) {
			if (!materia.tieneCorrelativasAprobadas(alumno)) {
				throw new IllegalArgumentException(
						"No puedes asignar una nota mayor o igual a 7 si no están todas las correlativas aprobadas");

			}
		}
		// 3- Validar cantidad de tipos de notas y que no haya duplicados
		if (!validarTiposNotas(id_alumno, nota.getTipo())) {
			throw new IllegalArgumentException("No puedes cargar 2 notas del mismo tipo o más de 3 tipos de notas");
		}

		alumno.registrarNota(nota);
	}

	private boolean validarTiposNotas(Integer id_alumno, String tipo) {
		Alumno alumno = buscarAlumnoPorId(id_alumno);
		Integer contadorTipoDeNotas = 0;
		Integer contadorMismoTipoDeNotas = 0;
		for (Nota nota : alumno.getNotasRegistradas()) {
			if (nota.getTipo().equals(tipo)) {
				contadorMismoTipoDeNotas++;

				if (contadorMismoTipoDeNotas >= 2) {
					return false;
				}
			}
			if (contadorTipoDeNotas >= 3) {
				return false;
			}
			if (!nota.getTipo().equals(tipo)) {
				contadorTipoDeNotas++;
			}
		}
		return true;
	}

	public Alumno buscarAlumnoPorId(Integer id_alumno) {
		for (Alumno alumno : alumnosInscritos) {
			if (alumno.getId() == id_alumno) {
				return alumno;
			}
		}
		throw new IllegalArgumentException("No existe el id del alumno");
	}

}
