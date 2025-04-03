package com.api.rest.cuadre.atmrefactor.repositorios;

import com.api.rest.cuadre.atmrefactor.entidades.ResumenTrx;
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
import java.util.Collections;
import java.util.List;

@Repository
@Slf4j
public class ResumenTrxRepositorio /*extends JpaRepository<ResumenTrx, Long>*/ {


    private final JdbcTemplate jdbc;

    public ResumenTrxRepositorio(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<ResumenTrx> lista(String fechaDesde, String fechaHasta) {

        String query = "select rownum,\n" +
                "           agencia,\n" +
                "           codigo_clase_cuenta,\n" +
                "           descripcion_clase,\n" +
                "           Codigo_Tipo_Transaccion,\n" +
                "           descripcion,\n" +
                "           valor,\n" +
                "           cantidad\n" +
                "      from (SELECT Gm_f_Agencia_Balance(a.Codigo_Agencia) agencia,\n" +
                "                   d.codigo_clase_cuenta,\n" +
                "                   d.descripcion descripcion_clase,\n" +
                "                   a.Codigo_Tipo_Transaccion,\n" +
                "                   b.Descripcion,\n" +
                "                   sum(a.Valor) valor,\n" +
                "                   count(*) cantidad\n" +
                "              FROM Ca_Movimientos_Mensuales  a,\n" +
                "                   Mg_Tipos_De_Transacciones b,\n" +
                "                   ca_cuentas_de_ahorro      c,\n" +
                "                   mg_clase_cuentas          d\n" +
                "             WHERE a.Codigo_Aplicacion = b.Codigo_Aplicacion\n" +
                "               AND a.Codigo_Tipo_Transaccion = b.Codigo_Tipo_Transaccion\n" +
                "               and a.numero_cuenta = c.numero_cuenta\n" +
                "               and c.clase_de_cuenta = d.codigo_clase_cuenta\n" +
                "               AND a.Fecha_Valida BETWEEN TO_DATE(?, 'DD-MM-YYYY') and TO_DATE(?, 'DD-MM-YYYY')\n" +
                "               AND a.Codigo_Tipo_Transaccion IN\n" +
                "                   (193,\n" +
                "                    194,\n" +
                "                    195,\n" +
                "                    196,\n" +
                "                    197,\n" +
                "                    179,\n" +
                "                    308,\n" +
                "                    926,\n" +
                "                    946,\n" +
                "                    309,\n" +
                "                    420,\n" +
                "                    310,\n" +
                "                    424,\n" +
                "                    319,\n" +
                "                    421,\n" +
                "                    320,\n" +
                "                    422,\n" +
                "                    321,\n" +
                "                    423,\n" +
                "                    318,\n" +
                "                    322,\n" +
                "                    904)\n" +
                "             group by Gm_f_Agencia_Balance(a.Codigo_Agencia),\n" +
                "                      a.Codigo_Tipo_Transaccion,\n" +
                "                      b.Descripcion,\n" +
                "                      d.codigo_clase_cuenta,\n" +
                "                      d.descripcion\n" +
                "             order by 1, 2, 3)";
        try {
            @SuppressWarnings("deprecation")
            List<ResumenTrx> resultado = jdbc.query(query, new Object[]{fechaDesde, fechaHasta}, new RowMapper<ResumenTrx>() {
                public ResumenTrx mapRow(ResultSet rs, int i) throws SQLException {
                    ResumenTrx resumenTrx = new ResumenTrx();
                    resumenTrx.setId(rs.getLong("rownum"));
                    resumenTrx.setCodigoAgencia(rs.getInt("agencia"));
                    resumenTrx.setCodigoClaseCuenta(rs.getInt("codigo_clase_cuenta"));
                    resumenTrx.setDescripcionClase(rs.getString("descripcion_clase"));
                    resumenTrx.setCodigoTipoTransaccion(rs.getInt("Codigo_Tipo_Transaccion"));
                    resumenTrx.setDescripcion(rs.getString("descripcion"));
                    resumenTrx.setValor(rs.getFloat("valor"));
                    resumenTrx.setCantidad(rs.getInt("cantidad"));
                    return resumenTrx;
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
}
/*
    public List<ResumenTrx> listaResumen(String fechaDesde, String fechaHasta) throws Exception{

        log.info("");

        List<ResumenTrx> listaResumenTrx = new ArrayList<>();

        try(Connection connection = dataSource.getConnection()){

            CallableStatement cs = connection.prepareCall("{call RPA_P_RESUMEN_TRX2(?, ?, ?)}");
            cs.setString(1, fechaDesde);
            cs.setString(2, fechaHasta);
            cs.registerOutParameter(3, OracleTypes.CURSOR);
            cs.execute();

            ResultSet rs = (ResultSet) cs.getObject(3);

            while (rs.next()){
                ResumenTrx resumenTrx = new ResumenTrx();
                resumenTrx.setId(rs.getLong("rownum"));
                resumenTrx.setCodigoAgencia(rs.getInt("agencia"));
                resumenTrx.setCodigoClaseCuenta(rs.getInt("codigo_clase_cuenta"));
                resumenTrx.setDescripcionClase(rs.getString("descripcion_clase"));
                resumenTrx.setCodigoTipoTransaccion(rs.getInt("Codigo_Tipo_Transaccion"));
                resumenTrx.setDescripcion(rs.getString("descripcion"));
                resumenTrx.setValor(rs.getFloat("valor"));
                resumenTrx.setCantidad(rs.getInt("cantidad"));
                listaResumenTrx.add(resumenTrx);
            }
            rs.close();
            cs.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al ejecutar el procedimiento RPA_P_COMISIONES_ATM2", e);
        }
        return listaResumenTrx;
    }
 */
/*
	@Transactional
    @Modifying
    @Query(nativeQuery = true, value = "call RPA_P_RESUMEN_TRX(:Gd_fecha_desde, :Gd_fecha_hasta)")
    void listaProcedure(@Param("Gd_fecha_desde") String gdFechaDesde,
                        @Param("Gd_fecha_hasta") String gdFechaHasta);
*/

