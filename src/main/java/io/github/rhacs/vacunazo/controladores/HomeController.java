package io.github.rhacs.vacunazo.controladores;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import io.github.rhacs.vacunazo.modelos.Agenda;
import io.github.rhacs.vacunazo.modelos.Especialidad;
import io.github.rhacs.vacunazo.modelos.Paciente;
import io.github.rhacs.vacunazo.repositorios.AgendasRepositorio;
import io.github.rhacs.vacunazo.repositorios.PacientesRepositorio;

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

    /**
     * Objeto {@link PacientesRepositorio} que contiene los métodos de manipulación
     * y consulta para los registros de {@link Paciente}s
     */
    @Autowired
    private PacientesRepositorio pacientesRepositorio;

    // Métodos
    // -----------------------------------------------------------------------------------------

    /**
     * Busca el listado de {@link Especialidad}es mediante el uso de un
     * {@link RestTemplate}
     * 
     * @return un objeto {@link List} con el resultado
     */
    private List<Especialidad> obtenerEspecialidades() {
        // Inicializar rest
        RestTemplate rest = new RestTemplate();

        // Realizar petición a la API y capturar respuesta
        ResponseEntity<Especialidad[]> respuesta = rest.getForEntity("http://localhost/vacunazo/api/especialidades",
                Especialidad[].class);

        // Devolver listado
        return Arrays.asList(respuesta.getBody());
    }

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

        // Buscar listado de especialidades
        List<Especialidad> especialidades = obtenerEspecialidades();

        // Agregar objetos al modelo
        modelo.addAttribute("especialidades", especialidades);
        modelo.addAttribute("agenda", agenda);

        // Devolver vista
        return "formulario";
    }

    /**
     * Elimina una {@link Agenda} del repositorio
     * 
     * @param id identificador numérico de la {@link Agenda}
     * @return un objeto {@link String} que contiene el nombre de la vista
     */
    @GetMapping(path = "/reservas/{id:^[0-9]$}/del")
    public String eliminarRegistro(@PathVariable Long id) {
        // Buscar información de la agenda
        Optional<Agenda> agenda = agendasRepositorio.findById(id);

        // Verificar si existe
        if (agenda.isPresent()) {
            // Eliminar registro del repositorio
            agendasRepositorio.delete(agenda.get());

            // Redireccionar
            return "redirect:/?rm=" + agenda.get().getId();
        }

        // Redireccionar
        return "redirect:/?no=" + id;
    }

    // Solicitudes POST
    // -----------------------------------------------------------------------------------------

    /**
     * Procesa el formulario para agregar o editar un registro
     * 
     * @param id            identificador numérico de la {@link Agenda}
     * @param agenda        objeto {@link Agenda} que contiene la información a
     *                      agregar
     * @param modelo        objeto {@link Model} que contiene el modelo de la vista
     * @param bindingResult objeto {@link BindingResult} que contiene los errores de
     *                      validación
     * @return un objeto {@link String} que contiene el nombre de la vista
     */
    @PostMapping(path = { "/reservas", "/reservas/{id:^[0-9]+$}" })
    public String procesarFormulario(@PathVariable Optional<Long> id, @Valid Agenda agenda, BindingResult bindingResult,
            Model modelo) {
        // Verificar si existen errores de validación
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            // Buscar listado de especialidades
            List<Especialidad> especialidades = obtenerEspecialidades();

            // Agregar listado al modelo
            modelo.addAttribute("especialidades", especialidades);

            // Devolver vista
            return "formulario";
        }

        // Buscar Paciente por RUT
        Optional<Paciente> ex = pacientesRepositorio.findByRutPaciente(agenda.getPaciente().getRutPaciente());

        // Verificar si existe
        if (ex.isPresent()) {
            // Reemplazar paciente
            agenda.setPaciente(ex.get());
        } else {
            // Guardar paciente
            Paciente paciente = pacientesRepositorio.save(agenda.getPaciente());

            // Asignar nuevo paciente a la agenda
            agenda.setPaciente(paciente);
        }

        // Verificar si el id de la agenda no existe
        if (agenda.getId() == null) {
            // Buscar último registro insertado en el registro
            Optional<Agenda> a = agendasRepositorio.findTopByOrderByIdDesc();

            // Verificar si existe
            if (a.isPresent()) {
                // Asignar el nuevo identificador
                agenda.setId(a.get().getId() + 1);
            } else {
                // Asignar un valor arbitrario al identificador
                agenda.setId(1L);
            }
        }

        // Guardar cambios
        agenda = agendasRepositorio.save(agenda);

        // Redireccionar
        return "redirect:/reservas/" + agenda.getId();
    }

}
