package io.github.rhacs.vacunazo.modelos;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import io.github.rhacs.vacunazo.Constantes;

@Entity
@Table(name = Constantes.TABLA_DOCTORES)
public class Doctor {

    // Atributos
    // -----------------------------------------------------------------------------------------

    /**
     * Identificador numérico del {@link Doctor}
     */
    @Id
    @Column(name = Constantes.DOCTOR_ID)
    private Long id;

    /**
     * Rol Único Tributario del {@link Doctor}
     */
    @NotEmpty
    @Size(max = 11)
    private String rutDoctor;

    /**
     * Nombre del {@link Doctor}
     */
    @NotEmpty
    @Size(max = 50)
    private String nombre;

    /**
     * Apellido del {@link Doctor}
     */
    @NotEmpty
    @Size(max = 50)
    private String apellido;

    /**
     * {@link Especialidad} del {@link Doctor}
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = Constantes.ESPECIALIDAD_ID, nullable = false)
    private Especialidad especialidad;

    // Constructores
    // -----------------------------------------------------------------------------------------

    /**
     * Crea una nueva instancia vacía de la entidad {@link Doctor}
     */
    public Doctor() {

    }

    /**
     * Crea una nueva instancia de la entidad {@link Doctor}
     * 
     * @param id           identificador numérico
     * @param rutDoctor    rol único tributario
     * @param nombre       nombre
     * @param apellido     apellido
     * @param especialidad {@link Especialidad}
     */
    public Doctor(Long id, String rutDoctor, String nombre, String apellido, Especialidad especialidad) {
        this.id = id;
        this.rutDoctor = rutDoctor;
        this.nombre = nombre;
        this.apellido = apellido;
        this.especialidad = especialidad;
    }

    // Métodos
    // -----------------------------------------------------------------------------------------

    /**
     * @return el nombre completo del {@link Doctor}
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
    public String getRutDoctor() {
        return rutDoctor;
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

    /**
     * @return la {@link Especialidad}
     */
    public Especialidad getEspecialidad() {
        return especialidad;
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
     * @param rutDoctor el rol único tributario a establecer
     */
    public void setRutDoctor(String rutDoctor) {
        this.rutDoctor = rutDoctor;
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

    /**
     * @param especialidad la {@link Especialidad} a establecer
     */
    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    // Herencias (Object)
    // -----------------------------------------------------------------------------------------

    @Override
    public int hashCode() {
        return Objects.hash(id, rutDoctor);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        Doctor other = (Doctor) obj;

        return Objects.equals(id, other.id) || Objects.equals(rutDoctor, other.rutDoctor);
    }

    @Override
    public String toString() {
        return String.format("Doctor [id=%s, rutDoctor=%s, nombre=%s, apellido=%s, especialidad=%s]", id, rutDoctor,
                nombre, apellido, especialidad);
    }

}
