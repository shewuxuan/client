<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<%@ include file="./import.jsp" %>
<html lang="en">
<head>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />

    <title>评估方案管理</title>
    <link rel="stylesheet" type="text/css" href="/css/fanganchuangjian.css"/>
    <link rel="stylesheet" type="text/css" href="/css/menu.css"/>
    <script type="text/javascript" src="/js/jquery.min.js"></script>
</head>
<script type="text/javascript">

   /* function open(){
        window.top.frames['mainFrame'].location=document.getElementsByName("aa")[0].href;
    }

    function ShowTagDetail(obj) {
        var lis = document.getElementsByName("aa");
        for (var i = 0; i < lis.length; i++) lis[i].className = "";//全部清空
        obj.className='on';
    }*/
</script>
<body onload="open()">
<div class="left" style="height: 1000px;">
    <div style="text-align: center;"><br/>
        <c:if test="${ssqy == 1}"><b>石景山</b></c:if>
        <c:if test="${ssqy == 2}"><b>门头沟</b></c:if>
        <c:if test="${ssqy == 3}"><b>朝阳</b></c:if>
    </div>
    <ul>
        <li class="current">
                <a href="/toSjglView.action" target='mainFrame'  onmousedown="test(this)"  class="current left_hover"  >
                    <img src="/images/top04.png" />
                    <p>数据管理</p>
                </a>
        </li>
        <li class="current">
            <a href="/jbxx/toJbxxView.action" target='mainFrame'  onmousedown="test(this)"  class="current left_hover"  >
                <img src="/images/top06.png" />
                <p>DTU调试</p>
            </a>
        </li>
        <li class="current">
            <a href="/ftuJbxx/toFtuJbxxView.action" target='mainFrame'  onmousedown="test(this)"  class="current left_hover"  >
                <img src="/images/top06.gif" />
                <p>FTU调试</p>
            </a>
        </li>
        <li class="current">
            <a href="/pwbhJbxx/toPwbhJbxxView.action" target='mainFrame'  onmousedown="test(this)"  class="current left_hover"  >
                <img src="/images/top02.gif" />
                <p>配网保护</p>
            </a>
        </li>
      <%--  <li>
            <a href="/user/sysByUser.action" target='mainFrame'  onmousedown="test(this)"  class="left_hover"  >
                <img src="/images/top08.png" />
                <p>系统管理</p>
            </a>
        </li>--%>
    </ul>
</div>
</body>
<script>
    window.onload=function(){
        var lis=document.getElementById("list").getElementsByTagName("li");
        for(var i=0;i<lis.length;i++){
            lis[i].setAttribute("index",i);
            lis[i].onclick=function(){
                for(var i=0;i<lis.length;i++){
                    if(this.getAttribute("index")==i){
                        lis[i].className="current";
                    }else{
                        lis[i].className="";
                    }
                }
            }
        }
    }

    /* 退出登陆 */
    function logOut(){
   	 if(confirm('确定已保存当前工作并退出吗？')==true){
   		 // window.location.href = '/logoutJson.action';
   		 if (top != window){
   			    top.location.href = '/logoutJson.action';
   			}
   	    }
   	 return false;
   }
    /*下载标准*/
    function getStandard(){
        if(confirm('确定重新下载所有标准吗？')==true){
           // main.location.href = '/getStandard.action';
           $.ajax({
                url : '/getStandard.action',
                type : 'POST',
                async : false,
                //data:getParam(),
                processData : false,
                contentType : false,
                beforeSend:function(){
                    console.log("正在下载，请稍候");
                },
                success : function(data) {
                  alert("下载成功！");
                },
                error : function() {
                    alert("下载错误！");
                }
            });
        }
    }
</script>

</html>