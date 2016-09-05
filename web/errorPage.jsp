<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>Error Page!</title>
</head>
<body style="background-color: deepskyblue">
<div style="width:100%;height:100%;">
    <table>
        <tr>
            <td width="1000" align="left">
                <a href="/mainPage.jsp">Home</a>
            </td>
        </tr>
    </table>
    <table style="width:100%;height:40%;">
        <tr>
            <td align="center">
                <Span style="font-size: 40;font-family: sans-serif;">${exception.message}</Span>
            </td>
        </tr>
    </table>
</div>
</body>
</html>