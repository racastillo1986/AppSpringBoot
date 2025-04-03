package com.api.rest.cuadre.atmrefactor.repositorios;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.rest.cuadre.atmrefactor.entidades.Seed;

@Repository
public interface SeedRepositorio extends JpaRepository<Seed, Long>{
	
	@Transactional
	@Modifying
    @Query(nativeQuery = true, value = "call RPA_P_VALORES_SEED(:Gv_identificacion, :Gd_fecha_desde, :Gd_fecha_hasta)")
    void listaProcedure(@Param("Gv_identificacion") String gvIdentificacion,
    					   @Param("Gd_fecha_desde") String gdFechaDesde,
                           @Param("Gd_fecha_hasta") String gdFechaHasta);

}
