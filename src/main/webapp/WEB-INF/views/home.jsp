<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="es">
    <head>
        <!-- Codificación de caracteres -->
        <meta charset="UTF-8">

        <!-- Configuración de ancho y escala inicial -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- Título -->
        <title>El Vacunazo</title>

        <!-- Hojas de Estilo -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha512-MoRNloxbStBcD8z3M/2BmnT+rg4IsMxPkXaGh2zD6LGNNFE80W3onsAhRcMAMrSoyWL9xD7Ert0men7vR8LUZg==" crossorigin="anonymous" />
    </head>

    <body>
        <!-- Navegación -->
        <nav class="navbar navbar-expand-md navbar-dark bg-dark shadow-sm">
            <div class="container">
                <!-- "Logo" -->
                <a class="navbar-brand" href="${pageContext.request.contextPath}">El Vacunazo</a>

                <!-- Botón de despliegue de menú para dispositivos con pantallas medianas hacia abajo -->
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#menu" aria-controls="menu" aria-expanded="false">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <!-- Menú -->
                <div class="collapse navbar-collapse" id="menu">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item active">
                            <a class="nav-link" href="${pageContext.request.contextPath}">Listado</a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/reservas">Reservas</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <!-- /Navegación -->

        <!-- Contenido -->
        <div class="container my-5">
            <div class="row">
                <div class="col-12">
                    <h1 class="border-bottom pb-2 mb-4">Listado de Reservas</h1>

                    <core:if test="${not empty param.no}">
                        <div class="alert alert-warning mb-4">No existe el registro con el identificador numérico <strong>${param.no}</strong></div>
                    </core:if>

                    <core:if test="${not empty param.rm}">
                        <div class="alert alert-success mb-4">El registro <strong>${param.rm}</strong> fue eliminado satisfactoriamente</div>
                    </core:if>

                    <div class="table-responsive">
                        <table class="table table-hover table-striped">
                            <thead>
                                <tr>
                                    <th scope="col" class="text-nowrap">#</th>
                                    <th scope="col" class="text-nowrap">Paciente</th>
                                    <th scope="col" class="text-nowrap">Doctor(a)</th>
                                    <th scope="col" class="text-nowrap">Fecha</th>
                                    <th scope="col" class="text-nowrap">Hora</th>
                                    <th scope="col"></th>
                                </tr>
                            </thead>

                            <tbody>
                                <core:choose>
                                    <core:when test="${agendas != null && agendas.size() > 0}">
                                        <core:forEach items="${agendas}" var="agenda">
                                            <tr>
                                                <th scope="row" class="text-nowrap">${agenda.getId()}</th>

                                                <td>
                                                    <p class="text-left">
                                                        ${agenda.getPaciente().getNombreCompleto()} <br />
                                                        ${agenda.getPaciente().getRutPaciente()}
                                                    </p>
                                                </td>

                                                <td>
                                                    <p class="text-left">
                                                        ${agenda.getDoctor().getNombreCompleto()} <br />
                                                        ${agenda.getDoctor().getEspecialidad().getDescripcion()}
                                                    </p>
                                                </td>

                                                <td class="text-nowrap">${agenda.getFecha()}</td>

                                                <td class="text-nowrap">${agenda.getHoraDesde()}</td>

                                                <td>
                                                    <button type="button" class="btn btn-sm btn-success" data-action="edit" data-id="${agenda.getId()}">✎</button>
                                                    <button type="button" class="btn btn-sm btn-danger" data-action="confirm" data-id="${agenda.getId()}">🗑</button>
                                                </td>
                                            </tr>
                                        </core:forEach>
                                    </core:when>

                                    <core:otherwise>
                                        <tr>
                                            <th scope="row" colspan="6" class="text-center">No hay registros para mostrar</th>
                                        </tr>
                                    </core:otherwise>
                                </core:choose>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <!-- /Contenido -->

        <!-- Modal de confirmación -->
        <div class="modal fade" id="confirmar" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered modal-sm">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Confirmar Eliminación</h5>

                        <button type="button" class="close" data-dismiss="modal">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>

                    <div class="modal-body">
                        <p class="text-left">
                            ¿Está seguro de querer eliminar el registro seleccionado? Una vez confirmado no se podrá recuperar
                        </p>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                        <button type="button" class="btn btn-primary" data-dismiss="modal" data-action="del">Confirmar</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- /Modal de confirmación -->

        <!-- Dependencias JavaScript -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js" integrity="sha512-bLT0Qm9VnAYZDflyKcBaQ2gg0hSYNQrJ8RilYldYQ1FxQYoCLtUjuuRuZo+fjqhx/qtq/1itJ0C2ejDxltZVFg==" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.1/esm/popper.min.js" integrity="sha512-dq7mCGEqpNQ0jbrUSQgoMWfpBrQRcV706ASOmbwt+qH0/r0K3Pqvri+0rwtJG+CeHVvnf2IQlq3f1a7pQNPCBQ==" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha512-M5KW3ztuIICmVIhjSqXe01oV2bpe248gOxqmlcYrEzAvws7Pw3z6BK0iGbrwvdrUQUhi3eXgtxp5I8PDo9YfjQ==" crossorigin="anonymous"></script>
    </body>
</html>
