$(function(){
	
	$('#my-search').click(function() {
		$('#my-search-box').toggle();
	});
	$('#my-check-all').click(function() {
		$(".my-check").each(function() {
			if ($(this).attr('checked') == "checked") {
				$(this).attr('checked', false);
			} else {
				$(this).attr('checked', true);
			}
		});
	});
	$('#addnew').click(function() {
		$('#myModal').modal('show');
	});
	$('#edit').click(function(){
		var obj = {};
		if($(":checkbox:checked").length==0)
			{
			alert("请选择要编辑的数据！");
			return;
			}else if($(":checkbox:checked").length>1){ 
				alert("只能对单行数据进行编辑！");
				return;
			}else{
				$(":checkbox:checked").each(function(){
					  var tablerow = $(this).parent("tr");
					  obj.id=tablerow.find("[name='p_code']").val();
					  var code = tablerow.find("[name='p_code']").val();
					  var name= tablerow.find("[name='p_name']").val();
					  var price= tablerow.find("[name='p_price']").val();
					  obj.push({Code:code,Name:name,Price:price});
					});
			}
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
var rowNo;
function clickList(id){
	rowNo=null;
	alert(id);
	if($("#ck_"+id).attr("checked")=="checked"){ 
		$("#ck_"+id).attr("checked",false);
		//$("#tr_"+id).style.backgroundColor = ''; 
	}else{
		$("#ck_"+id).attr("checked",true);
		//$("#tr_"+id).style.backgroundColor = '#DFEBF2'; 
	}
	rowNo= id;
}
function dbclickList(obj){
	rowNo=null;
	alert(obj.id);
	if($("#ck_"+obj.id).attr("checked")=="checked"){ 
		$("#ck_"+obj.id).attr("checked",false);  
	}else{
		$("#ck_"+obj.id).attr("checked",true);  
	}
	rowNo= obj.id;
}