package io.github.rhacs.vacunazo.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.rhacs.vacunazo.modelos.Paciente;

@Repository
public interface PacientesRepositorio extends JpaRepository<Paciente, Long> {

    /**
     * @return un objeto {@link Optional} que puede contener el último registro
     *         insertado en el repositorio
     */
    public Optional<Paciente> findTopByOrderByIdDesc();

    /**
     * Busca un registro en el repositorio de {@link Paciente}s
     * 
     * @param rutPaciente rol único tributario a buscar
     * @return un objeto {@link Optional} con el resultado
     */
    public Optional<Paciente> findByRutPaciente(String rutPaciente);

}
