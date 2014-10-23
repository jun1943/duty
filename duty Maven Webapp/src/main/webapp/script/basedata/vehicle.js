
$(function () {
	$('#dtVehicle').datagrid({
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
	               { title: '车辆类型', field: 'Name', align: 'left', width: 100 },
	               { title: '车牌号码', field: 'duty', align: 'left', width: 100 },
	               { title: '车辆用途', field: 'mobile', align: 'center', width: 100 },
	               { title: '车辆品牌', field: 'mobileshort', align: 'left', width: 150 },
	               { title: 'GPS设备ID', field: 'idcardno', align: 'left', width: 80 },
	               { title: 'GPS名称', field: 'code', align: 'left', width: 80 },
	               { title: '组呼号', field: 'gpsName', align: 'left', width: 200 }
	        ]]
	});
	$("#btnSearchVehicle").bind("click",function(){
		$('#my-search-box').toggle();	
	});
});

function btnAddVehicle(){ 
	$('#myModal').modal('show');	
};