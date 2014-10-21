

$(function () {
	$('#dtWeapon').datagrid({
		url:"",
		pagination: true,
		fitColumns:true,
        pageNumber: 1,
        pageSize: 10, 
        title:"s",
        singleSelect: true,
		columns: [[
	           	   { field: 'ck', checkbox: true },
	               { title: 'Id', field: 'Id', align: 'left', width: 10, hidden: true },
	               { title: '机构', field: 'orgName', align: 'left', width: 100 },
	               { title: '武器类型', field: 'Name', align: 'left', width: 100 },
	               { title: '武器编号', field: 'duty', align: 'left', width: 100 } 
	        ]]
	});
	$("#btnSearchWeapon").bind("click",function(){
		$('#my-search-box').toggle();	
	});
});

function btnAddWeapon(){ 
	$('#myModal').modal('show');	
};