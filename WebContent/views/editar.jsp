<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

<title>Editar Producto</title>
</head>
<body>
	<h1>Editar Producto</h1>
	<form action="productos" method="post">
	<c:set var="product" value="${producto}"></c:set>
		<input type="hidden" name="opcion" value="editar" />
		<input type="hidden" name="id" value="${product.id}" />
		<table border=1>
			<tr>
				<td>Nombre:</td>
				<td><input type="text" name="txtName" size="50" value="${product.nombre}"/></td>
			</tr>
			<tr>
				<td>Cantidad:</td>
				<td><input type="number" name="txtCantidad" size="50" value="${product.cantidad}"/></td>
			</tr>
			<tr>
				<td>Precio:</td>
				<td><input type="text" name="txtPrecio" size="50" value="${product.precio}"/></td>
			</tr>
		</table>
		<input type="submit" value="Guardar" />
	</form>
</body>
</html>