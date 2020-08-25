package io.github.rhacs.vacunazo.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.rhacs.vacunazo.modelos.Agenda;

@Repository
public interface AgendasRepositorio extends JpaRepository<Agenda, Long> {

    /**
     * @return un objeto {@link Optional} que puede contener el último registro
     *         insertado en el repositorio
     */
    public Optional<Agenda> findTopByOrderByIdDesc();

}
