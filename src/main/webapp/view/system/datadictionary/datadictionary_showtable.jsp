<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib  uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>首页</title>


<%@ include file="/view/public/common.jspf" %>

</head>

<body>
<div style="padding:0px; margin:0px;">
 <ul class="breadcrumb" style="  margin:0px; " >
    	<li><a href="#">系统管理</a></li>
        <li>数据字典</li>
    </ul>
</div>
<div class="row alert alert-info"  style="margin:0px; padding:3px;" >
<form class="form-horizontal">
	<div class="col-sm-2">类型:</div>
    <div class="col-sm-3">
    	<input type="text"  class="form-control input-sm"/>
    </div>
    <a  class="btn btn-danger"  href="${pageContext.request.contextPath}/system/datadictionary/list.action"   >查询</a>
     <a href="${pageContext.request.contextPath }/downdown/down.action" class="btn btn-success">下载SQL语句</a>
     <a href="${pageContext.request.contextPath }/downdown/down1.action" class="btn btn-success">下载表结构关系图</a>
     <a href="${pageContext.request.contextPath }/downdown/down2.action" class="btn btn-success">下载PDM文件</a>
    
    </form>
</div>

<img src="/InstructionalManagement/resource/table/table.png" width="100%">


</body>
</html>
