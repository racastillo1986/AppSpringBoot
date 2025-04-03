package fin.coop1504.rpa.servicios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import fin.coop1504.rpa.entidades.AtmTotal;
import fin.coop1504.rpa.repositorios.AtmTotalRepository;

@Service
public class AtmTotalService {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private AtmTotalRepository atmTotalRepository;

	public List<AtmTotal> lista_PP(String IDDispositivo, int SeqCierre, int Moneda, String FechaCierre, int Red) {

		StoredProcedureQuery procedimientoAlmacenado = entityManager
				.createStoredProcedureQuery("Reporte_ATMGetDatosCuadreMon");

		System.out.println("asdasdasd");
		
		procedimientoAlmacenado.registerStoredProcedureParameter("IDDispositivo", String.class, ParameterMode.IN);
		procedimientoAlmacenado.setParameter("IDDispositivo", IDDispositivo);
		
		procedimientoAlmacenado.registerStoredProcedureParameter("SeqCierre", Integer.class, ParameterMode.IN);
		procedimientoAlmacenado.setParameter("SeqCierre", SeqCierre);
		
		procedimientoAlmacenado.registerStoredProcedureParameter("Moneda", Integer.class, ParameterMode.IN);
		procedimientoAlmacenado.setParameter("Moneda", Moneda);
		
		procedimientoAlmacenado.registerStoredProcedureParameter("FechaCierre", String.class, ParameterMode.IN);
		procedimientoAlmacenado.setParameter("FechaCierre", FechaCierre);
		
		procedimientoAlmacenado.registerStoredProcedureParameter("Red", Integer.class, ParameterMode.IN);
		procedimientoAlmacenado.setParameter("Red", Red);

		System.out.println("xxxxxxxxxxx");
		
		procedimientoAlmacenado.execute();

		System.out.println(IDDispositivo + SeqCierre + Moneda + FechaCierre + Red);
		// atmTotalRepository.listaProcedure_PP(IDDispositivo, SeqCierre, Moneda,
		// FechaCierre, Red);
		
		
		return atmTotalRepository.findAll();

	}

}
