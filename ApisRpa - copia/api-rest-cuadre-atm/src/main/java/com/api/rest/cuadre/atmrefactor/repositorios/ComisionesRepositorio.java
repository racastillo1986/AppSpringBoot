package com.api.rest.cuadre.atmrefactor.repositorios;

import com.api.rest.cuadre.atmrefactor.entidades.Comisiones;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.OracleTypes;
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
public class ComisionesRepositorio {

    private final DataSource dataSource;

    public ComisionesRepositorio(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Comisiones> listaComisionesR(String gdFechaDesde, String gdFechaHasta) throws Exception {
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
                    comision.setFechaValida(fechaValida); // Establecer el objeto Date
                }
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
        } catch (DataAccessException e) {
            log.error("Error al ejecutar la consulta SQL: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno al consultar datos.");
        } catch (Exception e) {
            log.error("Error inesperado: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error inesperado.");
        }
        return comisiones;
    }
}

	/*
	@Transactional
    @Modifying
    @Query(nativeQuery = true, value = "call RPA_P_COMISIONES_ATM(:Gd_fecha_desde, :Gd_fecha_hasta)")
    void listaProcedure_PP(@Param("Gd_fecha_desde") String gdFechaDesde,
                           @Param("Gd_fecha_hasta") String gdFechaHasta);
*/

