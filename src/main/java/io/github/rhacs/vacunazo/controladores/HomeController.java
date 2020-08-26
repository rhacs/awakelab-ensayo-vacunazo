package io.github.rhacs.vacunazo.controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import io.github.rhacs.vacunazo.modelos.Agenda;
import io.github.rhacs.vacunazo.repositorios.AgendasRepositorio;

@Controller
@RequestMapping(path = "/")
public class HomeController {

    // Atributos
    // -----------------------------------------------------------------------------------------

    /**
     * Objeto {@link AgendasRepositorio} que contiene los métodos de manipulación y
     * consulta para los registros de {@link Agenda}s
     */
    @Autowired
    private AgendasRepositorio agendasRepositorio;

    // Solicitudes GET
    // -----------------------------------------------------------------------------------------

    /**
     * Muestra el listado de {@link Agenda}s disponibles en el repositorio
     * 
     * @param modelo objeto {@link Model} que contiene el modelo de la vista
     * @return un objeto {@link String} que contiene el nombre de la vista
     */
    @GetMapping
    public String verListado(Model modelo) {
        // Buscar todos los registros en el repositorio, ordenados por fecha descendente
        // y hora ascendente
        List<Agenda> agendas = agendasRepositorio.findAll(Sort.by(Order.desc("fecha"), Order.asc("horaDesde")));

        // Agregar listado al modelo
        modelo.addAttribute("agendas", agendas);

        // Devolver vista
        return "home";
    }

    /**
     * Muestra el formulario para agregar/editar un registro
     * 
     * @param id     identificador numérico de la {@link Agenda} (opcional)
     * @param modelo objeto {@link Model} que contiene el modelo de la vista
     * @return un objeto {@link String} que contiene el nombre de la vista
     */
    @GetMapping(path = { "/reservas", "/reservas/{id:^[0-9]+$}" })
    public String mostrarFormulario(@PathVariable Optional<Long> id, Model modelo) {
        // Inicializar agenda
        Agenda agenda = new Agenda();

        // Verificar si el id está presente
        if (id.isPresent()) {
            // Buscar información de la agenda
            Optional<Agenda> existente = agendasRepositorio.findById(id.get());

            // Verificar si no existe
            if (!existente.isPresent()) {
                // Redireccionar
                return "redirect:/?no=" + id.get();
            }

            // Reemplazar agenda
            agenda = existente.get();
        }

        // Agregar agenda al modelo
        modelo.addAttribute("agenda", agenda);

        // Devolver vista
        return "formulario";
    }

}
