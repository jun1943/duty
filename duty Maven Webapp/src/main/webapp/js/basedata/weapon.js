$(function() {

	$('#my-search').click(function() {
		$('#my-search-box').toggle();
	});
	$('#my-check-all').click(function() {
		$(".my-check").each(function() {
			if($(this).attr('checked')=="checked"){ 
				$(this).attr('checked', false);
			}else{ 
				$(this).attr('checked',true);
			}
		});
	});
	$('#addnew').click(function() {
		$('#myModal').modal('show');
	});
	$('#edit').click(function() {
		$('#myModal').modal('show');
	});
	$('#delete').click(function() {
		$('#confirmModal').modal('show');
	});
	$("table tr").click(function(){
		trclick(this);
	});
});

function saveweaponAction() {
	var typeid = $("#txttype").val();
	var number = $("#txtnumber").val();
	var orgid = $("#txtorg").val();
	alert(typeid + "-" + number + "-" + orgid);
	$("#txtnumber").val("");
	$('#myModal').modal('hide');
	var html = "<tr>"
			+ "<td><input type=\"checkbox\" class=\"my-check\" value=\"7\"></td>"
			+ "<td>64制自动步枪</td>" + "<td>510101198112130278</td>"
			+ "<td>青羊区分局太升南路派出所</td>" + "</tr>";
	$("#tbweaponList").append(html);
};

function confirmAction() {
	$('#confirmModal').modal('hide');
};

function trclick(obj) {
	var trs = document.getElementById('tbweaponList').getElementsByTagName('tr');
	for ( var o = 0; o < trs.length; o++) {
		if (trs[o] == obj) {
			trs[o].style.backgroundColor = '#DFEBF2';  
		} else {
			trs[o].style.backgroundColor = ''; 
			
		}
	}
};