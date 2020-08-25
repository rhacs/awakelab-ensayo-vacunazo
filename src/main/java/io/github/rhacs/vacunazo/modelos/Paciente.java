package io.github.rhacs.vacunazo.modelos;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import io.github.rhacs.vacunazo.Constantes;

@Entity
@Table(name = Constantes.TABLA_PACIENTES)
public class Paciente {

    // Atributos
    // -----------------------------------------------------------------------------------------

    /**
     * Identificador numérico del {@link Paciente}
     */
    @Id
    @Column(name = Constantes.PACIENTE_ID)
    private Long id;

    /**
     * Rol Único Tributario del {@link Paciente}
     */
    @NotEmpty
    @Size(max = 11)
    private String rutPaciente;

    /**
     * Nombre del {@link Paciente}
     */
    @NotEmpty
    @Size(max = 50)
    private String nombre;

    /**
     * Apellido del {@link Paciente}
     */
    @NotEmpty
    @Size(max = 50)
    private String apellido;

    // Constructores
    // -----------------------------------------------------------------------------------------

    /**
     * Crea una nueva instancia vacía de la entidad {@link Paciente}
     */
    public Paciente() {

    }

    /**
     * Crea una nueva instancia de la entidad {@link Paciente}
     * 
     * @param id          identificador numérico
     * @param rutPaciente rol único tributario
     * @param nombre      nombre
     * @param apellido    apellido
     */
    public Paciente(Long id, String rutPaciente, String nombre, String apellido) {
        this.id = id;
        this.rutPaciente = rutPaciente;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    // Métodos
    // -----------------------------------------------------------------------------------------

    /**
     * @return el nombre completo del {@link Paciente}
     */
    public String getNombreCompleto() {
        return String.format("%s %s", nombre, apellido);
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
     * @return el rol único tributario
     */
    public String getRutPaciente() {
        return rutPaciente;
    }

    /**
     * @return el nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return el apellido
     */
    public String getApellido() {
        return apellido;
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
     * @param rutPaciente el rol único tributario a establecer
     */
    public void setRutPaciente(String rutPaciente) {
        this.rutPaciente = rutPaciente;
    }

    /**
     * @param nombre el nombre a establecer
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @param apellido el apellido a establecer
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    // Herencias (Object)
    // -----------------------------------------------------------------------------------------

    @Override
    public int hashCode() {
        return Objects.hash(id, rutPaciente);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        Paciente other = (Paciente) obj;

        return Objects.equals(id, other.id) || Objects.equals(rutPaciente, other.rutPaciente);
    }

    @Override
    public String toString() {
        return String.format("Paciente [id=%s, rutPaciente=%s, nombre=%s, apellido=%s]", id, rutPaciente, nombre,
                apellido);
    }

}
