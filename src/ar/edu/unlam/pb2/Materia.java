package ar.edu.unlam.pb2;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Materia {

	private int id;
	private String nombre;
	private Set<Materia> materiasCorrelativas;

	public Materia(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
		this.materiasCorrelativas = new HashSet<Materia>();
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
	
	
	
	

}
