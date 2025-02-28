package com.lta.sistemapagos.services;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lta.sistemapagos.entities.Estudiante;
import com.lta.sistemapagos.entities.Pago;
import com.lta.sistemapagos.enums.PagoStatus;
import com.lta.sistemapagos.enums.TypePago;
import com.lta.sistemapagos.repositorios.EstudianteRepository;
import com.lta.sistemapagos.repositorios.PagoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PagoService {

    private PagoRepository pagoRepository;
    private EstudianteRepository estudianteRepository;

    public PagoService(PagoRepository pagoRepository, EstudianteRepository estudianteRepository) {
        this.pagoRepository = pagoRepository;
        this.estudianteRepository = estudianteRepository;
    }

    public Pago savePago(MultipartFile file, double cantidad, TypePago type, LocalDate date, String codigoEstudiante)
            throws IOException {
        /*
         * - Crear ruta donde se guarara el archivo
         * - System.getProperty("user.home"): Obtiene la ruta del directorio personal
         * del S.O
         * - Paths.get(): Crea un objeto Path apuntando a carpeta enset/pagos dentro del
         * directorio
         */
        Path folderPath = Paths.get(System.getProperty("user.home"), "enset-data", "pagos");

        if (!Files.exists(folderPath)) {
            Files.createDirectories(folderPath);
        }

        // Genera un id random
        String fileName = UUID.randomUUID().toString();

        // Path para el pdf q se guardara en enset/data
        Path filePath = Paths.get(System.getProperty("user.home"), "enset-data", "pagos", fileName + ".pdf");

        // files.getInputStream: obtiene el flujo de datos del archivo recibido desde la
        // solicitud http
        // Files.copy(): Copia los datos del archivo al detino filePath
        Files.copy(file.getInputStream(), filePath);

        Estudiante estudiante = estudianteRepository.findByCodigo(codigoEstudiante);
        Pago pago = Pago.builder()
                .type(type)
                .status(PagoStatus.CREADO)
                .fecha(date)
                .estudiante(estudiante)
                .cantidad(cantidad)
                .file(filePath.toUri().toString())
                .build();

        return pagoRepository.save(pago);
    }

    public byte[] getArchivoPorId(Long pagoId) throws IOException {
        Pago pago = pagoRepository.findById(pagoId).get();
        /*
         * - pago.getFile: Obtiene la URI del archivo guardado
         * - URI.create(): convierte la cadena en objeto URI
         * - Path.of(): Convierte el URI en un path
         * - Files.readAllBytes(): lee contenido del archivo y lo devuelve como array de
         * bytes
         */
        return Files.readAllBytes(Path.of(URI.create(pago.getFile())));
    }

    public Pago actualizarPagoPorStatus(PagoStatus status, Long id) {
        Pago pago = pagoRepository.findById(id).get();
        pago.setStatus(status);
        return pagoRepository.save(pago);
    }

}
