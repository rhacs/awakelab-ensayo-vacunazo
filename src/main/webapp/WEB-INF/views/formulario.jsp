<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/datepicker/1.0.9/datepicker.min.css" integrity="sha512-Yog+zkte66L91S9y4tcyhCpjvzKjlBxNLW38pm9K2ukBtPN55LKl3dyGvb7ylCFmn/kFKNzN9KbnGPOnHGHxbw==" crossorigin="anonymous" />
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
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}">Listado</a>
                        </li>

                        <li class="nav-item active">
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
                    <h1 class="border-bottom pb-2 mb-4">Detalle de la Reserva</h1>

                    <form:form method="post" modelAttribute="agenda">
                        <form:hidden path="id" />
                        <form:hidden path="paciente.id" />

                        <h4 class="border-bottom pb-2 mb-3">Detalles del Paciente</h4>
                        <div class="form-group">
                            <form:label path="paciente.nombre">Nombre</form:label>
                            <form:input path="paciente.nombre" cssClass="form-control" cssErrorClass="form-control is-invalid" />
                            <form:errors path="paciente.nombre" cssClass="invalid-feedback" />
                        </div>

                        <div class="form-group">
                            <form:label path="paciente.apellido">Apellido</form:label>
                            <form:input path="paciente.apellido" cssClass="form-control" cssErrorClass="form-control is-invalid" />
                            <form:errors path="paciente.apellido" cssClass="invalid-feedback" />
                        </div>

                        <div class="form-group">
                            <form:label path="paciente.rutPaciente">R.U.T.</form:label>
                            <form:input path="paciente.rutPaciente" cssClass="form-control" cssErrorClass="form-control is-invalid" />
                            <form:errors path="paciente.rutPaciente" cssClass="invalid-feedback" />
                        </div>

                        <h4 class="border-bottom pb-2 mb-3 mt-5">Detalles del Especialista</h4>
                        <div class="form-group">
                            <form:label path="doctor.especialidad">Especialidad</form:label>
                            <form:select path="doctor.especialidad" cssClass="form-control">
                                <form:options items="${especialidades}" itemValue="id" itemLabel="descripcion" />
                            </form:select>
                        </div>

                        <div class="form-group">
                            <form:label path="doctor">Doctor(a)</form:label>
                            <form:select path="doctor" cssClass="form-control"></form:select>
                        </div>

                        <h4 class="border-bottom pb-2 mb-3 mt-5">Detalles de la Cita</h4>
                        <div class="form-group">
                            <form:label path="fecha">Fecha</form:label>
                            <form:input path="fecha" cssClass="form-control" cssErrorClass="form-control is-invalid" />
                            <form:errors path="fecha" cssClass="invalid-feedback" />
                        </div>

                        <div class="form-group">
                            <form:label path="horaDesde">Hora</form:label>
                            <form:input path="horaDesde" cssClass="form-control" cssErrorClass="form-control is-invalid" />
                            <form:errors path="horaDesde" cssClass="invalid-feedback" />
                        </div>

                        <div class="form-group text-right">
                            <button type="reset" class="btn btn-secondary">Limpiar</button>
                            <button type="submit" class="btn btn-primary">Enviar</button>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
        <!-- /Contenido -->

        <!-- Dependencias JavaScript -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js" integrity="sha512-bLT0Qm9VnAYZDflyKcBaQ2gg0hSYNQrJ8RilYldYQ1FxQYoCLtUjuuRuZo+fjqhx/qtq/1itJ0C2ejDxltZVFg==" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.1/esm/popper.min.js" integrity="sha512-dq7mCGEqpNQ0jbrUSQgoMWfpBrQRcV706ASOmbwt+qH0/r0K3Pqvri+0rwtJG+CeHVvnf2IQlq3f1a7pQNPCBQ==" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha512-M5KW3ztuIICmVIhjSqXe01oV2bpe248gOxqmlcYrEzAvws7Pw3z6BK0iGbrwvdrUQUhi3eXgtxp5I8PDo9YfjQ==" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/datepicker/1.0.9/datepicker.min.js" integrity="sha512-Hkemjwczq2iepz/dVjaZ/Xd+agLwV7Od5nQ+R74m4E8ruIblnDAJOewx2+xT4TQ+Q9Q2bcMHFD/d70RzVjrWVw==" crossorigin="anonymous"></script>

        <script type="text/javascript">
            var doc = ${agenda.getDoctor().getId() != null ? agenda.getDoctor().getId() : -1};
        </script>

        <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/formulario.js"></script>
    </body>
</html>
