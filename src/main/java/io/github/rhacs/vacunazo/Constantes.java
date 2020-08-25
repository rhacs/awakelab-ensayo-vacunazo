package io.github.rhacs.vacunazo;

public class Constantes {

    // Tablas
    // -----------------------------------------------------------------------------------------

    /**
     * Nombre de la tabla que contiene los registros de {@link Paciente}s
     */
    public static final String TABLA_PACIENTES = "pacientes";

    /**
     * Nombre de la tabla que contiene los registros de {@link Especialidad}es
     */
    public static final String TABLA_ESPECIALIDADES = "especialidades";

    /**
     * Nombre de la tabla que contiene los registros de {@link Doctor}es
     */
    public static final String TABLA_DOCTORES = "doctores";

    /**
     * Nombre de la tabla que contiene los registros de {@link Agenda}s
     */
    public static final String TABLA_AGENDAS = "agendas";

    // Columnas
    // -----------------------------------------------------------------------------------------

    /**
     * Nombre de la llave primaria para la tabla {@value #TABLA_PACIENTES}
     */
    public static final String PACIENTE_ID = "idPaciente";

    /**
     * Nombre de la llave primaria para la tabla {@value #TABLA_ESPECIALIDADES}
     */
    public static final String ESPECIALIDAD_ID = "idEspecialidad";

    /**
     * Nombre de la llave primaria para la tabla {@value #TABLA_DOCTORES}
     */
    public static final String DOCTOR_ID = "idDoctor";

    /**
     * Nombre de la llave primaria para la tabla {@value #TABLA_AGENDAS}
     */
    public static final String AGENDA_ID = "idAgenda";

    // Constructores
    // -----------------------------------------------------------------------------------------

    private Constantes() {
        // Constructor privado para esconder el constructor público implícito
    }

}
