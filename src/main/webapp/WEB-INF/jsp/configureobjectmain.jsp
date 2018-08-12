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
		    $(this).find("input, select").each(function() {
		    	var res_data = {
		    			id: 	$(this).attr("id"),
		    			val: 	$(this).val()
		    	};
		    	data.push(res_data);
		    });
		});

		$.ajax({
			url : "update/3",
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
		<c:forEach items="${properties}" var="properties">
			<tr>
				<td>
					<label>${properties.id}</label>
				</td>
				<td>
					<label>Nazwa</label>
				</td>
				<td>
					<input id="Name${properties.id}" maxlength="255" value="${properties.name}"></input>
				</td>
				<td>
					<label>Typ</label>
				<td>
				<td>
					<select id="Type${properties.id}" data-dojo-type="dijit/form/ComboBox">
						<c:choose>
							<c:when test="${properties.type eq 'TEXT'}">
								<option selected>Pole Tekstowe</option>
							</c:when>
							<c:otherwise>
								<option>Pole Tekstowe</option>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${properties.type eq 'COMBO'}">
								<option selected>Combo Box</option>
							</c:when>
							<c:otherwise>
								<option>Combo Box</option>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${properties.type eq 'DATE'}">
								<option selected>Data</option>
							</c:when>
							<c:otherwise>
								<option>Data</option>
							</c:otherwise>
						</c:choose>
					</select>
				</td>
			</tr>		
		</c:forEach>
	</table>

PONIZEJ DO USUNIECIA

	<c:if test="${not empty properties}">
		<c:forEach items="${properties}" var="properties">
			<table id="propTable">
				<c:if test="${properties.type eq 'TEXT'}">
					<tr>
						<td>
							<label for="${properties.id}">${properties.name}</label>
						</td>
						<td>
							<input id="${properties.id}" maxlength="${properties.length}" value="${properties.value}"></input>
						<td>
					</tr>
				</c:if>

				<c:if test="${properties.type eq 'DATE'}">
					<tr>
						<td>
							<label for="${properties.id}">${properties.name}</label>
						</td>
						<td>
							<input id="${properties.id}" value="${properties.getValue()}"
							data-dojo-type="dijit/form/DateTextBox" required="true" />
						</td>
					</tr>
				</c:if>

				<c:if test="${properties.type eq 'COMBO'}">
					<tr>
						<td>
							<label for="${properties.id}">${properties.name}</label>
						</td>
						<td>
							<select id="${properties.id}" type="text" data-dojo-type="dijit/form/ComboBox"">
								<c:forEach items="${properties.options}" var="option">
									<c:choose>
										<c:when test="${option.id == properties.choosenOption}">
											<option selected>${option.value}</option>
										</c:when>
										<c:otherwise>
											<option>${option.value}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</td>
					</tr>
				</c:if>
			</table>
		</c:forEach>
	</c:if>

	<h1 id="greeting">Hello</h1>
	<button onclick="zapisz()">Zapisz</button>

	<input type="button" onclick="zmien()" id="submit" value="Ajax Submit" />

</body>