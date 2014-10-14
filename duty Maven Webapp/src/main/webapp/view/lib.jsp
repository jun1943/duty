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
    <link href='<%=basePath%>css/sys/customer.css' media='all' rel='stylesheet' type='text/css' />
    <link href='<%=basePath%>css/sys/Pager.css' media='all' rel='stylesheet' type='text/css' />
    <link href='<%=basePath%>css/sys/jquery-ui-1.10.0.custom.css' media='all' rel='stylesheet' type='text/css' />
    <link href='<%=basePath%>css/sys/jquery.ui.1.10.0.ie.css' media='all' rel='stylesheet' type='text/css' />
    
<!-- / jquery -->
<script src='<%=basePath%>js/sys/jquery-1.9.1.min.js' type='text/javascript'></script>
<script src='<%=basePath%>js/sys/bootstrap.min.js' type='text/javascript'></script>
<script src='<%=basePath%>js/sys/ie10-viewport-bug-workaround.js' type='text/javascript'></script>
<script src='<%=basePath%>js/sys/pages.js' type='text/javascript'></script>
<script src='<%=basePath%>js/sys/jquery.pager.js' type='text/javascript'></script>
<script src='<%=basePath%>js/sys/role.js' type='text/javascript'></script>
<script src='<%=basePath%>js/sys/jquery-ui.min.js' type='text/javascript'></script>


