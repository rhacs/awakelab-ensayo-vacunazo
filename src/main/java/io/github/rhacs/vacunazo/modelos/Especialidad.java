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
@Table(name = Constantes.TABLA_ESPECIALIDADES)
public class Especialidad {

    // Atributos
    // -----------------------------------------------------------------------------------------

    /**
     * Identificador numérico de la {@link Especialidad}
     */
    @Id
    @Column(name = Constantes.ESPECIALIDAD_ID)
    private Long id;

    /**
     * Descripción de la {@link Especialidad}
     */
    @NotEmpty
    @Size(max = 50)
    private String descripcion;

    // Constructores
    // -----------------------------------------------------------------------------------------

    /**
     * Crea una nueva instancia vacía de la entidad {@link Especialidad}
     */
    public Especialidad() {

    }

    /**
     * Crea una nueva instancia de la entidad {@link Especialidad}
     * 
     * @param id          identificador numérico
     * @param descripcion descripción
     */
    public Especialidad(Long id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
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
     * @return la descripción
     */
    public String getDescripcion() {
        return descripcion;
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
     * @param descripcion la descripción a establecer
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Herencias (Object)
    // -----------------------------------------------------------------------------------------

    @Override
    public int hashCode() {
        return Objects.hash(descripcion, id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        Especialidad other = (Especialidad) obj;

        return Objects.equals(descripcion, other.descripcion) || Objects.equals(id, other.id);
    }

    @Override
    public String toString() {
        return String.format("Especialidad [id=%s, descripcion=%s]", id, descripcion);
    }

}
