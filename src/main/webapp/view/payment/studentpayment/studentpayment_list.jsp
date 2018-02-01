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
    	<li><a href="#">财务管理</a></li>
        <li>学员缴费</li>
    </ul>
</div>
<script type="text/javascript">
		function query(condition){
			$("#keyword").attr("name",condition.value);
		}
</script>

<div class="row alert alert-info"  style="margin:0px; padding:3px;" >
<form  action="${pageContext.request.contextPath}/payment/studentpayment/list.action" method="post" class="form-horizontal">
	<div class="col-sm-1" >条件:</div>
    <div class="col-sm-3">
    	<select class="form-control  input-sm" onchange="query(this)" name="condition">
        	<option value="">请选择</option>
        	<option value="paymentId">账单编号</option>
        	<option value="studentName">学生姓名</option>
        	<option value="staffName">经办人姓名</option>
            <option value="studentId">学生编号</option>
            <option value="staffId">经办人编号</option>
        </select>
    </div>
    <div class="col-sm-3">
    	<input type="text"  id="keyword"  class="form-control input-sm"/>
    </div>
    <input type="submit"   class="btn btn-danger"   value="查询"/>
 	<a href="${pageContext.request.contextPath }/payment/studentpayment/addload.action" paramId="paymentId" paramProperty="paymentId" > 
    <input type="button"   class="btn btn-success"   value="添加" />
    </a> 
  <a href="${pageContext.request.contextPath }/payment/studentpayment/excel.action" paramId="staffSalaryId" paramProperty="staffSalaryId" > 
    <input type="button"   class="btn btn-success"   value="导出" />
    </a> 
 </form>
</div>

<div align="center">
	<div class="alert alert-warning" style="margin: 0px; padding: 5px; width: 80%;display:${empty info?'none':'block'} ">
		<button type="button" class="close" data-dismiss="alert">
			<span aria-hidden="true">&times;</span>
			
		</button>
		<p align="center" style="color: red;">缴费信息-${info}</p>
	</div>	
</div>


<div class="row" style="padding:15px; padding-top:0px; " align="right">
	<table class="table  table-condensed table-striped">
    </table>
    <display:table class="table table-condensed table-striped" name="list" pagesize="10" requestURI="${pageContext.request.contextPath }/payment/studentpayment/list.action">
    	<display:column property="paymentId" title="账单编号"></display:column>
    	<display:column property="studentName" title="学生编号" ></display:column>
    	<display:column property="staffName" title="经办人" href="${pageContext.request.contextPath }/system/staffinfo/show.action" paramId="staffId" paramProperty="staffId"></display:column>
    	<display:column property="paymentSitutation" title="缴费情况"></display:column>
    	<display:column property="stateName" title="缴费方式"></display:column>
    	<display:column property="paymentTime" title="缴费时间" format="{0,date,yyyy年MM月dd日}"></display:column>
    	<display:column property="paymentDiscountAmount" title="优惠金额" ></display:column>
    	<display:column property="paymentShouldAmount" title="应缴金额"></display:column>
    	<display:column property="paymentRealAmount" title="实际交款"></display:column>
    	<display:column property="paymentDebtAmount" title="欠款"></display:column>
    	<display:column property="paymentRemark" title="备注"></display:column>
    	

    	<display:column href="${pageContext.request.contextPath }/payment/studentpayment/load.action" paramId="paymentId" paramProperty="paymentId" value="修改" title="修改"></display:column>
    	<display:column href="${pageContext.request.contextPath }/payment/studentpayment/delete.action" paramId="paymentId" paramProperty="paymentId" value="删除" title="删除"></display:column>
    	
    </display:table>
    
    
</div>

</body>
</html>
