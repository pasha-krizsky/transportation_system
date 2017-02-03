<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
  <head>
    <title>Client side</title>
  </head>

  <body>
    <b>Buy ticket</b>
    <form action="ticket" method="POST">
      Bus:
      <select name="listBuses">
        <c:forEach var="bus" items="${form.buses}">
          <option value="${bus.busId}"><c:out value="${bus.busId}"/></option>
        </c:forEach>
      </select><br/>

      <select name="listStations">
        <c:forEach var="station" items="${form.stations}">
          <option value="${station.stationId}"><c:out value="${station.name}"/></option>
        </c:forEach>
      </select><br/>

      Name: <input type="name" name="name"/> <br/>
      Surname: <input type="surname" name="surname"/> <br/>
      Date of Birth: <input type="date" value="2000-02-01" name="calendar"/> <br/>
      Search from: <input type="datetime-local" value="2017-02-01T12:12:00" name="timeFrom"/><br/>
      <input type="submit" name="submit" value="buy"/>
    </form>

    <b>Find bus</b>
    <form action="buses" method="POST">
      From:
      <select name="listStationsFrom">
        <c:forEach var="station" items="${form.stations}">
          <option value="${station.stationId}"><c:out value="${station.name}"/></option>
        </c:forEach>
      </select><br/>

      To:
      <select name="listStationsTo">
        <c:forEach var="station" items="${form.stations}">
          <option value="${station.stationId}"><c:out value="${station.name}"/></option>
        </c:forEach>
      </select><br/>

      Min time: <input type="datetime-local" value="2017-02-01T11:11:00" name="minTime"/><br/>
      Max time: <input type="datetime-local" value="2017-02-01T16:16:00" name="maxTime"/><br/>

      <input type="submit" name="submit" value="find"/>
    </form>

    <b>Show schedule</b>
    <form action="show" method="POST">

      Station:
      <select name="listStations">
        <c:forEach var="station" items="${form.stations}">
          <option value="${station.stationId}"><c:out value="${station.name}"/></option>
        </c:forEach>
      </select><br/>

      <input type="submit" name="submit" value="show"/>
    </form>
  </body>
</html>