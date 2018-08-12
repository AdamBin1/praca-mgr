<!DOCTYPE html>
<html >
<head>

	<link rel="stylesheet" href="../../_static/js/dojo/../dijit/themes/claro/claro.css">
	
	<script>dojoConfig = {parseOnLoad: true}</script>
	<script src='../../_static/js/dojo/dojo.js'></script>
	
	<script>
require(["dojo/parser", "dijit/form/DateTextBox"]);
	</script>
</head>
<body class="claro">
    <label for="date1">Drop down Date box:</label>
<input type="text" name="date1" id="date1" value="2005-12-30"
    data-dojo-type="dijit/form/DateTextBox"
    required="true" />
</body>
</html>