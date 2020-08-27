// Esperar a que cargue la página
$(function() {
    // Asignar el evento 'change' al select correspondiente a las Especialidades
    $('#doctor.especialidad.id').on('change', function() {
        // Obtener el valor del elemento seleccionado
        let id = $(this).children('option:selected').val();

        // Obtener listado de doctores que pertenecen a la especialidad
        $.get('http://localhost/vacunazo/api/doctores/especialidad/' + id, function(data) {
            // Limpiar el select correspondiente a los Doctores
            $('#doctor.id').empty();

            // Por cada elemento de la respuesta
            $.each(data, function() {
                // Generar una nueva opción
                let opcion = $('<option />')
                        // Establecer valor
                        .val(this.id)
                        // Establecer etiqueta
                        .text(this.nombre + ' ' + this.apellido);

                // Agregar opción al select
                $('#doctor.id').append(opcion);
            });
        });
    });
});
