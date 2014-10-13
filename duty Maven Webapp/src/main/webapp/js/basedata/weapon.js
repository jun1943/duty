$(function(){
	$('#my-search').click(function(){
		$('#my-search-box').toggle();	
	});	
	$('#my-check-all').click(function(){
		$(".my-check").each(function(){
			$(this).attr('checked','checked');
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
});

function saveweaponAction(){ 
	var typeid = $("#txttype").val(); 
	var number = $("#txtnumber").val();
	var orgid = $("#txtorg").val();
	alert(typeid+"-"+number+"-"+orgid);
	 $("#txtnumber").val(""); 
	$('#myModal').modal('hide');	
	var html = "<tr>"
				+"<td><input type=\"checkbox\" class=\"my-check\" value=\"7\"></td>"
				+"<td>64制自动步枪</td>"
				+"<td>510101198112130278</td>"
				+"<td>青羊区分局太升南路派出所</td>"
				+"</tr>";
	$("#tbweaponList").append(html);
};

function confirmAction(){
	$('#confirmModal').modal('hide');	
};