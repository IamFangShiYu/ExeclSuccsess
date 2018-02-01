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
        <li>教师管理</li>
    </ul>
</div>
<script type="text/javascript">
		function query(condition){
			$("#keyword").attr("name",condition.value);
		}
</script>

<div class="row alert alert-info"  style="margin:0px; padding:3px;" >
<form  action="${pageContext.request.contextPath}/system/staffteacher/teacherlist.action" method="post" class="form-horizontal">
	<div class="col-sm-1" >条件:</div>
    <div class="col-sm-3">
    	<select class="form-control  input-sm" onchange="query(this)" name="condition">
        	<option value="">请选择</option>
        	<option value="staffId">编号</option>
            <option value="staffName">姓名</option>
            <option value="staffMobilePhone">移动电话</option>
        </select>
    </div>
    <div class="col-sm-3">
    	<input type="text"  id="keyword"  class="form-control input-sm"/>
    </div>
    <input type="submit"   class="btn btn-danger"   value="查询"/>
    <input type="button"   class="btn btn-success"   value="添加" onClick="javascript:window.location='${pageContext.request.contextPath}/view/classinfo/teacher/teacher_add.jsp'"/>
 </form>
</div>

<div align="center">
	<div class="alert alert-warning" style="margin: 0px; padding: 5px; width: 80%;display:${empty info?'none':'block'} ">
		<button type="button" class="close" data-dismiss="alert">
			<span aria-hidden="true">&times;</span>
			
		</button>
		<p align="center" style="color: red;">员工信息-${info}</p>
	</div>	
</div>


<div class="row" style="padding:15px; padding-top:0px; " align="right">
	<table class="table  table-condensed table-striped">
    </table>
    <display:table class="table table-condensed table-striped" name="list" pagesize="10" requestURI="${pageContext.request.contextPath }/system/staffinfo/teacherlist.action">
    	<display:column property="staffId" title="编号"></display:column>
    	<display:column property="staffName" title="姓名" href="${pageContext.request.contextPath }/system/staffteacher/show.action" paramId="staffId" paramProperty="staffId"></display:column>
    	<display:column property="staffAge" title="年龄"></display:column>
    	
    	<display:column property="staffMobilePhone" title="移动电话"></display:column>
    	<display:column property="staffEmail" title="邮箱"></display:column>
    	<display:column property="staffEntryTime" title="入职时间" format="{0,date,yyyy年MM月dd日}"></display:column>
    	<display:column property="staffEducationLevel" title="教育水平"></display:column>
    	<display:column href="${pageContext.request.contextPath }/system/staffteacher/teacherload.action" paramId="staffId" paramProperty="staffId" value="修改" title="修改"></display:column>
    	<display:column href="${pageContext.request.contextPath }/system/staffteacher/delete.action" paramId="staffId" paramProperty="staffId" value="删除" title="删除"></display:column>
    	
    </display:table>
    
    
</div>

</body>
</html>
