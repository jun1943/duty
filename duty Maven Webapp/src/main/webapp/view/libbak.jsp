<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="tiyi" uri="com.tiyi.tiyiTagLib/tags"%>
<%@page import="com.tianyi.drs.duty.common.Constants"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
    <!--[if lt IE 9]>
    <script src='<%=basePath%>js/sys/html5shiv.js' type='text/javascript'></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/sys/base.css"></link>
    
    <link href='<%=basePath%>css/sys/bootstrap.css' media='all' rel='stylesheet' type='text/css' />
    <link href='<%=basePath%>css/sys/bootstrap-responsive.css' media='all' rel='stylesheet' type='text/css' />
    <link href='<%=basePath%>css/sys/bootstrap-dialog.min.css' media='all' rel='stylesheet' type='text/css' />
    <link href='<%=basePath%>css/sys/customer.css' media='all' rel='stylesheet' type='text/css' />
    <!-- / jquery ui -->
    <link href='<%=basePath%>css/sys/jquery-ui-1.10.0.custom.css' media='all' rel='stylesheet' type='text/css' />
    <link href='<%=basePath%>css/sys/jquery.ui.1.10.0.ie.css' media='all' rel='stylesheet' type='text/css' />
    <!-- / switch buttons -->
    <link href='<%=basePath%>css/sys/plugins/bootstrap_switch/bootstrap-switch.css' media='all' rel='stylesheet' type='text/css' />
    <!-- / xeditable -->
    <link href='<%=basePath%>css/sys/plugins/xeditable/bootstrap-editable.css' media='all' rel='stylesheet' type='text/css' />
    <link href='<%=basePath%>css/sys/plugins/common/bootstrap-wysihtml5.css' media='all' rel='stylesheet' type='text/css' />
    <!-- / wysihtml5 (wysywig) -->
    <link href='<%=basePath%>css/sys/plugins/common/bootstrap-wysihtml5.css' media='all' rel='stylesheet' type='text/css' />
    <!-- / jquery file upload -->
    <link href='<%=basePath%>css/sys/plugins/jquery_fileupload/jquery.fileupload-ui.css' media='all' rel='stylesheet' type='text/css' />
    <!-- / full calendar -->
    <link href='<%=basePath%>css/sys/plugins/fullcalendar/fullcalendar.css' media='all' rel='stylesheet' type='text/css' />
    <!-- / select2 -->
    <link href='<%=basePath%>css/sys/plugins/select2/select2.css' media='all' rel='stylesheet' type='text/css' />
    <!-- / mention -->
    <link href='<%=basePath%>css/sys/plugins/mention/mention.css' media='all' rel='stylesheet' type='text/css' />
    <!-- / tabdrop (responsive tabs) -->
    <link href='<%=basePath%>css/sys/plugins/tabdrop/tabdrop.css' media='all' rel='stylesheet' type='text/css' />
    <!-- / jgrowl notifications -->
    <link href='<%=basePath%>css/sys/plugins/jgrowl/jquery.jgrowl.min.css' media='all' rel='stylesheet' type='text/css' />
    <!-- / datatables -->
    <link href='<%=basePath%>css/sys/plugins/datatables/bootstrap-datatable.css' media='all' rel='stylesheet' type='text/css' />
    <!-- / dynatrees (file trees) -->
    <link href='<%=basePath%>css/sys/plugins/dynatree/ui.dynatree.css' media='all' rel='stylesheet' type='text/css' />
    <!-- / color picker -->
    <link href='<%=basePath%>css/sys/plugins/bootstrap_colorpicker/bootstrap-colorpicker.css' media='all' rel='stylesheet' type='text/css' />
    <!-- / datetime picker -->
    <link href='<%=basePath%>css/sys/plugins/bootstrap_datetimepicker/bootstrap-datetimepicker.min.css' media='all' rel='stylesheet' type='text/css' />
    <!-- / daterange picker) -->
    <link href='<%=basePath%>css/sys/plugins/bootstrap_daterangepicker/bootstrap-daterangepicker.css' media='all' rel='stylesheet' type='text/css' />
    <!-- / flags (country flags) -->
    <link href='<%=basePath%>css/sys/plugins/flags/flags.css' media='all' rel='stylesheet' type='text/css' />
    <!-- / slider nav (address book) -->
    <link href='<%=basePath%>css/sys/plugins/slider_nav/slidernav.css' media='all' rel='stylesheet' type='text/css' />
    <!-- / fuelux (wizard) -->
    <link href='<%=basePath%>css/sys/plugins/fuelux/wizard.css' media='all' rel='stylesheet' type='text/css' />
    <!-- / flatty theme -->
    <link href='<%=basePath%>css/sys/light-theme.css' id='color-settings-body-color' media='all' rel='stylesheet' type='text/css' />
    <!-- / demo -->
    <link href='<%=basePath%>css/sys/demo.css' media='all' rel='stylesheet' type='text/css' />
    
