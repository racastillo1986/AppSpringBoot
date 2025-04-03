package fin.coop1504.rpa.dto;

public class ResultadoResumenAtm {

	private String transaccion;
	private Long m01;
	private Long m05;
	private Long m25;
	private Long m1;
	private Long b1;
	private Long b5;
	private Long b10;
	private Long b20;
	private Long b50;
	private Long b100;
	private Double total;

	public Long getM01() {
		return m01;
	}

	public void setM01(Long m01) {
		this.m01 = m01;
	}

	public Long getM05() {
		return m05;
	}

	public void setM05(Long m05) {
		this.m05 = m05;
	}

	public Long getM25() {
		return m25;
	}

	public void setM25(Long m25) {
		this.m25 = m25;
	}

	public Long getM1() {
		return m1;
	}

	public void setM1(Long m1) {
		this.m1 = m1;
	}

	public Long getB1() {
		return b1;
	}

	public void setB1(Long b1) {
		this.b1 = b1;
	}

	public Long getB5() {
		return b5;
	}

	public void setB5(Long b5) {
		this.b5 = b5;
	}

	public String getTransaccion() {
		return transaccion;
	}

	public void setTransaccion(String transaccion) {
		this.transaccion = transaccion;
	}

	public Long getB10() {
		return b10;
	}

	public void setB10(Long b10) {
		this.b10 = b10;
	}

	public Long getB20() {
		return b20;
	}

	public void setB20(Long b20) {
		this.b20 = b20;
	}

	public Long getB50() {
		return b50;
	}

	public void setB50(Long b50) {
		this.b50 = b50;
	}

	public Long getB100() {
		return b100;
	}

	public void setB100(Long b100) {
		this.b100 = b100;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

}
