package fin.coop1504.rpa.repositorios;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fin.coop1504.rpa.entidades.AtmTotal;

@Repository
public interface AtmTotalRepository extends JpaRepository<AtmTotal, String> {
	
	@Transactional
    @Modifying
    @Query(nativeQuery = true, value = "call Reporte_ATMGetDatosCuadreMon(:IDDispositivo, :SeqCierre, :Moneda, :FechaCierre, :Red)")
    void listaProcedure_PP(@Param("IDDispositivo") String IDDispositivo,
                           @Param("SeqCierre") int SeqCierre,
                           @Param("Moneda") int Moneda,
                           @Param("FechaCierre") String FechaCierre,
                           @Param("Red") int Red);	
	
}