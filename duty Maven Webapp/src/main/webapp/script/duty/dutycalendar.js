var y;
var m;
var m_dutyCalendar_Org = {};
$(function() {
	$("#dutyDetailsForDaywindow").window("close");
	var args = getUrlArgs();
	m_dutyCalendar_Org.id = args["orgId"];
	m_dutyCalendar_Org.code = args["orgCode"];
	m_dutyCalendar_Org.path = args["orgPath"];
	m_dutyCalendar_Org.name = args["orgName"];
	var date = new Date();
	y = date.getFullYear();
	m = date.getMonth() + 1;
	$("#sp_years").text(y);
	$("#sp_month").text(m);
	changeDivHeight(); // 表格自动高度设置
	getDateData(y + "-" + m + "-" + 1);// 初始化默认月份数据
	$('#tgddutydetailsforday').treegrid({
		fitColumns : true,
		resizable : true,
		idField : 'xid',
		width : 600,
		height : '100%',
		treeField : 'displayName',
		showFooter : true,
		onClickRow:onClickData,
		columns : [ [ {
			title : 'xid',
			field : 'xid',
			width : 0,
			hidden : true
		}, {
			title : '名称',
			field : 'displayName',
			width : 230
		}, {
			title : '类型',
			field : 'itemInnerTypeName',
			align : 'center',
			width : 100
		}, {
			title : '时间区间',
			field : 'beginTime',
			align : 'center',
			width : 200,
			formatter : fmtShiftPeriod
		}, {
			title : '车辆',
			field : 'velicleCount',
			align : 'right',
			width : 50,
			formatter : fmtDigit
		}, {
			title : '警员',
			field : 'policeCount',
			align : 'right',
			width : 50,
			formatter : fmtDigit
		}, {
			title : '武器',
			field : 'weaponCount',
			align : 'right',
			width : 50,
			formatter : fmtDigit
		}, {
			title : '定位',
			field : 'gpsCount',
			align : 'right',
			width : 50,
			formatter : fmtDigit
		} ] ]
	});
});
// 设置日历窗体的高度
function changeDivHeight() {
	var bodyHeight = document.body.clientHeight;
	var tableContentHeight = bodyHeight - 110;
	var tdHeight = parseInt(tableContentHeight / 6) - 3;
	var dateBoxMainDateTDBoxWidht = parseInt($("#dateTable").width() * 0.14 * 0.98);

	// var trObj = $("table tbody tr");
	var tdObj = $("table tbody tr td");
	for ( var i = 0; i < tdObj.length; i++) {
		$(tdObj[i]).height(tdHeight);
		$(tdObj[i]).width(dateBoxMainDateTDBoxWidht);
	}

	var dateBoxMainDateTDBoxS = $(".dateBoxMainDateTDBox");
	// alert(dateBoxMainDateTDBoxS.length);
	for ( var i = 0; i < dateBoxMainDateTDBoxS.length; i++) {
		$(dateBoxMainDateTDBoxS[i]).width(dateBoxMainDateTDBoxWidht);
	}
}
// 点击日期上月下月事件
function getDateClick(action) {
	if (action == 'next') {
		m++;
		if (m > 12) {
			y++;
			m = 1;
		}

	} else {
		m--;
		if (m < 1) {
			m = 12;
			y--;
		}
	}
	var date = y + "-" + m + "-" + 1;
	$("#dateY").text(y);
	$("#dateM").text(m);

	getDateData(date);
}
// 根据日期，获取后台数据
function getDateData(date) {
	$.ajax({
		url : 'dutyCalendar/getCalender.do?orgId=' + m_dutyCalendar_Org.id
				+ '&date=' + date,
		type : "POST",
		dataType : "json",
		// async:false,
		success : function(req) {
			if (req) {
				setDateData(req);
			} else {
				alert("获取数据失败");
			}
		}
	});
}
// 初始化日期数据显示
function setDateData(result) {
	// 鏃ュ巻鏁版嵁缁勮鎴愭暟缁�
	var dateArray = new Array();
	// var json = eval("(" + result + ")");
	var json = result;
	for ( var i = 0; i < 6; i++) {// 鍒濆鍖�6琛�7鍒楃┖鏁版嵁浜岀淮鏁扮粍
		dateArray[i] = new Array();
		for ( var j = 0; j < 7; j++) {
			dateArray[i][j] = 0;
		}
	}
	var lineIndexOf = 0;// 鍒濆鍖栧綋鍓嶇粍瑁呰涓嬫爣
	for ( var i = 0; i < json.length; i++) {
		dateArray[lineIndexOf][parseInt(json[i]["week"])] = new Array();// 澹版槑lineIndexOf琛屼笅,week(浠ｈ〃鍛ㄥ嚑鐨勪笅鏍�)鍒椾笅鏄竴涓柊鏁扮粍
		var today = new Array();// 鍒涘缓涓�涓柊鏁扮粍锛屽苟瀹屾暣鏁版嵁閲嶇粍
		today['y'] = json[i]["y"];
		today['m'] = json[i]["m"];
		today['d'] = json[i]["d"];
		today['totalpolice'] = json[i]["totalpolice"];
		today['dutyList'] = json[i]["dutyList"];
		dateArray[lineIndexOf][parseInt(json[i]["week"])] = today;
		if (parseInt(json[i]["week"]) == 6) {// 鍒ゆ柇鏄惁璇ユ崲琛�,鑻ュ綋鍓嶄笅鏍囧埌鍒拌揪6鍗冲彲鎹㈣+1
			lineIndexOf++;

		}
	}
	creatHtml(dateArray);// 灏嗛噸缁勫悗鐨勬暟缁勪紶缁檋tml閲嶇粍鍔熻兘鍑芥暟锛屽苟鎻掑叆鍒癏TML涓�
	// p(dateArray);
}

