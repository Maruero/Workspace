<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="pt">
  <head>
    <meta charset="ISO-8859-1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Studio Isabeli Vilhena - Manager</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/menu-home.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy this line! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

    <!-- Fixed navbar -->
    <div class="navbar navbar-default navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Studio Isabeli Vilhena</a>
        </div>
        <div class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li><a href="#">Home</a></li>
            <li class="active"><a href="alunos">Alunos</a></li>
            <li><a href="dicas">Dicas</a></li>
            <li><a href="recados">Recados</a></li>
            <li><a href="agenda-do-studio">Agenda do Studio</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>

    <!-- Begin page content -->
    <div class="container">
		<div class="painel painel-default">
			<div class="painel-heading">
				<h3 class="painel-title">Alunos cadastrados</h3> <a href="adicionar-aluno" class="btn btn-primary btn-lg">Novo aluno</a>
			</div>
			<div class="painel-body">
				<h5>${managerSession.message}</h5>
			</div>
			<table class="table">
				<thead>
					<tr>
						<th>Nome</th>
						<th>&nbsp;</th>
						<th>&nbsp;</th>
						<th>&nbsp;</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${customers}" var="customer">
						<tr>
							<td>${customer.name}</td>
							
							<td><a href="recados-do-aluno?customerID=${customer.customerID}" class="btn btn-primary btn-lg">Recados</a></td>
							<td><a href="agenda-aluno?customerID=${customer.customerID}" class="btn btn-primary btn-lg">Agenda</a></td>
							<td><a href="editar-aluno?customerID=${customer.customerID}" class="btn btn-primary btn-lg">Editar</a></td>
							<td><a href="#" onclick="return remover(${customer.customerID})" class="btn btn-primary btn-lg">Remover</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

    <div id="footer">
      <div class="container">
        <p class="text-muted">Dias tecnologia.</p>
      </div>
    </div>

	<script type="text/javascript">
		function remover( customerID ){
			var resp = confirm('Tem certeza que deseja remover o aluno?');
			if( resp ){
				window.location = "remover-aluno?customerID=" + customerID;
			}
			return false;
		}
	</script>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="lib/jquery.min.js"></script>
    <script src="lib/bootstrap.min.js"></script>
  </body>
</html>
