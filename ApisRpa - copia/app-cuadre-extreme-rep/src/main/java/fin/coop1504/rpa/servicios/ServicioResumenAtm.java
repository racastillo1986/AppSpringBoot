package fin.coop1504.rpa.servicios;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fin.coop1504.rpa.dto.ResultadoResumenAtm;
import fin.coop1504.rpa.procedimientos.ResumenAtm;

@Service
public class ServicioResumenAtm {

	@Autowired
	private EntityManager em;

	public List<ResultadoResumenAtm> ejecutarSP(String iDDispositivo, Integer seqCierre, Integer moneda,
			String fechaCierre, Integer red) {

		Session sesion = em.unwrap(Session.class);

		ResumenAtm resumenAtm = new ResumenAtm();

		resumenAtm.setFechaCierre(fechaCierre);
		resumenAtm.setiDDispositivo(iDDispositivo);
		resumenAtm.setMoneda(moneda);
		resumenAtm.setRed(red);
		resumenAtm.setSeqCierre(seqCierre);

		sesion.doWork(resumenAtm);

		return resumenAtm.getResultadoReporteAtm();

	}

}
