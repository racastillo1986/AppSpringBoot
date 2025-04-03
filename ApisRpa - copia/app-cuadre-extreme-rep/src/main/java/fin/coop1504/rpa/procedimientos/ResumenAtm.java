package fin.coop1504.rpa.procedimientos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.jdbc.Work;

import fin.coop1504.rpa.dto.ResultadoResumenAtm;

public class ResumenAtm implements Work {

	private String iDDispositivo;
	private Integer seqCierre;
	private Integer moneda;
	private String fechaCierre;
	private Integer red;
	private List<ResultadoResumenAtm> resultadoResumenAtm;

	Float valdena = 0F;
	Float valdenb = 0F;
	Float valdenc = 0F;
	Float valdend = 0F;
	Float valdene = 0F;
	Float valdenf = 0F;
	Float valdeng = 0F;
	Float valdenh = 0F;
	Long dispdena = 0L;
	Long dispdenb = 0L;
	Long dispdenc = 0L;
	Long dispdend = 0L;
	Long dispdene = 0L;
	Long dispdenf = 0L;
	Long dispdeng = 0L;
	Long dispdenh = 0L;
	String descripcionTrx = null;
	Integer normalReverso = 0;

	Long total1 = 0L;
	Long total5 = 0L;
	Long total10 = 0L;
	Long total20 = 0L;
	Long total50 = 0L;
	Long total100 = 0L;
	Long total_m01 = 0L;
	Long total_m05 = 0L;
	Long total_m025 = 0L;
	Long total_m1 = 0L;
	Double valorTotal = (double) 0;

	Long total1_inicio = 0L;
	Long total5_inicio = 0L;
	Long total10_inicio = 0L;
	Long total20_inicio = 0L;
	Long total50_inicio = 0L;
	Long total100_inicio = 0L;
	Double valorTotal_inicio = (double) 0;
	
	Long total1_adi = 0L;
	Long total5_adi = 0L;
	Long total10_adi = 0L;
	Long total20_adi = 0L;
	Long total50_adi = 0L;
	Long total100_adi = 0L;
	Long total_m01_adi = 0L;
	Long total_m05_adi = 0L;
	Long total_m025_adi = 0L;
	Long total_m1_adi = 0L;
	Double valorTotal_adi = (double) 0;

	Long total1_req = 0L;
	Long total5_req = 0L;
	Long total10_req = 0L;
	Long total20_req = 0L;
	Long total50_req = 0L;
	Long total100_req = 0L;
	Double valorTotal_req = (double) 0;

	Long total1_ret = 0L;
	Long total5_ret = 0L;
	Long total10_ret = 0L;
	Long total20_ret = 0L;
	Long total50_ret = 0L;
	Long total100_ret = 0L;
	Double valorTotal_ret = (double) 0;

	Long total1_vue = 0L;
	Long total5_vue = 0L;
	Long total10_vue = 0L;
	Long total20_vue = 0L;
	Long total50_vue = 0L;
	Long total100_vue = 0L;
	Long total_m01_vue = 0L;
	Long total_m05_vue = 0L;
	Long total_m025_vue = 0L;
	Long total_m1_vue = 0L;
	Double valorTotal_vue = (double) 0;

	Long total_m01_sal = 0L;
	Long total_m05_sal = 0L;
	Long total_m025_sal = 0L;
	Long total_m1_sal = 0L;
	Long total1_sal = 0L;
	Long total5_sal = 0L;
	Long total10_sal = 0L;
	Long total20_sal = 0L;
	Long total50_sal = 0L;
	Long total100_sal = 0L;
	Double valorTotal_sal = (double) 0;

