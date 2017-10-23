<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>添加成员</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/wx/wxTag/">标签列表</a></li>
		<li class="active"><a href="${ctx}/wx/wxTag/assign?id=${wxTag.id}"><shiro:hasPermission name="wx:wxTag:edit">添加成员</shiro:hasPermission><shiro:lacksPermission name="wx:wxTag:edit">成员员列表</shiro:lacksPermission></a></li>
	</ul>
	<div class="container-fluid breadcrumb">
		<div class="row-fluid span12">
			<span class="span4">标签名称: <b>${wxTag.name}</b></span>
			<span class="span4">所属企业: ${wxTag.office.name}</span>
			<span class="span4">英文名称: ${wxTag.enname}</span>
		</div>
		<div class="row-fluid span8">
			<%-- <span class="span4">角色类型: ${role.roleType}</span> --%>
			<c:set var="dictvalue" value="${wxTag.dataScope}" scope="page" />
			<span class="span4">数据范围: ${fns:getDictLabel(dictvalue, 'sys_data_scope', '')}</span>
		</div>
	</div>
	<sys:message content="${message}"/>
	<div class="breadcrumb">
		<form id="assignTagForm" action="${ctx}/wx/wxTag/assigntag" method="post" class="hide">
			<input type="hidden" name="id" value="${wxTag.id}"/>
			<input id="idsArr" type="hidden" name="idsArr" value=""/>
		</form>
		<input id="assignButton" class="btn btn-primary" type="submit" value="添加成员"/>
		<script type="text/javascript">
			$("#assignButton").click(function(){
				top.$.jBox.open("iframe:${ctx}/wx/wxTag/usertotag?id=${wxTag.id}", "添加成员",810,$(top.document).height()-240,{
					buttons:{"确定添加":"ok", "清除已选":"clear", "关闭":true}, bottomText:"通过选择部门，然后为列出的人员分配标签。",submit:function(v, h, f){
						var pre_ids = h.find("iframe")[0].contentWindow.pre_ids;
						var ids = h.find("iframe")[0].contentWindow.ids;
						//nodes = selectedTree.getSelectedNodes();
						if (v=="ok"){
							// 删除''的元素
							if(ids[0]==''){
								ids.shift();
								pre_ids.shift();
							}
							if(pre_ids.sort().toString() == ids.sort().toString()){
								top.$.jBox.tip("未给标签【${wxTag.name}】添加新成员！", 'info');
								return false;
							};
					    	// 执行保存
					    	loading('正在提交，请稍等...');
					    	var idsArr = "";
					    	for (var i = 0; i<ids.length; i++) {
					    		idsArr = (idsArr + ids[i]) + (((i + 1)== ids.length) ? '':',');
					    	}
					    	$('#idsArr').val(idsArr);
					    	$('#assignTagForm').submit();
					    	return true;
						} else if (v=="clear"){
							h.find("iframe")[0].contentWindow.clearAssign();
							return false;
		                }
					}, loaded:function(h){
						$(".jbox-content", top.document).css("overflow-y","hidden");
					}
				});
			});
		</script>
	</div>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>归属公司</th><th>归属部门</th><th>登录名</th><th>姓名</th><th>电话</th><th>手机</th><shiro:hasPermission name="sys:user:edit"><th>操作</th></shiro:hasPermission></tr></thead>
		<tbody>
		<c:forEach items="${userList}" var="user">
			<tr>
				<td>${user.company.name}</td>
				<td>${user.office.name}</td>
				<td><a href="${ctx}/sys/user/form?id=${user.id}">${user.loginName}</a></td>
				<td>${user.name}</td>
				<td>${user.phone}</td>
				<td>${user.mobile}</td>
				<shiro:hasPermission name="wx:wxTag:edit"><td>
					<a href="${ctx}/wx/wxTag/outtag?userId=${user.id}&wxTagId=${wxTag.id}" 
						onclick="return confirmx('确认要将用户<b>[${user.name}]</b>从<b>[${wxTag.name}]</b>标签中移除吗？', this.href)">移除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>
