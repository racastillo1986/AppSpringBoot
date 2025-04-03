package fin.coop1504.rpa.dto;

import java.util.Date;

public class ResultadoReporteAtm {

	private Long Secuencial;
	private Date Fecha;
	private String Horatran;
	private String Descripciontransaccion;
	private String Numtarjeta;
	private Float Valor;
	private Float Valordisp;
	private String Normaloreverso;
	private int Codcompletion;
	private String Dispensado;
	private String estado;

	public Long getSecuencial() {
		return Secuencial;
	}

	public void setSecuencial(Long secuencial) {
		Secuencial = secuencial;
	}

	public Date getFecha() {
		return Fecha;
	}

	public void setFecha(Date fecha) {
		Fecha = fecha;
	}

	public String getHoratran() {
		return Horatran;
	}

	public void setHoratran(String horatran) {
		Horatran = horatran;
	}

	public String getDescripciontransaccion() {
		return Descripciontransaccion;
	}

	public void setDescripciontransaccion(String descripciontransaccion) {
		Descripciontransaccion = descripciontransaccion;
	}

	public String getNumtarjeta() {
		return Numtarjeta;
	}

	public void setNumtarjeta(String numtarjeta) {
		Numtarjeta = numtarjeta;
	}

	public Float getValor() {
		return Valor;
	}

	public void setValor(Float valor) {
		Valor = valor;
	}

	public Float getValordisp() {
		return Valordisp;
	}

	public void setValordisp(Float valordisp) {
		Valordisp = valordisp;
	}

	public String getNormaloreverso() {
		return Normaloreverso;
	}

	public void setNormaloreverso(String normaloreverso) {
		Normaloreverso = normaloreverso;
	}

	public int getCodcompletion() {
		return Codcompletion;
	}

	public void setCodcompletion(int codcompletion) {
		Codcompletion = codcompletion;
	}

	public String getDispensado() {
		return Dispensado;
	}

	public void setDispensado(String dispensado) {
		Dispensado = dispensado;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	

}
