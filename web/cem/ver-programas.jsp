<%@ page contentType="text/html"
         pageEncoding="UTF-8"
         import="cem.modelo.entidad.Usuario" 
         session="true" %>
        
<!DOCTYPE html>
<html>
    <head>
        <title> Administración de programas </title>
        <%@include file="menu-cem.jsp" %>
    </head>
    <body>
        <%
            /**
             * Código que verifica si el usuario en sesión puede visitar esta
             * página. De no tener permiso, se le redirecciona a la página
             * "no autorizado".
             */
            /*if (session.getAttribute("usuarioActual") == null) {
                response.sendRedirect("no-autorizado.html");
            }
            else {
                if (((Usuario) session.getAttribute("usuarioActual"))
                        .getPerfil()
                        .compareToIgnoreCase("Administrador") != 0) {
                    response.sendRedirect("no-autorizado.html");
                }
            }*/
        %>
        <div class="container">
            <h2>Programas </h2>      
            <p>Si necesitas buscar un programa en especifico puedes hacerlo aqui:</p>
            <input class="form-control" id="myInput2" type="text"
                   placeholder="Escribe aca lo que buscas..">
            <br/>
            <div class="form-group">
                <label class="col-sm-2 control-label">${mensaje}</label>
            </div>
            <table class="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th>Codigo</th>
                        <th>Nombre</th>   
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody id="myTable2">
                    <c:forEach var="p" items="${listadoProgramas}" >
                        <tr>
                            <td> <c:out value="${p.codigo}" /> </td>
                            <td> <c:out value="${p.nombre}" /> </td>
                            <td><button type="button" class="btn btn-primary">
                                    <i class="glyphicon glyphicon-minus"></i>
                                </button></td>
                            <td><button type="button" class="btn btn-primary">
                                    <i class="glyphicon glyphicon-pencil"></i>
                                </button></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            
        </div>
        <script>
            $(document).ready(function () {
                $("#myInput2").on("keyup", function () {
                    var value = $(this).val().toLowerCase();
                    $("#myTable2 tr").filter(function () {
                        $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
                    });
                });
            });
        </script>
    </body>
</html>
