<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html >
<head>

<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
<meta http-equiv="Pragma" content="no-cache">

</head>
<body>
	Dost&#281;pne pola wyboru:
	<c:choose>
		<c:when test="${empty comboboxes}">
			<br/><br/>Brak dostępnych - <button onclick="location.href='pola_wyboru/dodaj'">Dodaj</button>
		</c:when>
		<c:otherwise>
			<table id="comboTable">
				<c:forEach items="${comboboxes}" var="combobox">
					<tr>
						<td>
							<label for="${combobox.id}">Nazwa: </label>
						</td>
						<td>
							<input id="${combobox.id}" value="${combobox.name}" disabled="disabled"></input>
						</td>
						<td>
							<button onclick="location.href='pola_wyboru/edytuj/${combobox.name}'">Edytuj</button>
						</td>
					</tr>
				</c:forEach>
			</table>
			<table>
				<tr>
					<td>
						<button onclick="location.href='pola_wyboru/dodaj'">Dodaj nowe pole wyboru</button>
					</td>
				</tr>
				<tr>
					<td>
						<button onclick="location.href='/SpringMVC/konfiguracja'">Wróć</button>
					</td>
				</tr>
			</table>
		</c:otherwise>
	</c:choose>
		
	
</body>
</html>