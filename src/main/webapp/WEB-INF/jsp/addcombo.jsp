<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<title>Konfiguracja pola wyboru</title>
<head>
<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
<meta http-equiv="Pragma" content="no-cache">
	
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

		$("#comboboxOptions tr").each(function(rowIndex) {
			var res_data = {
	    			id: 	"-1",
	    			val: 	"",
	    			sec:	0
	    	};
			$(this).find("td").each(function() {
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
	
	function addOption(){
		var table = document.getElementById("comboboxOptions");
		var row = table.insertRow(-1);
		var cell = row.insertCell(0);
		cell.innerHTML = "<label>Opcja: </label>" +
		"<input id=\"value\" maxlength=\"255\"/>" +
		"<label for=\"sec\"> Numer w sekwencji: </label>" +
		"<input id=\"sec\" type=\"number\" min=\"1\" max=\"999\"/>";
	};
</script>

</head>
<body>
	<label for="name">Nazwa: </label>
	<input id="name" maxlength="255" value="${combobox.name}"></input>
	<button onclick="addOption()">Dodaj opcję</button>
	<table id="comboboxOptions">
		<c:if test="${empty combobox.options}">
			<script>addOption();</script>
		</c:if>
		<c:forEach items="${combobox.options}" var="option">
			<tr>
				<td>
					<label for="value">Opcja: </label>
					<input id="value" maxlength="255" value="${option.value}"/>
					<label for="sec">Numer w sekwencji: </label>
					<input id="sec" type="number" min="1" max="999" value="${option.sec}"/>
					<input id="id" hidden="true" value="${option.id}"/>
				</td>
			</tr>
		</c:forEach>
	</table>
	<table>
		<tr>
			<td>
				<button onclick="save()">Zapisz</button>
				<button onclick="location.href='/SpringMVC/konfiguracja/pola_wyboru'">Wróć</button>
			</td>
		</tr>
	</table>
</body>
</html>