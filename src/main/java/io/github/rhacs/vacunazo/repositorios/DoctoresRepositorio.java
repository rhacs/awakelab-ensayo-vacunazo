package io.github.rhacs.vacunazo.repositorios;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.rhacs.vacunazo.modelos.Doctor;
import io.github.rhacs.vacunazo.modelos.Especialidad;

@Repository
public interface DoctoresRepositorio extends JpaRepository<Doctor, Long> {

    /**
     * Busca el listado de {@link Doctor}es que pertenecen a la {@link Especialidad}
     * proporcionada
     * 
     * @param especialidad {@link Especialidad} a buscar
     * @param sort         método {@link Sort} por el cual ordenar los resultados
     * @return un objeto {@link List} con los resultados
     */
    public List<Doctor> findByEspecialidad(Especialidad especialidad, Sort sort);

}
