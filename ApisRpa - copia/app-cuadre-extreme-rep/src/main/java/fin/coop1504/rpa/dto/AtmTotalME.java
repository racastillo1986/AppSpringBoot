package fin.coop1504.rpa.dto;

import java.io.Serializable;

public class AtmTotalME implements Serializable {

	private static final long serialVersionUID = 1L;

	private String iDDispositivo;
	private Integer seqCierre;
	private Integer moneda;
	private String fechaCierre;
	private Integer red;

	public String getiDDispositivo() {
		return iDDispositivo;
	}

	public void setiDDispositivo(String iDDispositivo) {
		this.iDDispositivo = iDDispositivo;
	}

	public Integer getSeqCierre() {
		return seqCierre;
	}

	public void setSeqCierre(Integer seqCierre) {
		this.seqCierre = seqCierre;
	}

	public Integer getMoneda() {
		return moneda;
	}

	public void setMoneda(Integer moneda) {
		this.moneda = moneda;
	}

	public String getFechaCierre() {
		return fechaCierre;
	}

	public void setFechaCierre(String fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	public Integer getRed() {
		return red;
	}

	public void setRed(Integer red) {
		this.red = red;
	}

}
