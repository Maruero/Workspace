<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertDefinition name="studioisabeli.template">
	
	<tiles:putAttribute name="content">
		<div class="jumbotron">
		  <h2>Sua pr&oacute;xima aula &eacute;</h2>
		  <p id="dia">${nextClass}</p>
		</div>
	</tiles:putAttribute>
	
	<tiles:putAttribute name="scripts">
            <script>
              $(document).ready(function() {
           var dia = $('#dia').html();
           dia = dia.replace('Monday', 'Segunda').replace('Tuesday', 'Terça').replace('Wednesday', 'Quarta').replace('Thursday', 'Quinta').replace('Friday', 'Sexta');
           $('#dia').html(dia);
                });
            </script>
	  </tiles:putAttribute>
	
</tiles:insertDefinition>