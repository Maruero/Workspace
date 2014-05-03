<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>


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
    <link href="css/studio-isabeli.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <tiles:insertAttribute name="head" />
    
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
          <a class="navbar-brand" href="home">Studio Isabeli Vilhena</a>
        </div>
        <div class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li id="menu-home" class="active"><a href="home">Home</a></li>
            <li id="menu-alunos"><a href="alunos">Alunos</a></li>
            <li id="menu-dicas"><a href="dicas">Dicas</a></li>
            <li id="menu-recados"><a href="adicionar-recados">Recados</a></li>
            <li id="menu-agenda-do-studio"><a href="agenda-do-studio">Agenda do Studio</a></li>
            <li id="menu-ranking"><a href="ranking">Ranking</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>

    <!-- Begin page content -->
    <div class="container">
     	<tiles:insertAttribute name="content" />
    </div>

    <div id="footer">
      <div class="container">
        <p class="text-muted"><span class="glyphicon glyphicon-registration-mark"></span> Dias tecnologia.</p>
      </div>
    </div>


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="lib/jquery.min.js"></script>
     <script src="lib/bootstrap.min.js"></script>
     <script src="lib/dropdown.js"></script>
     <script src="lib/studio-isabeli.js"></script>
    <tiles:insertAttribute name="scripts" />
   
  </body>
</html>