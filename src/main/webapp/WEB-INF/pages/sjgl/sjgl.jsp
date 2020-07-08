<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>数据管理</title>

	<link rel="stylesheet" type="text/css" href="/style/wholemain.css"/>
	<link rel="stylesheet" type="text/css" href="/style/easyui/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="/style/easyui/themes/icon.css"/>
	<script type="text/javascript" src="/js/common.js"></script>
	<script type="text/javascript" src="/js/easyui-custom.js"></script>
	<script type="text/javascript" src="/js/easyui-datagrid.js"></script>
	<script src="/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/js/jquery.min.js"></script>
	<script type="text/javascript" src="/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="/js/easyui-lang-zh_CN.js"></script>
</head>
<style>
	.black_overlay{
		display: none;
		position: absolute;
		overflow-y:visible;
		bottom:0;
		top: 0%;
		left: 0%;
		width: 100%;
		height: 100%;
		background-color: #dcdcdc;
		z-index:1001;
		-moz-opacity: 1.2;
		opacity:.80;
		filter: alpha(opacity=88);
	}
	.white_content {
		overflow-y:visible;
		display: none;
		position: absolute;
		width: 80%;
		height: 60%;
		padding-left: 20px;
		z-index:1002;
		/* overflow: auto;  */
	}
</style>
<script>
	/*$(function () {
		var curr_time = new Date();
		var strDate = curr_time.getFullYear() + "-";
		strDate += curr_time.getMonth() + 1 + "-";
		strDate += curr_time.getDate() + " ";
		$('#startTssj').datebox('setValue', strDate);
		$('#endTssj').datebox('setValue', strDate);
	});*/
</script>
<body>
<div class="right_nav">
	当前位置：数据管理
</div>
<div class="qh">
	<div class="tab" style="">
    	 <a href="javascript:;" class="on" style="border-left:1px solid white;">数据下载</a>
         <a href="javascript:;">DTU数据上传</a>
		 <a href="javascript:;">配网保护数据上传</a>
    </div>
    <br/>
 <div class="content">
    	<ul>
        	<li style="display:block;">
  				<div >
					<div class="right_table" style="padding-left:10px;padding-right:10px;" >
						<div class="tj">
							<input type="button" name="button"  value="下载标准" class="iput_m" onclick="getStandard()"/>
							<input type="button" name="button"  value="下载用户" class="iput_m" onclick="getUsers()">
							<input type="button" name="button"  value="下载厂家" class="iput_m" onclick="getSccj()">
							<input type="button" name="button"  value="下载所属线路" class="iput_m" onclick="getSsxl()" style="width: 75px;">
							<input type="button" name="button"  value="下载配网保护标准" class="iput_m" onclick="getPwbh()" style="width: 100px;">
						</div>
					</div>
					<div id="light" class="white_content">
						<div id="text1" style="text-align: center;margin:120px;"></div>
						<%-- <button onclick = "closeDialog()" class="float-button">关闭</button>--%>
					</div>
					<div id="fade" class="black_overlay"></div>
				</div>
			</li>
    		<!--  1 end-->
   			 <!--  2 begin-->
			<li style="display:none;">
				<div>
					<div  style="padding-left:10px;padding-right:10px;">
						<div class="tj">
							<form  method="post">
								<span>日期</span>
								<input class="easyui-datebox" name="startTssj" id="startTssj"
									   data-options="required:true" style="width:120px"/>
								&nbsp;至&nbsp;
								<input class="easyui-datebox" name="endTssj" id="endTssj"
									   data-options="required:true" style="width:120px"/>
								&nbsp;<span>调度号</span>&nbsp;<input id="azddDdh" type="text" class="right_ipu2"/>
								&nbsp;&nbsp;<span>装置类型</span>&nbsp;<select  name="zzlx" id="zzlx"  style="height:25px; border:1px solid #CCC;">
								</select>
								&nbsp;<span>是否上传</span>&nbsp;
								<select  name="upload" id="upload"  style="height:25px; border:1px solid #CCC;">
									<option value="-1">全部</option>
									<option value="0" selected>未上传</option>
									<option value="1">已上传</option>
								</select>
								<input type="button" name="button"  value="查 询" class="iput_m" onclick="searchList()"/>
								<input type="button" name="button"  value="上 传" class="iput_m" onclick="uploadAllData()"/>
							</form>
						</div>
						<br>
						<div >
								<table id="tableList"></table>
								</div>
					</div>
				</div>
			</li>

			<!--  3 begin-->
			<li style="display:none;">
				<div>
					<div  style="padding-left:10px;padding-right:10px;">
						<div class="tj">
							<form  method="post">
								<span>日期</span>
								<input class="easyui-datebox" name="startTssj2" id="startTssj2"
									   data-options="required:true" style="width:120px"/>
								&nbsp;至&nbsp;
								<input class="easyui-datebox" name="endTssj2" id="endTssj2"
									   data-options="required:true" style="width:120px"/>
								&nbsp;<span>调度号</span>&nbsp;<input id="azddDdh2" type="text" class="right_ipu2"/>
								&nbsp;<span>是否上传</span>&nbsp;
								<select  name="upload2" id="upload2"  style="height:25px; border:1px solid #CCC;">
									<option value="-1">全部</option>
									<option value="0" selected>未上传</option>
									<option value="1">已上传</option>
								</select>
								<input type="button" name="button"  value="查 询" class="iput_m" onclick="searchList2()"/>
								<input type="button" name="button"  value="上 传" class="iput_m" onclick="uploadAllData2()"/>
							</form>
						</div>
						<br>
						<div >
							<table id="tableList2"></table>
						</div>
					</div>
				</div>
			</li>
        </ul>
    </div>
