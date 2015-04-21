$(document).ready(function(){
	
	
});//ready


function loginUser()
{
  $("#btnLogin").attr("disabled", "true");
  var username = $("#txtUsername").val();
  var password = $("#txtPassword").val(); 
  
  var info = encodeURI("username="+username+"&password="+password);
  var uri = encodeURI(contextPath+"login");
  
  $.ajax({
  		type: "GET",
  		url: uri,
  		data: info,  		
  		success: function(data){
			
  			if(data.success)
  				window.location = contextPath+"admin/";
  			else
  				alert(data.message);	    	
		
		    $("#btnLogin").removeAttr('disabled');

  		}
	});  
}


function fireLoginUser(e)
{
	var key = e.which||e.keyCode;	
	
	if(key == 13){
		$("#btnLogin").click();
	}

}

function logoutUser()
{
	  
	  var uri = encodeURI(contextPath+"logout");
	  
	  $("#btnLogout").attr("disabled", "true");
	  
	  $.ajax({
	  		type: "GET",
	  		url: uri,
	  		data: "",  		
	  		success: function(data){
	  			
	  			 $("#btnLogout").removeAttr('disabled');
	  			 
	  			if(data.success)
	  				window.location = contextPath;
	  			else
	  				alert(data.message);
	  		}
		});  
}