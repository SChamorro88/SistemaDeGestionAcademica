package ar.edu.unlam.pb2;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Materia {

	private int id;
	private String nombre;
	private Set<Materia> materiasCorrelativas;
	public Double notaMateria;
	private Set<Curso> cursosAsociadosAMateria;
	private CicloLectivo cicloLectivo;
	private Nota nota;

	public Materia(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
		this.materiasCorrelativas = new HashSet<Materia>();
		this.nota = nota;
		this.cursosAsociadosAMateria = new HashSet<Curso>();
		this.cicloLectivo = cicloLectivo;
	}

	public Double getnotaMateria() {
		return notaMateria;
	}

	public void setnotaMateria(Double nota) {
		this.notaMateria = notaMateria;
	}

	public void setMateriasCorrelativas(Set<Materia> materiasCorrelativas) {
		this.materiasCorrelativas = materiasCorrelativas;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Materia other = (Materia) obj;
		return id == other.id;
	}

	public Set<Materia> getMateriasCorrelativas() {
		return materiasCorrelativas;
	}

	public void agregarMateriaCorrelativa(Materia materiaCorrelativa) {
		materiasCorrelativas.add(materiaCorrelativa);

	}

	public void eliminarMateriaCorrelativa(Materia materiaCorrelativa) {
		materiasCorrelativas.remove(materiaCorrelativa);

	}

	public void asociarCursoAMateria(Curso curso) {
		cursosAsociadosAMateria.add(curso);

	}

	public Set<Curso> getCursosAsociadosAMateria() {
		return cursosAsociadosAMateria;
	}

	public void setCursosAsociadosAMateria(Set<Curso> cursosAsociadosAMateria) {
		this.cursosAsociadosAMateria = cursosAsociadosAMateria;
	}

	public boolean tieneCorrelativasAprobadas(Alumno alumno) {
		for (Materia correlativa : materiasCorrelativas) {
			if (!alumno.tieneCorrelativaAprobada(correlativa)) {
				return false;
			}
		}
		return true;
	}

	public boolean estaAprobada() {
        // La lógica para determinar si la nota está aprobada es que el valor debe ser mayor o igual a 7
        // y todas las correlativas deben estar aprobadas con una nota mayor o igual a 7
        if (nota.getValor()>= 7) {
            for (Materia correlativa : materiasCorrelativas) {
                if (correlativa.getnotaMateria() < 7) {
                    return false; 
                }
            }
            return true;
        }
        return false; 
    }

	public CicloLectivo getCicloLectivo() {
		return cicloLectivo;
	}

	public void setCicloLectivo(CicloLectivo cicloLectivo) {
		this.cicloLectivo = cicloLectivo;
	}

}
