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
<title>Dane obiektu</title>

<script>
	//	require(["dojo/parser", "dijit/form/DateTextBox", "dijit/form/ComboBox"]);
</script>

<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">

	function zapisz() {
		
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

		$("#propTable tr").each(function(rowIndex) {
			var res_data = {
	    			id: 	"-1",
	    			name: 	"",
	    			sec:	0,
	    			type:	"",
	    			val1:	"",
	    			
	    	};
						
			$(this).find("td").each(function() {
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

<script>
	function zmien() {
		require([ 'dojo/dom', 'dojo/domReady!' ], function(dom) {
			var greeting = dom.byId('greeting');
			greeting.innerHTML += ' from Dojo!';
		});
	}
</script>

</head>

<body class="claro">
	<script src="//ajax.googleapis.com/ajax/libs/dojo/1.13.0/dojo/dojo.js"
		data-dojo-config="async: true"></script>

	<script>
		require([ 'dojo/dom', 'dojo/domReady!' ], function(dom) {
			var greeting = dom.byId('greeting');
			//       greeting.innerHTML += ' from Dojo!';
		});
	</script>
	
	<c:choose>
		<c:when test="${empty object.activeStage}">
		Nowy obiekt	
		</c:when>
		<c:otherwise>
		Edycja obiektu
		</c:otherwise>
	</c:choose>
	<br/><br/><br/>

	<table id="propTable">
		<c:forEach items="${object.values}" var="propvalue">
			<tr>
				<td hidden="true">
					<input id="id" hidden="true" value="${propvalue.id}"/>
				</td>
				<td>
					<label id="name" for="val">${propvalue.property.name}</label>
				</td>
				<c:choose>
					<c:when test="${propvalue.type eq 'TEXT'}">
					<td>
						<input id="val" value="${propvalue.value}"/>
					</td>
					</c:when>
				</c:choose>
				<c:choose>
					<c:when test="${propvalue.type eq 'COMBO'}">
						<td>
							<select id="val" data-dojo-type="dijit/form/ComboBox">
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
						</td>
					</c:when>
				</c:choose>
				<c:choose>
					<c:when test="${property.type eq 'DATE'}">
						<td>
							<input id="val" hidden="true"/>
						</td>
					</c:when>
				</c:choose>
			</tr>		
		</c:forEach>
	</table>
	
	<br/><br/><br/>
	
	<table>
		<tr>
			<td>
				<button onclick="zapisz()">Zapisz</button>
				<button onclick="location.href='/SpringMVC/konfiguracja/etapy'">Wróć</button>
			</td>
		</tr>
	</table>
</body>