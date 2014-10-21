
$(function () {
	$("#orgtree").tree({
		 //url: '/TreeData/GetFunTree' 
	});
	
	$('#dtPolice').datagrid({
		url:"/duty/police/getPoliceList.do",
		pagination: true,
		fitColumns:true,
        pageNumber: 1,
        pageSize: 10, 
        title:"s",
        singleSelect: true,
		columns: [[
	           	   { field: 'ck', checkbox: true },
	               { title: 'Id', field: 'id', align: 'left', width: 10, hidden: true },
	               { title: '机构', field: 'orgName', align: 'left', width: 100 },
	               { title: '姓名', field: 'name', align: 'left', width: 100 },
	               { title: '职务', field: 'title', align: 'left', width: 100 },
	               { title: '手机号', field: 'mobile', align: 'center', width: 100 },
	               { title: '公安短号', field: 'mobileShort', align: 'left', width: 150 },
	               { title: '身份证号码', field: 'idcardno', align: 'left', width: 80 },
	               { title: '警号', field: 'number', align: 'left', width: 80 },
	               { title: 'GPS名称', field: 'gpsName', align: 'left', width: 200 }
	        ]]
	});
	$("#btnSearchPolice").bind("click",function(){
		$('#my-search-box').toggle();	
	});
});

function btnAddPolice(){ 
	$('#myModal').modal('show');	
};