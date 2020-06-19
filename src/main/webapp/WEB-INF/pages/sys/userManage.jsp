<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>系统管理</title>

<link rel="stylesheet" type="text/css" href="/style/wholemain.css"/>
<link rel="Stylesheet" type="text/css" href="/style/main.css" />
<link rel="stylesheet" type="text/css" href="/style/easyui/themes/default/easyui.css"/>
<link rel="stylesheet" type="text/css" href="/style/easyui/themes/icon.css"/>
<script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript" src="/js/easyui-custom.js"></script>
<script type="text/javascript" src="/js/easyui-datagrid.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/jquery.min.js"></script>
<script type="text/javascript" src="/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/easyui-lang-zh_CN.js"></script>
	<script>
		function g4(){
			var w=window.screen.width/2-950/2;
			var t=window.screen.height/2-600/2-35;
			window.open("/user/toAddUserInfoView.action",null," height=500,width=1000,top="+t+",left="+w+",toolbar=no,menubar=no,localtion=no,scrollbars=no");
		}
		function g9(){
			var w=window.screen.width/2-950/2;
			var t=window.screen.height/2-600/2-35;
			window.open("/role/toAddRoleInfoView.action",null," height=500,width=1000,top="+t+",left="+w+",toolbar=no,menubar=no,localtion=no,scrollbars=no");
		}
	</script>
 </head>
<body>
<div class="right_nav">
    当前位置：系统管理
</div>
<div class="qh">
	<div class="tab" style="">
    	 <a href="javascript:;" class="on" style="border-left:1px solid white;">用户管理</a>
         <a href="javascript:;">权限管理</a>
        <a href="javascript:;">修改密码</a>
    </div>
    <br/>
 <div class="content">
    	<ul>
          <!--  1 begin-->
           <c:if test="${funcMap.yhgl == 0 }">
        	<li style="display:none;">
        	</c:if>
        	<c:if test="${funcMap.yhgl != 0 }">
        	<li style="display:block;">
        	</c:if>
  				<div >
					<div class="right_table" style="padding-left:10px;padding-right:10px;" >
						<div class="tj">
							<span>账户</span><input id="loginName" type="text" class="right_ipu2"/>
							<span>姓名</span><input id="userName" type="text" class="right_ipu2"/>
							<span>状态</span><select  id="zt" type="text" class="right_ipu2"/>
							<option value="0">启用</option>
							<option value="1">注销</option>
							</select>
							<c:if test="${funcMap.yhgl != 0 }">
							<input type="button" name="button"  value="查 询" class="iput_m" onclick="searchList()"/>
							</c:if>
							<c:if test="${funcMap.yhgl == 2 }">
							<input type="button" name="button"  value="创建" class="iput_m" onclick="g4()"/>
							</c:if>
						</div>
					</div>
					 <br/>
					<div title="" class="easyui-panel" iconCls="icon-redo" collapsible="true">
								<table id="tableList"></table>
						</div>
					</div>
          </li>

    		<!--  1 end-->
   			 <!--  2 begin-->
			<li style="display:none;">
				<div>
					<div  style="padding-left:10px;padding-right:10px;">
						<div class="tj">
							<span>角色名称</span><input id="roleName" type="text" class="right_ipu2">
							<c:if test="${funcMap.jsgl != 0 }">
							<input type="button" name="button"  value="查 询" class="iput_m" onclick="searchList2()"/>
							 </c:if>
							<c:if test="${funcMap.jsgl == 2 }">
							<input type="button" name="button"  value="创建" class="iput_m" onclick="g9()"/>
							 </c:if>
						</div>
						<br>
						<div >
								<table id="tableList2"></table>
								</div>
					</div>
				</div>
			</li>
            <!-- 修改密码 6 begin-->
			<li style="display:none;" id="li3">
				<div style=" height:483px;overflow-y:auto; overflow-x:auto;">
					<div class="right_table" >
						<table  width="100%"  cellpadding="0" cellspacing="1" bgcolor="#c6c6c6">
							<tbody>
							<tr>
								<th colspan="2" align="center" bgcolor="#f5f2ea">修改密码</th> </tr>
							<tr>
								<th bgcolor="#FFFFFF" >原密码</th>
								<td bgcolor="#FFFFFF " style="text-align:left; ">
									&nbsp;<input type="password" id="oldPassword" style="height:25px; border:1px solid #CCC;"/>
									<span style="color:red;">*</span>
								</td>
							</tr>
							<tr>
								<th bgcolor="#FFFFFF" >新密码</th>
								<td bgcolor="#FFFFFF " style="text-align:left; ">
									&nbsp;<input type="password" id="newPassword" style="height:25px; border:1px solid #CCC;"/>
									<span style="color:red;">*</span>
								</td>
							</tr>
							<tr>
								<th bgcolor="#FFFFFF" >确认密码</th>
								<td bgcolor="#FFFFFF" style="text-align:left; " > &nbsp;<input type="password" id="comPassword" style="height:25px; border:1px solid #CCC;"/>
								<span style="color:red;">*</span>
								</td>
							</tr>
							<tr>
								<td height="33" colspan="2"  bgcolor="#f5f2ea">
									<input type="button" onclick="changePwd()" name="button"  value="确认" class="iput_m"  style="height:25px;"/>
								</td>
							</tr>
							</tbody></table>
					</div>
				</div>
			</li>
        </ul>
    </div>
