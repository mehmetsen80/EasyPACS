$(document).ready(function(){	
	initTree();
	initFancyBox();
	initTab();	
	countImages();
	
});//ready

function initFancyBox()
{
	$('.fancybox-images')
	.attr('rel', 'image-gallery')
	.fancybox({
		prevEffect : 'elastic',
		nextEffect : 'elastic',
		closeEffect : 'elastic',
		closeClick : true,
		closeBtn  : true,
		arrows    : true,
		nextClick : false,			
		width: 520,
		height: 900,
		autoWidth: true,
		autoHeight: true,
		autoResize: true,
		autoCenter: true,
		scrolling: 'no',
		scrollOutside: false,
		
		iframe: {
			scrolling : 'auto',
			preload   : true
		},

		helpers : {
			thumbs : {
				width  : 70,
				height : 50
			}
		}
	});
}

function initTab(){
	$( "#tabs" ).tabs();
}

function initTree(){
	//$('#jstree').jstree();
	
	$('#jstree').jstree({
		  "core" : {
			  	'plugins': ["wholerow"],
    			'themes': {
    				'name': 'proton',
    				'responsive': true
    			}
		  }
		});
}

function buildPatient(patient){
	
	return  "<table class='table table-striped  table-condensed fixed-header'>" +
			"<caption>Patient</caption>" +
			"<tbody>" +
			"<tr><td>Patient Name</td><td>" + (patient.patientName==null?'':patient.patientName) + "</td></tr>" +
			"<tr><td>Patient ID</td><td>" + (patient.patientID==null?'':patient.patientID) + "</td></tr>" +
			"<tr><td>Patient Sex</td><td>" + (patient.patientSex==null?'':patient.patientSex) + "</td></tr>" +
			"<tr><td>Patient Age</td><td>" + (patient.patientAge==null?'':patient.patientAge) + "</td></tr>" +
			"</tbody>" +
			"</table>";
}

function buildStudy(study){
	
	return "<table class='table table-striped  table-condensed fixed-header'>" +
			"<caption>Study</caption>" +
			"<tbody>" +
			"<tr><td>Study Description</td><td>" + (study.studyDescription==null?'':study.studyDescription) + "</td></tr>" +	  						
			"<tr><td>Accession Number</td><td>" + (study.accessionNumber==null?'':study.accessionNumber) + "</td></tr>" +
			"<tr><td>Study Instance UID</td><td>" + (study.studyInstanceUID==null?'':study.studyInstanceUID) + "</td></tr>" +
			"<tr><td>Study ID</td><td>" + (study.studyID==null?'':study.studyID) + "</td></tr>" +
			"<tr><td>Study Date Time</td><td>" + (study.studyDateTime==null?'':new Date(study.studyDateTime * 1000)) + "</td></tr>" +
			"<tr><td>Study Priority ID</td><td>" + (study.studyPriorityID==null?'':study.studyPriorityID) + "</td></tr>" +
			"<tr><td>Study Status ID</td><td>" + (study.studyStatusID==null?'':study.studyStatusID) + "</td></tr>" +
			"<tr><td>Referring Physician Name</td><td>" + (study.referringPhysicianName==null?'':study.referringPhysicianName) + "</td></tr>" +
			"<tr><td>Additional Patient History</td><td>" + (study.additionalPatientHistory==null?'':study.additionalPatientHistory) + "</td></tr>" +
			"<tr><td>Admitting Diagnoses Description</td><td>" + (study.admittingDiagnosesDescription==null?'':study.admittingDiagnosesDescription) + "</td></tr>" +	  						
			"</tbody>" +
			"</table>";
}

function buildSeries(series){
	
	return "<table class='table table-striped  table-condensed fixed-header'>" +
			"<caption>Series</caption>" +
			"<tbody>" +
			"<tr><td>Series Description</td><td>" + (series.seriesDescription==null?'':series.seriesDescription) + "</td></tr>" +	  						
			"<tr><td>Body Part Examined</td><td>" + (series.bodyPartExamined==null?'':series.bodyPartExamined) + "</td></tr>" +
			"<tr><td>Series Number</td><td>" + (series.seriesNumber==null?'':series.seriesNumber) + "</td></tr>" +
			"<tr><td>Series Instance UID</td><td>" + (series.seriesInstanceUID==null?'':series.seriesInstanceUID) + "</td></tr>" +	  						
			"<tr><td>Series Date Time</td><td>" + (series.seriesDateTime==null?'':series.seriesDateTime) + "</td></tr>" +
			"<tr><td>Operators Name</td><td>" + (series.operatorsName==null?'':series.operatorsName) + "</td></tr>" +
			"<tr><td>Protocol Name</td><td>" + (series.protocolName==null?'':series.protocolName) + "</td></tr>" +
			"<tr><td>Laterality</td><td>" + (series.laterality==null?'':series.laterality) + "</td></tr>" +
			"<tr><td>Patient Position</td><td>" + (series.patientPosition==null?'':series.patientPosition) + "</td></tr>" +	  							  						
			"</tbody>" +
			"</table>";
}

