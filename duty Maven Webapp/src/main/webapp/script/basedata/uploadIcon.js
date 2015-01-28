/*
 * 上传插件应用；
 */

function InitUploadFun() {
	var button = $('#btnfindIcon'), interval;
	var fileType = "all", fileNum = "one";

	new AjaxUpload(button, {
		action : 'icons/uploadIcon.do',
		name : 'file',
		onSubmit : function(file, ext) {
			if (!(ext && /^(png)$/.test(ext))) {
				$.messager.alert("操作提示", "图片格式不正确,请选择 png 格式的文件!", 'error');
				return false;
			}
			var text = "文件上传中";
			var data = getData();
			if (!data || data == null) {
				return false;
			}

			this.setData(data);
			// if (fileNum == 'one')
			// this.disable();
			interval = window.setInterval(function() {
				if (text.length < 20) {
					text += ".";
				} else {
					text = "文件上传中";
				}
			}, 200);

		},
		onComplete : function(file, response) {// 上传成功的函数；response代表服务器返回的数据
			// button.text('上传图片(只允许上传JPG格式的图片,大小不得大于150K)');
			// 清楚按钮的状态
			button.text('文件上传');
			window.clearInterval(interval);
			this.enable();

			// $.messager.alert('提示', response.split(";")[0]);
			if (/msie/.test(navigator.userAgent.toLowerCase())) {
				response = response.replace("<PRE>","");
				response = response.replace("</PRE>","");
			}else{
				response = response.replace("<pre style=\"word-wrap: break-word; white-space: pre-wrap;\">","");
				response = response.replace("</pre>","");
				response = response.replace("<pre>","");
			}
			 
			
			$("#iconsId").val(response.split(";")[1]);
			var urlStr = response.split(";")[2];
			var srcUrl = "";
			if (urlStr.length > 0) {
				srcUrl = urlStr.substring(1, urlStr.length);
			}
			$("#sltImage").attr("src", srcUrl);
			if (response.split(";")[0] == ""
					|| response.split(";")[0] == undefined) {
				$("#txtfilename").val(response.split(";")[2]);
				$("#txtname").val(response.split(";")[2]);
			} else {
				$("#txtfilename").val(response.split(";")[2]);
				$("#txtname").val(response.split(";")[0]);
			}
			button.text('选择图片');
			btnSearchAction();
		}
	});
};
function getData() {

	var id = $("#iconsId").val();
	// icons.name = $("#txtname").val();
	if ($("#txttype").combobox("getValue") == 0
			|| $("#txttype").combobox("getValue") == "") {
		$.messager.alert("错误提示", "请选择图标类别", "error");
		return null;
	}
	var typeId = $("#txttype").combobox("getValue");
	var name = $.trim($("#txtname").val());
	if (name == "" || name == undefined) {
		$.messager.alert("错误提示", "请输入图片标题", "error");
		return null;
	}
	if ($.trim($("#txtname").val()).length > 50) {
		$.messager.alert("错误提示", "图片标题长度过长，限制长度为0--50！", "error");
		return null;
	}
	return {
		"id" : id,
		"typeId" : typeId,
		"name" : name
	};
};