function creatHtml(arr) {
	// 鏃ュ巻鏁版嵁HTML缁勮閮ㄥ垎
	var html = "";
	for ( var i = 0; i < 6; i++) {// 寰幆鏁扮粍锛岄噸缁刪tml
		var trHtml = "<tr >";
		for ( var j = 0; j < 7; j++) {

			var tdHtml = '';
			var isHaveData = arr[i][j]["d"] == null ? false : true;

			if (isHaveData == false) {

				tdHtml = '<td><div class="dateBoxMainDateTD"><div class="dateBoxMainDateTDLibOff"></div><div class="dateBoxMainDateTDBox"><ul><li>&nbsp;</li><li>&nbsp;</li><li>&nbsp;</li><li>&nbsp;</li></ul></div></div></td>';

				trHtml = trHtml + tdHtml;

				continue;
			}
			var d = "";
			if (arr[i][j]["d"] < 10) {
				d = "0" + arr[i][j]["d"];
			} else {
				d = arr[i][j]["d"];
			}
			tdHtml = '<td  onclick=getDateInfo("'
					+ y
					+ '-'
					+ m
					+ '-'
					+ d
					+ '")><div class="dateBoxMainDateTD"><div class="dateBoxMainDateTDLib">'
					+ arr[i][j]["d"]
					+ '</div><div class="dateBoxMainDateTDBox"><ul><li>警力：'
					+ arr[i][j]["totalpolice"] + '</li> '
					+ arr[i][j]["dutyList"] + '</ul></div></div></td>';

			trHtml = trHtml + tdHtml;
		}
		html = html + trHtml + "</tr>";
	}
	$("#dateBody").empty();// 娓呯┖html
	$("#dateBody").append(html);// 鎻掑叆html
	changeDivHeight();// 鎻掑叆html琛ㄦ牸楂樺害涓嶆槸鑷姩閫傚簲鐨勶紝璋冪敤楂樺害璋冩暣鍑芥暟锛岃嚜閫傚簲楂樺害
}
var dtime=null;
function onClickData(){
	parent.onClickData(dtime);
};
// 点击具体日期，加载详细信息对话框
function getDateInfo(date) {
	dtime = null;
	var dt = date.replace(/-/gm, '');
	dtime = dt;
	$.ajax({
				url : "duty/loadDutyByOrgIdAndYMD.do",
				type : "POST",
				dataType : "json",
				data : {
					'orgId' : m_dutyCalendar_Org.id,
					'ymd' : dt
				},
				async : false,
				success : function(req) {
					if (req.isSuccess) {// 成功填充数据
						if (req.obj) {
							var duty = req.obj;
							structureItemTree(duty.items);
							// m_duty = duty;
							$('#tgddutydetailsforday').treegrid('loadData',duty.items);
						} 
						$("#dutyDetailsForDaywindow").window("open");
					} else {
						alert("获取数据失败");
					}
				}
			});
}

/**
 * 汇总各级节点的数据
 * 
 * @param duty
 */
function structureItemTree(items) {
	$.each(items, function(i, val) {
		structureItem(val, null);
	});
}
function structureItem(item, parent) {

	item.getParent = function() {
		return parent;
	};
	/* 初始化数量等于0 */
	item.velicleCount = 0;
	item.policeCount = 0;
	item.weaponCount = 0;
	item.gpsCount = 0;

	if (item.xid == undefined || item.xid == null || item.xid == '') {
		if (item.itemId == null || item.itemId == 0 || item.itemId == '') {
			/* 班次，自定义的itemId等于null */
			item.xid = item.itemTypeId + "_" + item.id;
		} else {
			item.xid = item.itemTypeId + "_" + item.itemId;
		}
	}
	switch (item.itemTypeId) {
	case 1:
		item.velicleCount = 1;
		break;
	case 2:
		item.policeCount = 1;
		item.xid = 2 + "_" + item.itemId;
		break;
	case 3:
		item.weaponCount = 1;
		break;
	case 4:
		item.gpsCount = 1;
		break;
	}
	if (item.children != undefined && item.children != null
			&& item.children.length > 0) {
		$.each(item.children, function(i, val) {
			structureItem(val, item);/* 获取下级的汇总 */
			item.velicleCount += val.velicleCount;
			item.policeCount += val.policeCount;
			item.weaponCount += val.weaponCount;
			item.gpsCount += val.gpsCount;
		});
	}
}

function fmtDigit(value, row, index) {
	if (value == 0)
		return "";
	else
		return value;
}

function fmtShiftPeriod(value, row, index) {
	var result = "";
	if (row.beginTime != null && row.endTime) {
		var b = new Date(row.beginTime);
		var e = new Date(row.endTime);

		var b1 = new Date(b.getFullYear(), b.getMonth(), b.getDate());
		var e1 = new Date(e.getFullYear(), e.getMonth(), e.getDate());

		result = b.getHours() + ":" + b.getMinutes() + "至";

		var diff = b1.dateDiff("d", e1);

		switch (diff) {
		case 0:
			result += e.getHours() + ":" + e.getMinutes();
			break;
		case 1:
			result += "明日" + e.getHours() + ":" + e.getMinutes();
			break;
		default:
			result = "起止时间错误!";
		}

	}
	return result;
}