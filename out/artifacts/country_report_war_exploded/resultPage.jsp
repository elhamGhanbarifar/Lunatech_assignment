<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <title>List Of Airports And Runways</title>

</head>
<body style="background-color: deepskyblue">
<div style="width:100%;text-align:center">
    <table>
        <tr>
            <td width="1000" align="left">
                <a href="/mainPage.jsp">Home</a>
            </td>
        </tr>
    </table>
    <table cellpadding="2" cellspacing="2" align="center">
        <tr>
            <td>
                <c:if test="${currentPage != 1}">
                    <a href="main.java.controller.SearchServlet?page=${1}">FirstPage</a>
                    &nbsp;
                    &nbsp;
                </c:if>
            </td>
            <td>
                <c:if test="${currentPage != 1}">
                    <a href="main.java.controller.SearchServlet?page=${currentPage - 1}">Previous</a>
                    &nbsp;
                    &nbsp;
                </c:if>
            </td>
            <c:if test="${noOfPages != 1}">
                <c:forEach begin="${firstPageIndex}" end="${lastPageIndex}" var="i">
                    <td>
                    <c:choose>
                        <c:when test="${currentPage eq i}">
                            <td>${i}</td>
                            &nbsp;
                            &nbsp;
                        </c:when>
                        <c:otherwise>
                            <a href="main.java.controller.SearchServlet?page=${i}">${i}</a>
                        </c:otherwise>
                    </c:choose>
                    </td>
                </c:forEach>
            </c:if>
            <td>
                <c:if test="${currentPage lt noOfPages}">
                    <a href="main.java.controller.SearchServlet?page=${currentPage + 1}">Next</a>
                    &nbsp;
                </c:if>
            </td>
            <td>
                <c:if test="${currentPage lt noOfPages}">
                    <a href="main.java.controller.SearchServlet?page=${noOfPages}">LastPage</a>
                    &nbsp;
                </c:if>
            </td>
        </tr>
    </table>
    <table border="1" cellpadding="2" cellspacing="2" align="center">
        <tr>
            <td colspan="3" align="center">
                <span style="font-weight: bold">${countryAirportsInfo.countryCode} &nbsp; - &nbsp; ${countryAirportsInfo.countryName}</span>
            </td>
        </tr>
        <tr>
            <td width="100" align="center"><span style="font-weight: bold">Row</span></td>
            <td width="450" align="center"><span style="font-weight: bold">Airport</span></td>
            <td width="450" align="center"><span style="font-weight: bold">Runway</span></td>
        </tr>

        <c:forEach var="airportVO" items="${countryAirportsInfo.airportVOList}">
            <tr>
                <td align="center">${airportVO.index}</td>
                <td>${airportVO.airportName}</td>
                <td>
                    <c:forEach var="runway" items="${airportVO.runwayList}">
                     ${runway}
                    <br>
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>