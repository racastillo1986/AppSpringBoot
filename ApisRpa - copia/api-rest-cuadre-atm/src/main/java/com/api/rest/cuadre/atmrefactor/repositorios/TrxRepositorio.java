package com.api.rest.cuadre.atmrefactor.repositorios;

import com.api.rest.cuadre.atmrefactor.entidades.TrxDiarias;
import com.api.rest.cuadre.utils.Utilerias;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.internal.OracleTypes;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class TrxRepositorio {

    private final DataSource dataSource;

    public TrxRepositorio(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<TrxDiarias> listaTrx(String fechaDesde, String fechaHasta) throws Exception {

        List<TrxDiarias> listaTrx = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            CallableStatement cs = connection.prepareCall("{call RPA_P_TRX_DIARIAS_ATM2(?, ?, ?)}");
            cs.setString(1, fechaDesde);
            cs.setString(2, fechaHasta);
            cs.registerOutParameter(3, OracleTypes.CURSOR);
            cs.execute();

            ResultSet rs = (ResultSet) cs.getObject(3);

            while (rs.next()) {
                TrxDiarias trx = new TrxDiarias();
                trx.setId(rs.getLong("rownum"));
                trx.setNumeroCuenta(rs.getLong("numero_cuenta"));
                trx.setSecuenciaTrxCms(rs.getInt("secuencia_trx_cms"));
                trx.setFechaValida(rs.getDate("fecha_valida"));
                trx.setHora(rs.getString("hora5"));
                trx.setCodigoTipoTransaccion(rs.getInt("codigo_tipo_transaccion"));
                trx.setDescripcion(rs.getString("descripcion"));
                trx.setClaseCuenta(rs.getString("clase_cuenta"));
                trx.setClaseDeCuenta(rs.getInt("CLASE_DE_CUENTA"));
                trx.setDescClaseCuenta(rs.getString("DESC_CLASE_CUENTA"));
                trx.setValor(rs.getFloat("valor"));
                trx.setCodigoAgencia(rs.getInt("codigo_agencia"));
                trx.setNombreAgencia(rs.getString("nombre_agencia"));
                trx.setNumeroTarjeta(rs.getString("NUMERO_TARJETA"));
                trx.setTipoTransaccion(rs.getString("TIPO_TRANSACCION"));
                trx.setCodigoCanalAdquiriente(rs.getString("CODIGO_CANAL_ADQUIRIENTE"));
                listaTrx.add(trx);
            }
            rs.close();
            cs.close();
        } catch (DataAccessException e) {
            log.error("Error al ejecutar la consulta SQL: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno al consultar datos.");
        } catch (Exception e) {
            log.error("Error inesperado: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error inesperado.");
        }
        return listaTrx;
    }
/*
    public List<TrxDiarias> listaTrx(String fechaDesde, String fechaHasta) {

        if (!utilerias.esFechaValida(fechaDesde) || !utilerias.esFechaValida(fechaHasta)) {
            log.error("Fecha invalida proporcionadaaaa.");
            log.info("*********************************************************************************************************");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fecha invalida proporcionada.");
        }

        String query = "select rownum,\n" +
                "       numero_cuenta,\n" +
                "       nvl(secuencia_trx_cms, 0) secuencia_trx_cms,\n" +
                "       fecha_valida,\n" +
                "       to_char(hora, 'HH24:MI:SS') hora5,\n" +
                "       codigo_tipo_transaccion,\n" +
                "       descripcion,\n" +
                "       clase_cuenta,\n" +
                "       CLASE_DE_CUENTA,\n" +
                "       DECODE(CLASE_DE_CUENTA,\n" +
                "              5,\n" +
                "              'CUENTAS BASICAS',\n" +
                "              'CUENTAS NORMALES E INFANTILES') DESC_CLASE_CUENTA,\n" +
                "       valor,\n" +
                "       codigo_agencia,\n" +
                "       nombre_agencia,\n" +
                "       NUMERO_TARJETA,\n" +
                "       TIPO_TRANSACCION,\n" +
                "       CODIGO_CANAL_ADQUIRIENTE\n" +
                "  from (select a.numero_cuenta,\n" +
                "               NULL                        secuencia_trx_cms,\n" +
                "               a.fecha_valida,\n" +
                "               a.hora,\n" +
                "               a.codigo_tipo_transaccion,\n" +
                "               b.descripcion,\n" +
                "               a.valor,\n" +
                "               d.codigo_agencia,\n" +
                "               d.nombre_agencia,\n" +
                "               c.CLASE_DE_CUENTA,\n" +
                "               e.descripcion               clase_cuenta,\n" +
                "               LA.NUMERO_TARJETA,\n" +
                "               LA.TIPO_TRANSACCION,\n" +
                "               LA.CODIGO_CANAL_ADQUIRIENTE\n" +
                "          from ca_movimientos_mensuales               a,\n" +
                "               mg_tipos_de_transacciones              b,\n" +
                "               ca_cuentas_de_ahorro                   c,\n" +
                "               mg_agencias                            d,\n" +
                "               IB4CADMIN.TAM_LOG_AUTORIZACION         LA,\n" +
                "               IB4CADMIN.TAM_DETALLE_LOG_AUTORIZACION DL,\n" +
                "               mg_clase_cuentas                       e\n" +
                "         where a.codigo_tipo_transaccion in\n" +
                "               (193, 194, 195, 196, 197, 179, 308)\n" +
                "           and a.fecha_valida BETWEEN TO_DATE(?, 'DD-MM-YYYY') and TO_DATE(?, 'DD-MM-YYYY')\n" +
                "           and a.codigo_tipo_transaccion = b.codigo_tipo_transaccion\n" +
                "           and a.codigo_aplicacion = b.codigo_aplicacion\n" +
                "           and a.numero_cuenta = c.numero_cuenta\n" +
                "           and c.clase_de_cuenta = e.codigo_clase_cuenta\n" +
                "           and a.agencia_origen = d.codigo_agencia\n" +
                "           AND LA.SECUENCIAL_LOG = DL.SECUENCIAL_LOG\n" +
                "           and (DL.ID_TRX_RETIRO_COREBANK = A.SECUENCIA_MOVIMIENTO OR\n" +
                "               DL.ID_TRX_COMIS_COREBANK = A.SECUENCIA_MOVIMIENTO OR\n" +
                "               DL.ID_TRX_REVERS0_COREBANK = A.SECUENCIA_MOVIMIENTO OR\n" +
                "               DL.Id_Trx_Retsaldobanred_Corebank = A.SECUENCIA_MOVIMIENTO)\n" +
                "           and nvl(a.situacion_movimiento, 0) <> 5\n" +
                "        union all\n" +
                "        select a.numero_cuenta,\n" +
                "               f.secuencia_trx_cms,\n" +
                "               a.fecha_valida,\n" +
                "               a.hora,\n" +
                "               a.codigo_tipo_transaccion,\n" +
                "               b.descripcion,\n" +
                "               a.valor,\n" +
                "               d.codigo_agencia,\n" +
                "               d.nombre_agencia,\n" +
                "               c.CLASE_DE_CUENTA,\n" +
                "               e.descripcion clase_cuenta,\n" +
                "               f.numero_tarjeta NUMERO_TARJETA,\n" +
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
                "                                                          f.origen)))))) CODIGO_CANAL_ADQUIRIENTE\n" +
                "          from ca_movimientos_mensuales  a,\n" +
                "               mg_tipos_de_transacciones b,\n" +
                "               ca_cuentas_de_ahorro      c,\n" +
                "               mg_agencias               d,\n" +
                "               cms_log_por_trxs_en_linea f,\n" +
                "               mg_clase_cuentas          e\n" +
                "         where a.codigo_tipo_transaccion in\n" +
                "               (193, 194, 195, 196, 197, 179, 308, 926, 946)\n" +
                "           and a.fecha_valida BETWEEN TO_DATE(?, 'DD-MM-YYYY') and TO_DATE(?, 'DD-MM-YYYY')\n" +
                "           and a.codigo_tipo_transaccion = b.codigo_tipo_transaccion\n" +
                "           and a.codigo_aplicacion = b.codigo_aplicacion\n" +
                "           and a.numero_cuenta = c.numero_cuenta\n" +
                "           and c.clase_de_cuenta = e.codigo_clase_cuenta\n" +
                "           and a.agencia_origen = d.codigo_agencia\n" +
                "           and (f.secuencia_trx_abanks = A.SECUENCIA_MOVIMIENTO OR\n" +
                "               f.secuencia_comic_abanks = A.SECUENCIA_MOVIMIENTO OR\n" +
                "               f.secuemontoretconsaldo_abk = A.SECUENCIA_MOVIMIENTO)\n" +
                "           and nvl(a.situacion_movimiento, 0) <> 5)\n" +
                " order by DESC_CLASE_CUENTA, fecha_valida, hora";

        try {
            @SuppressWarnings("deprecation")
            List<TrxDiarias> resultado = jdbc.query(query, new Object[]{fechaDesde, fechaHasta, fechaDesde, fechaHasta}, new RowMapper<TrxDiarias>() {
                public TrxDiarias mapRow(ResultSet rs, int i) throws SQLException {
                    TrxDiarias rp = new TrxDiarias();
                    rp.setId(rs.getLong("rownum"));
                    rp.setNumeroCuenta(rs.getLong("numero_cuenta"));
                    rp.setSecuenciaTrxCms(rs.getInt("secuencia_trx_cms"));
                    rp.setFechaValida(rs.getDate("fecha_valida"));
                    rp.setHora(rs.getString("hora5"));
                    rp.setCodigoTipoTransaccion(rs.getInt("codigo_tipo_transaccion"));
                    rp.setDescripcion(rs.getString("descripcion"));
                    rp.setClaseCuenta(rs.getString("clase_cuenta"));
                    rp.setClaseDeCuenta(rs.getInt("CLASE_DE_CUENTA"));
                    rp.setDescClaseCuenta(rs.getString("DESC_CLASE_CUENTA"));
                    rp.setValor(rs.getFloat("valor"));
                    rp.setCodigoAgencia(rs.getInt("codigo_agencia"));
                    rp.setNombreAgencia(rs.getString("nombre_agencia"));
                    rp.setNumeroTarjeta(rs.getString("NUMERO_TARJETA"));
                    rp.setTipoTransaccion(rs.getString("TIPO_TRANSACCION"));
                    rp.setCodigoCanalAdquiriente(rs.getString("CODIGO_CANAL_ADQUIRIENTE"));
                    return rp;
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

*/
}