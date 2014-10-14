$(document).ready(function () {
    $("#pager").pager({
        pagenumber: $("#pageNumber").val(),
        /* 表示初始页数 */
        pagecount: $("#pageCount").val(),
        /* 表示总页数 */
        totalCount: $("#totalCount").val(),
        /* 记录总数		*/
        buttonClickCallback: PageClick /* 表示点击分页数按钮调用的方法 */

    });
});


/*
		PageClick = function(pageclickednumber) {}部分
		PageClick，表示自定义点击分页数时的function方法，如：function(pageclickednumber){}
		jQuery插件JQuery Pager分页器只需要起始页数pagenumber，最大页数pagecount，
		点击页数时的调用buttonClickCallback的 function方法就可实现javascript分页功能，
		实际应用中只需对PageClick方法进行简单修改就可使用，如将pagenumber和 pagecount设为变量，
		可通过GET的方法进行页数值传递，JQuery Pager就可实现javascript分页功能
		*/
PageClick = function (pageclickednumber) {
    $("#pager").pager({
        pagenumber: pageclickednumber,
        /* 表示启示页 */
        pagecount: $("#pageCount").val(),
        /* 表示最大页数pagecount */
        totalCount: $("#totalCount").val(),
        /* 记录总数		*/
        buttonClickCallback: PageClick /* 表示点击页数时的调用的方法就可实现javascript分页功能 */
    });


    $("#pageNumber").val(pageclickednumber); /* 给pageNumber从新赋值 */

    /* 执行Action */
    pagesearch();
}


//执行查询条件
function search(form) {
    $("#pageNumber").val("1");
    form.submit();
}
//删除查询条件
function deleteSearch(url) { //清除查询条件
    window.location.href = url;
}

//执行分页查询
function pagesearch() {
    var pageNumber = $("#pageNumber").val();
    $("#searchForm").submit();
}