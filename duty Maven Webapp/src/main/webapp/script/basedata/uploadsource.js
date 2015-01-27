function InitEntityUploadFun(){
	var button = $('#btnfindfile'), interval;
	var fileType = "all", fileNum = "one";

	new AjaxUpload(button, {
		action : 'excelUpload/excelUploadAction.do',
		name : 'file',
		onSubmit : function(file, ext) {
			if (!(ext && /^(xls)$/.test(ext))) {
				$.messager.alert("操作提示", "文件格式不正确,请选择xls 格式的文件!", 'error');
				return false;
			}
			var text = "文件上传中";
//			var data = getData();
//			if (!data || data == null) {
//				return false;
//			}
//
//			this.setData(data);
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
			 
			window.clearInterval(interval);
			this.enable();
			if (/msie/.test(navigator.userAgent.toLowerCase())) {
				response = response.replace("<PRE>","");
				response = response.replace("</PRE>","");
			}else{
				response = response.replace("<pre style=\"word-wrap: break-word; white-space: pre-wrap;\">","");
				response = response.replace("</pre>","");
				response = response.replace("<pre>","");
			}
			var states = response.split(";")[0];
			if(states == 0||states =="0"){
				$.messager.alert("错误提示","文件上传到服务器出错","error");
			}else
			{ 
				$("#txtentityfilename").val(response.split(";")[1]); 
			}
			 
		}
	});
}

function getData() {

	var orgid = m_Police_OrgId;   
	return {
		"orgid" : orgid
	};
	
};
function InitVehicleUploadFun(){
	
}
function InitGpsUploadFun(){
	
}
function InitWeaponUploadFun(){
	
}