/*
 * 
 * 通用
 * 
 * 
 * 
 */

function getUrlArgs() {
    var url = location.search; //获取url中"?"符后的字串 
    var theRequest = new Object();
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        strs = str.split("&");
        for (var i = 0; i < strs.length; i++) {
            theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);

        }
    }
    return theRequest;
}
/**
 * 将后台的org列表转换成tree格式
 * @param orgs
 */
function buildOrgTree(orgs){
	var ss = [];
	
	if(orgs == null || orgs.length==0){
		return ss;
	}
    var count = orgs.length;    
    /**
     * 如果已经排过序的话，第一个肯定在根节点上
     * 以这个parentid作为后续rog是否在根节点上的依据。
     */
    var rootParent=orgs[0].parentId;
    
    for (var i = 0; i < count; i++) {
    	var node = orgs[i];
    	node.text = node.shortName;
        node.children=[];
        
        for (var j = 0; j < count; j++) {
        	var tmp = orgs[j];
        	if (tmp.parentId == node.id){
        		node.children.push(tmp);
        	}
        }
        if(node.parentId==rootParent){
        	ss.push(node);
        }
    }
    
    return ss;
}

function getBaseData(urlStr, name, id) {
	$.ajax({
		url : urlStr,
		type : "POST",
		dataType : "json",
		async : false,
		success : function(req) {
			var html = "<option value=\"0\">请选择" + name + "</option>";
			if (req.length > 0) {
				for ( var i = 0; i < req.length; i++) {
					html += "<option value=\"" + req[i].id + "\">"
							+ req[i].name + "</option>";
				}
			}
			$("#" + id).append(html);
		}
	});
}

function getBaseDataCombobox(urlStr, id){
	$("#"+id).combobox({
        valueField: 'id',
        textField: 'name', 
        url:urlStr
    });
}
