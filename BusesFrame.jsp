<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
  <head>
    <title>Schedule</title>
  </head>

  <body>
  <h1>We found some buses.. :)</h1>

  <table border="1">
    <caption>Buses</caption>
      <tr>
        <th>Bus number</th>
        <th>Free seats</th>
      </tr>
      <c:forEach var="bus" items="${form.buses}">
        <tr><td>"${bus.busNumber}"</td><td>"${bus.freeSeats}"</td></tr>
      </c:forEach>
  </table>

  <form action="initial" method="post">
    <input type="submit" name="button1" value="Return" />
  </form>

  </body>
</html>