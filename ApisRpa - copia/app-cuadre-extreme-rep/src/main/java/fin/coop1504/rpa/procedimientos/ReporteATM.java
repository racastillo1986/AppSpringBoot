package fin.coop1504.rpa.procedimientos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.jdbc.Work;

import fin.coop1504.rpa.dto.ResultadoReporteAtm;

public class ReporteATM implements Work {

	private String iDDispositivo;
	private Integer seqCierre;
	private Integer moneda;
	private String fechaCierre;
	private Integer red;

	private List<ResultadoReporteAtm> resultadoReporteAtm;

	@Override
	public void execute(Connection connection) throws SQLException {

		CallableStatement sp = connection.prepareCall("{call Reporte_ATMGetDatosCuadreMon(?, ?, ?, ?, ?)}");
		sp.setString(1, iDDispositivo);
		sp.setInt(2, seqCierre);
		sp.setInt(3, moneda);
		sp.setString(4, fechaCierre);
		sp.setInt(5, red);

		ResultSet resultado = sp.executeQuery();
		resultadoReporteAtm = new ArrayList<>();			
		
		while (resultado.next()) {
			ResultadoReporteAtm registro = new ResultadoReporteAtm();
			registro.setCodcompletion(resultado.getInt("Codcompletion"));
			registro.setDescripciontransaccion(resultado.getString("Descripciontransaccion"));
			registro.setDispensado(calculoDispensado(resultado));
			registro.setFecha(resultado.getDate("Fecha"));
			registro.setHoratran(resultado.getString("Horatran"));
			registro.setNormaloreverso(calcularNormalOReverso(resultado));
			registro.setNumtarjeta(resultado.getString("Numtarjeta"));
			registro.setSecuencial(resultado.getLong("Secuencial"));
			registro.setValor(resultado.getFloat("Valor"));
			registro.setValordisp(calcularValorDisponible(resultado));
			registro.setEstado(calculoEstado(resultado));						
			resultadoReporteAtm.add(registro);
		}

	}
	
