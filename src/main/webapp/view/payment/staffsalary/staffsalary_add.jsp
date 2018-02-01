<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <li>员工工资</li>
        <li>添加工资信息</li>
    </ul>
</div>



	<c:if test="${allErrors!=null }">
	<br/>
			 <c:forEach items="${allErrors}" var="error">
			 	${error.defaultMessage }<br/>
			 </c:forEach>
		</c:if>
		

<form action="${pageContext.request.contextPath}/payment/staffsalary/add.action" class="form-horizontal">
   	<div class="row">
    	<div class="col-sm-4 col-sm-offset-4">
        	<input  type="submit" class="btn btn-success" value="保存"/>
            <input  type="reset" class="btn  btn-danger" value="取消"/>
            <a href="${pageContext.request.contextPath }/payment/staffsalary/list.action" class="btn btn-success">返回</a>
        </div>
    </div>
    <h5 class="page-header alert-info" style="padding:10px; margin:0px; margin-bottom:5px;">工资信息</h5>
	<div class="row">
    	<div class="col-sm-5">
        	<div class="form-group">
            	<label class="col-sm-3 control-label">编号</label>
                <div class="col-sm-9">
<input type="text" name="staffSalaryId" value="${staffSalary.staffSalaryId }" readonly="readonly" class="form-control input-sm" placeholder="默认自动生成"/>
                
                </div>
            </div>       
        </div>
       <div class="col-sm-5">
				<label class="col-sm-3 control-label">领取人</label>
				<div class="form-group">
					<div class="col-sm-5">
						<select class="form-control input-sm" name="staffId">
							<option>请选择</option>
							<c:forEach items="${stafflist}" var="sta">
								<option value="${sta.staffId}" >${sta.staffName}</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>

    </div>
    <!-- 开始2 -->
	<div class="row">
    	<div class="col-sm-5">
				<label class="col-sm-3 control-label">财务员工</label>
				<div class="form-group">
					<div class="col-sm-5">
						<select class="form-control input-sm" name="staStaffId">
							<option>请选择</option>
							<c:forEach items="${stastafflist}" var="stasta">
								<option value="${stasta.staffId}"
								${stasta.staffId==staffSalary.staStaffId?'selected':''}
								>${stasta.staffName}</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>
        <div class="col-sm-5">
        	<div class="form-group">
            	<label class="col-sm-3 control-label">本月薪水</label>
                <div class="col-sm-5">
                	<input type="text" name="staffSalaryTotal"  value="${staffSalary.staffSalaryTotal }" class="form-control input-sm" placeholder="请输入本月薪水"/>
                </div>
            </div>     
        </div>
    </div>
    <!-- 结束2 -->
    <!-- 开始3 -->  
    <div class="row">     
        <div class="col-sm-5">
        	<div class="form-group">
            	<label class="col-sm-3 control-label">扣除</label>
                <div class="col-sm-5">
                	<input type="text" name="staffSalaryDeduct" value="${staffSalary.staffSalaryDeduct }" class="form-control input-sm" placeholder="请输入扣除薪水"/>
                </div>
            </div>     
        </div>       
        <div class="col-sm-5">
            
        	<div class="form-group">
            	<label class="col-sm-3 control-label">实际发放</label>
                <div class="col-sm-9">
                	<input type="text" name="staffSalaryReal"  value="${staffSalary.staffSalaryReal }"  class="form-control input-sm" placeholder="请输入实际发放薪水"/>
                </div>
            </div>
        </div>
    </div>
    <!-- 结束3 -->   
    

    <!-- 开始4 -->
	<div class="row">
    	<div class="col-sm-5">
        	<div class="form-group">
            	<label class="col-sm-3 control-label">是否发放</label>
                <div class="col-sm-9">
                <input type="text" name="staffSalaryIsused" value="${staffSalary.staffSalaryIsused }"   class="form-control input-sm" placeholder="请输入是否发放"/>
                </div>
            </div>
        </div>
        
        
        <div class="col-sm-5">

				<div class="form-group">
					<label class="col-sm-3 control-label">缴费日期</label>
					<div class="col-sm-5">
						<input type="text" name="staffSalaryTime"
							onclick="WdatePicker()" readonly="readonly"
							class="form-control input-sm" placeholder="请输入发放日期" />

					</div>
				</div>
				</div>
        
        
        
    </div>
    <!-- 结束4 -->     
 
     <h5 class="page-header alert-info" style="padding:10px; margin:0px; margin-bottom:5px;">备注</h5>
    	<div class="row">
    	<div class="col-sm-10">
        	<div class="form-group">
            	<label class="col-sm-3 control-label">备注</label>
                <div class="col-sm-9">
                	<textarea class="form-control" name="remark"   >${staffSalary.remark }</textarea>
                </div>
            </div>
        
        </div>

    </div>
    
 
   	<div class="row">
    	<div class="col-sm-3 col-sm-offset-4">
        	<input  type="submit" class="btn btn-success" value="保存"/>
            <input  type="reset" class="btn  btn-danger" value="取消"/>
        </div>
    </div>
</form>

</body>
</html>
