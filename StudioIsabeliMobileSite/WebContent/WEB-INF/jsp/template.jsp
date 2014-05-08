<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>


<!DOCTYPE html>
<html lang="pt">
  <head>
  
  	<meta name="mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-capable" content="yes">
    
    <link rel="icon" sizes="196x196" href="apple-touch-icon-196x196-precomposed.png" />
    
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="apple-touch-icon-114x114-precomposed.png" />
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="apple-touch-icon-72x72-precomposed.png" />
    <link rel="apple-touch-icon-precomposed" href="apple-touch-icon-57x57-precomposed.png" />
  
    <meta charset="ISO-8859-1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Studio Isabeli</title>

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
    <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="menu">Studio Isabeli Vilhena</a>
        </div>
        <div class="collapse navbar-collapse navbar-inverse">
          <ul class="nav navbar-nav">
            <li id="menu-sua-agenda"><a href="sua-agenda">Sua Agenda</a></li>
            <li id="menu-agenda-do-studio"><a href="agenda-do-studio">Agenda do Studio</a></li>
            <li id="menu-suas-metas"><a href="marcas-do-aluno">Suas Marcas</a></li>
            <li id="menu-suas-medalhas"><a href="suas-medalhas">Medalhas</a></li>
            <li id="menu-ranking"><a href="ranking">Ranking</a></li>
         </ul>
         <ul class="nav navbar-nav navbar-right">   
            <li id="menu-sair"><a href="sair">Sair</a></li>
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
     <script>(function(a,b,c){if(c in b&&b[c]){var d,e=a.location,f=/^(a|html)$/i;a.addEventListener("click",function(a){d=a.target;while(!f.test(d.nodeName))d=d.parentNode;"href"in d&&(d.href.indexOf("http")||~d.href.indexOf(e.host))&&(a.preventDefault(),e.href=d.href)},!1)}})(document,window.navigator,"standalone")</script>
    <tiles:insertAttribute name="scripts" />
   
   <iframe src="cache.html" style="display:none;">
   	</iframe>
   
  </body>
</html>