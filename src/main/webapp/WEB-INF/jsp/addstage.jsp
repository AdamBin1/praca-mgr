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

<script>
	//	require(["dojo/parser", "dijit/form/DateTextBox", "dijit/form/ComboBox"]);
</script>

<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">

	var v1nameTEXT = "<label id=\"v1name\" for=\"val1\">Długość</label>";
	var v1nameCOMBO = "<label id=\"v1name\" for=\"val1\">Wartości</label>";
	var v1nameDATE =  "<label id=\"v1name\" for=\"val1\" hidden=\"true\"/>";
	
	var val1TEXT = "<input id=\"val1\" type=\"number\" min=\"1\" max=\"999\"/>";
	// var val1COMBO pobierany z gotowego formularza
	var val1DATE = "<input id=\"val1\" hidden=\"true\"/>";

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
	
	function addProperty(){
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
		
		
		
		
	//	cell.innerHTML = "<label>Opcja: </label>" +
	//	"<input id=\"value\" maxlength=\"255\"/>" +
	//	"<label for=\"sec\"> Numer w sekwencji: </label>" +
	//	"<input id=\"sec\" type=\"number\" min=\"1\" max=\"999\"/>";
	};
	
	function typeChange(selectedObject) {
		
		var currentRow = $(selectedObject).closest('tr');
		var value = selectedObject.value;
		
		if(value == "COMBO"){
		    $("#toReplace tr").find("#val1").each(function() {
		    	newElementVal1 = $(this).clone();
		    	newElementVal1.show();
		    });
			newElementV1name = v1nameCOMBO;
		    
		} else if(value == "TEXT"){

			newElementV1name = v1nameTEXT;
			newElementVal1 = val1TEXT;
		    
		} else if(value == "DATE"){

			newElementV1name = v1nameDATE;
			newElementVal1 = val1DATE;
			
		}
		
	    $(currentRow).find("#v1name").each(function() {
	    	$(this).replaceWith(newElementV1name);
	    });
	    $(currentRow).find("#val1").each(function() {
	    	$(this).replaceWith(newElementVal1);
   	    });
		
	}
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
	
	Konfiguracja etapu
	<button onclick="addProperty()">Dodaj właściwość</button>
	<br/><br/>
		<label for="name">Nazwa: </label>
		<input id="name" maxlength="255" value="${stage.name}"></input>
		<label for="sec">Numer w sekwencji: </label>
		<input id="sec" type="number" min="1" max="999" value="${stage.sec}"/>
	<br/><br/>

	<table id="propTable">
		<c:if test="${empty stage.properties}">
			<script>addProperty();</script>
		</c:if>
		<c:forEach items="${stage.properties}" var="property">
			<tr>
				<td hidden="true">
					<input id="id" hidden="true" value="${property.id}"/>
				</td>
				<td>
					<label for="sec">Numer w sekwencji: </label>
				</td>
				<td>
					<input id="sec" type="number" min="1" max="999" value="${property.sec}"/>
				</td>
				<td>
					<label for="name">Nazwa</label>
				</td>
				<td>
					<input id="name" maxlength="255" value="${property.name}"></input>
				</td>
				<td>
					<label for="type">Typ</label>
				</td>
				<td>
					<select id="type" data-dojo-type="dijit/form/ComboBox" onchange="typeChange(this)">
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
				</td>
				<c:choose>
					<c:when test="${property.type eq 'TEXT'}">
					<td>
						<label id="v1name" for="val1">Długość</label>
					</td>
					<td>
						<input id="val1" type="number" min="1" max="999" value="${property.length}"/>
					</td>
					</c:when>
				</c:choose>
				<c:choose>
					<c:when test="${property.type eq 'COMBO'}">
						<td>
							<label id="v1name" for="val1">Wartości</label>
						</td>
						<td>
							<select id="val1" data-dojo-type="dijit/form/ComboBox">
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
						</td>
					</c:when>
				</c:choose>
				<c:choose>
					<c:when test="${property.type eq 'DATE'}">
						<td>
							<label id="v1name" for="val1" hidden="true"/>
						</td>
						<td>
							<input id="val1" hidden="true"/>
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
	
	<table id="toReplace">
		<tr>
			<td>
				<select id="val1" hidden="true" data-dojo-type="dijit/form/ComboBox">
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
			</td>
		</tr>
	</table>

</body>