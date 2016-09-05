<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>Search For Airport Information Of Countries</title>
  <script type="text/javascript">
    function validateInput() {
      if (document.getElementById("code").checked) {
        if (document.form.searchField.value == "") {
          alert("Please enter country code");
          document.form.searchField.focus();
          return false;
        }
      }
      if (document.getElementById("name").checked) {
        if (document.form.searchField.value == "") {
          alert("Please enter country name.");
          document.form.searchField.focus();
          return false;
        }
      }
      return true;
    }
  </script>
</head>
<body style="background-color: deepskyblue">
<form name="form" action="controller.SearchServlet" method="post" onsubmit="return validateInput();">
  <h1 align="center">Java Web App For Searching Airports And Runways</h1>

  <div style="width:100%;height:100%;">
    <table style="width:100%;height:100%;">
        <tr height="100px"></tr>
        <tr>

            <td align="center">
                <INPUT TYPE="radio" NAME="radios" VALUE="radio1" id="code" CHECKED> Searching by country code
                &nbsp;
                &nbsp;
                &nbsp;
                <INPUT TYPE="radio" NAME="radios" VALUE="radio2" id="name"> Searching by country name
            </td>
        </tr>
        <tr height="20px"></tr>
        <tr>
            <td align="center">
                <Span> Enter search field: </Span>
                &nbsp;
                &nbsp;
                <input type="text" name="searchField">
            </td>
        </tr>
        <tr height="10px"></tr>
        <tr>
            <td align="center">
                <input type="submit" value="Search"/>
            </td>
        </tr>
    </table>
  </div>
</form>
</body>
</html>