	@Override
	public void execute(Connection connection) throws SQLException {

		CallableStatement sp = connection.prepareCall("{call Reporte_ATMGetDatosCuadreMon(?, ?, ?, ?, ?)}");
		sp.setString(1, iDDispositivo);
		sp.setInt(2, seqCierre);
		sp.setInt(3, moneda);
		sp.setString(4, fechaCierre);
		sp.setInt(5, red);

		ResultSet resultado = sp.executeQuery();
		resultadoResumenAtm = new ArrayList<>();


		while (resultado.next()) {

			valdena = resultado.getFloat("Valdena");
			valdenb = resultado.getFloat("Valdenb");
			valdenc = resultado.getFloat("Valdenc");
			valdend = resultado.getFloat("Valdend");
			valdene = resultado.getFloat("Valdene");
			valdenf = resultado.getFloat("Valdenf");
			valdeng = resultado.getFloat("Valdeng");
			valdenh = resultado.getFloat("Valdenh");

			dispdena = resultado.getLong("Dispdena");
			dispdenb = resultado.getLong("Dispdenb");
			dispdenc = resultado.getLong("Dispdenc");
			dispdend = resultado.getLong("Dispdend");
			dispdene = resultado.getLong("Dispdene");
			dispdenf = resultado.getLong("Dispdenf");
			dispdeng = resultado.getLong("Dispdeng");
			dispdenh = resultado.getLong("Dispdenh");

			descripcionTrx = resultado.getString("DescripcionTransaccion");
			normalReverso = resultado.getInt("NormaloReverso");

			// INICIO DE CAJERO
			if (descripcionTrx.equals("INICIO DE CAJERO")) {

				if (valdena == 10.0) {
					total10_inicio += dispdena;
				} else if (valdena == 20.0) {
					total20_inicio += dispdena;
				} else if (valdena == 0.01F) {
					total_m01 += dispdena;
				} else if (valdena == 0.05F) {
					total_m05 += dispdena;
				} else if (valdena == 0.25F) {
					total_m025 += dispdena;
				} else if (valdena == 1.0) {
					total_m1 += dispdena;
				}

				if (valdenb == 10.0) {
					total10_inicio += dispdenb;
				} else if (valdenb == 20.0) {
					total20_inicio += dispdenb;
				} else if (valdenb == 0.01F) {
					total_m01 += dispdenb;
				} else if (valdenb == 0.05F) {
					total_m05 += dispdenb;
				} else if (valdenb == 0.25F) {
					total_m025 += dispdenb;
				} else if (valdenb == 1.0) {
					total_m1 += dispdenb;
				}

				if (valdenc == 10.0) {
					total10_inicio += dispdenc;
				} else if (valdenc == 20.0) {
					total20_inicio += dispdenc;
				} else if (valdenc == 0.01F) {
					total_m01 += dispdenc;
				} else if (valdenc == 0.05F) {
					total_m05 += dispdenc;
				} else if (valdenc == 0.25F) {
					total_m025 += dispdenc;
				} else if (valdenc == 1.0) {
					total_m1 += dispdenc;
				}

				if (valdend == 10.0) {
					total10_inicio += dispdend;
				} else if (valdend == 20.0) {
					total20_inicio += dispdend;
				} else if (valdend == 0.01F) {
					total_m01 += dispdend;
				} else if (valdend == 0.05F) {
					total_m05 += dispdend;
				} else if (valdend == 0.25F) {
					total_m025 += dispdend;
				} else if (valdend == 1.0) {
					total_m1 += dispdend;
				}

				if (valdene == 10.0) {
					total10_inicio += dispdene;
				} else if (valdene == 20.0) {
					total20_inicio += dispdene;
				} else if (valdene == 0.01F) {
					total_m01 += dispdene;
				} else if (valdene == 0.05F) {
					total_m05 += dispdene;
				} else if (valdene == 0.25F) {
					total_m025 += dispdene;
				} else if (valdene == 1.0) {
					total_m1 += dispdene;
				}

				if (valdenf == 10.0) {
					total10_inicio += dispdenf;
				} else if (valdenf == 20.0) {
					total20_inicio += dispdenf;
				} else if (valdenf == 0.01F) {
					total_m01 += dispdenf;
				} else if (valdenf == 0.05F) {
					total_m05 += dispdenf;
				} else if (valdenf == 0.25F) {
					total_m025 += dispdenf;
				} else if (valdenf == 1.0) {
					total_m1 += dispdenf;
				}

			}
			
			// ADICION EFECTIVO
			if (descripcionTrx.equals("Adicion de Efectivo")) {

							if (valdena == 10.0) {
								total10_adi += dispdena;
							} else if (valdena == 20.0) {
								total20_adi += dispdena;
							} else if (valdena == 0.01F) {
								total_m01_adi += dispdena;
							} else if (valdena == 0.05F) {
								total_m05_adi += dispdena;
							} else if (valdena == 0.25F) {
								total_m025_adi += dispdena;
							} else if (valdena == 1.0) {
								total_m1_adi += dispdena;
							}

							if (valdenb == 10.0) {
								total10_adi += dispdenb;
							} else if (valdenb == 20.0) {
								total20_adi += dispdenb;
							} else if (valdenb == 0.01F) {
								total_m01_adi += dispdenb;
							} else if (valdenb == 0.05F) {
								total_m05_adi += dispdenb;
							} else if (valdenb == 0.25F) {
								total_m025_adi += dispdenb;
							} else if (valdenb == 1.0) {
								total_m1_adi += dispdenb;
							}

							if (valdenc == 10.0) {
								total10_adi += dispdenc;
							} else if (valdenc == 20.0) {
								total20_adi += dispdenc;
							} else if (valdenc == 0.01F) {
								total_m01_adi += dispdenc;
							} else if (valdenc == 0.05F) {
								total_m05_adi += dispdenc;
							} else if (valdenc == 0.25F) {
								total_m025_adi += dispdenc;
							} else if (valdenc == 1.0) {
								total_m1_adi += dispdenc;
							}

							if (valdend == 10.0) {
								total10_adi += dispdend;
							} else if (valdend == 20.0) {
								total20_adi += dispdend;
							} else if (valdend == 0.01F) {
								total_m01_adi += dispdend;
							} else if (valdend == 0.05F) {
								total_m05_adi += dispdend;
							} else if (valdend == 0.25F) {
								total_m025_adi += dispdend;
							} else if (valdend == 1.0) {
								total_m1_adi += dispdend;
							}

							if (valdene == 10.0) {
								total10_adi += dispdene;
							} else if (valdene == 20.0) {
								total20_adi += dispdene;
							} else if (valdene == 0.01F) {
								total_m01_adi += dispdene;
							} else if (valdene == 0.05F) {
								total_m05_adi += dispdene;
							} else if (valdene == 0.25F) {
								total_m025_adi += dispdene;
							} else if (valdene == 1.0) {
								total_m1_adi += dispdene;
							}

							if (valdenf == 10.0) {
								total10_adi += dispdenf;
							} else if (valdenf == 20.0) {
								total20_adi += dispdenf;
							} else if (valdenf == 0.01F) {
								total_m01_adi += dispdenf;
							} else if (valdenf == 0.05F) {
								total_m05_adi += dispdenf;
							} else if (valdenf == 0.25F) {
								total_m025_adi += dispdenf;
							} else if (valdenf == 1.0) {
								total_m1_adi += dispdenf;
							}

						}

			// DEPOSITOS
			if (descripcionTrx.equals("DEPOSITO") || descripcionTrx.equals("Pago Prestamo")
					|| descripcionTrx.equals("Abono")) {

				if (valdena == 10.0) {
					total10 += dispdena;
				} else if (valdena == 20.0) {
					total20 += dispdena;
				} else if (valdena == 5.0) {
					total5 += dispdena;
				} else if (valdena == 1.0) {
					total1 += dispdena;
				} else if (valdena == 50.0) {
					total50 += dispdena;
				} else if (valdena == 100.0) {
					total100 += dispdena;
				}

				if (valdenb == 10.0) {
					total10 += dispdenb;
				} else if (valdenb == 20.0) {
					total20 += dispdenb;
				} else if (valdenb == 5.0) {
					total5 += dispdenb;
				} else if (valdenb == 1.0) {
					total1 += dispdenb;
				} else if (valdenb == 50.0) {
					total50 += dispdenb;
				} else if (valdenb == 100.0) {
					total100 += dispdenb;
				}

				if (valdenc == 10.0) {
					total10 += dispdenc;
				} else if (valdenc == 20.0) {
					total20 += dispdenc;
				} else if (valdenc == 5.0) {
					total5 += dispdenc;
				} else if (valdenc == 1.0) {
					total1 += dispdenc;
				} else if (valdenc == 50.0) {
					total50 += dispdenc;
				} else if (valdenc == 100.0) {
					total100 += dispdenc;
				}

				if (valdend == 10.0) {
					total10 += dispdend;
				} else if (valdend == 20.0) {
					total20 += dispdend;
				} else if (valdend == 5.0) {
					total5 += dispdend;
				} else if (valdend == 1.0) {
					total1 += dispdend;
				} else if (valdend == 50.0) {
					total50 += dispdend;
				} else if (valdend == 100.0) {
					total100 += dispdend;
				}

				if (valdene == 10.0) {
					total10 += dispdene;
				} else if (valdene == 20.0) {
					total20 += dispdene;
				} else if (valdene == 5.0) {
					total5 += dispdene;
				} else if (valdene == 1.0) {
					total1 += dispdene;
				} else if (valdene == 50.0) {
					total50 += dispdene;
				} else if (valdene == 100.0) {
					total100 += dispdene;
				}

				if (valdenf == 10.0) {
					total10 += dispdenf;
				} else if (valdenf == 20.0) {
					total20 += dispdenf;
				} else if (valdenf == 5.0) {
					total5 += dispdenf;
				} else if (valdenf == 1.0) {
					total1 += dispdenf;
				} else if (valdenf == 50.0) {
					total50 += dispdenf;
				} else if (valdenf == 100.0) {
					total100 += dispdenf;
				}

				if (valdeng == 10.0) {
					total10 += dispdeng;
				} else if (valdeng == 20.0) {
					total20 += dispdeng;
				} else if (valdeng == 5.0) {
					total5 += dispdeng;
				} else if (valdeng == 1.0) {
					total1 += dispdeng;
				} else if (valdeng == 50.0) {
					total50 += dispdeng;
				} else if (valdeng == 100.0) {
					total100 += dispdeng;
				}

				if (valdenh == 10.0) {
					total10 += dispdenh;
				} else if (valdenh == 20.0) {
					total20 += dispdenh;
				} else if (valdenh == 5.0) {
					total5 += dispdenh;
				} else if (valdenh == 1.0) {
					total1 += dispdenh;
				} else if (valdenh == 50.0) {
					total50 += dispdenh;
				} else if (valdenh == 100.0) {
					total100 += dispdenh;
				}
			}

			// REQUERIDO
			if (descripcionTrx.equals("RETIRO")) {

				if (valdena == 10.0) {
					total10_req += dispdena;
				} else if (valdena == 20.0) {
					total20_req += dispdena;
				} else if (valdena == 5.0) {
					total5_req += dispdena;
				} else if (valdena == 1.0) {
					total1_req += dispdena;
				} else if (valdena == 50.0) {
					total50_req += dispdena;
				} else if (valdena == 100.0) {
					total100_req += dispdena;
				}

				if (valdenb == 10.0) {
					total10_req += dispdenb;
				} else if (valdenb == 20.0) {
					total20_req += dispdenb;
				} else if (valdenb == 5.0) {
					total5_req += dispdenb;
				} else if (valdenb == 1.0) {
					total1_req += dispdenb;
				} else if (valdenb == 50.0) {
					total50_req += dispdenb;
				} else if (valdenb == 100.0) {
					total100_req += dispdenb;
				}

				if (valdenc == 10.0) {
					total10_req += dispdenc;
				} else if (valdenc == 20.0) {
					total20_req += dispdenc;
				} else if (valdenc == 5.0) {
					total5_req += dispdenc;
				} else if (valdenc == 1.0) {
					total1_req += dispdenc;
				} else if (valdenc == 50.0) {
					total50_req += dispdenc;
				} else if (valdenc == 100.0) {
					total100_req += dispdenc;
				}

				if (valdend == 10.0) {
					total10_req += dispdend;
				} else if (valdend == 20.0) {
					total20_req += dispdend;
				} else if (valdend == 5.0) {
					total5_req += dispdend;
				} else if (valdend == 1.0) {
					total1_req += dispdend;
				} else if (valdend == 50.0) {
					total50_req += dispdend;
				} else if (valdend == 100.0) {
					total100_req += dispdend;
				}

				if (valdene == 10.0) {
					total10_req += dispdene;
				} else if (valdene == 20.0) {
					total20_req += dispdene;
				} else if (valdene == 5.0) {
					total5_req += dispdene;
				} else if (valdene == 1.0) {
					total1_req += dispdene;
				} else if (valdene == 50.0) {
					total50_req += dispdene;
				} else if (valdene == 100.0) {
					total100_req += dispdene;
				}

				if (valdenf == 10.0) {
					total10_req += dispdenf;
				} else if (valdenf == 20.0) {
					total20_req += dispdenf;
				} else if (valdenf == 5.0) {
					total5_req += dispdenf;
				} else if (valdenf == 1.0) {
					total1_req += dispdenf;
				} else if (valdenf == 50.0) {
					total50_req += dispdenf;
				} else if (valdenf == 100.0) {
					total100_req += dispdenf;
				}

				if (valdeng == 10.0) {
					total10_req += dispdeng;
				} else if (valdeng == 20.0) {
					total20_req += dispdeng;
				} else if (valdeng == 5.0) {
					total5_req += dispdeng;
				} else if (valdeng == 1.0) {
					total1_req += dispdeng;
				} else if (valdeng == 50.0) {
					total50_req += dispdeng;
				} else if (valdeng == 100.0) {
					total100_req += dispdeng;
				}

				if (valdenh == 10.0) {
					total10_req += dispdenh;
				} else if (valdenh == 20.0) {
					total20_req += dispdenh;
				} else if (valdenh == 5.0) {
					total5_req += dispdenh;
				} else if (valdenh == 1.0) {
					total1_req += dispdenh;
				} else if (valdenh == 50.0) {
					total50_req += dispdenh;
				} else if (valdenh == 100.0) {
					total100_req += dispdenh;
				}
			}

			// RETIRO
			if (descripcionTrx.equals("RETIRO") && normalReverso.equals(0)) {

				if (valdena == 10.0) {
					total10_ret += dispdena;
				} else if (valdena == 20.0) {
					total20_ret += dispdena;
				} else if (valdena == 5.0) {
					total5_ret += dispdena;
				} else if (valdena == 1.0) {
					total1_ret += dispdena;
				} else if (valdena == 50.0) {
					total50_ret += dispdena;
				} else if (valdena == 100.0) {
					total100_ret += dispdena;
				}

				if (valdenb == 10.0) {
					total10_ret += dispdenb;
				} else if (valdenb == 20.0) {
					total20_ret += dispdenb;
				} else if (valdenb == 5.0) {
					total5_ret += dispdenb;
				} else if (valdenb == 1.0) {
					total1_ret += dispdenb;
				} else if (valdenb == 50.0) {
					total50_ret += dispdenb;
				} else if (valdenb == 100.0) {
					total100_ret += dispdenb;
				}

				if (valdenc == 10.0) {
					total10_ret += dispdenc;
				} else if (valdenc == 20.0) {
					total20_ret += dispdenc;
				} else if (valdenc == 5.0) {
					total5_ret += dispdenc;
				} else if (valdenc == 1.0) {
					total1_ret += dispdenc;
				} else if (valdenc == 50.0) {
					total50_ret += dispdenc;
				} else if (valdenc == 100.0) {
					total100_ret += dispdenc;
				}

				if (valdend == 10.0) {
					total10_ret += dispdend;
				} else if (valdend == 20.0) {
					total20_ret += dispdend;
				} else if (valdend == 5.0) {
					total5_ret += dispdend;
				} else if (valdend == 1.0) {
					total1_ret += dispdend;
				} else if (valdend == 50.0) {
					total50_ret += dispdend;
				} else if (valdend == 100.0) {
					total100_ret += dispdend;
				}

				if (valdene == 10.0) {
					total10_ret += dispdene;
				} else if (valdene == 20.0) {
					total20_ret += dispdene;
				} else if (valdene == 5.0) {
					total5_ret += dispdene;
				} else if (valdene == 1.0) {
					total1_ret += dispdene;
				} else if (valdene == 50.0) {
					total50_ret += dispdene;
				} else if (valdene == 100.0) {
					total100_ret += dispdene;
				}

				if (valdenf == 10.0) {
					total10_ret += dispdenf;
				} else if (valdenf == 20.0) {
					total20_ret += dispdenf;
				} else if (valdenf == 5.0) {
					total5_ret += dispdenf;
				} else if (valdenf == 1.0) {
					total1_ret += dispdenf;
				} else if (valdenf == 50.0) {
					total50_ret += dispdenf;
				} else if (valdenf == 100.0) {
					total100_ret += dispdenf;
				}

				if (valdeng == 10.0) {
					total10_ret += dispdeng;
				} else if (valdeng == 20.0) {
					total20_ret += dispdeng;
				} else if (valdeng == 5.0) {
					total5_ret += dispdeng;
				} else if (valdeng == 1.0) {
					total1_ret += dispdeng;
				} else if (valdeng == 50.0) {
					total50_ret += dispdeng;
				} else if (valdeng == 100.0) {
					total100_ret += dispdeng;
				}

				if (valdenh == 10.0) {
					total10_ret += dispdenh;
				} else if (valdenh == 20.0) {
					total20_ret += dispdenh;
				} else if (valdenh == 5.0) {
					total5_ret += dispdenh;
				} else if (valdenh == 1.0) {
					total1_ret += dispdenh;
				} else if (valdenh == 50.0) {
					total50_ret += dispdenh;
				} else if (valdenh == 100.0) {
					total100_ret += dispdenh;
				}
			}

			// Vuelto
			if (descripcionTrx.equals("Vuelto") && normalReverso.equals(0)) {			
				

				if (valdena == 0.01F) {
					total_m01_vue += dispdena;
				} else if (valdena == 0.05F) {
					total_m05_vue += dispdena;
				} else if (valdena == 0.25F) {
					total_m025_vue += dispdena;
				} else if (valdena == 1.0) {
					total_m1_vue += dispdena;
				} else if (valdena == 10.0) {
					total10_vue += dispdena;
				} else if (valdena == 20.0) {
					total20_vue += dispdena;
				}
				

				if (valdenb == 0.01F) {
					total_m01_vue += dispdenb;
				} else if (valdenb == 0.05F) {
					total_m05_vue += dispdenb;
				} else if (valdenb == 0.25F) {
					total_m025_vue += dispdenb;
				} else if (valdenb == 1.0) {
					total_m1_vue += dispdenb;
				} else if (valdenb == 10.0) {
					total10_vue += dispdenb;
				} else if (valdenb == 20.0) {
					total20_vue += dispdenb;
				}
				

				if (valdenc == 0.01F) {
					total_m01_vue += dispdenc;
				} else if (valdenc == 0.05F) {
					total_m05_vue += dispdenc;
				} else if (valdenc == 0.25F) {
					total_m025_vue += dispdenc;
				} else if (valdenc == 1.0) {
					total_m1_vue += dispdenc;
				}

				if (valdend == 0.01F) {
					total_m01_vue += dispdend;
				} else if (valdend == 0.05F) {
					total_m05_vue += dispdend;
				} else if (valdend == 0.25F) {
					total_m025_vue += dispdend;
				} else if (valdend == 1.0) {
					total_m1_vue += dispdend;
				}

			}

		}

		valorTotal_inicio = (total10_inicio * 10) +
				            (total20_inicio * 20) +
				            (total_m01 * 0.01) +
				            (total_m05 * 0.05) +
				            (total_m025 * 0.25) +
				            (total_m1);
		
		valorTotal_adi = (total10_adi * 10) +
	            		 (total20_adi * 20) +
	            		 (total_m01_adi * 0.01) +
	            		 (total_m05_adi * 0.05) +
	            		 (total_m025_adi * 0.25) +
	            		 (total_m1_adi);
		

		valorTotal = (double) (total1 + 
				              (total5 * 5) + 
				              (total10 * 10) + 
				              (total20 * 20) + 
				              (total50 * 50) + 
				              (total100 * 100));

		valorTotal_req = (double) (total1_ret + 
				                  (total5_ret * 5) + 
				                  (total10_ret * 10) + 
				                  (total20_ret * 20));

		valorTotal_ret = (double) (total1_ret + 
				                  (total5_ret * 5) + 
				                  (total10_ret * 10) + 
				                  (total20_ret * 20));

		valorTotal_vue = (total10_vue * 10) +
	                     (total20_vue * 20) +
						 (total_m01_vue * 0.01) + 
				         (total_m05_vue * 0.05) + 
				         (total_m025_vue * 0.25) + 
				         (total_m1_vue);

		total_m01_sal = total_m01 - total_m01_vue + total_m01_adi;
		total_m05_sal = total_m05 - total_m05_vue + total_m05_adi;
		total_m025_sal = total_m025 - total_m025_vue + total_m025_adi;
		total_m1_sal = total_m1 - total_m1_vue + total_m1_adi;
		total1_sal = total1_inicio + total1 - total1_ret - total1_vue + total1_adi;
		total5_sal = total5_inicio + total5 - total5_ret - total5_vue + total5_adi;
		total10_sal = total10_inicio + total10 - total10_ret - total10_vue + total10_adi;
		total20_sal = total20_inicio + total20 - total20_ret - total20_vue + total20_adi;
		total50_sal = total50 - total50_ret - total50_vue;
		total100_sal = total100 - total100_ret - total100_vue;
		
		valorTotal_sal = (double) (total1_sal + 
				                  (total5_sal * 5) + 
				                  (total10_sal * 10) + 
				                  (total20_sal * 20) + 
				                  (total50_sal * 50) + 
				                  (total100_sal * 100) + 
				                  (total_m01_sal * 0.01) + 
				                  (total_m05_sal * 0.05) + 
				                  (total_m025_sal * 0.25) + 
				                  (total_m1_sal));

		ResultadoResumenAtm registro1 = new ResultadoResumenAtm();
		ResultadoResumenAtm registro_adi = new ResultadoResumenAtm();
		ResultadoResumenAtm registroD = new ResultadoResumenAtm();
		ResultadoResumenAtm registroR = new ResultadoResumenAtm();
		ResultadoResumenAtm registroReq = new ResultadoResumenAtm();
		ResultadoResumenAtm registroV = new ResultadoResumenAtm();
		ResultadoResumenAtm registroS = new ResultadoResumenAtm();

		registro1.setTransaccion("APERTURA");
		registro1.setM01(total_m01);
		registro1.setM05(total_m05);
		registro1.setM25(total_m025);
		registro1.setM1(total_m1);
		registro1.setB1(total1_inicio);
		registro1.setB5(total5_inicio);
		registro1.setB10(total10_inicio);
		registro1.setB20(total20_inicio);
		registro1.setB50(0L);
		registro1.setB100(0L);
		registro1.setTotal(valorTotal_inicio);
		resultadoResumenAtm.add(registro1);
		
		registro_adi.setTransaccion("ADICION");
		registro_adi.setM01(total_m01_adi);
		registro_adi.setM05(total_m05_adi);
		registro_adi.setM25(total_m025_adi);
		registro_adi.setM1(total_m1_adi);
		registro_adi.setB1(total1_adi);
		registro_adi.setB5(total5_adi);
		registro_adi.setB10(total10_adi);
		registro_adi.setB20(total20_adi);
		registro_adi.setB50(0L);
		registro_adi.setB100(0L);
		registro_adi.setTotal(valorTotal_adi);
		resultadoResumenAtm.add(registro_adi);

		registroD.setTransaccion("RECIBIDO DEL CLIENTE");
		registroD.setM01(0L);
		registroD.setM05(0L);
		registroD.setM25(0L);
		registroD.setM1(0L);
		registroD.setB1(total1);
		registroD.setB5(total5);
		registroD.setB10(total10);
		registroD.setB20(total20);
		registroD.setB50(total50);
		registroD.setB100(total100);
		registroD.setTotal(valorTotal);
		resultadoResumenAtm.add(registroD);

		registroReq.setTransaccion("REQUERIDO");
		registroReq.setM01(0L);
		registroReq.setM05(0L);
		registroReq.setM25(0L);
		registroReq.setM1(0L);
		registroReq.setB1(total1_req);
		registroReq.setB5(total5_req);
		registroReq.setB10(total10_req);
		registroReq.setB20(total20_req);
		registroReq.setB50(total50_req);
		registroReq.setB100(total100_req);
		registroReq.setTotal(valorTotal_req);
		resultadoResumenAtm.add(registroReq);

		registroR.setTransaccion("DISPENSADO");
		registroR.setM01(0L);
		registroR.setM05(0L);
		registroR.setM25(0L);
		registroR.setM1(0L);
		registroR.setB1(total1_ret);
		registroR.setB5(total5_ret);
		registroR.setB10(total10_ret);
		registroR.setB20(total20_ret);
		registroR.setB50(total50_ret);
		registroR.setB100(total100_ret);
		registroR.setTotal(valorTotal_ret);
		resultadoResumenAtm.add(registroR);

		registroV.setTransaccion("VUELTO");
		registroV.setM01(total_m01_vue);
		registroV.setM05(total_m05_vue);
		registroV.setM25(total_m025_vue);
		registroV.setM1(total_m1_vue);
		registroV.setB1(total1_vue);
		registroV.setB5(total5_vue);
		registroV.setB10(total10_vue);
		registroV.setB20(total20_vue);
		registroV.setB50(total50_vue);
		registroV.setB100(total100_vue);
		registroV.setTotal(valorTotal_vue);
		resultadoResumenAtm.add(registroV);

		registroS.setTransaccion("SALDOS");
		registroS.setM01(total_m01_sal);
		registroS.setM05(total_m05_sal);
		registroS.setM25(total_m025_sal);
		registroS.setM1(total_m1_sal);
		registroS.setB1(total1_sal);
		registroS.setB5(total5_sal);
		registroS.setB10(total10_sal);
		registroS.setB20(total20_sal);
		registroS.setB50(total50_sal);
		registroS.setB100(total100_sal);
		registroS.setTotal(valorTotal_sal);
		resultadoResumenAtm.add(registroS);

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

	public List<ResultadoResumenAtm> getResultadoReporteAtm() {
		return resultadoResumenAtm;
	}

}
