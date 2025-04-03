package com.api.rest.cuadre.atmrefactor.repositorios;

import com.api.rest.cuadre.atmrefactor.entidades.Comisiones;
import com.api.rest.cuadre.utils.Utilerias;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Slf4j
public class ComisionesRepositorio {

    private final JdbcTemplate jdbc;

    public ComisionesRepositorio(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Comisiones> listaComisionesR(String fechaDesde, String fechaHasta) {

        String query = "select rownum,\n" +
                "       numero_cuenta,\n" +
                "       nvl(secuencia_trx_cms, 0) secuencia_trx_cms,\n" +
                "       fecha_valida,\n" +
                "       to_char(hora, 'HH24:MI:SS') hora5,\n" +
                "       codigo_tipo_transaccion,\n" +
                "       descripcion,\n" +
                "       clase_cuenta,\n" +
                "       clase_de_cuenta,\n" +
                "       valor,\n" +
                "       codigo_agencia,\n" +
                "       nombre_agencia,\n" +
                "       numero_tarjeta,\n" +
                "       tipo_transaccion,\n" +
                "       codigo_canal_adquiriente\n" +
                "  from (select a.numero_cuenta,\n" +
                "               f.secuencia_trx_cms,\n" +
                "               a.fecha_valida,\n" +
                "               a.hora,\n" +
                "               e.codigo_tipo_transaccion,\n" +
                "               b.descripcion,\n" +
                "               e.valor,\n" +
                "               d.codigo_agencia,\n" +
                "               d.nombre_agencia,\n" +
                "               c.CLASE_DE_CUENTA,\n" +
                "               g.descripcion clase_cuenta,\n" +
                "               f.numero_tarjeta,\n" +
                "               decode(f.tipo_transaccion,\n" +
                "                      '0001',\n" +
                "                      '002',\n" +
                "                      decode(f.tipo_transaccion,\n" +
                "                             '0000',\n" +
                "                             '001',\n" +
                "                             decode(f.tipo_transaccion,\n" +
                "                                    '30',\n" +
                "                                    '001',\n" +
                "                                    f.tipo_transaccion))) TIPO_TRANSACCION,\n" +
                "               to_char(decode(f.origen,\n" +
                "                              0,\n" +
                "                              3,\n" +
                "                              decode(f.origen,\n" +
                "                                     1,\n" +
                "                                     2,\n" +
                "                                     decode(f.origen,\n" +
                "                                            2,\n" +
                "                                            0,\n" +
                "                                            decode(f.origen,\n" +
                "                                                   3,\n" +
                "                                                   1,\n" +
                "                                                   decode(f.origen,\n" +
                "                                                          4,\n" +
                "                                                          4,\n" +
                "                                                          f.origen)))))) CODIGO_CANAL_ADQUIRIENTE --origen\n" +
                "          from ca_movimientos_mensuales  a,\n" +
                "               mg_tipos_de_transacciones b,\n" +
                "               ca_cuentas_de_ahorro      c,\n" +
                "               mg_agencias               d,\n" +
                "               ca_desglose_atm           e,\n" +
                "               cms_log_por_trxs_en_linea f,\n" +
                "               mg_clase_cuentas          g\n" +
                "         where a.fecha_valida BETWEEN TO_DATE(?, 'DD-MM-YYYY') and TO_DATE(?, 'DD-MM-YYYY')\n" +
                "           and e.codigo_tipo_transaccion = b.codigo_tipo_transaccion\n" +
                "           and a.codigo_aplicacion = b.codigo_aplicacion\n" +
                "           and a.numero_cuenta = c.numero_cuenta\n" +
                "           and a.agencia_origen = d.codigo_agencia\n" +
                "           and c.numero_cuenta = e.numero_cuenta\n" +
                "           and c.clase_de_cuenta = g.codigo_clase_cuenta\n" +
                "           and a.secuencia_movimiento = e.secuencia_movimiento\n" +
                "           and a.fecha_valida = e.fecha_valida\n" +
                "           and e.codigo_tipo_transaccion in\n" +
                "               (309,\n" +
                "                420,\n" +
                "                310,\n" +
                "                424,\n" +
                "                319,\n" +
                "                421,\n" +
                "                320,\n" +
                "                422,\n" +
                "                321,\n" +
                "                423,\n" +
                "                318,\n" +
                "                322,\n" +
                "                904)\n" +
                "           and (f.secuencia_trx_abanks = A.SECUENCIA_MOVIMIENTO OR\n" +
                "               f.secuencia_comic_abanks = A.SECUENCIA_MOVIMIENTO OR\n" +
                "               f.secuemontoretconsaldo_abk = A.SECUENCIA_MOVIMIENTO)\n" +
                "           and nvl(a.situacion_movimiento, 0) <> 5\n" +
                "           and nvl(e.situacion_movimiento, 0) <> 5\n" +
                "           and e.valor > 0)\n" +
                " order by fecha_valida, hora";

        try {
            @SuppressWarnings("deprecation")
            List<Comisiones> resultado = jdbc.query(query, new Object[]{fechaDesde, fechaHasta}, new RowMapper<Comisiones>() {

                public Comisiones mapRow(ResultSet rs, int i) throws SQLException {
                    Comisiones com = new Comisiones();
                    com.setId(rs.getLong("rownum"));
                    com.setNumeroCuenta(rs.getLong("numero_cuenta"));
                    com.setSecuenciaTrxCms(rs.getInt("secuencia_trx_cms"));
                    com.setFechaValida(rs.getDate("fecha_valida"));
                    com.setHora(rs.getString("hora5"));
                    com.setCodigoTipoTransaccion(rs.getInt("codigo_tipo_transaccion"));
                    com.setDescripcion(rs.getString("descripcion"));
                    com.setClaseCuenta(rs.getString("clase_cuenta"));
                    com.setClaseDeCuenta(rs.getInt("clase_de_cuenta"));
                    com.setValor(rs.getFloat("valor"));
                    com.setCodigoAgencia(rs.getInt("codigo_agencia"));
                    com.setNombreAgencia(rs.getString("nombre_agencia"));
                    com.setNumeroTarjeta(rs.getString("numero_tarjeta"));
                    com.setTipoTransaccion(rs.getString("tipo_transaccion"));
                    com.setCodigoCanalAdquiriente(rs.getString("codigo_canal_adquiriente"));

                    return com;
                }
            });

            return resultado;
        } catch (DataAccessException e) {
            log.error("Error al ejecutar la consulta SQL: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno al consultar datos.");
        } catch (Exception e) {
            log.error("Error inesperado: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error inesperado.");
        }
    }
/*
    public List<Comisiones> listaComisiones(String gdFechaDesde, String gdFechaHasta) throws Exception {

        log.info("repositorio");
        List<Comisiones> comisiones = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {

            CallableStatement cs = connection.prepareCall("{call RPA_P_COMISIONES_ATM2(?, ?, ?)}");
            cs.setString(1, gdFechaDesde);
            cs.setString(2, gdFechaHasta);
            cs.registerOutParameter(3, OracleTypes.CURSOR);
            cs.execute();

            ResultSet rs = (ResultSet) cs.getObject(3);

            while (rs.next()) {
                Comisiones comision = new Comisiones();
                comision.setId(rs.getLong("rownum"));
                comision.setNumeroCuenta(rs.getLong("numero_cuenta"));
                comision.setSecuenciaTrxCms(rs.getInt("secuencia_trx_cms"));
                comision.setFechaValida(rs.getDate("fecha_valida"));
                comision.setHora(rs.getString("hora5"));
                comision.setCodigoTipoTransaccion(rs.getInt("codigo_tipo_transaccion"));
                comision.setDescripcion(rs.getString("descripcion"));
                comision.setClaseCuenta(rs.getString("clase_cuenta"));
                comision.setClaseDeCuenta(rs.getInt("CLASE_DE_CUENTA"));
                comision.setValor(rs.getFloat("valor"));
                comision.setCodigoAgencia(rs.getInt("codigo_agencia"));
                comision.setNombreAgencia(rs.getString("nombre_agencia"));
                comision.setNumeroTarjeta(rs.getString("NUMERO_TARJETA"));
                comision.setTipoTransaccion(rs.getString("TIPO_TRANSACCION"));
                comision.setCodigoCanalAdquiriente(rs.getString("CODIGO_CANAL_ADQUIRIENTE"));

                comisiones.add(comision);
            }

            rs.close();
            cs.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al ejecutar el procedimiento RPA_P_COMISIONES_ATM2", e);
        }

        return comisiones;
    }
*/

	/*
	@Transactional
    @Modifying
    @Query(nativeQuery = true, value = "call RPA_P_COMISIONES_ATM(:Gd_fecha_desde, :Gd_fecha_hasta)")
    void listaProcedure_PP(@Param("Gd_fecha_desde") String gdFechaDesde,
                           @Param("Gd_fecha_hasta") String gdFechaHasta);
*/

}
