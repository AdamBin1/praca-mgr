<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html >
<title>Etapy</title>
<head>

<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
<meta http-equiv="Pragma" content="no-cache">

</head>
<body>
	Aktualne etapy:
	<c:choose>
		<c:when test="${empty stages}">
			<br/><br/>Brak dostępnych - <button onclick="location.href='etapy/dodaj'">Dodaj</button>
		</c:when>
		<c:otherwise>
			<table id="stagesTable">
				<c:forEach items="${stages}" var="stage">
					<tr>
						<td>
							<label for="name">Nazwa: </label>
						</td>
						<td>
							<input id="name" value="${stage.name}" disabled="disabled"></input>
						</td>
						<td>
							<label for="sec">Numer w sekwencji: </label>
						</td>
						<td>
							<input id="sec" value="${stage.sec}" disabled="disabled"></input>
						</td>
						<td>
							<button onclick="location.href='etapy/edytuj/${stage.name}'">Edytuj</button>
						</td>
					</tr>
				</c:forEach>
			</table>
			<table>
				<tr>
					<td>
						<button onclick="location.href='etapy/dodaj'">Dodaj nowy etap</button>
					</td>
				</tr>
				<tr>
					<td>
						<button onclick="location.href='obiekt'">Wróć</button>
					</td>
				</tr>
			</table>
		</c:otherwise>
	</c:choose>
		
	
</body>
</html>