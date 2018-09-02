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
<title>Konfiguracja obiektu</title>

<script>
	//	require(["dojo/parser", "dijit/form/DateTextBox", "dijit/form/ComboBox"]);
</script>

<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
	function zapisz() {
		
		var data = [];

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
			url : "konfiguracja/update",
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
	
	GŁÓWNA KONFIGURACJA
	<br/><br/><br/>

	<table id="propTable">
		<c:forEach items="${properties}" var="property">
			<tr>
				<td>
					<input id="id" hidden="true" value="${property.id}"/>
				</td>
				<td>
					<label for="sec">Numer w sekwencji: </label>
				</td>
				<td>
					<input id="sec" type="number" min="1" max="999" value="${property.sec}"/>
				</td>
				<td>
					<label>Nazwa</label>
				</td>
				<td>
					<input id="name" maxlength="255" value="${property.name}"></input>
				</td>
				<td>
					<label>Typ</label>
				<td>
				<td>
					<select id="type" data-dojo-type="dijit/form/ComboBox">
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
				<td>
					
					<c:choose>
						<c:when test="${property.type eq 'TEXT'}">
						<td>
							<label for="val1">Długość</label>
						</td>
						<td>
							<input id="val1" type="number" min="1" max="999" value="${property.length}"/>
						</td>
						</c:when>
					</c:choose>
					<c:choose>
						<c:when test="${property.type eq 'COMBO'}">
							<td>
								<label for="val1">Wartości</label>
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
						</c:when>
					</c:choose>
					<c:choose>
						<c:when test="${property.type eq 'DATE'}">
							<!-- nastepne wartosci do daty -->
						</c:when>
					</c:choose>
				</td>
			</tr>		
		</c:forEach>
	</table>
	
	<br/><br/><br/>
	
	<table>
		<tr>
			<td>
				<button onclick="zapisz()">Zapisz</button>
			</td>
			<td>
				<button onclick="location.href='konfiguracja/pola_wyboru'">Pola wyboru</button>
			</td>
		</tr>
	</table>

</body>