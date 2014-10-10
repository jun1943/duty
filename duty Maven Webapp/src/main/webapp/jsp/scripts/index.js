function loadAction(){   
	alert("调用ajax成功");
	$.ajax({
        //url: "../Index/savePolice.do",
        //url: "../Index/getPolice.do",
        //url: "../Index/selectPolice.do",
        //url: "../Index/updatePolice.do",
		url:"policeman/savePolice.do",
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