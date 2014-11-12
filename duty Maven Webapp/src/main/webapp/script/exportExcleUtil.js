function createExcelApplication(obj) {
	if (obj.length == 0) {
		$.messager.alert("提示消息", "无报备数据，无效的操作", "info");
		return;
	}
	var xls;
	try {
		xls = new ActiveXObject("Excel.Application");
	} catch (ex) {
		$.messager
				.alert(
						"消息提示",
						"请确认:\n1.安装Microsoft Excel.\n2.设置浏览器安全级别：Internet 选项=>安全=>自定义级别 \n \"将ActiveX控件启用\"",
						"info");
		return;
	}

	var xlBook = xls.Workbooks.Add;

	var xlsheet = xlBook.Worksheets(1);
	xlsheet.Rows(1).RowHeight = 25;
	xlsheet.Rows(1).Font.Size = 14;
	xlsheet.Rows(1).Font.Name = "黑体";
	xlsheet.Cells(1, 1).Value = "名称";

	xlsheet.Cells(1, 2).Value = "类型";

	xlsheet.Cells(1, 3).Value = "时间区间";

	xlsheet.Cells(1, 4).Value = "车辆";

	xlsheet.Cells(1, 5).Value = "警员";

	xlsheet.Cells(1, 6).Value = "武器";

	xlsheet.Cells(1, 7).Value = "定位";


	var objlist = [];
	setCellList(obj, objlist);
	for(var i = 0; i<objlist.length;i++){
		xlsheet.Cells(2+i, 1).Value = objlist[i].displayName;

		xlsheet.Cells(2+i, 2).Value = objlist[i].itemInnerTypeName;

		xlsheet.Cells(2+i, 3).Value = objlist[i].beginTime == null ? ""
				: objlist[i].beginTime + "至" + objlist[i].endTime == null ? ""
						: objlist[i].endTime;

		xlsheet.Cells(2+i, 4).Value = objlist[i].velicleCount > 0 ? objlist[i].velicleCount
				: "";

		xlsheet.Cells(2+i, 5).Value = objlist[i].policeCount > 0 ? objlist[i].policeCount
				: "";

		xlsheet.Cells(2+i, 6).Value = objlist[i].weaponCount > 0 ? objlist[i].weaponCount
				: "";

		xlsheet.Cells(2+i, 7).Value = objlist[i].gpsCount > 0 ? objlist[i].gpsCount : "";
	}
	 
	xls.visible = true; // 设置excel为可见
	xls.UserControl = true; // 很重要,不能省略,不然会出问题 意思是excel交由用户控制
	xls = null;

	xlBook = null;

	xlsheet = null;
}
function setCellList(obj, objlist) {
	for ( var i = 0; i < obj.length; i++) {
		var entity = {};
		entity.name = obj[i].displayName;
		entity.typeName = obj[i].itemInnerTypeName;
		var date = "";
		if (obj[i].beginTime != null) {
			date = obj[i].beginTime;
		}
		if (obj[i].endTime != null) {
			date += "至" + obj[i].endTime;
		}
		entity.date = date;
		entity.velicleCount = obj[i].velicleCount > 0 ? obj[i].velicleCount
				: "";
		entity.policeCount = obj[i].policeCount > 0 ? obj[i].policeCount : "";
		entity.weaponCount = obj[i].weaponCount > 0 ? obj[i].weaponCount : "";
		entity.gpsCount = obj[i].gpsCount > 0 ? obj[i].gpsCount : "";
		objlist.push(entity);
		if (obj[i].children.length > 0) {
			setCellList(obj[i].children, objlist);
		} 
	}
}