</div>
<script>
	/* 用户管理 */
	function editUser(userId){
		var w=window.screen.width/2-950/2;
		var t=window.screen.height/2-600/2-35;
		window.open("${ctx}/user/toAddUserInfoView.action?userId="+userId,null," height=500,width=1000,top="+t+",left="+w+",toolbar=no,menubar=no,localtion=no,scrollbars=no");
	}
	function delUser(userId){
		var formdata = new FormData();
		formdata.append("userId",userId);
		if(!confirm("确认删除吗？")){
			return;
		}
		$.ajax({
			url : '${ctx}/user/delUserInfo.action',
			type : 'POST',
			async : false,
			data:formdata,
			// 告诉jQuery不要去处理发送的数据
			processData : false,
			// 告诉jQuery不要去设置Content-Type请求头
			contentType : false,
			beforeSend:function(){
				console.log("正在加载，请稍候");
			},
			success : function(data) {
				alert(data.message);
				if(data.code == 0)
 					$('#tableList').datagrid("load");
			},
			error : function() {
				alert("页面加载错误！");
			}
		}); 
	}
	function getQueryParams() {
		var queryParams = new Object();
        queryParams.loginName = $('#loginName').val();
        queryParams.userName = $('#userName').val();
        queryParams.zt = $('#zt').val();
        return queryParams;
	}
	function searchList(){
		$('#tableList').datagrid('options').url = '/user/selectListUserInfo.action';
		$('#tableList').datagrid('options').queryParams = getQueryParams();
        $('#tableList').datagrid("load");
   	}
	var yhgl = '${funcMap.yhgl}';
	$(function(){
		$('#tableList').datagrid({
			//title:'用户管理',
			iconCls:'icon-ok',
			/* url:listUrl, */
			queryParams: getQueryParams(),
			nowrap: false,
			striped: true,
			collapsible:false,
			fitColumns: true,
			pagination:true,
			singleSelect:true,
			rowsNumbers:true,
			remoteSort:true,
			pageList:[10,15,30,50],
			pageSize: 10,//每页显示的记录条数，默认为10 
			idField:'ID',
			columns:[[
					{field:'USER_ID',align:"center",title:'员工号',width:30,hidden:'true'},
					{field:'USER_NAME',align:"center",title:'姓名',width:30},
					{field:'LOGIN_NAME',align:"center",title:'账号',width:30},
					{field:'USER_TYPE',align:"center",title:'账号类型',width:30},
					{field:'ROLE_NAME',align:"center",title:'所属权限',width:30},
					{field:'PHONE',align:"center",title:'电话',width:30},
					{field:'EMAIL',align:"center",title:'邮箱',width:30},
					{field:'ZW',align:"center",title:'职务',width:30},
					{field:'ZT',align:"center",title:'状态',width:30},
					{field:'trans',align:"left",title:'操作',width:30,formatter:function(value,row,index){ 
						var txt = '<button href="javascript:void(0);" onclick="editUser(\'' + row.user_id + '\')" class="iput_m" style="width: 40px; height: 20px;">' + '修改' + '</button>';
					   
						var txt2 = '&nbsp;&nbsp;&nbsp;<button href="javascript:void(0);" onclick="delUser(\'' + row.user_id + '\')" class="iput_m" style="width: 40px; height: 20px;">' + '删除' + '</button>';
							if(row.STATUS == '注销'){
								 return txt;
							}else{
								 return txt+txt2;
							}
					}},
			]],
		});
		searchList();
	});
	/*用户管理结束*/
	/*权限管理 */
	function getQueryParams2() {
		var queryParams = new Object();
        queryParams.roleName = $('#roleName').val();
        return queryParams;
	}
	function searchList2(){
		$('#tableList2').datagrid('options').url = '${ctx}/role/selectListRoleInfo.action';
		$('#tableList2').datagrid('options').queryParams = getQueryParams2();
        $('#tableList2').datagrid("load");
   	}
	function editRole(roleId){
		var w=window.screen.width/2-950/2;
		var t=window.screen.height/2-600/2-35;
		window.open("${ctx}/role/toAddRoleInfoView.action?roleId="+roleId,null," height=500,width=1000,top="+t+",left="+w+",toolbar=no,menubar=no,localtion=no,scrollbars=no");
	}
	function delRole(roleId){
		var formdata = new FormData();
		formdata.append("roleId",roleId);
		if(!confirm("确认删除吗？")){
			return;
		}
		$.ajax({
			url : '${ctx}/role/delRoleInfo.action',
			type : 'POST',
			async : false,
			data:formdata,
			// 告诉jQuery不要去处理发送的数据
			processData : false,
			// 告诉jQuery不要去设置Content-Type请求头
			contentType : false,
			beforeSend:function(){
				console.log("正在加载，请稍候");
			},
			success : function(data) {
				alert(data.message);
				if(data.code == 0)
 					$('#tableList2').datagrid("load");
			},
			error : function(data) {
				alert(data.msg);
				alert("页面加载错误！");
			}
		}); 
	}

	$(function(){
		$(".qh .tab a").click(function(){
			$(this).addClass('on').siblings().removeClass('on');
			var index = $(this).index();
			$('.qh .content li').hide();
			$('.qh .content li:eq('+(index+1)+')').show();
			if(index == 1){
				$('#tableList2').datagrid({
					iconCls:'icon-ok',
					//queryParams: getQueryParams(),
					nowrap: false,
					striped: true,
					collapsible:false,
					fitColumns: true,
					pagination:true,
					singleSelect:true,
					rownumbers:true,
					remoteSort:true,
					pageList:[10,15,30,50],
					pageSize: 10,//每页显示的记录条数，默认为10 
					idField:'ID',
					columns:[[
						{field:'roleId',align:"center",title:'角色Id',width:30,hidden:true},
						{field:'roleName',align:"center",title:'角色名称',width:20},
						{field:'remark',align:"center",title:'备注',width:30},
						{field:'trans',align:"left",title:'操作',width:30,formatter:function(value,row){
								var jsgl = '${funcMap.jsgl}';
								var txt = '<button href="javascript:void(0);" onclick="editRole(\'' + row.roleId + '\')" class="iput_m" style="width: 40px; height: 20px;">' + '修改' + '</button>';
								var txt2 = '&nbsp;&nbsp;&nbsp;<button href="javascript:void(0);" onclick="delRole(\'' + row.roleId + '\')" class="iput_m" style="width: 40px; height: 20px;">' + '删除' + '</button>';
								if(jsgl > 1){
									return txt+txt2;
								}
								return "";
							}},
					]],
				});
				searchList2();
			}
		});

	/*权限管理结束  */
	});

	/* 修改密码 */
	function changePwd(){
			var formdata = new FormData(); // 模拟表单对象
			formdata.append("oldPassword", $("#oldPassword").val());
			formdata.append("newPassword", $("#newPassword").val());
			formdata.append("comPassword", $("#comPassword").val());
			$.ajax({
				url : '${ctx}/user/changePassword.action',
				type : 'POST',
				async : false,
				data: formdata,
				// 告诉jQuery不要去处理发送的数据
				processData : false,
				// 告诉jQuery不要去设置Content-Type请求头
				contentType : false,
				beforeSend:function(){
					console.log("正在加载，请稍候");
				},
				success : function(data) {
						alert(data.message);
				},
				error : function() {
					alert("页面加载错误！");
				}
			}); 
   	}
</script>


</body>
</html>
