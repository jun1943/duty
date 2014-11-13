var y;
var m;
var m_xid_max = 0; // duty的treegrid的id,必须确保
var m_ymd = null; /* 当前年月日 */
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
		idField : 'xid',
		// title:"报备明细",
		resizable : true,
		width : "99%",
		height : 390,
		treeField : 'displayName',
		showFooter : true,
		toolbar : '#tbdutydetailsforday',
		columns : [ [ {
			title : 'xid',
			field : 'xid',
			width : 100,
			hidden : true
		}, {
			title : '名称',
			field : 'displayName',
			width : 300
		}, {
			title : '类型',
			field : 'itemInnerTypeName',
			align : 'left',
			width : 100
		}, {
			title : '时间区间',
			field : 'beginTime',
			align : 'left',
			width : 100,
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
	var tdObj = $("#dateTable tbody tr td");
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
			tdHtml = '<td  onmouseover=mouseOverFunction("'
					+ y
					+ '-'
					+ m
					+ '-'
					+ d
					+ '") onmouseout=mouseOutFunction()  onclick=onClickData("'
					+ y
					+ '-'
					+ m
					+ '-'
					+ d
					+ '")><div class="dateBoxMainDateTD"><div class="dateBoxMainDateTDLib">'
					+ arr[i][j]["d"]
					+ '</div><div class="dateBoxMainDateTDBox"><ul><li> '
					+ arr[i][j]["totalpolice"] + '</li> </ul></div></div></td>';
			// + arr[i][j]["dutyList"] + '</ul></div></div></td>';

			trHtml = trHtml + tdHtml;
		}
		html = html + trHtml + "</tr>";
	}
	$("#dateBody").empty();// 娓呯┖html
	$("#dateBody").append(html);// 鎻掑叆html
	changeDivHeight();// 鎻掑叆html琛ㄦ牸楂樺害涓嶆槸鑷姩閫傚簲鐨勶紝璋冪敤楂樺害璋冩暣鍑芥暟锛岃嚜閫傚簲楂樺害
}
var dtime = null;
function onClickData(date) {
	dtime = null;
	var dt = date.replace(/-/gm, '');
	dtime = dt;
	parent.onClickData(dtime);
};
var timeouts;
var timer = 1500;
function mouseOverFunction(date) {
	dtime = null;
	var dt = date.replace(/-/gm, '');
	dtime = dt;
	timeouts = setTimeout('getDateInfo("' + dt + '")', timer);
	// timeouts=setTimeout(function(){
	// getDateInfo(date);
	// clearTimeout(timeouts);
	// },2*1000);
}

