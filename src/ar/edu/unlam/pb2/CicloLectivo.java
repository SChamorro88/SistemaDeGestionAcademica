package ar.edu.unlam.pb2;

import java.time.LocalDate;
import java.util.Objects;

public class CicloLectivo {

	private Integer idCiclo = 1;
	private LocalDate fechaInicioCicloElectivo;
	private LocalDate fechaFinalizacionCicloElectivo;
	private LocalDate fechaInicioInscripcion;
	private LocalDate fechaFinalizacionInscripcion;

	public CicloLectivo(Integer idCiclo, LocalDate fechaInicioCicloElectivo, LocalDate fechaFinalizacionCicloElectivo,
			LocalDate fechaInicioInscripcion, LocalDate fechaFinalizacionInscripcion) {
		this.idCiclo = idCiclo;
		this.fechaInicioCicloElectivo = fechaInicioCicloElectivo;
		this.fechaFinalizacionCicloElectivo = fechaFinalizacionCicloElectivo;
		this.fechaInicioInscripcion = fechaInicioInscripcion;
		this.fechaFinalizacionInscripcion = fechaFinalizacionInscripcion;

	}

	public Integer getIdCiclo() {
		return idCiclo;
	}

	public void setIdCiclo(Integer idCiclo) {
		this.idCiclo = idCiclo;
	}

	public LocalDate getFechaInicioCicloElectivo() {
		return fechaInicioCicloElectivo;
	}

	public void setFechaInicioCicloElectivo(LocalDate fechaInicioCicloElectivo) {
		this.fechaInicioCicloElectivo = fechaInicioCicloElectivo;
	}

	public LocalDate getFechaFinalizacionCicloElectivo() {
		return fechaFinalizacionCicloElectivo;
	}

	public void setFechaFinalizacionCicloElectivo(LocalDate fechaFinalizacionCicloElectivo) {
		this.fechaFinalizacionCicloElectivo = fechaFinalizacionCicloElectivo;
	}

	public LocalDate getFechaInicioInscripcion() {
		return fechaInicioInscripcion;
	}

	public void setFechaInicioInscripcion(LocalDate fechaInicioInscripcion) {
		this.fechaInicioInscripcion = fechaInicioInscripcion;
	}

	public LocalDate getFechaFinalizacionInscripcion() {
		return fechaFinalizacionInscripcion;
	}

	public void setFechaFinalizacionInscripcion(LocalDate fechaFinalizacionInscripcion) {
		this.fechaFinalizacionInscripcion = fechaFinalizacionInscripcion;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idCiclo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CicloLectivo other = (CicloLectivo) obj;
		return Objects.equals(idCiclo, other.idCiclo);
	}
	
	 public boolean estaEnPeriodoDeInscripcion(LocalDate fecha) {
	        return !fecha.isBefore(fechaInicioInscripcion) && !fecha.isAfter(fechaFinalizacionInscripcion);
	    }

}
