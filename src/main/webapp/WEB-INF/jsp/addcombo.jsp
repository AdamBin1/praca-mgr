<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<title>Konfiguracja pola wyboru</title>
<head>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
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
	
<script src="http://code.jquery.com/jquery-latest.js"></script>

<script type="text/javascript">
	function save() {
		
		var data = [];
		
		var combo_id = "${combobox.id}";
		
		if(combo_id != ""){
	    	var id_data = {
    			combo_id: combo_id
	    	};	
	    	data.push(id_data);
		}
		
		var name = {
				name: $("#name").val()
		};
		
		data.push(name);

		$("#mainContainer").find(".row-to-add").each(function() {
			var res_data = {
	    			id: 	"-1",
	    			val: 	"",
	    			sec:	0
	    	};
			    $(this).find("#id").each(function() {
			    	res_data.id = $(this).val();
			    });
			    $(this).find("#sec").each(function() {
			    	res_data.sec = $(this).val();
			    });
			    $(this).find("#value").each(function() {
			    	res_data.val = $(this).val();
			    });
			    data.push(res_data);
		});
		

		$.ajax({
			url : "zatwierdz",
			type : "POST",
			dataType : 'json',
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify(data), //Stringified Json Object
			async : false, //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
			cache : false, //This will force requested pages not to be cached by the browser          
			processData : false, //To avoid making query String instead of JSON
			success : function(resposeJsonObject) {
				// Success Message Handler
				alert(resposeJsonObject.message);
			}
		});
	};
	
	function addRow(){		
		var rowToInsert = $( "#rowToInsert" ).clone();
		rowToInsert.attr("id","row");
		rowToInsert.insertBefore( "#buttons" );
	};
	
	function removeOption(selected){
		$(selected).closest( 'div' ).remove();
	};
</script>

</head>
<body>
	<div id="mainContainer" class="container">
		<div class="card break1">
			<div class="card-header">Pole wyboru</div>
			<div class="card-body m-3">
				<div class="form-group row">
					  <label class="col-form-label">Nazwa</label>
					  <div class="col-6">
					    <input id="name" class="form-control" type="text" value="${combobox.name}">
					  </div>
					  <div class="col-3">
					  	<button class="btn btn-light" onclick="addRow()">Dodaj opcję</button>
					  </div>
				</div>
				
				<c:if test="${empty combobox.options}">
					<script>addRow();</script>
				</c:if>
				<c:forEach items="${combobox.options}" var="option">
					<div id="row" class="form-group row row-to-add">
					  <label class="col-form-label">Opcja</label>
					  <div class="col-7">
					    <input class="form-control" id="value" maxlength="255" value="${option.value}">
					  </div>
					  <label class="col-form-label">Numer w sekwencji</label>
					  <div class="col-2">
					    <input class="form-control" id="sec" type="number" min="1" max="999" value="${option.sec}">
					  </div>
					  <input id="id" hidden="true" value="${option.id}"/>
					</div>
				</c:forEach>
					
				<div id="buttons" class="container">
					<div class="row break1">
						<div class="col-sm">
							<button class="btn btn-primary" onclick="save()">Zapisz</button>
							<button class="btn btn-light" onclick="location.href='/SpringMVC/konfiguracja/pola_wyboru'">Wróć</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div hidden="true">
		<div id="rowToInsert" class="form-group row">
		  <label class="col-form-label">Opcja</label>
		  <div class="col-7">
		    <input class="form-control" id="value" maxlength="255">
		  </div>
		  <label class="col-form-label">Numer w sekwencji</label>
		  <div class="col-2">
		    <input class="form-control" id="sec" type="number" min="1" max="999">
		  </div>
			<button type="button" class="close" aria-label="Close" onclick="removeOption(this)">
			  <span aria-hidden="true">&times;</span>
			</button>
		</div>
	</div>
</body>
</html>