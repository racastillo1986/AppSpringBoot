package fin.coop1504.rpa.servicios;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fin.coop1504.rpa.dto.ControlGlobal;
import fin.coop1504.rpa.dto.ResultadoReporteAtm;
import fin.coop1504.rpa.procedimientos.ReporteATM;

@Service
//@Slf4j
public class ServicioAtm {

	@Autowired
	private EntityManager em;

	public List<ResultadoReporteAtm> ejecutarSP(String iDDispositivo, Integer seqCierre, Integer moneda,
			String fechaCierre, Integer red) {

		Session sesion = em.unwrap(Session.class);

		ReporteATM reporteATM = new ReporteATM();
		reporteATM.setFechaCierre(fechaCierre);
		reporteATM.setiDDispositivo(iDDispositivo);
		reporteATM.setMoneda(moneda);
		reporteATM.setRed(red);
		reporteATM.setSeqCierre(seqCierre);

		sesion.doWork(reporteATM);

		// control para obtener data cuando ya exista "Cierre Lote"
		int controlCierre = 0;
		List<ResultadoReporteAtm> lista = reporteATM.getResultadoReporteAtm();

		if (lista.isEmpty()) {
			ControlGlobal.codigo = "000";
			// log.info("LISTA VACIA cod: " + ControlGlobal.codigo);
		} else {
			ControlGlobal.codigo = "001";
			// log.info("LISTA LLENA cod: " + ControlGlobal.codigo);
			for (ResultadoReporteAtm object : lista) {
				String descripcion = object.getDescripciontransaccion();
				if (descripcion.equals("Cierre Lote")) {
					controlCierre = 1;
				}
			}
			if (controlCierre == 1) {
				// log.info("control cierre: " + controlCierre + " toncs SI hay Cierre Lote");
				ControlGlobal.cierreLote = "SI";
			} else {
				// log.info("control cierre: " + controlCierre + " toncs NO hay Cierre Lote");
				ControlGlobal.cierreLote = "NO";
			}
		}
		return lista;
	}
}
