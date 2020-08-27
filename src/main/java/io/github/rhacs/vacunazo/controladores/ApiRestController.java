package io.github.rhacs.vacunazo.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
