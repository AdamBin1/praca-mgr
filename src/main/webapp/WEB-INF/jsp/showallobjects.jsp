<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Dostępne obiekty</title>


<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs4/dt-1.10.18/datatables.min.css"/> 
<script type="text/javascript" src="https://cdn.datatables.net/v/bs4/dt-1.10.18/datatables.min.js"></script>

<style>
body {
	background-color: #f7f7f7;
}
.rounded40 {
	border-radius:40px;
}
.mainctr {
	background-color: white;
}

.break1 {
	margin-top:15px;
}
</style>

<script type="text/javascript">

	$(document).ready(function() {
		var table = $('#example').DataTable( {
	    	// to wywalic
	    	"lengthMenu": [[1, 2, 50, -1], [1, 2, 50, "Wszystkie"]],
	    	////////
	    	 "scrollX": true,
	         "language": {
	        	    "processing":     "Przetwarzanie...",
	        	    "search":         "Szukaj:",
	        	    "lengthMenu":     "Pokaż _MENU_ pozycji",
	        	    "info":           "Pozycje od _START_ do _END_ z _TOTAL_ łącznie",
	        	    "infoEmpty":      "Pozycji 0 z 0 dostępnych",
	        	    "infoFiltered":   "(filtrowanie spośród _MAX_ dostępnych pozycji)",
	        	    "infoPostFix":    "",
	        	    "loadingRecords": "Wczytywanie...",
	        	    "zeroRecords":    "Nie znaleziono pasujących pozycji",
	        	    "emptyTable":     "Brak danych",
	        	    "paginate": {
	        	        "first":      "Pierwsza",
	        	        "previous":   "Poprzednia",
	        	        "next":       "Następna",
	        	        "last":       "Ostatnia"
	        	    },
	        	    "aria": {
	        	        "sortAscending": ": aktywuj, by posortować kolumnę rosnąco",
	        	        "sortDescending": ": aktywuj, by posortować kolumnę malejąco"
	        	    }
	        	}
	    } );
	    
	    $('#example tbody').on( 'click', 'tr', function () {
	        if ( $(this).hasClass('selected') ) {
	            $(this).removeClass('selected');
	        }
	        else {
	            table.$('tr.selected').removeClass('selected');
	            $(this).addClass('selected');
	        }
	    } );
	} );
	
</script>

</head>

<body>
	<div id="alert">
	</div>
	<div id="mainContainer" class="container">
		<div class="card break1">
			<div class="card-header">Dostępne obiekty</div>
			<div class="card-body m-3">
				
				<table id="example" class="table table-striped table-bordered" style="width:100%">
				        <thead>
				            <tr>
				            <c:forEach items="${allMainStages.get(0).properties}" var="property">
				            	<th>${property.name}</th>
				            </c:forEach>
				            </tr>
				        </thead>
		               <tbody>
				            <c:forEach items="${allMainStages}" var="stage">
					            <tr>
					            	<c:forEach items="${stage.properties}" var="property">
					            		<c:choose>
					            			<c:when test="${property.type eq 'COMBO'}">
					            				<c:forEach items="${property.comboBoxField.options}" var="option">
					            					<c:if test="${option.id eq property.propValue.value}">
					            						<th>${option.value}</th>
					            					</c:if>
					            				</c:forEach>
					            			</c:when>
					            			<c:otherwise>
					            				<th>${property.propValue.value}</th>
					            			</c:otherwise>
					            		</c:choose>
					            	</c:forEach>
					            </tr>
				            </c:forEach>
			            </tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>