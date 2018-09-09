<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Konfiguracja obiektu</title>

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
		
		$("#mainContainer").find(".row-to-add").each(function() {
			
			var res_data = {
	    			id: 	"",
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
				showSuccessAlert();
				//dodawanie id do zapisanych elementów
				resposeJsonObject.idSecPairs.forEach(updateDivId);
				disableFieldsAfterSave();
			},
			error : function(resposeJsonObject) {
				// Error Message Handler
				showErrorAlert();
				resposeJsonObject.responseJSON.errors.forEach(addErrorDiv);
			}
		});
	};

	function disableFieldsAfterSave(){
		removeRemovalButtons();
		disableTypes();
		disableVal1Combo();
	}
	
	function removeRemovalButtons(){
		$("#mainContainer").find("#removeOption").each(function() {
			$(this).remove();
		});
	};
	
	function disableTypes(){
		$("#mainContainer").find(".type-select").each(function() {
			$(this).prop( "disabled", true );
		});
	};
	
	function disableVal1Combo(){
		$("#mainContainer").find(".val1-combo").each(function() {
			$(this).prop( "disabled", true );
		});
	};
	
	function updateDivId(value){
		$("#mainContainer").find(".row-to-add").each(function() {
		    $(this).find("#sec").each(function() {
		    	if($(this).val() == value.sec){
		    		setIdForSec(this, value.id);
		    	}
		    });
		});
	};
	
	function setIdForSec(sec, id){
		var currentRow = $(sec).closest('div').parent();
		currentRow.find("#id").each(function() {
	    	$(this).attr("value", id);
	    });
	};
	
	function addErrorDiv(value){
		var divToInsert = $( "#errorToInsert" ).clone();
		divToInsert.attr("id","error");
		divToInsert.text(decodeAscii(value.error));
		divToInsert.appendTo( "#error-alert-txt" );		
	}
	
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
		    
		    $(currentRow).find("#val1div").show();
		    
		} else if(value == "TEXT"){

		    $("#v1nameText").each(function() {
		    	newElementV1name = $(this).clone();
		    });
		    $("#val1Text").each(function() {
		    	newElementVal1 = $(this).clone();
		    });
		    
		    $(currentRow).find("#val1div").show();
		    
		} else if(value == "DATE"){

		    $("#v1nameDate").each(function() {
		    	newElementV1name = $(this).clone();
		    });
		    $("#val1Date").each(function() {
		    	newElementVal1 = $(this).clone();
		    });
		    
		    $(currentRow).find("#val1div").hide();			
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
	
    function showSuccessAlert() {
    	$("#alert").empty();
    	var alertToInsert = $( "#success-alertToInsert" ).clone();
    	alertToInsert.attr("id","success-alert");
    	alertToInsert.appendTo($("#alert"));
        $("#success-alert").fadeTo(2000, 500).slideUp(500, function(){
      		 $("#success-alert").slideUp(500);
        });   
        $(window).scrollTop(0);
    };
    
    function showErrorAlert() {
    	$("#alert").empty();
    	var alertToInsert = $( "#error-alertToInsert" ).clone();
    	alertToInsert.attr("id","error-alert");
    	alertToInsert.appendTo($("#alert"));
    	$(window).scrollTop(0);
    };
	
    function decodeAscii(text){
    	text = text.replace(/&#261;/g, "ą");
    	text = text.replace(/&#263;/g, "ć");
    	text = text.replace(/&#281;/g, "ę");
    	text = text.replace(/&#322;/g, "ł");
    	text = text.replace(/&#324;/g, "ń");
    	text = text.replace(/&#211;/g, "ó");
    	text = text.replace(/&#347;/g, "ś");
    	text = text.replace(/&#378;/g, "ź");
    	text = text.replace(/&#380;/g, "ż");
    	return text;
    }
</script>

</head>

<body>
	<div id="alert">
	</div>
	<div id="mainContainer" class="container">
		<div class="card break1">
			<div class="card-header">Konfiguracja obiektu</div>
			<div class="card-body m-3">

				<div class="form-group row">
					  <div class="col-3">
					  	<button class="btn btn-light" onclick="addRow()">Dodaj właściwość</button>
					  </div>
				</div>
				
				<c:if test="${empty mainstage.properties}">
					<script>addRow();</script>
				</c:if>
				<c:forEach items="${mainstage.properties}" var="property">
					<div id="row" class="form-group row row-to-add">
					  <label class="col-form-label">Numer w sekwencji</label>
					  <div class="col-2">
					    <input class="form-control" id="sec" type="number" min="1" max="999" value="${property.sec}">
					  </div>
					  <label class="col-form-label">Nazwa</label>
					  <div class="col-2">
					    <input class="form-control" id="name" maxlength="50" value="${property.name}">
					  </div>
  					  <label class="col-form-label">Typ</label>
					  <div class="col-2">
						<select id="type" class="form-control" disabled="disabled">
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
									<option value="COMBO" selected>Pole wyboru</option>
								</c:when>
								<c:otherwise>
									<option value="COMBO">Pole wyboru</option>
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
									<select id="val1" class="form-control" disabled="disabled">
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
							<label id="v1name"class="col-form-label" id="v1name" hidden="true"></label>
							<div class="col-2">
								<input id="val1" hidden="true">
							</div>
							</c:when>
						</c:choose>
					  <input id="id" hidden="true" value="${property.id}"/>
					</div>
					
				</c:forEach>
			

				
				<div id="buttons" class="container">
					<div class="row break1">
						<div class="col-sm">
							<button class="btn btn-primary" onclick="save()">Zapisz</button>
							<button class="btn btn-light" onclick="location.href='pola_wyboru'">Pola wyboru</button>
							<button class="btn btn-light" onclick="location.href='etapy'">Etapy</button>
							<button class="btn btn-light" onclick="location.href='/SpringMVC'">Zakończ konfigurację</button>
							
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
			    <input class="form-control" id="name" maxlength="50">
			  </div>
			  <label class="col-form-label">Typ</label>
			  <div class="col-2">
				<select id="type" class="form-control type-select" onchange="typeChange(this)">
					<option value="TEXT" selected>Pole Tekstowe</option>
					<option value="COMBO">Pole wyboru</option>
					<option value="DATE">Data</option>
				</select>
			  </div>
			  <label class="col-form-label" id="v1name">Długość</label>
			  <div id="val1div" class="col-2">
			    <input class="form-control" id="val1" type="number" min="1" max="999">
			  </div>
			  <input id="id" hidden="true"/>
			<button id="removeOption" type="button" class="close" aria-label="Close" onclick="removeOption(this)">
			  <span aria-hidden="true">&times;</span>
			</button>
		</div>
		<label class="col-form-label" id="v1nameText">Długość</label>
		<label class="col-form-label" id="v1nameCombo">Wartości</label>
		<label id="v1nameDate" hidden="true"></label>
		<input class="form-control" id="val1Text" type="number" min="1" max="999">
		<select id="val1Combo" class="form-control val1-combo">
		<option></option>
			<c:forEach items="${comboboxes}" var="combobox">
				<option value="${combobox.id}">${combobox.name}</option>
			</c:forEach>
		</select>
		<label id="val1Date" hidden="true"></label>
		<div id="errorToInsert"></div>
		<div class="alert alert-success" id="success-alertToInsert">
		    <button type="button" class="close" data-dismiss="alert">x</button>
		    Dane zostały zapisane
		</div>
		<div class="alert alert-danger" id="error-alertToInsert">
		    <button type="button" class="close" data-dismiss="alert">x</button>
		    <div id="error-alert-txt"></div>
		</div>
	</div>
</body>
</html>