var interval = null;

$(document).ready(function(){		
	
	interval = setInterval(updateDiv,1000);
	//getSeries();
	
});//ready

function updateDiv(){
  
    var uri = encodeURI(contextPath+"live");
    $.ajax({
    		type: "GET",
    		url: uri,
    		data: null,  		
    		success: function(data){  			
    			$('#divLive').html(data.message);
    		},
            error: function(){
                clearInterval(interval); // stop the interval               
                $('#divLive').html('<span style="color:red">Connection problems</span>');
            }
  	});  
}


function getSeries(){
	
	var uri = encodeURI(contextPath+"entities");
    $.ajax({
    		type: "GET",
    		url: uri,
    		data: null,
    		dataType: 'json',
    		cache: false,
    		success: function(data,status){  			
    			    			
    			$.each(data.patients, function( index, value ) {
    				console.log( index + ": " + value );
    			});
    			
    		}
  	});
}
