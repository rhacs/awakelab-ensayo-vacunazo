package io.github.rhacs.vacunazo.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.rhacs.vacunazo.modelos.Especialidad;

@Repository
public interface EspecialidadesRepositorio extends JpaRepository<Especialidad, Long> {

}
