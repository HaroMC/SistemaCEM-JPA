<%@ page contentType="text/html" pageEncoding="UTF-8" autoFlush="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Bienvenido </title>
        <link rel="stylesheet" href="./bootstrap-3.3.7-dist/css/bootstrap.min.css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
                integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
                crossorigin="anonymous"> </script>
    </head>
    <body>
        <h1>
            Sistema de intercambios estudiantiles
            <br />
            Centro de Estudios Montreal
        </h1>
        <div class="container">
            <div class="row vertical-offset-100">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <form class="form-horizontal" action="ingresar" method="post">
                            <fieldset>
                                <div class="form-group">
                                    <label for="nombreUsuario" class="col-lg-2 control-label">Nombre de Usuario:</label>
                                    <div class="col-xs-3">
                                        <input type="text" class="form-control" name="nU"
                                               required="true" id="nombreUsuario"
                                               placeholder="nombre Usuario">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="ejemplo_password_3" class="col-lg-2 control-label">Contraseña</label>
                                    <div class="col-xs-3">
                                        <input type="password" class="form-control" name="c"
                                               required="true" id="ejemplo_password_3" 
                                               placeholder="Contraseña">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-lg-offset-2 col-lg-10">
                                        <button type="submit" class="btn btn-default">Entrar</button>
                                    </div>
                                </div>   
                                <h5>Si no estas registrado y eres alumno o familia anfitriona elije una opcion</h5>
                                <div class="row">
                                    <div class="col-xs-3 col-sm-6 col-md-6">
                                        <a href="#" class="btn btn-lg btn-primary btn-block">Si eres alumno matriculado, regístrate aquí</a>
                                        <!--"registro/alumno.jsp"-->
                                    </div>                                  
                                </div>
                                <br />   
                                <div class="row">
                                    <div class="col-xs-3 col-sm-6 col-md-6">
                                        <a href="#" class="btn btn-lg btn-primary btn-block">Si deseas ser una familia anfitriona, regístrate aquí</a>
                                        <!--"registro/familia.jsp"-->
                                    </div>   
                                </div>
                                <br />
                                <label> <c:out value="${mensajeError}" /> </label>         
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
