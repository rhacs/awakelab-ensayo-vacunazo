// Esperar a que la página termine de cargar
$(function() {
    // Inicializar identificador
    var id = -1;

    // Asignar el evento 'click' a todos los elementos marcados con 'data-action'
    $('[data-action]').on('click', function() {
        // Extraer datos
        let accion = $(this).data('action');
        let auxId = $(this).data('id');

        // Verificar si el identificador está presente
        if(typeof auxId !== 'undefined') {
            // Reemplazar el valor del id
            id = auxId;
        }

        // Filtrar acción
        if(accion === 'edit') {
            // Redireccionar
            $(location).attr('href', '/vacunazo/reservas/' + id);
        } else if(accion === 'confirm') {
            // Mostrar modal
            $('#confirmar').modal('toggle');
        } else if(accion === 'del') {
            // Redireccionar
            $(location).attr('href', '/vacunazo/reservas/' + id + '/del');
        }
    });
});
