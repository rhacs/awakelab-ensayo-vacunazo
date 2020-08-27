package io.github.rhacs.vacunazo.controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.rhacs.vacunazo.modelos.Doctor;
import io.github.rhacs.vacunazo.modelos.Especialidad;
import io.github.rhacs.vacunazo.repositorios.DoctoresRepositorio;
import io.github.rhacs.vacunazo.repositorios.EspecialidadesRepositorio;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiRestController {

    // Atributos
    // -----------------------------------------------------------------------------------------

    /**
     * Objeto {@link EspecialidadesRepositorio} que contiene los métodos de
     * manipulación y consulta para el repositorio de {@link Especialidad}es
     */
    @Autowired
    private EspecialidadesRepositorio especialidadesRepositorio;

    /**
     * Objeto {@link DoctoresRepositorio} que contiene los métodos de manipulación y
     * consulta para el repositorio de {@link Doctor}es
     */
    @Autowired
    private DoctoresRepositorio doctoresRepositorio;

    // Solicitudes GET
    // -----------------------------------------------------------------------------------------

    /**
     * Muestra el listado de {@link Especialidad}es disponibles en el repositorio
     * 
     * @return un objeto {@link List} con el resultado
     */
    @GetMapping(path = "/especialidades")
    public List<Especialidad> obtenerTodas() {
        return especialidadesRepositorio.findAll(Sort.by(Order.asc("descripcion")));
    }

    /**
     * Muestra el listado de {@link Doctor}es de acuerdo a la {@link Especialidad}
     * proporcionada
     * 
     * @param id identificador numérico de la {@link Especialidad}
     * @return un objeto {@link List} con el resultado, {@code null} en cualquier
     *         otro caso
     */
    @GetMapping(path = "/doctores/especialidad/{id:^[0-9]+$}")
    public List<Doctor> buscarDoctoresPorEspecialidad(@PathVariable Long id) {
        // Buscar información de la Especialidad
        Optional<Especialidad> especialidad = especialidadesRepositorio.findById(id);

        // Verificar si existe
        if (especialidad.isPresent()) {
            // Buscar listado y devolver resultado
            return doctoresRepositorio.findByEspecialidad(especialidad.get(), Sort.by(Order.asc("nombre")));
        }

        // Devolver null si la especialidad no existe
        return null;
    }

}
