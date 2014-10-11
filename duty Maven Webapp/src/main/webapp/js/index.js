function loadAction(){   
	alert("调用ajax成功");
	$.ajax({
        //url: "../police/savePolice.do",
        //url: "../police/getPolice.do",
        //url: "../police/selectPolice.do",
        url: "../police/selectPoliceList.do",
        //url: "../police/updatePolice.do",
		//url:"../police/savePolice.do",
        type: "POST",
        dataType: "json",
        async:false,  
        //contentType: "application/x-www-form-urlencoded; charset=utf-8", 
        success: function (req) {  
        	alert("success"); 
        },
        failer:function(a,b){
        	alert("2"+a);
        },
        error:function(a){
        	alert("3"+a);
        }
    });
} 