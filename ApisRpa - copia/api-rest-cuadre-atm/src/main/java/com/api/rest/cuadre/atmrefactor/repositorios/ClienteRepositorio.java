package com.api.rest.cuadre.atmrefactor.repositorios;

import com.api.rest.cuadre.atmrefactor.entidades.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ClienteRepositorio extends JpaRepository<Clientes, Long> {

	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "call RPA_P_CLIENTES_X_TRX(:Gd_fecha_desde, :Gd_fecha_hasta)")
	void listaClientes(@Param("Gd_fecha_desde") String gdFechaDesde,
					   @Param("Gd_fecha_hasta") String gdFechaHasta);
}

/*
	private final DataSource dataSource;
	private final JdbcTemplate jdbc;

	public ClienteRepositorio(JdbcTemplate jdbc){
		this.jdbc = jdbc;
	}

	public List<Clientes> listaClientesTrx(String fechaDesde, String fechaHasta){
		String query = "select rownum,\n" +
				"           a.codigo_usuario,\n" +
				"           a.numero_cuenta,\n" +
				"           d.numero_identificacion,\n" +
				"           a.fecha_valida,\n" +
				"           a.codigo_tipo_transaccion,\n" +
				"           a.codigo_agencia,\n" +
				"           b.nombre_agencia,\n" +
				"           a.VALOR base_imponible,\n" +
				"           a.secuencia_movimiento_origen,\n" +
				"           a.codigo_aplicacion,\n" +
				"           a.codigo_subaplicacion,\n" +
				"           nvl((select sum(Xmltype.Createxml(x.Xml_Generado).Extract('//totalConImpuestos/totalImpuesto/valor/text()')\n" +
				"                          .Getstringval())\n" +
				"                 from Tfac_Comprobantes x\n" +
				"                where x.Secuencia_Origen in\n" +
				"                      ((select Secuencia_Comic_Abanks\n" +
				"                         from Cms_Log_Por_Trxs_En_Linea\n" +
				"                        where Nro_Cuenta = a.numero_cuenta\n" +
				"                          and fecha_valida = a.fecha_valida\n" +
				"                          and secuencia_trx_abanks =\n" +
				"                              a.secuencia_movimiento_origen),\n" +
				"                       (select Secuemontoretconsaldo_Abk\n" +
				"                          from Cms_Log_Por_Trxs_En_Linea\n" +
				"                         where Nro_Cuenta = a.numero_cuenta\n" +
				"                           and fecha_valida = a.fecha_valida\n" +
				"                           and secuencia_trx_abanks =\n" +
				"                               a.secuencia_movimiento_origen))\n" +
				"                  and x.Codigo_Cliente = d.codigo_cliente\n" +
				"                  and x.Numero_Cuenta = a.numero_cuenta\n" +
				"                  and Trunc(x.Fecha_Registro) = a.fecha_valida),\n" +
				"               0) iva,\n" +
				"           -----------------------------------------------------\n" +
				"           nvl((select sum(Xmltype.Createxml(x.Xml_Generado).Extract('//totalSinImpuestos/text()')\n" +
				"                          .Getstringval())\n" +
				"                 from Tfac_Comprobantes x\n" +
				"                where x.Secuencia_Origen in\n" +
				"                      ((select Secuencia_Comic_Abanks\n" +
				"                         from Cms_Log_Por_Trxs_En_Linea\n" +
				"                        where Nro_Cuenta = a.numero_cuenta\n" +
				"                          and fecha_valida = a.fecha_valida\n" +
				"                          and secuencia_trx_abanks =\n" +
				"                              a.secuencia_movimiento_origen),\n" +
				"                       (select Secuemontoretconsaldo_Abk\n" +
				"                          from Cms_Log_Por_Trxs_En_Linea\n" +
				"                         where Nro_Cuenta = a.numero_cuenta\n" +
				"                           and fecha_valida = a.fecha_valida\n" +
				"                           and secuencia_trx_abanks =\n" +
				"                               a.secuencia_movimiento_origen))\n" +
				"                  and x.Codigo_Cliente = d.codigo_cliente\n" +
				"                  and x.Numero_Cuenta = a.numero_cuenta\n" +
				"                  and Trunc(x.Fecha_Registro) = a.fecha_valida),\n" +
				"               0) valor_sin_impuestos,\n" +
				"           -----------------------------------------------------          \n" +
				"           a.VALOR valor_total\n" +
				"      from ca_movimientos_MENSUALES a\n" +
				"      join mg_Agencias b\n" +
				"        on a.codigo_agencia = b.codigo_agencia\n" +
				"      join ca_cuentas_de_ahorro c\n" +
				"        on a.numero_cuenta = c.numero_cuenta\n" +
				"      join mg_clientes d\n" +
				"        on c.codigo_cliente = d.codigo_cliente\n" +
				"     where a.codigo_tipo_transaccion in (190, 552, 372, 946)\n" +
				"       and a.situacion_movimiento <> 5\n" +
				"       and a.fecha_valida between TO_DATE(?, 'DD-MM-YYYY') and TO_DATE(?, 'DD-MM-YYYY')\n" +
				"     order by a.CODIGO_AGENCIA, a.CODIGO_TIPO_TRANSACCION";

		@SuppressWarnings("deprecation")
		List<Clientes> resultado = jdbc.query(query, new Object[] {fechaDesde, fechaHasta}, new RowMapper<Clientes>() {
			public Clientes mapRow(ResultSet rs, int i) throws SQLException {
				Clientes cli = new Clientes();
				cli.setId(rs.getLong("rownum"));
				cli.setCodigoUsuario(rs.getString("codigo_usuario"));
				cli.setNumeroCuenta(rs.getLong("numero_cuenta"));
				cli.setNumeroIdentificacion(rs.getString("numero_identificacion"));
				cli.setFechaValida(rs.getDate("fecha_valida"));
				cli.setCodigoTipoTransaccion(rs.getInt("codigo_tipo_transaccion"));
				cli.setCodigoAgencia(rs.getInt("codigo_agencia"));
				cli.setNombreAgencia(rs.getString("nombre_agencia"));
				cli.setBaseImponible(rs.getFloat("base_imponible"));
				cli.setSecuenciaMovimientoOrigen(rs.getLong("secuencia_movimiento_origen"));
				cli.setCodigoAplicacion(rs.getString("codigo_aplicacion"));
				cli.setCodigoSubAplicacion(rs.getInt("codigo_subaplicacion"));
				cli.setIva(rs.getFloat("iva"));
				cli.setValorSinImpuestos(rs.getFloat("valor_sin_impuestos"));
				cli.setValorTotal(rs.getFloat("valor_total"));
				return cli;
			}
		});
		return resultado;
	}

*/

