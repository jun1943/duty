/*
 * 报备首页，日历页面业务逻辑操作
 * 
 * 包括：日历的展示；
 * 报备情况统计
 * 报备明细；
 * 
 */

var y;
var m_date = null;
var m;
var m_xid_max = 0; // duty的treegrid的id,必须确保
var m_ymd = null; /* 当前年月日 */
var m_dutyCalendar_Org = {};
var m_duty = {};
$(function() {
	$("#dutyDetailsForDaywindow").window("close");

	// 获取地址栏参数，获取组织结构信息；
	var args = getUrlArgs();
	m_dutyCalendar_Org.id = args["orgId"];
	m_dutyCalendar_Org.code = args["orgCode"];
	m_dutyCalendar_Org.path = args["orgPath"];
	m_dutyCalendar_Org.name = args["orgName"];
	m_dutyCalendar_Org.userId = args["userId"];

	m_year = args["year"];
	m_month = args["month"];

	// var date = new Date();
	// y = date.getFullYear();
	// m = date.getMonth() + 1;

	// 初始化日历，若没有传入日期，则去当前系统时间；

	y = m_year;
	m = m_month;
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
		} ] ],
		rowStyler : function(row, index) {
			if (row._parentId == "undefined" || row._parentId == undefined) {
				return "background-color:#A2C4EA;color:black;font-weight:bold";
			}
		}
	});
});
// 设置日历窗体的高度
function changeDivHeight() {

	var bodyHeight = document.body.clientHeight;
	var tableContentHeight = bodyHeight - 60;
	var tdHeight = parseInt(tableContentHeight / 6) - 3;
	var dateBoxMainDateTDBoxWidht = parseInt($("#dateTable").width() * 0.14 * 0.98);
	var tdContentHeight = tdHeight - 43;
	var trObj = $("#dateTable tbody tr");
	var tdObj = $("#dateTable tbody tr td");
	for ( var i = 0; i < tdObj.length; i++) {
		$(tdObj[i]).height(tdHeight);
		$(tdObj[i]).width(dateBoxMainDateTDBoxWidht);
		$(tdObj[i]).find(".dateBoxMainDateTDBox").height(tdContentHeight);
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
	$("#sp_years").text(y);
	$("#sp_month").text(m);
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
				changeDivHeight();
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
	PlanArray = dateArray;
	creatHtml(dateArray);// 灏嗛噸缁勫悗鐨勬暟缁勪紶缁檋tml閲嶇粍鍔熻兘鍑芥暟锛屽苟鎻掑叆鍒癏TML涓�
	// p(dateArray);
}

function creatHtml(arr) {
	// 鏃ュ巻鏁版嵁HTML缁勮閮ㄥ垎
	var html = "";
	for ( var i = 0; i < 6; i++) {// 寰幆鏁扮粍锛岄噸缁刪tml
		var trHtml = "<tr  style='vertical-align:top'>";
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
			tdHtml = '<td doc="td_'
					+ i
					+ '_'
					+ j
					+ '" id="date_'
					+ y
					+ '-'
					+ m
					+ '-'
					+ d
					+ '"><div class="DateBoxbg" id="modeldiv_'
					+ i
					+ '_'
					+ j
					+ '"></div>'
					+ '<div class="pasteBtnBox" id="pasteBtn_'
					+ i
					+ "_"
					+ j
					+ '"  onclick=selectPasteBox("'
					+ y
					+ '-'
					+ m
					+ '-'
					+ d
					+ '",'
					+ i
					+ ','
					+ j
					+ ') style="display: none;"><a href="javascript:void(0);">粘贴</a></div><div ';
			if (arr[i][j]["totalpolice"] != "<li class='nobaobei' style='display: list-item;'>无报备</li>") {
				tdHtml += ' onmouseover=mouseOverFunction("' + y + '-' + m
						+ '-' + d + '") onmouseout=mouseOutFunction() ;';
			}
			tdHtml += ' onclick=onClickData("'
					+ y
					+ '-'
					+ m
					+ '-'
					+ d
					+ '") class="dateBoxMainDateTD"><div class="dateBoxMainDateTDLib">'
					+ arr[i][j]["d"]
					+ '</div><div class="dateBoxMainDateTDBox"><ul id="ulcontent_'
					+ i + '_' + j + '">' + arr[i][j]["totalpolice"]
					+ '<li class="baoBeiBtn">' + '</li>' + '</div>'
					+ ' </ul></div></div>';
			if (arr[i][j]["totalpolice"] != "<li class='nobaobei' style='display: list-item;'>无报备</li>") {
				tdHtml += '<div id="calendarOpratdiv_'
						+ y
						+ '_'
						+ m
						+ '_'
						+ d
						+ '" onmouseover=mouseOverOpratdiv("'
						+ y
						+ '_'
						+ m
						+ '_'
						+ d
						+ '") onmouseout=mouseOutOpratdiv("'
						+ y
						+ '_'
						+ m
						+ '_'
						+ d
						+ '") style="width:100% ;margin-bottom:3px; color:#0000ff; font-size:12px;cursor:pointer;">'
						+ '<a id="copylink_' + y + '_' + m + '_' + d
						+ '"  onclick=copyDutyByDays("' + y + '-' + m + '-' + d
						+ '",' + i + ',' + j
						+ ')  style="float:right;margin-right:8px;">　　</a>'
						+ '<a id="dellink_' + y + '_' + m + '_' + d
						+ '" onclick=deleteDutyConfirm("' + y + '-' + m + '-'
						+ d + '",' + i + ',' + j
						+ ') style="float:right;">　　</a>' + '</div>';
			}
			tdHtml += '</td>';

			trHtml = trHtml + tdHtml;
		}
		html = html + trHtml + "</tr>";
	}
	$("#dateBody").empty();// 娓呯┖html
	$("#dateBody").append(html);// 鎻掑叆html
	changeDivHeight();// 鎻掑叆html琛ㄦ牸楂樺害涓嶆槸鑷姩閫傚簲鐨勶紝璋冪敤楂樺害璋冩暣鍑芥暟锛岃嚜閫傚簲楂樺害
}

