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