	private String calculoEstado(ResultSet resultado) {
		String estado = "";
		Boolean codigo;
		
		try {
			String descripcionTransaccion = resultado.getString("DescripcionTransaccion");			
			codigo = resultado.getBoolean("LlegoCompletion");
			if (descripcionTransaccion.equals("INICIO DE CAJERO") || descripcionTransaccion.equals("Cierre Lote") || descripcionTransaccion.equals("Adicion de Efectivo")) {
				estado = "OK";				
			} else {				
				if (codigo) {
					estado = "OK";
				} else {
					estado = "No Confirmada";
				}				
			}			
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return estado;
	}

	private String calcularNormalOReverso(ResultSet resultado) {
		int reverso;
		String valor = null;
		try {
			reverso = resultado.getInt("Normaloreverso");
			if (reverso == 0) {
				valor = "Normal";
			} else {
				valor = "Reverso";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return valor;
	}

	private Float calcularValorDisponible(ResultSet resultado) {

		Float dispdena = null;
		Float dispdenb = null;
		Float dispdenc = null;
		Float dispdend = null;
		Float dispdene = null;
		Float dispdenf = null;
		Float dispdeng = null;
		Float dispdenh = null;
		Float valdena = null;
		Float valdenb = null;
		Float valdenc = null;
		Float valdend = null;
		Float valdene = null;
		Float valdenf = null;
		Float valdeng = null;
		Float valdenh = null;
		Float valorDisp = null;

		String descripciontransaccion = null;

		try {
			dispdena = resultado.getFloat("Dispdena");
			dispdenb = resultado.getFloat("Dispdenb");
			dispdenc = resultado.getFloat("Dispdenc");
			dispdend = resultado.getFloat("Dispdend");
			dispdene = resultado.getFloat("Dispdene");
			dispdenf = resultado.getFloat("Dispdenf");
			dispdeng = resultado.getFloat("Dispdeng");
			dispdenh = resultado.getFloat("Dispdenh");
			valdena = resultado.getFloat("Valdena");
			valdenb = resultado.getFloat("Valdenb");
			valdenc = resultado.getFloat("Valdenc");
			valdend = resultado.getFloat("Valdend");
			valdene = resultado.getFloat("Valdene");
			valdenf = resultado.getFloat("Valdenf");
			valdeng = resultado.getFloat("Valdeng");
			valdenh = resultado.getFloat("Valdenh");

			descripciontransaccion = resultado.getString("Descripciontransaccion");
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (descripciontransaccion.equals("INICIO DE CAJERO") || descripciontransaccion.equals("Cierre Lote") || descripciontransaccion.equals("Adicion de Efectivo")) {			
			valorDisp = 0F;			
		} else {
			valorDisp = ((dispdena * valdena) + (dispdenb * valdenb) + (dispdenc * valdenc) + (dispdend * valdend)
					+ (dispdene * valdene) + (dispdenf * valdenf) + (dispdeng * valdeng) + (dispdenh * valdenh));
		}

		return valorDisp;
	}

	private String calculoDispensado(ResultSet resultado) {

		String valdena = null;
		String valdenb = null;
		String valdenc = null;
		String valdend = null;
		String valdene = null;
		String valdenf = null;
		String valdeng = null;
		String valdenh = null;

		String reqdena = null;
		String reqdenb = null;
		String reqdenc = null;
		String reqdend = null;
		String reqdene = null;
		String reqdenf = null;
		String reqdeng = null;
		String reqdenh = null;

		String dispensado;
		Integer reverso;

		try {
			valdena = String.valueOf(resultado.getFloat("Valdena"));
			valdenb = String.valueOf(resultado.getFloat("Valdenb"));
			valdenc = String.valueOf(resultado.getFloat("Valdenc"));
			valdend = String.valueOf(resultado.getFloat("Valdend"));
			valdene = String.valueOf(resultado.getFloat("Valdene"));
			valdenf = String.valueOf(resultado.getFloat("Valdenf"));
			valdeng = String.valueOf(resultado.getFloat("Valdeng"));
			valdenh = String.valueOf(resultado.getFloat("Valdenh"));
						
			
			if (valdena.equals("10.0") || valdena.equals("20.0") || valdena.equals("5.0") || valdena.equals("1.0")) {
				valdena = valdena.replace(".0", "");
			}
			if (valdenb.equals("10.0") || valdenb.equals("20.0") || valdenb.equals("5.0") || valdenb.equals("1.0")) {
				valdenb = valdenb.replace(".0", "");
			}
			if (valdenc.equals("10.0") || valdenc.equals("20.0") || valdenc.equals("5.0") || valdenc.equals("1.0")) {
				valdenc = valdenc.replace(".0", "");
			}
			if (valdend.equals("10.0") || valdend.equals("20.0") || valdend.equals("5.0") || valdend.equals("1.0")) {
				valdend = valdend.replace(".0", "");
			}
			if (valdene.equals("10.0") || valdene.equals("20.0") || valdene.equals("5.0") || valdene.equals("1.0")) {
				valdene = valdene.replace(".0", "");
			}
			if (valdenf.equals("10.0") || valdenf.equals("20.0") || valdenf.equals("5.0") || valdenf.equals("1.0")) {
				valdenf = valdenf.replace(".0", "");
			}
			if (valdeng.equals("10.0") || valdeng.equals("20.0") || valdeng.equals("5.0") || valdeng.equals("1.0")) {
				valdeng = valdeng.replace(".0", "");
			}
			if (valdenh.equals("10.0") || valdenh.equals("20.0") || valdenh.equals("5.0") || valdenh.equals("1.0")) {
				valdenh = valdenh.replace(".0", "");
			}
			
			reqdena = String.valueOf(resultado.getInt("Reqdena"));
			reqdenb = String.valueOf(resultado.getInt("Reqdenb"));
			reqdenc = String.valueOf(resultado.getInt("Reqdenc"));
			reqdend = String.valueOf(resultado.getInt("Reqdend"));
			reqdene = String.valueOf(resultado.getInt("Reqdene"));
			reqdenf = String.valueOf(resultado.getInt("Reqdenf"));
			reqdeng = String.valueOf(resultado.getInt("Reqdeng"));
			reqdenh = String.valueOf(resultado.getInt("Reqdenh"));

		} catch (Exception e) {
			e.printStackTrace();
		}

		dispensado = valdena + '=' + reqdena + ',' + 
				     valdenb + '=' + reqdenb + ',' + 
				     valdenc + '=' + reqdenc + ',' + 
				     valdend + '=' + reqdend + ',' + 
				     valdene + '=' + reqdene + ',' + 
				     valdenf + '=' + reqdenf + ',' + 
				     valdeng + '=' + reqdeng + ',' + 
				     valdenh + '=' + reqdenh;		

		dispensado = dispensado.replace(",0.0=0", "");

		// condicion para normales y reversos
		try {
			reverso = resultado.getInt("Normaloreverso");
			if (reverso == 1) {
				dispensado = "R:" + dispensado + ",D:";
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return dispensado;
	}

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

	public List<ResultadoReporteAtm> getResultadoReporteAtm() {
		return resultadoReporteAtm;
	}

}
