package com.api.rest.cuadre.atmrefactor.repositorios;

import com.api.rest.cuadre.atmrefactor.entidades.TrxDiarias;
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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
@Slf4j
public class TrxRepositorio {

    private final DataSource dataSource;

    public TrxRepositorio(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<TrxDiarias> listaTrx(String fechaDesde, String fechaHasta) {

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
                Timestamp fechaValidaTimestamp = rs.getTimestamp("fecha_valida");
                if (fechaValidaTimestamp != null) {
                    // Convertir Timestamp a ZonedDateTime en UTC
                    ZonedDateTime zonedDateTime = fechaValidaTimestamp.toInstant().atZone(ZoneId.of("UTC"));
                    // Formatear la fecha
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
                    String formattedFechaValida = zonedDateTime.format(formatter);
                    // Convertir la fecha formateada a un objeto Date
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
                    Date fechaValida = sdf.parse(formattedFechaValida);
                    trx.setFechaValida(fechaValida);
                }
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
}