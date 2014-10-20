

/*
 * 警员组
 * 
 */

$(function () {
	$('#dtGroup').datagrid({
		pagination: true,
        pageNumber: 1,
        pageSize: 20,
		columns: [[
	               { title: 'Id', field: 'Id', align: 'left', width: 10, hidden: true },
	               { title: '状态', field: 'IsCheck', align: 'left', width: 100 },
	               { title: '案件编号', field: 'Number', align: 'left', width: 100 },
	               { title: '单据编号', field: 'SerialNo', align: 'left', width: 100 },
	               { title: '记录日期', field: 'BillTime', align: 'center', width: 100 },
	               { title: '办案单位', field: 'TransferDeptName', align: 'left', width: 150 },
	               { title: '办案人', field: 'TransferName', align: 'left', width: 80 },
	               { title: '保管部门', field: 'ManageDeptName', align: 'left', width: 200 }
	        ]]
	});
	
});