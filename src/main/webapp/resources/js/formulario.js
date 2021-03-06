// Esperar a que cargue la página
$(function() {
    // Datepicker
    $('#fecha').datepicker({
        'language': "es-ES",
        'format': 'dd/MM/yyyy'
    });

    // Timepicker
    $('#horaDesde').timepicker({
        'minTime': '10:00am',
        'maxTime': '09:00pm',
        'disableTimeRanges': [
            ['01:00pm', '02:00pm']
        ],
        'timeFormat': 'H:i',
        'step': 30,
        'forceRoundTime': true
    });

    // Asignar el evento 'change' al select correspondiente a las Especialidades
    $('#doctor\\.especialidad\\.id').change(function() {
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
                $('#doctor\\.id').append(opcion);
            });

            // Seleccionar el doctor correspondiente
            if (doc != -1) {
                $('#doctor\\.id option[value="' + doc + '"]').attr('selected', true);
            }
        });
    });

    // Forzar evento 'change'
    $('#doctor\\.especialidad\\.id').change();
});
