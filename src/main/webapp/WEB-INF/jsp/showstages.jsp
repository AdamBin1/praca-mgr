<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html >
<title>Etapy</title>
<head>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
<style>
body {
	background-color: #f7f7f7;
}
.rounded40 {
	border-radius:40px;
}
.mainctr {
	background-color: white;
}

.break1 {
	margin-top:15px;
}
</style>

</head>

<body>
	<div id="mainContainer" class="container">
		<div class="card break1">
			<div class="card-header">Aktualne etapy:</div>
			<div class="card-body m-3">

			<c:choose>
				<c:when test="${empty stages}">
					<label class="col-10 col-form-label">Brak etapów</label>
					<div class="container">
						<div class="row break1">
							<div class="col-sm">
								<button class="btn btn-primary" onclick="location.href='etapy/dodaj'">Dodaj nowy etap</button>
								<button class="btn btn-light" onclick="location.href='produkt'">Wróć</button>
							</div>
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<c:forEach items="${stages}" var="stage">
							<div class="form-group row">
							  <label class="col-form-label">Nazwa</label>
							  <div class="col-5">
							    <input class="form-control" type="text" value="${stage.name}" disabled="disabled"/>
							  </div>
							  <label class="col-form-label">Numer w sekwencji</label>
							  <div class="col-3">
							    <input class="form-control" type="text" value="${stage.sec}" disabled="disabled"/>
							  </div>
							  <div>
							  	<button class="btn btn-light" onclick="location.href='etapy/edytuj/${stage.id}'">Edytuj</button>
							  </div>
							</div>
					</c:forEach>
					<div class="container">
						<div class="row break1">
							<div class="col-sm">
								<button class="btn btn-primary" onclick="location.href='etapy/dodaj'">Dodaj nowy etap</button>
								<button class="btn btn-light" onclick="location.href='produkt'">Wróć</button>
							</div>
						</div>
					</div>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
</body>
</html>