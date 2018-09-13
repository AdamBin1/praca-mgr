<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<title>Dane produktu</title>

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

	var object_id = "${object.id}";
	
	function save() {
		
		var data = [];
		
		if(object_id != ""){
	    	var id_data = {
	    			object_id: object_id
	    	};	
	    	data.push(id_data);
		}
		
		$("#mainContainer").find(".row-to-add").each(function() {
			
			var res_data = {
	    			id: 	"-1",
	    			prop_id: "",
	    			type:	"",
	    			val:	"",
	    			
	    	};
						
			    $(this).find("#id").each(function() {
			    	res_data.id = $(this).val();
			    });
			    $(this).find("#prop_id").each(function() {
			    	res_data.prop_id = $(this).val();
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
				showSuccessAlert();
				//w trybie edycji dodawanie id do zapisanych elementów
				if(object_id != ""){
					resposeJsonObject.idPropIdPairs.forEach(updateDivId);
				}
				
			},
			error : function(resposeJsonObject) {
				// Error Message Handler
				showErrorAlert();
				resposeJsonObject.responseJSON.errors.forEach(addErrorDiv);
			}
		});
	};
	
	function updateDivId(value){
		$("#mainContainer").find(".row-to-add").each(function() {
			var row = $(this).find("#prop_id").closest('div');
			if(row.find("#prop_id").first().val() == value.propId){
				if(row.find("#type").first().val() == value.type){
					row.find("#id").first().attr("value", value.id);
				}
			}
		});
	};
	
	function addErrorDiv(value){
		var divToInsert = $( "#errorToInsert" ).clone();
		divToInsert.attr("id","error");
		divToInsert.text(decodeAscii(value.error));
		divToInsert.appendTo( "#error-alert-txt" );		
	};
	
    function showSuccessAlert() {
    	$("#alert").empty();
    	var alertToInsert = $( "#success-alertToInsert" ).clone();
    	alertToInsert.attr("id","success-alert");
    	alertToInsert.appendTo($("#alert"));
        $("#success-alert").fadeTo(2000, 500).slideUp(500, function(){
      		 $("#success-alert").slideUp(500);
        });   
    };
    
    function showErrorAlert() {
    	$("#alert").empty();
    	var alertToInsert = $( "#error-alertToInsert" ).clone();
    	alertToInsert.attr("id","error-alert");
    	alertToInsert.appendTo($("#alert"));
    };
    
    function decodeAscii(text){
    	text = text.replace(/&#261;/g, "ą");
    	text = text.replace(/&#263;/g, "ć");
    	text = text.replace(/&#281;/g, "ę");
    	text = text.replace(/&#322;/g, "ł");
    	text = text.replace(/&#324;/g, "ń");
    	text = text.replace(/&#243;/g, "ó");
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
			<div class="card-header">
				<c:choose>
					<c:when test="${empty object.id}">
					Nowy produkt	
					</c:when>
					<c:otherwise>
					Edycja produktu
					</c:otherwise>
				</c:choose>
			</div>
			<div class="card-body m-3">
			<c:forEach items="${stage.properties}" var="property">
				<div id="row" class="form-group row row-to-add">
				  <label class="col-form-label col-2">${property.name}</label>
				  <div class="col-5">
					<c:choose>
						<c:when test="${property.type eq 'TEXT'}">
							<input class="form-control" id="val" maxlength="${property.length}" value="${property.propValue.value}"/>
						</c:when>
						<c:when test="${property.type eq 'COMBO'}">
							<select class="form-control" id="val">
								<option value=""/>
								<c:forEach items="${property.comboBoxField.options}" var="combobox">
									<c:choose>
										<c:when test="${property.propValue.value eq combobox.id}">
											<option value="${combobox.id}" selected>${combobox.value}</option>
										</c:when>
										<c:otherwise>
											<option value="${combobox.id}">${combobox.value}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</c:when>
						<c:when test="${property.type eq 'DATE'}">
							<input class="form-control" type="date" id="val" value="${property.propValue.value}"/>
						</c:when>
					</c:choose>
					<input id="prop_id" hidden="true" value="${property.id}"/>
					<input id="id" hidden="true" value="${property.propValue.id}"/>
					<input id="type" hidden="true" value="${property.type}"/>
				  </div>
			  </div>
			  </c:forEach>
			<div class="container">
				<div class="row break1">
					<div class="">
						<button class="btn btn-primary" onclick="save()">Zapisz</button>
						<c:if test="${not empty object.id}">
							<button class="btn btn-light" onclick="location.href='${object.id}/etap/${object.activeStageId}'">Pokaż aktywny etap</button>
						</c:if>
						<button class="btn btn-light" onclick="location.href='/SpringMVC/modelowanie/produkty'">Pokaż produkty</button>
					</div>
				</div>
			</div>
			</div>
		</div>
	</div>
	
	<div hidden="true">
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