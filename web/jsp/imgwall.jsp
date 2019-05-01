<%@ page import="java.io.File" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: xiaocheng
  Date: 2019/2/5
  Time: 15:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    List<File> list= (List<File>) session.getAttribute("filelist");
    for (int i=0;i<list.size();i++){
%>
<a href="file_downloadFileList?selectedFile=<%=list.get(i).getName() %>"><%=list.get(i).getName() %></a><br>
<%
    }
%>
</body>
</html>
