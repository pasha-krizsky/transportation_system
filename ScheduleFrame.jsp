<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
  <head>
    <title>Schedule</title>
  </head>

  <body>
  <h1>We found something... :)</h1>

  <table border="1">
    <caption>Schedule</caption>
      <tr>
        <th>Bus number</th>
        <th>Time</th>
      </tr>
      <c:forEach var="record" items="${form.records}">
        <tr><td>"${record.bus.busNumber}"</td><td>"${record.time}"</td></tr>
      </c:forEach>
  </table>

  <form action="initial" method="post">
    <input type="submit" name="button1" value="Return" />
  </form>

  </body>
</html>