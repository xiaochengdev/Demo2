<%--
  Created by IntelliJ IDEA.
  User: xiaocheng
  Date: 2019/2/12
  Time: 22:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--form action="/Demo2/json/user_register" method="post" enctype="multipart/form-data">
    <table>
        <tr>
            <td><label>名 称：</label></td><td><input type="text"  name="user.name"></td>
        </tr>
        <tr>
            <td><label>密 码：</label></td><td><input type="password" name="user.passwd"></td>
        </tr>
        <tr>
            <td><label>性 别：</label></td><td><input type="text" name="user.sex"></td>
        </tr>
        <tr>
            <td><label>描 述：</label></td><td><input type="text" name="user.des"></td>
        </tr>
        <tr>
            <td style="collapse: 2"><input type="submit" value="注册"/></td>
        </tr>
    </table>
</form>--%>
<s:form namespace="/json" action="user_register" method="POST">
    <s:textfield label="姓 名" name="user.name"/>
    <s:password label="密 码" name="user.passwd"/>
    <s:radio list="#{'0':'女','1':'男'}" label="性 别" name="user.sex" value="1"/>
    <s:textarea label="简 介" name="user.des" cols="20" rows="5"/>
    <apan><s:submit value="提交"></s:submit><s:reset value="重置"></s:reset></apan>
</s:form>
</body>
</html>
