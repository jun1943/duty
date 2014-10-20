<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
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
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>css/sys/base.css"></link>

<link href='<%=basePath%>asset/css/bootstrap.min.css' media='all'
	rel='stylesheet' type='text/css' />
<link href='<%=basePath%>asset/css/easyui/bootstrap/easyui.css'
	media='all' rel='stylesheet' type='text/css' />
<script src='<%=basePath%>asset/script/jquery-1.11.1.min.js'
	type='text/javascript'></script>
<script src='<%=basePath%>asset/script/bootstrap.min.js'
	type='text/javascript'></script>
<script src='<%=basePath%>asset/script/jquery.easyui.min.js'
	type='text/javascript'></script>
<script
	src='<%=basePath%>asset/script/jquery.loadmask/jquery.easyui.min.js'
	type='text/javascript'></script>

<script src='<%=basePath%>asset/script/json2.js' type='text/javascript'></script>

