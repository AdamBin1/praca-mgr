<!DOCTYPE html>
<html >
<head>

<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
<meta http-equiv="Pragma" content="no-cache">
<script> 
	dojoConfig={async:true, parseOnLoad: true}
</script>
<link rel="stylesheet" href="//ajax.googleapis.com/ajax/libs/dojo/1.13.0/dijit/themes/tundra/tundra.css">
<script src="//ajax.googleapis.com/ajax/libs/dojo/1.13.0/dojo/dojo.js" type="text/javascript">
	
require(["dojo/topic", "dojo/dom", "dojo/on", "dojo/domReady!"],
function(topic, dom, on){

  var handle = topic.subscribe("some/topic", function(e){
    dom.byId("output").innerHTML = "I received: " + e.msg;
  });

  on(dom.byId("publish"), "click", function(){
    topic.publish("some/topic", { msg: "hello world" });
  });

});
	</script>
</head>
<body class="tundra">
    <button type="button" id="publish">Publish "some/topic"</button>
<div id="output">Nothing Yet...</div>

    <body class="tundra">
        <button id="btn"></button>
        <script>
 				require(["dijit/form/Button", "dojo/topic", "dojo/domReady!"], function(Button) {
                var button = new Button({
                    label: "Click Me!",
                    onClick: function(){ topic.publish("some/topic", { msg: "hello world" }); }
                }, "btn");
                button.startup();
            });
        </script>
    </body>
    
    
    
</body>
</html>