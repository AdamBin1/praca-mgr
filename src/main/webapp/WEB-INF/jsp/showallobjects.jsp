<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Produkty</title>

<script src="http://code.jquery.com/jquery-latest.js"></script>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
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

.selected {
	background-color: #17a2b8 !important;
}

</style>

<script type="text/javascript">

	var clicked_id;

	$(document).ready(function() {
		var table = $('#objects').DataTable( {
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
	    
	    $('#objects tbody').on( 'click', 'tr', function () {
	        if ( $(this).hasClass('selected') ) {
	            $(this).removeClass('selected');
	            clicked_id = null;
	            disableButtons();
	        } else {
	            table.$('tr.selected').removeClass('selected');
	            $(this).addClass('selected');
	            clicked_id = $(this).attr('id');
	            if(clicked_id != null){
	            	enableButtons();	            	
	            }
	        }
	    } );
	} );
	
	function disableButtons(){
        $("#editButton").first().prop( "disabled", true );
        $("#showActiveStageButton").first().prop( "disabled", true );
        
        $("#removeButton").first().prop( "disabled", true );
	};
	
	function enableButtons(){
        $("#editButton").first().prop( "disabled", false );
        $("#showActiveStageButton").first().prop( "disabled", false );
        $("#removeButton").first().prop( "disabled", false );		
	};
	
	function edit(){
		location.href="produkt/" + clicked_id;
	};
	
	function showActiveStage(){
		location.href="produkt/" + clicked_id + "/otworz_aktywny";
	};
	
	function remove(){
		
		$.ajax({
			url : "usun/" + clicked_id,
			type : "POST",
			async : false, //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
			cache : false, //This will force requested pages not to be cached by the browser          
			processData : false, //To avoid making query String instead of JSON
			success : function(resposeJsonObject) {
				// Success Message Handler
				$('#objects').DataTable().rows('.selected').remove().draw();
				disableButtons();
			},
			error : function(resposeJsonObject) {
				// Error Message Handler
				showErrorAlert();
				//resposeJsonObject.responseJSON.errors.forEach(addErrorDiv);
			}
		});
	};
	
	function addErrorDiv(value){
		var divToInsert = $( "#errorToInsert" ).clone();
		divToInsert.attr("id","error");
		divToInsert.text(decodeAscii(value.error));
		divToInsert.appendTo( "#error-alert-txt" );		
	};
	
    function showErrorAlert() {
    	$("#alert").empty();
    	var alertToInsert = $( "#error-alertToInsert" ).clone();
    	alertToInsert.attr("id","error-alert");
    	alertToInsert.appendTo($("#alert"));
    };
	
</script>

</head>

<body>
	<div id="alert">
	</div>
	<div id="mainContainer" class="container">
		<div class="card break1">
			<div class="card-header">Produkty</div>
			<div class="card-body m-3">
			
				<div>
					<div class="row">
						<div class="col-sm">
							<button class="btn btn-primary" onclick="location.href='dodaj'">Dodaj</button>
							<button id="editButton" class="btn btn-primary" onclick="edit()" disabled="disabled">Edytuj</button>
							<button id="showActiveStageButton" class="btn btn-primary" onclick="showActiveStage()" disabled="disabled">Otwórz aktywny etap</button>
							<button id="removeButton" class="btn btn-secondary" onclick="remove()" disabled="disabled">Usuń</button>
							<button class="btn btn-light" onclick="location.href='/SpringMVC'">Zakończ modelowanie</button>
						</div>
					</div>
				</div>
				
				<div class="break1">
					<table id="objects" class="table table-striped table-bordered" style="width:100%">
				        <thead>
				            <tr>
				            <c:forEach items="${stage.properties}" var="property">
				            	<th>${property.name}</th>
				            </c:forEach>
				            </tr>
				        </thead>
		               <tbody>
				            <c:forEach items="${allMainStages}" var="stage">
					            <tr id="${stage.properties.get(0).propValue.objectId}">
					            	<c:forEach items="${stage.properties}" var="property">
					            		<c:choose>
					            			<c:when test="${property.type eq 'COMBO'}">
					            				<c:choose>
					            					<c:when test="${empty property.propValue.value}">
					            						<td></td>
					            					</c:when>
					            					<c:otherwise>
						            					<c:forEach items="${property.comboBoxField.options}" var="option">
							            					<c:if test="${option.id eq property.propValue.value}">
							            						<td>${option.value}</td>
							            					</c:if>
						            					</c:forEach>
					            					</c:otherwise>
				            					</c:choose>
					            			</c:when>
					            			<c:otherwise>
					            				<td>${property.propValue.value}</td>
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
	</div>
	<div hidden="true">
		<div id="errorToInsert"></div>
		<div class="alert alert-danger" id="error-alertToInsert">
		    <button type="button" class="close" data-dismiss="alert">x</button>
		    <div id="error-alert-txt"></div>
		</div>
	</div>
</body>
</html>