function buildEquipment(series){
	
	return "<table class='table table-striped  table-condensed fixed-header'>" +
			"<caption>Equipment</caption>" +
			"<tbody>" +
			"<tr><td>Institution Name</td><td>" + (series.equipment.institutionName==null?'':series.equipment.institutionName) + "</td></tr>" +
			"<tr><td>Institutional Department Name</td>" + (series.equipment.institutionalDepartmentName==null?'':series.equipment.institutionalDepartmentName)  + "<td></td></tr>" +
			"<tr><td>Institution Address</td><td>" + (series.equipment.institutionalAddress==null?'':series.equipment.institutionalAddress) + "</td></tr>" +
			"<tr><td>Manufacturer</td><td>" + (series.equipment.manufacturer==null?'':series.equipment.manufacturer) + "</td></tr>" +
			"<tr><td>Manufacturer Model Name</td><td>" + (series.equipment.manufacturerModelName==null?'':series.equipment.manufacturerModelName) + "</td></tr>" +
			"<tr><td>Modality</td><td>" + (series.equipment.modality==null?'':series.equipment.modality) + "</td></tr>" +
			"<tr><td>Software Version</td><td>" + (series.equipment.softwareVersion==null?'':series.equipment.softwareVersion) + "</td></tr>" +
			"<tr><td>Station Name</td><td>" + (series.equipment.stationName==null?'':series.equipment.stationName) + "</td></tr>" +
			"<tr><td>Device Serial Number</td><td>" + (series.equipment.deviceSerialNumber==null?'':series.equipment.deviceSerialNumber) + "</td></tr>" +
			"<tr><td>Conversion Type</td><td>" + (series.equipment.conversionType==null?'':series.equipment.conversionType) + "</td></tr>" +
			"</tbody>" +
			"</table>";
}

function buildInstance(instance){
	
	return "<table class='table table-striped  table-condensed fixed-header'>" +
			"<caption>Instance</caption>" +
			"<tbody>" +
			"<tr><td>Instance Number</td><td>" + (instance.instanceNumber==null?'':instance.instanceNumber) + "</td></tr>" +	  						
			"<tr><td>SOP Instance UID</td><td>" + (instance.sopInstanceUID==null?'':instance.sopInstanceUID) + "</td></tr>" +
			"<tr><td>Media Storage SOP Instance UID</td><td>" + (instance.mediaStorageSopInstanceUID==null?'':instance.mediaStorageSopInstanceUID) + "</td></tr>" +
			"<tr><td>SOP Class UID</td><td>" + (instance.sopClassUID==null?'':instance.sopClassUID) + "</td></tr>" +	  						
			"<tr><td>KVP</td><td>" + (instance.kvp==null?'':instance.kvp) + "</td></tr>" +
			"<tr><td>Exposure Time</td><td>" + (instance.exposureTime==null?'':instance.exposureTime) + "</td></tr>" +
			"<tr><td>Image Orientation</td><td>" + (instance.imageOrientation==null?'':instance.imageOrientation) + "</td></tr>" +
			"<tr><td>Image Type</td><td>" + (instance.imageType==null?'':instance.imageType) + "</td></tr>" +
			"<tr><td>Image Position</td><td>" + (instance.imagePosition==null?'':instance.imagePosition) + "</td></tr>" +
			
			"<tr><td>Content Date Time</td><td>" + (instance.contentDateTime==null?'': new Date(instance.contentDateTime * 1000)) + "</td></tr>" +
			"<tr><td>Acquisition Date Time</td><td>" + (instance.acquisitionDateTime==null?'': new Date(instance.acquisitionDateTime * 1000)) + "</td></tr>" +
			"<tr><td>Patient Orientation</td><td>" + (instance.patientOrientation==null?'':instance.patientOrientation) + "</td></tr>" +
			"<tr><td>Pixel Spacing</td><td>" + (instance.pixelSpacing==null?'':instance.pixelSpacing) + "</td></tr>" +
			"<tr><td>Slice Location</td><td>" + (instance.sliceLocation==null?'':instance.sliceLocation) + "</td></tr>" +
			"<tr><td>Slice Thickness</td><td>" + (instance.sliceThickness==null?'':instance.sliceThickness) + "</td></tr>" +
			"<tr><td>Transfer Syntax UID</td><td>" + (instance.transferSyntaxUID==null?'':instance.transferSyntaxUID)+ "</td></tr>" +
			"<tr><td>Window Center</td><td>" + (instance.windowCenter==null?'':instance.windowCenter) + "</td></tr>" +
			"<tr><td>Window Width</td><td>" + (instance.windowWidth==null?'':instance.windowWidth)+ "</td></tr>" +
			"<tr><td>Xray Tube Current</td><td>" + (instance.xrayTubeCurrent==null?'':instance.xrayTubeCurrent) + "</td></tr>" +	  						
			"</tbody>" +
			"</table>";
}



