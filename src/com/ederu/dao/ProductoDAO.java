package com.ederu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ederu.model.Producto;

import como.ederu.conn.Conection;

public class ProductoDAO {
	private Connection connection;
	private PreparedStatement statement;
	private Boolean estadoOperacion;
	private String sql=null;
	
	
	public Boolean guardar(Producto producto) throws SQLException {
		
		estadoOperacion =false;
		connection =obtenerConexion();
		
		try {
			connection.setAutoCommit(false);
			sql="INSERT INTO productos (nombre, cantidad, precio, fecha_crear, fecha_actualizar) VALUES (?,?,?,?,?)";
			statement=connection.prepareStatement(sql);
			
			statement.setString(1, producto.getNombre());
			statement.setDouble(2, producto.getCantidad());
			statement.setDouble(3, producto.getPrecio());
			statement.setDate(4, producto.getFechaCrear());
			statement.setDate(5, producto.getFechaActualizar());
			estadoOperacion=statement.executeUpdate()>0;
			connection.commit();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			connection.rollback();
			e.printStackTrace();
		}
		return estadoOperacion;
	}

	public Boolean editar(Producto producto) throws SQLException {
		//String sql=null;
		estadoOperacion =false;
		connection =obtenerConexion();
		
		try {
			connection.setAutoCommit(false);
			sql="Update productos set nombre=?, cantidad=?, precio=?, fecha_actualizar=? WHERE id=?";
			statement=connection.prepareStatement(sql);
			
			statement.setString(1, producto.getNombre());
			statement.setDouble(2, producto.getCantidad());
			statement.setDouble(3, producto.getPrecio());
			statement.setDate(4, producto.getFechaActualizar());
			statement.setInt(5, producto.getId());
			
			estadoOperacion=statement.executeUpdate()>0;
			connection.commit();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			connection.rollback();
			e.printStackTrace();
		}
		return estadoOperacion;
	}

	public Boolean eliminar(int idProducto) throws SQLException{
		//String sql=null;
		estadoOperacion =false;
		connection =obtenerConexion();
		
		try {
			connection.setAutoCommit(false);
			sql="DELETE FROM productos WHERE id=?";
			statement=connection.prepareStatement(sql);
			
			statement.setInt(1, idProducto);
			
			estadoOperacion=statement.executeUpdate()>0;
			connection.commit();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			connection.rollback();
			e.printStackTrace();
		}
		return estadoOperacion;
	}

	public List<Producto> obtenerProductos() throws SQLException {
		//String sql=null;
		ResultSet resultSet;
		List<Producto> listaProductos= new ArrayList<>();
		estadoOperacion =false;
		connection =obtenerConexion();
		try {
			sql="SELECT * from Productos";
			statement=connection.prepareStatement(sql);
			resultSet= statement.executeQuery(sql);
			while(resultSet.next()){
				Producto producto=new Producto();
				producto.setId(resultSet.getInt("id"));
				producto.setNombre(resultSet.getString(2));
				producto.setCantidad(resultSet.getDouble(3));
				producto.setPrecio(resultSet.getDouble("precio"));
				producto.setFechaCrear(resultSet.getDate(5));
				producto.setFechaActualizar(resultSet.getDate("fecha_actualizar"));
				listaProductos.add(producto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaProductos;
	}

	public Producto obtenerProducto(int idProducto) throws SQLException {
		ResultSet resultSet;
		Producto producto=new Producto();
		estadoOperacion =false;
		connection =obtenerConexion();
		
		try {
			sql="SELECT * from Productos where id=?";
			statement=connection.prepareStatement(sql);
			statement.setInt(1, idProducto);
			resultSet= statement.executeQuery();
			
			if(resultSet.next()){
				producto.setId(resultSet.getInt("id"));
				producto.setNombre(resultSet.getString(2));
				producto.setCantidad(resultSet.getDouble(3));
				producto.setPrecio(resultSet.getDouble("precio"));
				producto.setFechaCrear(resultSet.getDate(5));
				producto.setFechaActualizar(resultSet.getDate("fecha_actualizar"));
			}else{
				producto=null;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return producto;
	}
	
	private Connection obtenerConexion () throws SQLException{
		return Conection.getConexion();
	}
}
