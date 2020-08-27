// Esperar a que cargue la página
$(function() {
    // Datepicker
    $('#fecha').datepicker({
        language: "es-ES"
    });

    // Asignar el evento 'change' al select correspondiente a las Especialidades
    $('#doctor\\.especialidad').change(function() {
        // Obtener el valor del elemento seleccionado
        let id = $(this).children('option:selected').val();

        // Obtener listado de doctores que pertenecen a la especialidad
        $.get('http://localhost/vacunazo/api/doctores/especialidad/' + id, function(data) {
            // Limpiar el select correspondiente a los Doctores
            $('#doctor').empty();

            // Por cada elemento de la respuesta
            $.each(data, function() {
                // Generar una nueva opción
                let opcion = $('<option />')
                    // Establecer valor
                    .val(this.id)
                    // Establecer etiqueta
                    .text(this.nombreCompleto);

                // Agregar opción al select
                $('#doctor').append(opcion);
            });

            // Seleccionar el doctor correspondiente
            if (doc != -1) {
                $('#doctor option[value="' + doc + '"]').attr('selected', true);
            }
        });
    });

    // Forzar evento 'change'
    $('#doctor\\.especialidad').change();
});
