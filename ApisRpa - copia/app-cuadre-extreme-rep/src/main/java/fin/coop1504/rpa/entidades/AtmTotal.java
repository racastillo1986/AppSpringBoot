package fin.coop1504.rpa.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ATM_TOTAL_RC")
public class AtmTotal {

	
	private Long Secuencial;
	private Date Fecha;
	@Id
	private String Horatran;
	private String Descripciontransaccion;
	private String Numtarjeta;
	private Float Valor;
	private Float Valordisp;
	private String Normaloreverso;
	private int Codcompletion;
	private String Dispensado;

	public AtmTotal() {
		super();
	}

	public AtmTotal(Long secuencial, Date fecha, String horatran, String descripciontransaccion, String numtarjeta,
			Float valor, Float valordisp, String normaloreverso, int codcompletion, String dispensado) {
		super();
		Secuencial = secuencial;
		Fecha = fecha;
		Horatran = horatran;
		Descripciontransaccion = descripciontransaccion;
		Numtarjeta = numtarjeta;
		Valor = valor;
		Valordisp = valordisp;
		Normaloreverso = normaloreverso;
		Codcompletion = codcompletion;
		Dispensado = dispensado;
	}

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

}
