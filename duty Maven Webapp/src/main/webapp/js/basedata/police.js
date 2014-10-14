$(function(){
	
	$('#my-search').click(function(){
		$('#my-search-box').toggle();	
	});	
	$('#my-check-all').click(function(){
		$(".my-check").each(function() {
			if($(this).attr('checked')=="checked"){ 
				$(this).attr('checked', false);
			}else{ 
				$(this).attr('checked',true);
			}
		});
	});
	$('#addnew').click(function(){
		$('#myModal').modal('show');	
	});
	$('#edit').click(function(){
		$('#myModal').modal('show');	
	});
	$('#delete').click(function(){
		$('#confirmModal').modal('show');	
	});
	$("table tr").click(function(){
		trclick(this);
	});
});


function savepoliceAction(){  
	var police = {};
	var id = $("#policeid").val();
	var name = $("#name").val();
	var policetypeid = $("#policetype").val();
	var idcardno = $("#idcardno").val();
	var code = $("#code").val();
	var code = $("#duty").val();
	var mobilephone = $("#mobilephone").val();
	var shortno = $("#shortno").val();
	var gpsno = $("#groupno").val();
	var personno = $("#personno").val();
	var dutytypeid = $("#dutytype").val();
	var gpsid = $("#gpsid").val();
	var gpsdes = $("#gpsdes").val();
	
	$.ajax({
        url: "../police/savePolice.do", 
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
	
	$('#myModal').modal('hide');	 
};

function confirmAction(){ 
	$('#confirmModal').modal('hide');	
};
function trclick(obj) {
	var trs = document.getElementById('tbpoliceList').getElementsByTagName('tr');
	for ( var o = 0; o < trs.length; o++) {
		if (trs[o] == obj) {
			trs[o].style.backgroundColor = '#DFEBF2';  
		} else {
			trs[o].style.backgroundColor = ''; 
			
		}
	}
};