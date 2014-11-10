function InitUploadFun(){
	var button = $('#btnfindIcon'), interval;
    var fileType = "all", fileNum = "one";

    new AjaxUpload(button, {
        action: 'icons/uploadIcon.do',
        name: 'file',
        onSubmit: function (file, ext) {
        	if (!(ext && /^(png)$/.test(ext))) {
                alert('图片格式不正确,请选择 png 格式的文件!', '系统提示');
                return false;
            }
            var text = "文件上传中";
            var data= getData();
            if(!data||data==null){
            	return false;
            }
           
            this.setData(data);
//            if (fileNum == 'one')
//                this.disable();
            interval = window.setInterval(function () { 
                if (text.length < 20) {
                    text += ".";
                } else {
                    text = "文件上传中";
                }
            }, 200);


        },
        onComplete: function (file, response) {//上传成功的函数；response代表服务器返回的数据  
        	button.text('上传图片(只允许上传JPG格式的图片,大小不得大于150K)');
            //清楚按钮的状态  
            //button.text('文件上传');
            window.clearInterval(interval);
            this.enable();
            
            $.messager.alert('提示', response);

        }
    });
};
function getData(){
 
	var id=$("#iconsId").val();
	//icons.name = $("#txtname").val();
	if ($("#txttype").combobox("getValue") == 0
			|| $("#txttype").combobox("getValue") == "") { 
		$.messager.alert("错误提示", "请选择图标类别", "error"); 
		return null;
	}
	var typeId = $("#txttype").combobox("getValue");
	if($("#txtname").val()==""){ 
		$.messager.alert("错误提示", "请选择图标名称", "error");
		return null;
	}
	 var name = $("#txtname").val();
	return {"id":id,"typeId":typeId,"name":name};
};