<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>自动化测试管理系统</title>
</head>

<frameset rows="110,*" cols="*" frameborder="no" border="0" framespacing="0">
    <frame src="/toTopView.action" name="topFrame" scrolling="no" noresize="noresize" id="topFrame" title="topFrame"/>
    <frameset name="btmframe" id="btmframe" cols="145,**" frameborder="no">
        <frame name="left" src="/toLeftView.action" scrolling="No" noresize="noresize" id="leftFrame"
               title="leftFrame"/>
        <frame name="mainFrame" src="/jbxx/toJbxxView.action" id="mainFrame" title="mainFrame"/>
    </frameset>
</frameset>

<body>
</body>

</html>