function getStudy(pkTBLStudyID){	
		
	var info = encodeURI("pkTBLStudyID="+pkTBLStudyID);
	var uri = encodeURI(contextPath+"study");
	
	$("#divStudy").html("<img src='img/ajax-loader.gif' />");
	
	  $.ajax({
	  		type: "GET",
	  		url: uri,
	  		data: info,  		
	  		success: function(data){
				
	  			if(data.success){	  				
	  				$("#divStudy").html(buildStudy(data.study));//fill study
	  				getPatient(data.study.patient.pkTBLPatientID);//get the parent patient
	  			}
	  		}
		});  
}

function getSeries(pkTBLSeriesID){	
		
	var info = encodeURI("pkTBLSeriesID="+pkTBLSeriesID);
	var uri = encodeURI(contextPath+"series");
	
	$("#divSeries").html("<img src='img/ajax-loader.gif' />");
	
	  $.ajax({
	  		type: "GET",
	  		url: uri,
	  		data: info,  		
	  		success: function(data){
				
	  			if(data.success){	  				
	  				$("#divSeries").html(buildSeries(data.series));//fill Series	  				
	  				$("#divEquipment").html(buildEquipment(data.series));//fill the equipment	  				
	  				getStudy(data.series.study.pkTBLStudyID);//get the parent study
	  				getPatient(data.series.study.patient.pkTBLPatientID);//get the parent of parent patient
	  			}
	  		}
		});  
}

function getInstance(pkTBLInstanceID){
		
	var info = encodeURI("pkTBLInstanceID="+pkTBLInstanceID);
	var uri = encodeURI(contextPath+"instance");
	
	$("#divInstance").html("<img src='img/ajax-loader.gif' />");
	
	  $.ajax({
	  		type: "GET",
	  		url: uri,
	  		data: info,  		
	  		success: function(data){
				
	  			if(data.success){	  				
	  				$("#divInstance").html(buildInstance(data.instance));//fill instance	  				
	  				getSeries(data.instance.series.pkTBLSeriesID);//get parent series
	  				getStudy(data.instance.series.study.pkTBLStudyID);//get the parent of parent study
	  			}
	  		}
	  });
}

function getPatient(pkTBLPatientID){
	
	var info = encodeURI("pkTBLPatientID="+pkTBLPatientID);
	var uri = encodeURI(contextPath+"patient");
	
	$("#divPatient").html("<img src='img/ajax-loader.gif' />");
	
	$.ajax({
	  		type: "GET",
	  		url: uri,
	  		data: info,  		
	  		success: function(data){
				
	  			if(data.success){	  				
	  				$("#divPatient").html(buildPatient(data.patient));//fill patient
	  			}
	  		}
	  });	
}

//get total num of images for this patient
function countImages(){
	
	var pkTBLPatientID = purl().param('pkTBLPatientID');
	var info = encodeURI("pkTBLPatientID="+pkTBLPatientID);
	var uri = encodeURI(contextPath+"patient");
		
	$.ajax({
	  		type: "GET",
	  		url: uri,
	  		data: info,  		
	  		success: function(data){
				
	  			if(data.success){	  					  				
	  				var numOfImages = 0;	  				
	  				$.each( data.patient.study, function(indexOfStudy, study){//iterate each study	
	  					$.each(study.series, function(indexOfSeries, series){//iterate each series				
	  						$.each(series.instance, function(indexOfInstance, instance){//iterate each instance
	  							numOfImages++;
	  						});
	  					});
	  				});
	  				
	  				$("#divImageCount").html("<h3 class='panel-title'>Entities (" + numOfImages + " images)</h3>" );
	  			}
	  		}
	  });
	
}