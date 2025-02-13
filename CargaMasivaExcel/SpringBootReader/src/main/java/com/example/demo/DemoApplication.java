package com.example.demo;

import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;
import com.monitorjbl.xlsx.StreamingReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.stream.StreamSupport;

//@Slf4j
@SpringBootApplication
//@RequiredArgsConstructor
public class DemoApplication implements CommandLineRunner {

    private CustomerRepository customerRepository;

    public DemoApplication(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        long startTimeRead = System.currentTimeMillis();
        log.info("Reading file..");
        InputStream is = new FileInputStream("../customers.xlsx");

        Workbook workbook = StreamingReader.builder()
                .rowCacheSize(5000)   // number of rows to keep in memory(default to 10)
                .bufferSize(131072)   // buffer size to use when reading InputStream to file (default to 1024)
                .open(is);            // InputStream or File for XLS file( required)

        List<Customer> customers = StreamSupport.stream(workbook.spliterator(), false)// devuelve las hojas
                .flatMap(sheet -> StreamSupport.stream(sheet.spliterator(), false)) // devuelve las filas de cada hoa
                .skip(1) // para que inicie en la fila 1 xq la fila 0 es el encabezado
                .map(con -> {
                    Customer customer = new Customer();
                    customer.setId((long) con.getCell(0).getNumericCellValue());
                    customer.setName(con.getCell(1).getStringCellValue());
                    customer.setLastName(con.getCell(2).getStringCellValue());
                    customer.setAddress(con.getCell(3).getStringCellValue());
                    customer.setEmail(con.getCell(4).getStringCellValue());
                    return customer;
                })
                .toList();

        long endTimeRead = System.currentTimeMillis();
        log.info("-> Reading finished, time " + (endTimeRead - startTimeRead) + " ms\"");

        log.info("Writing file..");
        long startTimeWrite = System.currentTimeMillis();
        customerRepository.saveAll(customers);
        long endTimeWrite = System.currentTimeMillis();
        log.info("-> Writing finished, time " + (endTimeWrite - startTimeWrite) + " ms\"");
    }
}
