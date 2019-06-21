<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>Excel</title>
</head>
<body>
<div style="margin-top: 25px">
	<form id="form_table" class="form-horizontal" action="doImport.do" enctype="multipart/form-data" method="post">
        Excel Name : 
		<input type="text" id="excelName" name="excelName" placeholder="请输入Excel名称"/>
		<!-- <input id="search" style="margin-left: 10px" type="button" value="搜索"><br/> -->
		<button onclick="doSearch()" type="button">搜索</button>
		<button onclick="doDelete()" type="button">删除</button>
        &nbsp;&nbsp;&nbsp;
        <button onclick="checkout()" type="submit" class="btn btn-primary">导入</button>
        <input id="file_excel" class="form-input" type="file" name="filename" accept=".xls,.xlsx,.csv"></input>
    </form>
	<input type="checkbox" name="isSearchMax" value="1">Occupancy Rate (max)
</div>
<div class="table-responsive" style="margin-top: 50px">
    <table class="table" border="1">
        <thead class="Table cell">
            <th><input type="checkbox" id="checkAllId">全选</th>
            <th>Excel Name</th>
            <th>Excel Date</th>
            <th>Week</th>
            <th>Import Date</th>
            <th>Occupancy Rate(%)</th>
            <!-- <th>Delete</th> -->
        </thead>
        <tbody id="tbodyId">
            <c:forEach var="excel" items="${excel}">
                <tr>
                	<td><input type="checkbox" name="excelId" class="excelId" value="${excel.excelId}"></td>
                    <%-- <td>${excel.excelId}</td> --%>
                    <td><input type="submit" value="${excel.excelName}" onclick="getExcelAll(${excel.excelId})"></td>
                    <td>${excel.excelDate}</td>
                    <td>${excel.week}</td>
                    <td>${excel.createDate}</td>
                    <td>${excel.occupancyRate}%</td>
                    <%-- <td><a href="doDeleteExcel.do?excelIds=${excel.excelId}" onclick="return confirm('确定删除该记录吗?')">删除</a></td> --%>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>
<script type="text/javascript" src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
<script language="javascript">
$(function(){
	var state = "${requestScope.result.state}";
	var message = "${requestScope.result.message}";
	if(message != "" && state == 0)alert(message);
	
	$("#checkAllId").change(doChangeTBodyCheckBoxState);
	$("#tbodyId").on("change", ".excelId", doChangeTHeadCheckBoxState);
	//$(".input-group-btn").on("click", "btn-delete", doDelete);
});
//单选操作
function doChangeTHeadCheckBoxState(){
	var flag = true;
	$("#tbodyId input[type='checkbox']").each(function(){
		flag = flag && $(this).prop("checked");
	});
	$("#checkAllId").prop("checked", flag);
}
//全选操作
function doChangeTBodyCheckBoxState(){
	var state = $(this).prop("checked");
	$("#tbodyId input[type='checkbox']").prop("checked", state);
}
//获取用户选中的记录id
function doGetCheckedIds(){
	var array = [];
	$("#tbodyId input[type='checkbox").each(function(){
		if($(this).prop("checked")){
			array.push($(this).val());
		}
	});
	return array;
}
//删除操作
function doDelete(){
	var ids = doGetCheckedIds();
	if(ids.length == 0){
		alert("请先选择");
		return;
	}
	if(window.confirm("您确定要删除吗？")){
		var params = {"excelIds":ids.toString()}
		var url = "doDeleteExcel.do";
		$.post(url, params, function(result){
			if(result.state == 1){
				alert(result.message);
				window.location.href="doIndexUI.do";
			}else{
				alert(result.message);
			}
		});
	}
}

//导入文件上传前校验
function checkout(){
	$("#form_table").bind("submit", function(){  
		var file = $("#file_excel").val();
		if(file == ""){
			alert("请选择文件！！！");
			return false;
		}
	});
}
//根据 Excel ID 获取 Excel内容
function getExcelAll(excelId){
	window.location.href="doExcelAllUI.do?excelId=" + excelId;
}
//查询框操作
function doSearch(){
	var excelName = $("#excelName").val();
	if(excelName != ''){
		var isSearchMax = $('input[name="isSearchMax"]:checked').val()==null ? 0:1;
		window.location.href="doSearch.do?excelName=" + excelName + "&isSearchMax=" + isSearchMax;
	}else{
		window.location.href="doIndexUI.do";
	}
}
</script>
</html>