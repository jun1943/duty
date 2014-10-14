/**角色管理**/
function edit() {
	var num = 0;
	$("#userList input[name='checkedIds']").each(function(i) {
		if ($(this).attr("checked")) {
			num++;
		}
	});
	if (num > 1) {
		alert("只能选择一项进行修改！");
		return;
	}else if(num == 0){
		alert("请选择一项进行修改！");
		return;
	} else {
		$("#userList input[name='checkedIds']").each(
				function(n) {
					if ($(this).attr("checked")) {
						var id = $(this).attr("id");
						window.location.href = basePath
								+ "system/role/initEdit.do?roleId=" + id;
					} 
					
				});
	}
}

function del() {
	var param = "";
	var num = 0;
	$("#userList input[name='checkedIds']").each(
			function() {
				if ($(this).attr("checked")) {
					num++;
				}
			});
	if(num == 0){
		alert("请至少选择一项删除！");
	}else if(num == 1){
		$("#userList input[name='checkedIds']").each(function(i) {
				if ($(this).attr("checked")) {
					if (window.confirm("是否确定要删除该记录？")) {
						param = $(this).attr("id");
					}
				}
			});
		window.location = basePath
		+ "system/role/delete.do?roleId="
		+ param;
	}else{
		if (window.confirm("是否确定要删除该记录？")) {
			$("#userList input[name='checkedIds']").each(function(i) {
				if ($(this).attr("checked")) {
					param += tp + $(this).attr("id")+",";
				}
			});
			param = param.substring(0,param.length-1);
			window.location = basePath
					+ "system/role/delete.do?roleId="
					+ param;
		}
	}
}

/**用户管理**/
function userEdit() {
	var row = "";
	var num=0;
	$("#dateGrid tr td input[name='checkedIds']:checked").each(function (i) {
		num++;
		row = $(this).attr("id");
	});
	if(num == 0){
		$.messager.alert('提示','请选择要编辑操作的数据行!','info');
		return;
	}else if(num == 1){
		if (row != ""){
			location.href = basePath+"system/user/user_edit.do?userId=" + row;
		}
	}else{
		alert("只能选一个");
	}
}

function role_author(){//用户角色授权
	var	row = "",
			goName = "",
			userName = "",
			num=0;
	$("#dateGrid tr td input[name='checkedIds']:checked").each(function (i) {
		num++;
		row = $(this).attr("id");
		goName = $(this).parent().next().text();
		userName = $(this).parent().next().next().text();
	});
	if(num == 0){
		$.messager.alert('提示','请选择要编辑操作的数据行!','info');
		return;
	}else if(num == 1){
			
			location.href = basePath+"system/user/init_role_author.do?userId=" + row +"&goName=" + goName +"&userName=" + userName;
	}else{
		alert("只能选一个");
	}
}

function delrow() {
	var	row = "",
			param = "",
			num=0;
	$("#dateGrid tr td input[name='checkedIds']:checked").each(function (i) {
		num++;
		row = $(this).attr("id");
	});
	if(num == 0){
		$.messager.alert('提示','请选择要编辑操作的数据行!','info');
		return;
	}else if(num == 1){
		
		location.href = basePath+"system/user/deleteUserByUserId.do?userId=" + row;
	}else{
		$("#dateGrid tr td input[name='checkedIds']").each(function(i) {
			if ($(this).attr("checked")) {
				param += $(this).attr("id")+",";
			}
		});
		param = param.substring(0,param.length-1);
		window.location = basePath
				+ "system/user/deleteUserByUserId.do?userId="
				+ param;
	}
}
function goFind() {
	var nam = "",pho = "";
	nam = $("#goName").val();
	pho = $("#phoneNumber").val();
	$.load("system/user/user_list.do",{"goName":nam,"phoneNumber":pho});
}
