package com.lta.sistemapagos.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lta.sistemapagos.entities.Estudiante;

public interface EstudianteRepository extends JpaRepository<Estudiante, String> {

    Estudiante findByCodigo(String codigo);

    List<Estudiante> findByProgramaId(String programaId);
}