</div>
</body>
<script>
	/*下载厂家*/
	function getSccj(){
		if(confirm('确定重新下载所有厂家信息吗？')){
			// main.location.href = '/getStandard.action';
			document.getElementById('light').style.display='block';
			document.getElementById('fade').style.display='block';
			var times=setInterval(clocks,1000);
			$.ajax({
				url : '/getSccj.action',
				type : 'POST',
				async : false,
				//data:getParam(),
				processData : false,
				contentType : false,
				success : function(data) {
					window.clearInterval(times);
					document.getElementById('text1').innerHTML = data.message;
					setTimeout(function(){
								document.getElementById('light').style.display='none';
								document.getElementById('fade').style.display='none';
							}
							,3000);
				},
				error : function() {
					alert("下载错误！");
				}
			});
		}
	}
	/*下载所属线路*/
	function getSsxl(){
		if(confirm('确定重新下载所属线路吗？')){
			// main.location.href = '/getStandard.action';
			document.getElementById('light').style.display='block';
			document.getElementById('fade').style.display='block';
			var times=setInterval(clocks,1000);
			$.ajax({
				url : '/getSsxl.action',
				type : 'POST',
				async : false,
				//data:getParam(),
				processData : false,
				contentType : false,
				success : function(data) {
					window.clearInterval(times);
					document.getElementById('text1').innerHTML = data.message;
					setTimeout(function(){
								document.getElementById('light').style.display='none';
								document.getElementById('fade').style.display='none';
							}
							,3000);
				},
				error : function() {
					alert("下载错误！");
				}
			});
		}
	}
    /*下载配网保护*/
    function getPwbh(){
        if(confirm('确定重新下载配网保护标准吗？')){
            // main.location.href = '/getStandard.action';
            document.getElementById('light').style.display='block';
            document.getElementById('fade').style.display='block';
            var times=setInterval(clocks,1000);
            $.ajax({
                url : '/getPwbhStandard.action',
                type : 'POST',
                async : false,
                //data:getParam(),
                processData : false,
                contentType : false,
                success : function(data) {
                    window.clearInterval(times);
                    document.getElementById('text1').innerHTML = data.message;
                    setTimeout(function(){
                            document.getElementById('light').style.display='none';
                            document.getElementById('fade').style.display='none';
                        }
                        ,3000);
                },
                error : function() {
                    alert("下载错误！");
                }
            });
        }
    }
	/*dtu上传*/
	$(function(){
		var formdata = new FormData(); // 模拟表单对象
		$.ajax({
			url : '/jbxx/selectZzlxAll.action',
			type : 'POST',
			async : false,
			data:formdata,
			processData : false,
			contentType : false,
			beforeSend:function(){
				console.log("正在加载，请稍候");
			},
			success:function(result){
				$("#zzlx").append("<option value='"+"-1"+"' >"+"全部"+"</option>");
				$.each(result,function(index,value){
					$("#zzlx").append("<option value='"+value.keyvalue+"' >"+value.keymemo+"</option>");
				});
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(errorThrown);
			},
			async:false             //false表示同步
		});
	});
	function getQueryParams() {
		var queryParams = new Object();
		queryParams.startTssj = $('#startTssj').datebox('getValue');
		queryParams.endTssj = $('#endTssj').datebox('getValue');
		queryParams.azddDdh = $('#azddDdh').val();
		queryParams.zzlx = $('#zzlx').val();
		queryParams.upload = $('#upload').val();
		return queryParams;
	}
	function searchList(){
		flagAllArray=[];
		$('#tableList').datagrid('options').url = '${ctx}/jbxx/selectListJbxx.action';
		$('#tableList').datagrid('options').queryParams = getQueryParams();
		$('#tableList').datagrid("load");
	}

	//配网保护
	function getQueryParams2() {
		var queryParams = new Object();
		queryParams.startTssj = $('#startTssj2').datebox('getValue');
		queryParams.endTssj = $('#endTssj2').datebox('getValue');
		queryParams.azddDdh = $('#azddDdh2').val();
		queryParams.upload = $('#upload2').val();
		return queryParams;
	}
	function searchList2(){
		flagAllArray=[];
		$('#tableList2').datagrid('options').url = '${ctx}/pwbhJbxx/selectListPwbhJbxx.action';
		$('#tableList2').datagrid('options').queryParams = getQueryParams2();
		$('#tableList2').datagrid("load");
	}
	var flagArray2 = new Array();
	var flagAllArray2 = new Array();
	function uploadSingle2(tsid){
		flagArray2=[];
		flagArray2.push(tsid);
		upload2(flagArray2);
	}
	function uploadAllData2(){
		upload2(flagAllArray2);
	}
	function upload2(array){
		$.ajax({
			type: "POST",
			url: "/uploadPwbhJl",
			data: JSON.stringify(array),
			contentType: "application/json;charsetset=UTF-8",
			dataType: "json",
			success: function (data) {
				if(data.code==0) searchList2();
				alert(data.msg);
			}
		});
	}
	//配网保护end

	/*下载标准*/
	var i=0;
	function clocks(){
		document.getElementById('text1').innerHTML = '<b>正在下载  '+i+' 秒，请稍后。。。</b>';
		i=i+1;
	}
	function getStandard(){
		if(confirm('确定重新下载所有标准吗？')){
			// main.location.href = '/getStandard.action';
			document.getElementById('light').style.display='block';
			document.getElementById('fade').style.display='block';
			var times=setInterval(clocks,1000);
			$.ajax({
				url : '/getStandard.action',
				type : 'POST',
				//async : false,
				//data:getParam(),
				processData : false,
				contentType : false,

				complete: function () {
					$.messager.progress('close');
				},
				success : function(data) {
					window.clearInterval(times);
					document.getElementById('text1').innerHTML = '<b>'+data.message+'</b>';
					setTimeout(function(){
								document.getElementById('light').style.display='none';
								document.getElementById('fade').style.display='none';
							}
							,3000);
				},
				error : function() {
					alert("下载错误！");
				}
			});
		}
	}

	/*下载用户*/
	function getUsers(){
		if(confirm('确定重新下载所有用户信息吗？')){
			// main.location.href = '/getStandard.action';
			document.getElementById('light').style.display='block';
			document.getElementById('fade').style.display='block';
			var times=setInterval(clocks,1000);
			$.ajax({
				url : '/getUsers.action',
				type : 'POST',
				async : false,
				//data:getParam(),
				processData : false,
				contentType : false,
				success : function(data) {
					window.clearInterval(times);
					document.getElementById('text1').innerHTML = data.message;
					setTimeout(function(){
								document.getElementById('light').style.display='none';
								document.getElementById('fade').style.display='none';
							}
							,3000);
				},
				error : function() {
					alert("下载错误！");
				}
			});
		}
	}

	var flagArray = new Array();
	var flagAllArray = new Array();
	function uploadSingle(tsid){
		flagArray=[];
		flagArray.push(tsid);
		upload(flagArray);
	}
	function uploadAllData(){
		upload(flagAllArray);
	}
	function upload(array){
		/*var formdata = new FormData();
		formdata.append("flagArray",flagArray);
		console.log(JSON.stringify(formdata.get("flagArray")));*/
		$.ajax({
			type: "POST",
			url: "/uploadJl",
			data: JSON.stringify(array),
			contentType: "application/json;charsetset=UTF-8",
			dataType: "json",
			success: function (data) {
				if(data.code==0) searchList();
				alert(data.msg);
			}
		});
	}

		$(".qh .tab a").click(function(){
			$(this).addClass('on').siblings().removeClass('on');
			var index = $(this).index();
			$('.qh .content li').hide();
			$('.qh .content li:eq('+(index)+')').show();
			if(index == 1){
				flagArray=[];
				$('#tableList').datagrid({
					iconCls:'icon-ok',
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
						{field:'TSID',align:"left",title:'调试ID',width:0,hidden:'true'},
						{field:'TSSJ',align:"left",title:'调试时间',width:30,  formatter : function(value){
								var date = new Date(value);
								var y = date.getFullYear();
								var m = date.getMonth() + 1;
								var d = date.getDate();
								return y + '-' +m + '-' + d;
							}},
						{field:'AZDD_DDH',align:"left",title:'设备调度号',width:30},
						{field:'SSXL',align:"left",title:'所属线路',width:30,formatter:function(value,row,index){
								var xlmc = "";
								$.ajax({
									url: '/ssxl/selectByPrimaryKey/'+value,
									type: 'GET',
									async: false,
									processData: false,
									contentType: false,
									beforeSend: function () {
										console.log("正在加载，请稍候");
									},
									success: function (data) {
										let data2 = JSON.parse(data);
										xlmc = data2.bdz+"-"+data2.xlmc;
									},
									error: function () {
										//alert("页面加载错误！");
									}
								});
								return xlmc;
							}},
						{field:'ZZXH',align:"left",title:'装置型号',width:30},
						{field:'SCCS',align:"left",title:'生产厂商',width:30},
						{field:'ZZLX',align:"left",title:'装置类型',width:30},
						{field:'TSRYXM',align:"left",title:'调试人员',width:30},
						{field:'STATE',align:"left",title:'状态',width:30,formatter:function(value,row,index){
								var context="";
								var formdata = new FormData();
								formdata.append("tsid",row.TSID);
								$.ajax({
									url : '${ctx}/jbxx/selectListJbxxToState.action',
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
										context = data.state;
									},
									error : function() {
										alert("页面加载错误！");
									}
								});
								return context;
							}},
						{field:'UPLOAD',align:"left",title:'是否上传',width:30},
						{field:'trans',align:"left",title:'操作',width:90,formatter:function(value,row,index){
								flagAllArray.push(row.TSID);
								var txt1 = '&nbsp;&nbsp;&nbsp;<button href="javascript:void(0);" onclick="uploadSingle(\'' + row.TSID + '\')" class="iput_m" style="width: 40px; height: 20px;">' + '上传' + '</button>';
								/*var txt2 = '&nbsp;&nbsp;&nbsp;<button href="javascript:void(0);" onclick="exportJbxx(\'' + row.TSID + '\')" class="iput_m" style="width: 40px; height: 20px;">' + '导出' + '</button>';*/
								return txt1;
							}},
					]],
				});
				searchList();
			}else if(index == 2) {
				flagArray2 = [];
				$('#tableList2').datagrid({
					iconCls: 'icon-ok',
					//queryParams: getQueryParams(),
					nowrap: false,
					striped: true,
					collapsible: false,
					fitColumns: true,
					pagination: true,
					singleSelect: true,
					rownumbers: true,
					remoteSort: true,
					pageList: [10, 15, 30, 50],
					pageSize: 10,//每页显示的记录条数，默认为10 
					idField: 'ID',
					columns: [[
						{field: 'TSID', align: "left", title: '调试ID', width: 0, hidden: 'true'},
						{
							field: 'TSSJ', align: "left", title: '调试时间', width: 30, formatter: function (value) {
								var date = new Date(value);
								var y = date.getFullYear();
								var m = date.getMonth() + 1;
								var d = date.getDate();
								return y + '-' + m + '-' + d;
							}
						},
						{field: 'AZDD_DDH', align: "left", title: '设备调度号', width: 30},
						{
							field: 'SSXL',
							align: "left",
							title: '所属线路',
							width: 30,
							formatter: function (value, row, index) {
								var xlmc = "";
								$.ajax({
									url: '/ssxl/selectByPrimaryKey/' + value,
									type: 'GET',
									async: false,
									processData: false,
									contentType: false,
									beforeSend: function () {
										console.log("正在加载，请稍候");
									},
									success: function (data) {
										let data2 = JSON.parse(data);
										xlmc = data2.bdz + "-" + data2.xlmc;
									},
									error: function () {
										//alert("页面加载错误！");
									}
								});
								return xlmc;
							}
						},
						{field: 'LM', align: "left", title: '路名', width: 30},
						{
							field: 'UPLOAD',
							align: "left",
							title: '是否上传',
							width: 30,
							formatter: function (value, row, index) {
								txt1 = "已上传";
								if(value==0){
									txt1 = "未上传";
								}
								return txt1;
							}
						},
						{
							field: 'trans',
							align: "left",
							title: '操作',
							width: 60,
							formatter: function (value, row, index) {
								flagAllArray2.push(row.TSID);
								var txt1 = '&nbsp;&nbsp;&nbsp;<button href="javascript:void(0);" onclick="uploadSingle2(\'' + row.TSID + '\')" class="iput_m" style="width: 40px; height: 20px;">' + '上传' + '</button>';
								return txt1;
							}
						},
					]],
				});
				searchList2();
			}
		});
</script>
</html>