var dtime = null;
// 点击日历号数，进入详细报备页面
function onClickData(date) {
	dtime = null;
	var dt = date.replace(/-/gm, '');
	if (dt.length == 7) {
		dt = dt.substr(0, 4) + "0" + dt.substr(4, 7);
	}
	dtime = dt;
	parent.onClickData(dtime);
};
var timeouts;
var timer = 1500;

// 鼠标在进入有效日历号数表格内，且停留时间超过1.5S时，弹出报备明细；
function mouseOverFunction(date) {
	dtime = null;
	var dt = date.replace(/-/gm, '');
	if (dt.length == 7) {
		dt = dt.substr(0, 4) + "0" + dt.substr(4, 7);
	}
	dtime = dt;
	timeouts = setTimeout('getDateInfo("' + dt + '")', timer);
	// timeouts=setTimeout(function(){
	// getDateInfo(date);
	// clearTimeout(timeouts);
	// },2*1000);
}
// 鼠标移开事件，清楚定时器；
function mouseOutFunction() {
	// $("#dutyDetailsForDaywindow").window("close");
	window.clearTimeout(timeouts);
}
// 点击具体日期，加载详细信息对话框
function getDateInfo(date) {
	$("#txttargetName").val("");
	m_dutyCalendar_Org.date = date;
	m_ymd = YMD.createNew(date);
	$
			.ajax({
				url : "duty/loadDutyByOrgIdAndYMD.do",
				type : "POST",
				dataType : "json",
				async : false,
				data : {
					'orgId' : m_dutyCalendar_Org.id,
					'ymd' : date
				},
				// async : false,
				success : function(req) {
					if (req.isSuccess) {// 成功填充数据
						if (req.obj) {
							var duty = req.obj;
							structureItemTree(duty.items);
							m_duty = duty;
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
// 鼠标移动到日历底部，显示删除、复制按钮
function mouseOverOpratdiv(tags) {
	$("#calendarOpratdiv_" + tags + " a[id='dellink_" + tags + "']").html("删除");
	$("#calendarOpratdiv_" + tags + " a[id='copylink_" + tags + "']").html(
			"　复制");
}
// 鼠标移开日历底部，隐藏删除、复制按钮
function mouseOutOpratdiv(tags) {
	$("#calendarOpratdiv_" + tags + " a[id='dellink_" + tags + "']")
			.html("　　　");
	$("#calendarOpratdiv_" + tags + " a[id='copylink_" + tags + "']").html(
			"　　　");
}
// 删除报备
function deleteDutyConfirm(date, i, j) {
	$.messager.confirm("系统提示", "确认删除    " + date + " 的报备数据吗？", function(r) {
		if (r) {
			dtime = null;
			var dt = date.replace(/-/gm, '');
			if (dt.length == 7) {
				dt = dt.substr(0, 4) + "0" + dt.substr(4, 7);
			}
			dtime = dt;
			deleteDutyAction(dtime, i, j);
		}
	});
}
function deleteDutyAction(dt, i, j) {
	$
			.ajax({
				url : "dutyCalendar/deleteDutyByYMD.do",
				type : "POST",
				dataType : "json",
				data : {
					"ymd" : dt,
					"orgId" : m_dutyCalendar_Org.id
				},
				async : false,
				success : function(req) {
					if (req.isSuccess) {// 成功填充数据
						var y = dt.substring(0, 4);
						var m = dt.substring(4, 6);
						var d = dt.substring(6, 9);
						var html = '<li class="nobaobei" style="display: list-item;">无报备</li>';
						// html += '<li class="baoBeiBtn">'
						// + '<div class="pasteBtnBox" id="pasteBtn_'
						// + i
						// + "_"
						// + j
						// + '" onclick=selectPasteBox("'
						// + y
						// + '-'
						// + m
						// + '-'
						// + d
						// + '",'
						// + i
						// + ','
						// + j
						// + ') style="display: none;"><a
						// href="javascript:void(0);">粘贴</a></div>'
						// + '</div></li>';
						$("#ulcontent_" + i + "_" + j).html(html);

					} else {
						alert("报备信息删除失败");
					}
				}
			});
}
var pasteDate = "";
var copyX = 0;// 要复制数组的X下标
var copyY = 0;// 要复制数组的Y下标
var PlanArray = new Array();// 报备情况数组，记录每天的报备情况
function copyDutyByDays(date, i, j) {
	pasteDate = "";
	copyX = i;
	copyY = j;
	var dt = date.replace(/-/gm, '');
	pasteDate = dt;

	for ( var i = 0; i < PlanArray.length; i++) {
		for ( var j = 0; j < PlanArray[i].length; j++) {

			var obj = $("td[doc='td_" + i + "_" + j + "']");
			var LT = getPasteBtnBoxWidthHeight();
			if (i == copyX) {// 同一行，之判断列
				if (j > copyY) {
					$(obj).find('div[class=DateBoxbg]').each(function() { // 遮罩
						$(this).css('display', 'block');
					});
					$(obj).find('div[class=pasteBtnBox]').each(function() { // 遮罩
						$(this).css('left', LT[0]);
						$(this).css('top', LT[1]);
						$(this).show();
					});
					$("#pasteBtn_" + i + "_" + j).show();

				}
			} else if (i > copyX) {// 下一行，直接追加div
				// var obj=$("#pasteBtn_" + i+"_"+j).parent().parent();
				// $(obj).find('li[class=nobaobei]').hide();
				$(obj).find('div[class=DateBoxbg]').each(function() { // 遮罩
					$(this).css('display', 'block');
				});
				$(obj).find('div[class=pasteBtnBox]').each(function() { // 遮罩
					$(this).css('left', LT[0]);
					$(this).css('top', LT[1]);
					$(this).show();
				});
				$("#pasteBtn_" + i + "_" + j).show();

			}

		}

	}

	// $('div[class=DateBoxbg]').each(function(){ //遮罩
	// $(this).css('display','block');
	// });
	// var LT=getPasteBtnBoxWidthHeight();
	// $('div[class=pasteBtnBox]').each(function(){
	// $(this).css('left',LT[0]);
	// $(this).css('top',LT[1]);
	// $(this).show();
	// });

}
// 计算浏览器高度，计算行高
function getPasteBtnBoxWidthHeight() {

	var arr = new Array();
	$("#dateBody TD").each(function() {
		var h = parseInt($(this).height());
		var w = parseInt($(this).width());
		var top = (h - 25) / 2;
		var left = (w - 69) / 2;
		arr[0] = left;
		arr[1] = top;
		return arr;
		return false;
	});
	return arr;
}
// 粘贴按钮事件
function selectPasteBox(date, i, j) {
	var dt = date.replace(/-/gm, '');

	if (dt.length == 7) {
		dt = dt.substr(0, 4) + "0" + dt.substr(4, 7);
	}
	if (pasteDate.length == 7) {
		pasteDate = pasteDate.substr(0, 4) + "0" + pasteDate.substr(4, 7);
	}
	var pars = {
		orgId : m_dutyCalendar_Org.id,
		ymd : pasteDate,
		targetYmd : dt
	};
	$.ajax({
		url : "dutyCalendar/copyDutyByOrgIdAndYMD.do",
		type : "POST",
		dataType : "json",
		data : pars,
		// async : false,
		success : function(req) {
			if (req.isSuccess) {// 成功填充数据
				var html = $("#ulcontent_" + copyX + "_" + copyY).html();
				$("#ulcontent_" + i + "_" + j).html(html);
				$("#modeldiv_" + i + "_" + j).css('display', 'none');
				var obj = $("#ulcontent_" + i + "_" + j).find(
						"div[class='pasteBtnBox']");
				obj.attr("id", "pasteBtn_" + i + "_" + j);
				obj.attr("onclick", "selectPasteBox('" + date + "'," + i + ","
						+ j + ")");
				$("#pasteBtn_" + i + "_" + j).hide();
			} else {
				alert("报备信息复制失败");
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
var m_iconCls;
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
		var b = gCreateDate(item.beginTime);
		var e = gCreateDate(item.endTime);

		var diffDay = b.dateDiffOfDay(e);

		if (diffDay > 1) {
			alert('date diff day is error !');
		}

		b.setFullYear(m_ymd.getYear(), m_ymd.getMonth() - 1, m_ymd.getDay());
		e.setFullYear(m_ymd.getYear(), m_ymd.getMonth() - 1, m_ymd.getDay());
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

// function btnExportToExcelAction() {
// var obj = $('#tgddutydetailsforday').treegrid("getData");
// createExcelApplication(obj);
// };

// 导出具体日期的报备明细
function btnExportAction() {
	$.ajax({
		url : "dutyCalendar/exportDataToExcle.do",
		type : "POST",
		dataType : "json",
		async : false,
		timeout : 60000,
		data : {
			orgId : m_dutyCalendar_Org.id,
			ymd : m_dutyCalendar_Org.date
		},
		success : function(req) {
			if (req.isSuccess) {
				var urlStr = req.Data.substring(1, req.Data.length);
				if (/msie/.test(navigator.userAgent.toLowerCase())) {
					if (b_version.indexOf("MSIE 8.0", 0) > -1
							|| b_version.indexOf("MSIE 9.0", 0) > -1) {
						urlStr = "../../" + urlStr;
					}
				}
				// var urlStr = req.Data.substring(1, req.Data.length);
				window.location.href = urlStr;
			}
		},
		failer : function(a, b) {
			$.messager.alert("消息提示", a, "info");
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$.messager.alert("消息提示", errorThrown, "error");
		}
	});
};
// 清除当月所有报备数据
function clearAlldutyData() {
	$.messager.confirm("系统提示", "确认删除    " + y + "年" + m + "月" + " 的所有报备数据吗？",
			function(r) {
				if (r) {
					deleteAllDutyDataAction(y, m);
				}
			});
};
function deleteAllDutyDataAction(year, month) {
	$.ajax({
		url : "dutyCalendar/deleteAllDutyData.do",
		type : "POST",
		dataType : "json",
		data : {
			orgId : m_dutyCalendar_Org.id,
			year : year,
			month : month
		},
		success : function(req) {
			var date = year + "-" + month + "-" + 1;
			getDateData(date);
		},
		failer : function(a, b) {
			$.messager.alert("消息提示", a, "info");
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$.messager.alert("消息提示", errorThrown, "error");
		}
	});
};

// 清除粘贴模板，清空剪切板
function clearClipbord() {
	$('div[class=pasteBtnBox]').each(function() { // 开始遍历
		$(this).hide();
	});
	$('div[class=DateBoxbg]').each(function() { // 遮罩
		$(this).css('display', 'none');
	});
};
function btnSearchAction() {
	var name = $('#txttargetName').val();
	if (name != "") {
		getDateInfo(m_dutyCalendar_Org.date);
		var a = findDutyPoint(name);
		$('#tgddutydetailsforday').treegrid("loadData", a);
	} else {
		getDateInfo(m_dutyCalendar_Org.date);
	}
};

function findDutyPoint(name) {
	var a = [];
	if (m_duty.items != null) {
		$.each(m_duty.items, function(index, value) {
			var o = findDutyTreeGrid(value, name);
			if (o != null) {
				a.push(o);
			}
		});
	}
	return a;
}

function findDutyTreeGrid(item, xname) {
	if (xname == "" || item.displayName.indexOf(xname) >= 0) {
		return item;
	} else {
		var ls = [];
		if (item.children != null && item.children.length > 0) {
			$.each(item.children, function(index, value) {
				var o = findDutyTreeGrid(value, xname);
				if (o != null) {
					ls.push(o);
				}
			});
			item.children = ls;
			if (ls.length > 0)
				return item;
			else
				return null;
		} else {
			return null;
		}
	}
}

function mouseOut() {
	$("#dutyDetailsForDaywindow").window("close");
}
function mouseOver() {
	$("#dutyDetailsForDaywindow").window("open");
}