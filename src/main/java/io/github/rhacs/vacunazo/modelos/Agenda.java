package io.github.rhacs.vacunazo.modelos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Future;

import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.NotNull;

import io.github.rhacs.vacunazo.Constantes;

@Entity
@Table(name = Constantes.TABLA_AGENDAS)
public class Agenda {

    // Atributos
    // -----------------------------------------------------------------------------------------

    /**
     * Identificador numérico de la {@link Agenda}
     */
    @Id
    @Column(name = Constantes.AGENDA_ID)
    private Long id;

    /**
     * Fecha de la {@link Agenda}
     */
    @NotNull
    @Future
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate fecha;

    /**
     * Hora de la {@link Agenda}
     */
    @NotNull
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime horaDesde;

    /**
     * Duración de la {@link Agenda}
     */
    private Long duracion;

    /**
     * {@link Paciente} de la {@link Agenda}
     */
    @Valid
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = Constantes.PACIENTE_ID, nullable = false)
    private Paciente paciente;

    /**
     * {@link Doctor} de la {@link Agenda}
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = Constantes.DOCTOR_ID, nullable = false)
    private Doctor doctor;

    // Constructores
    // -----------------------------------------------------------------------------------------

    /**
     * Crea una nueva instancia vacía de la entidad {@link Agenda}
     */
    public Agenda() {

    }

    /**
     * Crea una nueva instancia de la entidad {@link Agenda}
     * 
     * @param id        identificador numérico
     * @param fecha     fecha
     * @param horaDesde hora
     * @param duracion  duración
     * @param paciente  {@link Paciente}
     * @param doctor    {@link Doctor}
     */
    public Agenda(Long id, LocalDate fecha, LocalTime horaDesde, Long duracion, Paciente paciente, Doctor doctor) {
        this.id = id;
        this.fecha = fecha;
        this.horaDesde = horaDesde;
        this.duracion = duracion;
        this.paciente = paciente;
        this.doctor = doctor;
    }

    // Getters
    // -----------------------------------------------------------------------------------------

    /**
     * @return el identificador numérico
     */
    public Long getId() {
        return id;
    }

    /**
     * @return la fecha
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * @return la hora
     */
    public LocalTime getHoraDesde() {
        return horaDesde;
    }

    /**
     * @return la duración
     */
    public Long getDuracion() {
        return duracion;
    }

    /**
     * @return el {@link Paciente}
     */
    public Paciente getPaciente() {
        return paciente;
    }

    /**
     * @return el {@link Doctor}
     */
    public Doctor getDoctor() {
        return doctor;
    }

    // Setters
    // -----------------------------------------------------------------------------------------

    /**
     * @param id el identificador numérico a establecer
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @param fecha la fecha a establecer
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    /**
     * @param horaDesde la hora a establecer
     */
    public void setHoraDesde(LocalTime horaDesde) {
        this.horaDesde = horaDesde;
    }

    /**
     * @param duracion la duración a establecer
     */
    public void setDuracion(Long duracion) {
        this.duracion = duracion;
    }

    /**
     * @param paciente el {@link Paciente} a establecer
     */
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    /**
     * @param doctor el {@link Doctor} a establecer
     */
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    // Herencias (Object)
    // -----------------------------------------------------------------------------------------

    @Override
    public int hashCode() {
        return Objects.hash(doctor, id, paciente);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        Agenda other = (Agenda) obj;

        return (Objects.equals(doctor, other.doctor) && Objects.equals(paciente, other.paciente))
                || Objects.equals(id, other.id);
    }

    @Override
    public String toString() {
        return String.format("Agenda [id=%s, fecha=%s, horaDesde=%s, duracion=%s, paciente=%s, doctor=%s]", id, fecha,
                horaDesde, duracion, paciente, doctor);
    }

}
