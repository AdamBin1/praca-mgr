<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="//ajax.googleapis.com/ajax/libs/dojo/1.10.4/dijit/themes/claro/claro.css">
<title>Konfiguracja etapu</title>

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
		
		var stage_id = "${stage.id}";
		
		if(stage_id != ""){
	    	var id_data = {
    			stage_id: stage_id
	    	};	
	    	data.push(id_data);
		}
		
		var name = {
				name: $("#name").val()
		};
		
		data.push(name);

		var sec = {
				sec: $("#sec").val()
		};
		
		data.push(sec);

		$("#mainContainer").find(".row-to-add").each(function() {
			
			var res_data = {
	    			id: 	"-1",
	    			name: 	"",
	    			sec:	0,
	    			type:	"",
	    			val1:	"",
	    			
	    	};
						
			    $(this).find("#id").each(function() {
			    	res_data.id = $(this).val();
			    });
			    $(this).find("#sec").each(function() {
			    	res_data.sec = $(this).val();
			    });
			    $(this).find("#name").each(function() {
			    	res_data.name = $(this).val();
			    });
			    $(this).find("#type").each(function() {
			    	res_data.type = $(this).val();
			    });
			    $(this).find("#val1").each(function() {
			    	res_data.val1 = $(this).val();
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
		var table = document.getElementById("propTable");
		var row = table.insertRow(-1);
		var cell0 = row.insertCell(0);
		cell0.innerHTML = "<label for=\"sec\">Numer w sekwencji: </label>";
		var cell1 = row.insertCell(1);
		cell1.innerHTML = "<input id=\"sec\" type=\"number\" min=\"1\" max=\"999\"/>";
		var cell2 = row.insertCell(2);
		cell2.innerHTML = "<label for=\"name\">Nazwa</label>";
		var cell3 = row.insertCell(3);
		cell3.innerHTML = "<input id=\"name\" maxlength=\"255\"></input>";
		var cell4 = row.insertCell(4);
		cell4.innerHTML = "<label for=\"type\">Typ</label>";
		var cell5 = row.insertCell(5);
		cell5.innerHTML = "<select id=\"type\" data-dojo-type=\"dijit/form/ComboBox\" onchange=\"typeChange(this)\">" +
			"<option value=\"TEXT\" selected>Pole Tekstowe</option>" +
			"<option value=\"COMBO\">Combo Box</option>" +
			"<option value=\"DATE\">Data</option>" +
			"</select>";
		var cell6 = row.insertCell(6);
		cell6.innerHTML = v1nameTEXT;
		var cell7 = row.insertCell(7);
		cell7.innerHTML = val1TEXT;
		
	};
	
	function addRow(){		
		var rowToInsert = $( "#rowToInsert" ).clone();
		rowToInsert.attr("id","row");
		rowToInsert.insertBefore( "#buttons" );
	};
	
	function removeOption(selected){
		$(selected).closest( 'div' ).remove();
	};
	
	function typeChange(selectedObject) {
		
		var currentRow = $(selectedObject).closest('div').parent();
		var value = selectedObject.value;
		
		if(value == "COMBO"){
		    $("#v1nameCombo").each(function() {
		    	newElementV1name = $(this).clone();
		    });
		    $("#val1Combo").each(function() {
		    	newElementVal1 = $(this).clone();
		    });
		    
		} else if(value == "TEXT"){

		    $("#v1nameText").each(function() {
		    	newElementV1name = $(this).clone();
		    });
		    $("#val1Text").each(function() {
		    	newElementVal1 = $(this).clone();
		    });
		    
		} else if(value == "DATE"){

		    $("#v1nameDate").each(function() {
		    	newElementV1name = $(this).clone();
		    });
		    $("#val1Date").each(function() {
		    	newElementVal1 = $(this).clone();
		    });
			
		}
		
		newElementV1name.attr("id","v1name");
		newElementVal1.attr("id","val1");
		
	    $(currentRow).find("#v1name").each(function() {
	    	$(this).replaceWith(newElementV1name);
	    });
	    $(currentRow).find("#val1").each(function() {
	    	$(this).replaceWith(newElementVal1);
   	    });
		
	}
</script>

</head>
	
<body>
	<div id="mainContainer" class="container">
		<div class="card break1">
			<div class="card-header">Konfiguracja etapu</div>
			<div class="card-body m-3">
				<div class="form-group row">
					  <label class="col-form-label">Nazwa</label>
					  <div class="col-4">
					    <input id="name" class="form-control" type="text" value="${stage.name}">
					  </div>
					  <label class="col-form-label">Numer w sekwencji</label>
					  <div class="col-2">
					    <input id="sec" class="form-control" type="number" min="1" max="999" value="${stage.sec}">
					  </div>
					  <div class="col-3">
					  	<button class="btn btn-light" onclick="addRow()">Dodaj właściwość</button>
					  </div>
				</div>
				
				<c:if test="${empty stage.properties}">
					<script>addRow();</script>
				</c:if>
				<c:forEach items="${stage.properties}" var="property">
					<div id="row" class="form-group row row-to-add">
					  <label class="col-form-label">Numer w sekwencji</label>
					  <div class="col-2">
					    <input class="form-control" id="sec" type="number" min="1" max="999" value="${property.sec}">
					  </div>
					  <label class="col-form-label">Nazwa</label>
					  <div class="col-2">
					    <input class="form-control" id="name" maxlength="255" value="${property.name}">
					  </div>
  					  <label class="col-form-label">Typ</label>
					  <div class="col-2">
						<select id="type" class="form-control" onchange="typeChange(this)">
							<c:choose>
								<c:when test="${property.type eq 'TEXT'}">
									<option value="TEXT" selected>Pole Tekstowe</option>
								</c:when>
								<c:otherwise>
									<option value="TEXT">Pole Tekstowe</option>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${property.type eq 'COMBO'}">
									<option value="COMBO" selected>Combo Box</option>
								</c:when>
								<c:otherwise>
									<option value="COMBO">Combo Box</option>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${property.type eq 'DATE'}">
									<option value="DATE" selected>Data</option>
								</c:when>
								<c:otherwise>
									<option value="DATE">Data</option>
								</c:otherwise>
							</c:choose>
						</select>
					  </div>
						<c:choose>
							<c:when test="${property.type eq 'TEXT'}">
							  <label id="v1name"class="col-form-label" id="v1name">Długość</label>
							  <div class="col-2">
							    <input class="form-control" id="val1" type="number" min="1" max="999" value="${property.length}">
							  </div>
							</c:when>
						</c:choose>
						<c:choose>
							<c:when test="${property.type eq 'COMBO'}">
								<label class="col-form-label" id="v1name">Wartości</label>
								<div class="col-2">
									<select id="val1" class="form-control">
										<c:forEach items="${comboboxes}" var="combobox">
										
											<c:choose>
												<c:when test="${property.comboBoxField.id eq combobox.id}">
													<option value="${combobox.id}" selected>${combobox.name}</option>
												</c:when>
												<c:otherwise>
													<option value="${combobox.id}">${combobox.name}</option>
												</c:otherwise>
											</c:choose>
										
										</c:forEach>
									</select>
								</div>
							</c:when>
						</c:choose>
						<c:choose>
							<c:when test="${property.type eq 'DATE'}">
							<label id="v1name"class="col-form-label" id="v1name"></label>
							</c:when>
						</c:choose>
					  <input id="id" hidden="true" value="${property.id}"/>
					</div>
					
				</c:forEach>

				<div id="buttons" class="container">
					<div class="row break1">
						<div class="col-sm">
							<button class="btn btn-primary" onclick="save()">Zapisz</button>
							<button class="btn btn-light" onclick="location.href='/SpringMVC/konfiguracja/etapy'">Wróć</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div hidden="true">
		<div id="rowToInsert" class="form-group row row-to-add">
			  <label class="col-form-label">Numer w sekwencji</label>
			  <div class="col-2">
			    <input class="form-control" id="sec" type="number" min="1" max="999">
			  </div>
			  <label class="col-form-label">Nazwa</label>
			  <div class="col-2">
			    <input class="form-control" id="name" maxlength="255">
			  </div>
			  <label class="col-form-label">Typ</label>
			  <div class="col-2">
				<select id="type" class="form-control" onchange="typeChange(this)">
					<option value="TEXT" selected>Pole Tekstowe</option>
					<option value="COMBO">Combo Box</option>
					<option value="DATE">Data</option>
				</select>
			  </div>
			  <label class="col-form-label" id="v1name">Długość</label>
			  <div class="col-2">
			    <input class="form-control" id="val1" type="number" min="1" max="999">
			  </div>
			<button type="button" class="close" aria-label="Close" onclick="removeOption(this)">
			  <span aria-hidden="true">&times;</span>
			</button>
		</div>
		<label class="col-form-label" id="v1nameText">Długość</label>
		<label class="col-form-label" id="v1nameCombo">Wartości</label>
		<label id="v1nameDate" hidden="true"></label>
		<input class="form-control" id="val1Text" type="number" min="1" max="999">
		<select id="val1Combo" class="form-control">
		<option value="-1"></option>
			<c:forEach items="${comboboxes}" var="combobox">
				<option value="${combobox.id}">${combobox.name}</option>
			</c:forEach>
		</select>
		<label id="val1Date" hidden="true"></label>
	</div>
</body>