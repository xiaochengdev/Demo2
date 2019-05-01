<%--
  Created by IntelliJ IDEA.
  User: xiaocheng
  Date: 2018/9/22
  Time: 22:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Demo</title>
    <script type="text/javascript" src="../js/jquery-3.3.1.min.js"></script>
</head>
<body>
<div>
    <div>
        <form action="/Demo2/json/user_login" method="post" enctype="multipart/form-data">
            <table>
                <tr>
                    <td><label>账 户：</label></td><td><input type="text" id="account" name="user.account"></td>
                </tr>
                <tr>
                    <td><label>密 码：</label></td><td><input type="password" id="passwd" name="user.passwd"></td>
                </tr>
                <tr>
                    <td style="collapse: 2"><input type="submit" value="登陆"/></td>
                </tr>
            </table>
        </form>
        <table border="1" cellpadding="0" cellspacing="0">
            <s:iterator var="name" value="{'Java','C#','JQuery','Oracle','Java Web'}" status="st">
                <s:if test="#st.odd">
                    <tr style="background-color: aqua">
                        <td><s:property value="name"></s:property> </td>
                    </tr>
                </s:if>
                <s:else>
                    <tr style="background-color: chartreuse">
                        <td><s:property value="name"></s:property> </td>
                    </tr>
                </s:else>
            </s:iterator>
            <s:a action="user_queryUsers" namespace="/json">queryUsers</s:a>
           <%-- <s:debug></s:debug>--%>
            <s:include value="register.jsp"></s:include>
            <s:form namespace="/json" action="user_login" method="POST">
                <s:textfield label="账 号" name="user.account"></s:textfield>
                <s:password label="密 码" name="user.passwd"></s:password>
                <s:submit value="登陆"></s:submit>
            </s:form>

        </table>

        <s:form namespace="/file" action="file_uploadFile" method="POST" enctype="multipart/form-data">
            <s:file name="file" label="上传文件"></s:file>
            <s:submit value="上传"/>
            <s:reset value="重置"/>
        </s:form>
    </div>
</div>
</body>
</html>
