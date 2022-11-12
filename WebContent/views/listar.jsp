<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

<title>Listar Productos</title>
</head>
<body>
	<h1>Listar Productos</h1>
	<table border=1>
		<tr>
			<th>Id</th>
			<th>Nombre</th>
			<th>Cantidad</th>
			<th>Precio</th>
			<th>Fecha Creacion</th>
			<th>Fecha Actualizacion</th>
			<th>Accion</th>
		</tr>
		<c:forEach var="producto" items="${listaProductos}">
		<tr>
			<td><a href="productos?opcion=meditar&id=<c:out value="${producto.id}"></c:out>"><c:out value="${producto.id}"></c:out></a></td>
			<td><c:out value="${producto.nombre}"></c:out></td>
			<td><c:out value="${producto.cantidad}"></c:out></td>
			<td><c:out value="${producto.precio}"></c:out></td>
			<td><c:out value="${producto.fechaCrear}"></c:out></td>
			<td><c:out value="${producto.fechaActualizar}"></c:out></td>
			<td><a href="productos?opcion=eliminar&id=<c:out value="${producto.id}"></c:out>"><c:out value="Eliminar"></c:out></a></td>
		</tr>
		</c:forEach>
	</table>

</body>
</html>