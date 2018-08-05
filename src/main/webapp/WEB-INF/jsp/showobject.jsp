<!DOCTYPE html>
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Reprezentacja obiektu</title>
</head>

<body>
    <script src="//ajax.googleapis.com/ajax/libs/dojo/1.13.0/dojo/dojo.js"
    data-dojo-config="async: true"></script>
    
        <script>
        require([
            'dojo/dom',
            'dojo/domReady!'
        ], function (dom) {
            var greeting = dom.byId('greeting');
            greeting.innerHTML += ' from Dojo!';
        });
    </script>


<c:if test="${not empty properties}">
    <c:forEach items="${properties}" var="properties">
       ${properties.stage}
       ${properties.sequence}
       ${properties.length}
       ${properties.value}
       
       <input maxlength="${properties.length}">${properties.value}</input>
       <script>
		require([
		    "dojo/parser",
		    "dojo/date",
		    "dijit/Calendar"
		]);
		</script>
</c:forEach>
</c:if>
<h1 id="greeting">Hello</h1>

<div id="dijitcontainer">
    <div data-dojo-type="dijit/Calendar">

    </div>
    <p id="formatted"></p>
</div>



</body>