/*
	public List<Clientes> listaClientesTrx(String fechaDesde, String fechaHasta) throws  Exception{
		log.info(" ");

		List<Clientes> listaClientes = new ArrayList<>();

		try (Connection connection = dataSource.getConnection()){
			CallableStatement cs = connection.prepareCall("{call RPA_P_CLIENTES_X_TRX2(?, ?, ?)}");
			cs.setString(1, fechaDesde);
			cs.setString(2, fechaHasta);
			cs.registerOutParameter(3, OracleTypes.CURSOR);
			cs.execute();

			ResultSet rs = (ResultSet) cs.getObject(3);

			while (rs.next()){
				Clientes cli = new Clientes();
				cli.setId(rs.getLong("rownum"));
				cli.setCodigoUsuario(rs.getString("codigo_usuario"));
				cli.setNumeroCuenta(rs.getLong("numero_cuenta"));
				cli.setNumeroIdentificacion(rs.getString("numero_identificacion"));
				cli.setFechaValida(rs.getDate("fecha_valida"));
				cli.setCodigoTipoTransaccion(rs.getInt("codigo_tipo_transaccion"));
				cli.setCodigoAgencia(rs.getInt("codigo_agencia"));
				cli.setNombreAgencia(rs.getString("nombre_agencia"));
				cli.setBaseImponible(rs.getFloat("base_imponible"));
				cli.setSecuenciaMovimientoOrigen(rs.getLong("secuencia_movimiento_origen"));
				cli.setCodigoAplicacion(rs.getString("codigo_aplicacion"));
				cli.setCodigoSubAplicacion(rs.getInt("codigo_subaplicacion"));
				cli.setIva(rs.getFloat("iva"));
				cli.setValorSinImpuestos(rs.getFloat("valor_sin_impuestos"));
				cli.setValorTotal(rs.getFloat("valor_total"));

				listaClientes.add(cli);
			}
			rs.close();
			cs.close();

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al ejecutar el procedimiento RPA_P_TRX_DIARIAS_ATM2", e);
		}
		return listaClientes;
	}
*/


