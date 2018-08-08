<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="//ajax.googleapis.com/ajax/libs/dojo/1.10.4/dijit/themes/claro/claro.css">
<title>Reprezentacja obiektu</title>

<script>
//	require(["dojo/parser", "dijit/form/DateTextBox", "dijit/form/ComboBox"]);
</script>

<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
function zmien2(){
var arrayOfObjects = [];
arrayOfObjects.push({"divid":"1","xCordinates":"1","yCordinates":"1","Height":"1","Width":"1", "PageNo":"1"});

var param = '?objarray=' + JSON.stringify(arrayOfObjects);

var valObject = [{"storedKey":"vc","storedValue":"1","clientId":"1","locationId":"1"},
	 {"storedKey":"vr","storedValue":"","clientId":"1","locationId":"1"}];

$.ajax({ 
    url:"update/3",    
    type:"POST", 
    contentType: "application/json; charset=utf-8",
    data: JSON.stringify(valObject), //Stringified Json Object
    async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
    cache: false,    //This will force requested pages not to be cached by the browser          
    processData:false, //To avoid making query String instead of JSON
    success: function(resposeJsonObject){
        // Success Message Handler
    }
});

$.ajax({
    type: "POST",
    url : "update/2",
    data : "data=" + JSON.stringify(valObject),
    contentType:"application/json; charset=utf-8",
    dataType:"json",
    success : function(resp){
        console.log(resp);
    },
    error : function(resp){
        console.log(resp);
    }
});

var z = '[{"name":"1","age":"2"},{"name":"1","age":"3"}]';
$.ajax({
    url: "update/1",
    data: "data=" + z,
    type: "POST",
    dataType:"json",
    contentType:'application/json'               
});

$.ajax({
     url: 'update/1',
     type: 'POST', 
     data: "data=" + JSON.stringify(arrayOfObjects),
     dataType: 'json', 
     contentType:'application/json',
     success: function(result) {
       alert('SUCCESS');
     }
});
};
</script>

<script>
	function zmien(){
        require([
            'dojo/dom',
            'dojo/domReady!'
        ], function (dom) {
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
        require([
            'dojo/dom',
            'dojo/domReady!'
        ], function (dom) {
            var greeting = dom.byId('greeting');
     //       greeting.innerHTML += ' from Dojo!';
        });
    </script>


<c:if test="${not empty properties}">
    <c:forEach items="${properties}" var="properties">
    	<c:if test="${properties.type eq 'TEXT'}">
       <input maxlength="${properties.length}" value = "${properties.value}"></input><br>
       </c:if>
       <c:if test="${properties.type eq 'DATE'}">
		<div>
				<label for="date1">Drop down Date box:</label>
		<input type="text" name="date1" id="date1" value="${properties.getValue()}"
		    data-dojo-type="dijit/form/DateTextBox"
		    required="true" />
		</div>
       </c:if>
       
        <c:if test="${properties.type eq 'COMBO'}">
		<div>
			<label for="fruit">Combo box:</label>
			<select data-dojo-type="dijit/form/ComboBox" id="fruit" name="fruit">
				<c:forEach items="${properties.options}" var="option">
			    <option>${option.value}</option>
			    </c:forEach>
			</select>
		</div>
       </c:if>
       
</c:forEach>
</c:if>
<h1 id="greeting">Hello</h1>
<button onclick="zmien2()">Zmień</button>

<input type="button" id="submit" value="Ajax Submit"/>

</body>
