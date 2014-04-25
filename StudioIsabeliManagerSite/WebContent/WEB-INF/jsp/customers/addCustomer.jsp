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
    	<form class="bs-example bs-example-form" role="form" action="salvar-aluno" method="post" id="salvar-aluno-form">
    		<input type="hidden" id="customerID" name="customer.customerID" value="${customer.customerID}"/>
			<div class="input-group">
				<span class="input-group-addon">Nome do Aluno</span>
				<input type="text" class="form-control" required autofocus placeholder="Digite o nome" name="customer.name" value="${customer.name}"/>
				<span class="input-group-btn">
					<button class="btn btn-default" type="submit">Salvar</button>
				</span>
			</div>
			
			<c:if test="${customer.customerID != null && customer.customerID != 0 }">
				<div class="input-group">
					<span class="input-group-addon">Token Gerado</span>
					<input type="text" id="token" readonly="readonly" class="form-control" placeholder="Nenhum token gerado" value="${token}"/>
					<span class="input-group-btn">
						<c:choose>
							<c:when test="${token == null}">
								<button id="button-gerar-token" class="btn btn-default" type="button" onClick="return gerarToken();">Gerar</button>
							</c:when>
							<c:otherwise>
								<button id="button-gerar-token" disabled="disabled" class="btn btn-default" type="button" onClick="return gerarToken();">Gerar</button>
							</c:otherwise>
						</c:choose>
					</span>
				</div>
			</c:if>
			
		</form>
    </div>

    <div id="footer">
      <div class="container">
        <p class="text-muted">Dias tecnologia.</p>
      </div>
    </div>


	<script type="text/javascript">
		function gerarToken(){
			
			$.ajax({
				url : 'gerar-token-de-aluno',
				data : 'customerID=' + $('#customerID').val(),
				success: function(data){
					$('#token').val(data.string);
					$('#button-gerar-token').attr('disabled', 'disabled');
				},
				error: function(){
					alert('Ocorreu um problema, por favor tente novamente mais tarde.');
				}
			});
			
			return false;
		}
	</script>    

    <script src="lib/jquery.min.js"></script>
    <script src="lib/bootstrap.min.js"></script>
  </body>
</html>