<!-- / jquery -->
<script src='<%=basePath%>js/sys/jquery-1.9.1.min.js' type='text/javascript'></script>
<!-- / jquery mobile events (for touch and slide) -->
<script src='<%=basePath%>js/sys/plugins/mobile_events/jquery.mobile-events.min.js' type='text/javascript'></script>
<!-- / jquery migrate (for compatibility with new jquery) -->
<script src='<%=basePath%>js/sys/jquery-migrate.min.js' type='text/javascript'></script>
<!-- / jquery ui -->
<script src='<%=basePath%>js/sys/jquery-ui.min.js' type='text/javascript'></script>
<!-- / bootstrap -->
<script src='<%=basePath%>js/sys/bootstrap.min.js' type='text/javascript'></script>
<script src='<%=basePath%>js/sys/plugins/flot/excanvas.js' type='text/javascript'></script>
<!-- / sparklines -->
<script src='<%=basePath%>js/sys/plugins/sparklines/jquery.sparkline.min.js' type='text/javascript'></script>
<!-- / flot charts -->
<script src='<%=basePath%>js/sys/plugins/flot/flot.min.js' type='text/javascript'></script>
<script src='<%=basePath%>js/sys/plugins/flot/flot.resize.js' type='text/javascript'></script>
<script src='<%=basePath%>js/sys/plugins/flot/flot.pie.js' type='text/javascript'></script>
<!-- / bootstrap switch -->
<script src='<%=basePath%>js/sys/plugins/bootstrap_switch/bootstrapSwitch.min.js' type='text/javascript'></script>
<!-- / fullcalendar -->
<script src='<%=basePath%>js/sys/plugins/fullcalendar/fullcalendar.min.js' type='text/javascript'></script>
<!--<script src='<%=basePath%>assets/javascripts/plugins/fullcalendar/lang/zh-cn.js' type='text/javascript'></script>
<script src='<%=basePath%>assets/javascripts/plugins/fullcalendar/lib/moment.min.js' type='text/javascript'></script>
-->
<!-- / datatables -->
<script src='<%=basePath%>js/sys/plugins/datatables/jquery.dataTables.min.js' type='text/javascript'></script>
<script src='<%=basePath%>js/sys/plugins/datatables/jquery.dataTables.columnFilter.js' type='text/javascript'></script>
<!-- / wysihtml5 -->
<script src='<%=basePath%>js/sys/plugins/common/wysihtml5.min.js' type='text/javascript'></script>
<script src='<%=basePath%>js/sys/plugins/common/bootstrap-wysihtml5.js' type='text/javascript'></script>
<!-- / select2 -->
<script src='<%=basePath%>js/sys/plugins/select2/select2.js' type='text/javascript'></script>
<!-- / color picker -->
<script src='<%=basePath%>js/sys/plugins/bootstrap_colorpicker/bootstrap-colorpicker.min.js' type='text/javascript'></script>
<!-- / mention -->
<script src='<%=basePath%>js/sys/plugins/mention/mention.min.js' type='text/javascript'></script>
<!-- / input mask -->
<script src='<%=basePath%>js/sys/plugins/input_mask/bootstrap-inputmask.min.js' type='text/javascript'></script>
<!-- / fileinput -->
<script src='<%=basePath%>js/sys/plugins/fileinput/bootstrap-fileinput.js' type='text/javascript'></script>
<!-- / modernizr -->
<script src='<%=basePath%>js/sys/plugins/modernizr/modernizr.min.js' type='text/javascript'></script>
<!-- / retina -->
<script src='<%=basePath%>js/sys/plugins/retina/retina.js' type='text/javascript'></script>
<!-- / fileupload -->
<script src='<%=basePath%>js/sys/plugins/fileupload/tmpl.min.js' type='text/javascript'></script>
<script src='<%=basePath%>js/sys/plugins/fileupload/load-image.min.js' type='text/javascript'></script>
<script src='<%=basePath%>js/sys/plugins/fileupload/canvas-to-blob.min.js' type='text/javascript'></script>
<script src='<%=basePath%>js/sys/plugins/fileupload/jquery.iframe-transport.min.js' type='text/javascript'></script>
<script src='<%=basePath%>js/sys/plugins/fileupload/jquery.fileupload.min.js' type='text/javascript'></script>
<script src='<%=basePath%>js/sys/plugins/fileupload/jquery.fileupload-fp.min.js' type='text/javascript'></script>
<script src='<%=basePath%>js/sys/plugins/fileupload/jquery.fileupload-ui.min.js' type='text/javascript'></script>
<script src='<%=basePath%>js/sys/plugins/fileupload/jquery.fileupload-init.js' type='text/javascript'></script>
<!-- / timeago -->
<script src='<%=basePath%>js/sys/plugins/timeago/jquery.timeago.js' type='text/javascript'></script>
<!-- / slimscroll -->
<script src='<%=basePath%>js/sys/plugins/slimscroll/jquery.slimscroll.min.js' type='text/javascript'></script>
<!-- / autosize (for textareas) -->
<script src='<%=basePath%>js/sys/plugins/autosize/jquery.autosize-min.js' type='text/javascript'></script>
<!-- / charCount -->
<script src='<%=basePath%>js/sys/plugins/charCount/charCount.js' type='text/javascript'></script>
<!-- / validate -->
<script src='<%=basePath%>js/sys/plugins/validate/jquery.validate.min.js' type='text/javascript'></script>
<script src='<%=basePath%>js/sys/plugins/validate/additional-methods.js' type='text/javascript'></script>
<!-- / naked password -->
<script src='<%=basePath%>js/sys/plugins/naked_password/nakead_password-0.2.4.min.js' type='text/javascript'></script>
<!-- / nestable -->
<script src='<%=basePath%>js/sys/plugins/nestable/jquery.nestable.js' type='text/javascript'></script>
<!-- / tabdrop -->
<script src='<%=basePath%>js/sys/plugins/tabdrop/bootstrap-tabdrop.js' type='text/javascript'></script>
<!-- / jgrowl -->
<script src='<%=basePath%>js/sys/plugins/jgrowl/jquery.jgrowl.min.js' type='text/javascript'></script>
<!-- / bootbox -->
<script src='<%=basePath%>js/sys/plugins/bootbox/bootbox.min.js' type='text/javascript'></script>
<!-- / inplace editing -->
<script src='<%=basePath%>js/sys/plugins/xeditable/bootstrap-editable.min.js' type='text/javascript'></script>
<script src='<%=basePath%>js/sys/plugins/xeditable/wysihtml5.js' type='text/javascript'></script>
<!-- / ckeditor -->
<script src='<%=basePath%>js/sys/plugins/ckeditor/ckeditor.js' type='text/javascript'></script>
<!-- / filetrees -->
<script src='<%=basePath%>js/sys/plugins/dynatree/jquery.dynatree.min.js' type='text/javascript'></script>
<!-- / datetime picker -->
<script src='<%=basePath%>js/sys/plugins/bootstrap_datetimepicker/bootstrap-datetimepicker.js' type='text/javascript'></script>
<!-- / daterange picker -->
<script src='<%=basePath%>js/sys/plugins/bootstrap_daterangepicker/moment.min.js' type='text/javascript'></script>
<script src='<%=basePath%>js/sys/plugins/bootstrap_daterangepicker/bootstrap-daterangepicker.js' type='text/javascript'></script>
<!-- / max length -->
<script src='<%=basePath%>js/sys/plugins/bootstrap_maxlength/bootstrap-maxlength.min.js' type='text/javascript'></script>
<!-- / dropdown hover -->
<script src='<%=basePath%>js/sys/plugins/bootstrap_hover_dropdown/twitter-bootstrap-hover-dropdown.min.js' type='text/javascript'></script>
<!-- / slider nav (address book) -->
<script src='<%=basePath%>js/sys/plugins/slider_nav/slidernav-min.js' type='text/javascript'></script>
<!-- / fuelux -->
<script src='<%=basePath%>js/sys/plugins/fuelux/wizard.js' type='text/javascript'></script>
<!-- / flatty theme -->
<script src='<%=basePath%>js/sys/nav.js' type='text/javascript'></script>
<script src='<%=basePath%>js/sys/tables.js' type='text/javascript'></script>
<script src='<%=basePath%>js/sys/theme.js' type='text/javascript'></script>
<!-- / demo -->
<script src='<%=basePath%>js/sys/demo/jquery.mockjax.js' type='text/javascript'></script>
<script src='<%=basePath%>js/sys/demo/inplace_editing.js' type='text/javascript'></script>
<script src='<%=basePath%>js/sys/demo/charts.js' type='text/javascript'></script>
<script src='<%=basePath%>js/sys/demo/demo.js' type='text/javascript'></script>

