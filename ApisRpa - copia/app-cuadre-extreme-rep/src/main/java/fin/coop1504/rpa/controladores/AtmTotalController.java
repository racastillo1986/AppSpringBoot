package fin.coop1504.rpa.controladores;

import fin.coop1504.rpa.dto.*;
import fin.coop1504.rpa.servicios.ServicioAtm;
import fin.coop1504.rpa.servicios.ServicioResumenAtm;
import fin.coop1504.rpa.utils.Utilerias;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/atmTotal_v1")
@Slf4j
public class AtmTotalController {

    private final ServicioAtm servicio;
    private final ServicioResumenAtm servicioResumen;
    private final Utilerias utilerias;

    public static final String SEPARADOR = "***********************************************************************************************************************";

    public AtmTotalController(ServicioAtm servicio, ServicioResumenAtm servicioResumen, Utilerias utilerias){
        this.servicio = servicio;
        this.servicioResumen = servicioResumen;
        this.utilerias = utilerias;
    }

    // EndPoint con restriccion de Cierre Lote Existente

    @PostMapping("/reporteATM")
    public ResponseEntity<Object> reporteATM(@RequestBody AtmTotalME request) {
        log.info(SEPARADOR);
        log.info("* METODO: ExtremeReport - F.Consumo: {} Disp: {} Sec: {} Mon: {} F.Cierre: {}", utilerias.fechaHora()
                , request.getiDDispositivo(), request.getSeqCierre(), request.getMoneda(), request.getFechaCierre());
        log.info(SEPARADOR);

        List<ResultadoReporteAtm> resultado = servicio.ejecutarSP(request.getiDDispositivo(),
                                                                  request.getSeqCierre(),
                                                                  request.getMoneda(),
                                                                  request.getFechaCierre(),
                                                                  request.getRed());
        //--
        String cod = ControlGlobal.codigo;
        if (cod.equals("000")) {
            log.info("Verificar parametros o aun no hay Movimientos.");
            ResponseDataNull dataNull = new ResponseDataNull("001", "No hay data para lote: " + request.getSeqCierre() +
                    " Verificar parametros o aun no hay Movimientos.");
            return new ResponseEntity<>(dataNull, HttpStatus.NOT_FOUND);
        } else if (cod.equals("001")) {
            String cierre = ControlGlobal.cierreLote;
            log.info("SI hay Data...se procede a verificar - existe Cierre Lote: " + cierre);
            if (cierre.equals("NO")) {
                ResponseDataNull dataNull = new ResponseDataNull("002", "Lote: " + request.getSeqCierre() + " No disponible - Sin Cierre Lote.");
                return new ResponseEntity<>(dataNull, HttpStatus.NOT_FOUND);
            }
        }
        //--
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


    @PostMapping("/resumenATM")
    public ResponseEntity<Object> resumenAtm(@RequestBody AtmTotalME request) {
        log.info(SEPARADOR);
        log.info("* METODO: ExtremeResumen - F.Consumo: {} Disp: {} Sec: {} Mon: {} F.Cierre: {}", utilerias.fechaHora()
                , request.getiDDispositivo(), request.getSeqCierre(), request.getMoneda(), request.getFechaCierre());
        log.info(SEPARADOR);

        //Control lote valido - Existe cierre Lote
        @SuppressWarnings("unused")
        List<ResultadoReporteAtm> resultado1 = servicio.ejecutarSP(request.getiDDispositivo(),
                                                                   request.getSeqCierre(),
                                                                   request.getMoneda(),
                                                                   request.getFechaCierre(),
                                                                   request.getRed());
        String cod = ControlGlobal.codigo;
        if (cod.equals("000")) {
            log.info("Verificar parametros o aun no hay Movimientos.");
            ResponseDataNull dataNull = new ResponseDataNull("001", "No hay data para lote: " + request.getSeqCierre() +
                    " Verificar parametros o aun no hay Movimientos.");
            return new ResponseEntity<>(dataNull, HttpStatus.NOT_FOUND);
        } else if (cod.equals("001")) {
            String cierre = ControlGlobal.cierreLote;
            log.info("SI hay Data...se procede a verificar - existe Cierre Lote: " + cierre);
            if (cierre.equals("NO")) {
                ResponseDataNull dataNull = new ResponseDataNull("002", "Lote: " + request.getSeqCierre() + " No disponible - Sin Cierre Lote.");
                return new ResponseEntity<>(dataNull, HttpStatus.NOT_FOUND);
            }
        }
        //FIN Control lote valido - Existe cierre Lote
        List<ResultadoResumenAtm> resultado = servicioResumen.ejecutarSP(request.getiDDispositivo(),
                request.getSeqCierre(),
                request.getMoneda(),
                request.getFechaCierre(),
                request.getRed());

        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    ///--------------------Metodos sin restriccion de cierre lote---------------------------------------------------------------


    @PostMapping("/reporteATMSR")
    public ResponseEntity<Object> reporteATMSR(@RequestBody AtmTotalME request) {
        log.info(SEPARADOR);
        log.info("* METODO: ExtremeReportSR - F.Consumo: {} Disp: {} Sec: {} Mon: {} F.Cierre: {}", utilerias.fechaHora()
                , request.getiDDispositivo(), request.getSeqCierre(), request.getMoneda(), request.getFechaCierre());
        log.info(SEPARADOR);

        List<ResultadoReporteAtm> resultado = servicio.ejecutarSP(request.getiDDispositivo(),
                                                                  request.getSeqCierre(),
                                                                  request.getMoneda(),
                                                                  request.getFechaCierre(),
                                                                  request.getRed());

        if (resultado.isEmpty()) {
            log.info("No hay data para parametros ingresados... Revision");
        }

        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    //-----------------------------------------------------------------------------------------------------------------
    @PostMapping("/resumenATMSR")
    public ResponseEntity<Object> resumenAtmSR(@RequestBody AtmTotalME request) {
        log.info(SEPARADOR);
        log.info("* METODO: ExtremeResumenSR - F.Consumo: {} Disp: {} Sec: {} Mon: {} F.Cierre: {}", utilerias.fechaHora()
                , request.getiDDispositivo(), request.getSeqCierre(), request.getMoneda(), request.getFechaCierre());
        log.info(SEPARADOR);

        List<ResultadoResumenAtm> resultado = servicioResumen.ejecutarSP(request.getiDDispositivo(),
                                                                         request.getSeqCierre(),
                                                                         request.getMoneda(),
                                                                         request.getFechaCierre(),
                                                                         request.getRed());

        if (resultado.isEmpty()) {
            log.info("No hay data para parametros ingresados... Revision");
        }

        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }
    //----------------------------------------------------

}