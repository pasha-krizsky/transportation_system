<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
  <head>
    <title>Schedule</title>
  </head>

  <body>
  <h1>Here you are your ticket</h1>

  <table border="1">
    <caption>Your ticket:</caption>
      <tr>
        <th>Bus number</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Station</th>
        <th>Time</th>
      </tr>
      <tr>
        <td>"${form.ticket.bus.busId}"</td><td>"${form.ticket.passenger.name}"</td>
        <td>"${form.ticket.passenger.surName}"</td><td>"${form.ticket.station.name}"</td>
        <td>"${form.ticket.time}"</td></tr>

  </table>

  <form action="initial" method="post">
    <input type="submit" name="button1" value="Return" />
  </form>

  </body>
</html>