function mouseOutFunction() {
	window.clearTimeout(timeouts);
}
// 点击具体日期，加载详细信息对话框
function getDateInfo(date) {
	m_dutyCalendar_Org.date = date;
	m_ymd = YMD.createNew(date);
	$
			.ajax({
				url : "duty/loadDutyByOrgIdAndYMD.do",
				type : "POST",
				dataType : "json",
				data : {
					'orgId' : m_dutyCalendar_Org.id,
					'ymd' : date
				},
				async : false,
				success : function(req) {
					if (req.isSuccess) {// 成功填充数据
						if (req.obj) {
							var duty = req.obj;
							structureItemTree(duty.items);
							// m_duty = duty;
							$('#tgddutydetailsforday').treegrid('loadData',
									duty.items);
						} else {
							$('.datagrid-body').html("");
						}
						$("#dutyDetailsForDaywindow").window("open");
					} else {
						alert("获取报备数据详细信息失败");
					}
				}
			});
	window.clearInterval(timeouts);
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
	item.xid = genXId(item.itemTypeId);

	if (item.itemTypeId == 101) {
		initDate(item);
	}

	itemiconCls = createIconStyle(item, item.itemTypeId, item.iconUrl);

	switch (item.itemTypeId) {
	case 1:
		item.velicleCount = 1;
		break;
	case 2:
		item.policeCount = 1;
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

function genXId(itemTypeId, itemId) {
	m_xid_max++;
	return itemTypeId + "_AI_" + m_xid_max;
}
/**
 * 设置图标，如果没有图标就采用默认图标
 * 
 * @param row
 * @param itemTypeId
 * @param iconUrl
 */
function createIconStyle(row, itemTypeId, iconUrl) {
	if (row != null) {
		if (row.iconCls == undefined || row.iconCls == null) {
			if (row.iconUrl != null && row.iconUrl.length > 0) {
				var classId = "icon_" + itemTypeId + "_" + row.id;
				var classId2 = m_iconCls[classId];
				if (classId2 == undefined || classId2 == null) {
					var style = "." + classId + "{	background:url('" + iconUrl
							+ "');}";
					createStyle(style);
					m_iconCls[classId] = classId;
				}
				row.iconCls = classId;
			} else {/* 获取默认图标 */
				switch (row.itemTypeId) {
				case 1:
					row.iconCls = 'icon_default_vehicle';
					break;
				case 2:
					row.iconCls = 'icon_default_police';
					break;
				case 3:
					row.iconCls = 'icon_default_weapon';
					break;
				case 4:
					row.iconCls = 'icon_default_gps';
					break;
				case 100:
					row.iconCls = 'icon_default_dutytype';
					break;
				case 101:
					row.iconCls = 'icon_default_shift';
					break;
				case 999:
					row.iconCls = 'icon_default_usernode';
					break;
				}
			}
		}
	}
}
/**
 * 动态创建一个css样式
 * 
 * @param css
 */
function createStyle(css) {
	try { // IE下可行
		var style = document.createStyleSheet();
		style.cssText = css;
	} catch (e) { // Firefox,Opera,Safari,Chrome下可行
		var style = document.createElement("style");
		style.type = "text/css";
		style.textContent = css;
		document.getElementsByTagName("HEAD").item(0).appendChild(style);
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

	if (row.itemTypeId == 101 && row.beginTime2 != undefined
			&& row.beginTime2 != null) {
		result = row.beginTime2.getHours() + ":" + row.beginTime2.getMinutes()
				+ "至";
		var diff = row.beginTime2.dateDiffOfDay(row.endTime2);

		switch (diff) {
		case 0:
			result += row.endTime2.getHours() + ":" + row.endTime2.getMinutes();
			break;
		case 1:
			result += "明日" + row.endTime2.getHours() + ":"
					+ row.endTime2.getMinutes();
			break;
		default:
			result = "起止时间错误!";
		}

		return result;
	}
}

/**
 * 初始化日期
 * 
 * @param ymd
 * @param item
 */
function initDate(item) {

	if (item.beginTime2 == undefined || item.endTime2 == undefined) {
		var b = new Date(item.beginTime);
		var e = new Date(item.endTime);

		var diffDay = b.dateDiffOfDay(e);

		if (diffDay > 1) {
			alert('date diff day is error !');
		}

		b.setFullYear(m_ymd.getYear(), m_ymd.getMonth(), m_ymd.getDay());
		e.setFullYear(m_ymd.getYear(), m_ymd.getMonth(), m_ymd.getDay());
		e.add('d', diffDay);

		item.beginTime2 = b;
		item.endTime2 = e;
	}
}
var YMD = {
	createNew : function(ymd) {
		var _ymd = {};
		_ymd.ymd = ymd;

		var year = Number(ymd.substr(0, 4));
		var month = Number(ymd.substr(4, 2));
		var day = Number(ymd.substr(6, 2));

		_ymd.getYear = function() {
			return year;
		};
		_ymd.getMonth = function() {
			return month;
		};
		_ymd.getDay = function() {
			return day;
		};
		return _ymd;
	}
};

function btnExportToExcelAction() {
	var obj = $('#tgddutydetailsforday').treegrid("getData");
	createExcelApplication(obj);
};
function btnExportAction() { 
	$.ajax({
		url : "dutyCalendar/exportDataToExcle.do",
		type : "POST",
		dataType : "json",
		data : {
			'orgId' : m_dutyCalendar_Org.id,
			'orgName' : m_dutyCalendar_Org.id,
			'ymd' : m_dutyCalendar_Org.date
		}, 
		success : function(req) { 
			window.open(req.Data);
		},
		failer : function(a, b) {
			$.messager.alert("消息提示", a, "info");
		},
		error : function(a) { 
			$.messager.alert("消息提示", a, "error");
		}
	});
};
