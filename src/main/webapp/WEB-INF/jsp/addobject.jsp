<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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

<title>Dane obiektu</title>

<script>
	//	require(["dojo/parser", "dijit/form/DateTextBox", "dijit/form/ComboBox"]);
</script>

<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">

	function save() {
		
		var data = [];
		
		var stage_id = "${stage.id}";
		
		if(stage_id != ""){
	    	var id_data = {
    			stage_id: stage_id
	    	};	
	    	data.push(id_data);
		}

		$("#mainContainer").find(".row-to-add").each(function() {
			
			var res_data = {
	    			id: 	"-1",
	    			type:	"",
	    			val:	"",
	    			
	    	};
						
			    $(this).find("#id").each(function() {
			    	res_data.id = $(this).val();
			    });
			    $(this).find("#type").each(function() {
			    	res_data.type = $(this).val();
			    });
			    $(this).find("#val").each(function() {
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
	
</script>

</head>

<body>
	<div id="mainContainer" class="container">
		<div class="card break1">
			<div class="card-header">
			<c:choose>
				<c:when test="${empty object.id}">
				Nowy obiekt	
				</c:when>
				<c:otherwise>
				Edycja obiektu
				</c:otherwise>
			</c:choose>
			</div>
			<div class="card-body m-3">
			<c:forEach items="${object.values}" var="propvalue">
				<div id="row" class="form-group row row-to-add">
				  <label class="col-form-label col-3">${propvalue.property.name}</label>
				  <div class="col-4">
					<c:choose>
						<c:when test="${propvalue.type eq 'TEXT'}">
							<input class="form-control" id="val" maxlength="${propvalue.property.length}" value="${propvalue.value}">
						</c:when>
						<c:when test="${propvalue.type eq 'COMBO'}">
							<select class="form-control" id="val">
								<option value="-1"></option>
								<c:forEach items="${propvalue.property.comboBoxField.options}" var="combobox">
									<c:choose>
										<c:when test="${propvalue.value eq combobox.id}">
											<option value="${combobox.id}" selected>${combobox.value}</option>
										</c:when>
										<c:otherwise>
											<option value="${combobox.id}">${combobox.value}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</c:when>
						<c:when test="${propvalue.type eq 'DATE'}">
							<input class="form-control" type="date" id="val" value="${propvalue.value}">
						</c:when>
					</c:choose>
					<input id="id" hidden="true" value="${propvalue.id}"/>
					<input id="type" hidden="true" value="${propvalue.type}"/>
				  </div>
			  </div>
			  </c:forEach>
			<div class="container">
				<div class="row break1">
					<div class="col-sm">
						<button class="btn btn-primary" onclick="save()">Zapisz</button>
						<button class="btn btn-light" onclick="location.href='/SpringMVC'">Wróć</button>
					</div>
				</div>
			</div>
			</div>
		</div>
	</div>
</body>