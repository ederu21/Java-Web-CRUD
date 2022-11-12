package com.ederu.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ederu.dao.ProductoDAO;
import com.ederu.model.Producto;

/**
 * Servlet implementation class ProductoController
 */
@WebServlet(description = "Administra  peticiones a ala tabla procuto", urlPatterns = { "/productos" })
public class ProductoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String opcion = request.getParameter("opcion");
		if(opcion.equals("crear")){
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("views/crear.jsp");
			requestDispatcher.forward(request, response);
		}else if(opcion.equals("listar")){
			ProductoDAO productoDAO= new ProductoDAO();
			List<Producto> lista= new ArrayList<>();
			try {
				lista=productoDAO.obtenerProductos();
//				for (Producto producto : lista) {
//					System.out.println(producto);
//				}
				request.setAttribute("listaProductos", lista);
				RequestDispatcher requestDispatcher=request.getRequestDispatcher("/views/listar.jsp");
				requestDispatcher.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(opcion.equals("meditar")){
			int id =Integer.parseInt(request.getParameter("id"));
			ProductoDAO productoDAO= new ProductoDAO();
			Producto p= new Producto();
			try {
				p=productoDAO.obtenerProducto(id);
				request.setAttribute("producto", p);
				RequestDispatcher requestDispatcher=request.getRequestDispatcher("/views/editar.jsp");
				requestDispatcher.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(opcion.equals("eliminar")){
			int id =Integer.parseInt(request.getParameter("id"));
			ProductoDAO productoDAO= new ProductoDAO();
			try {
				productoDAO.eliminar(id);
				System.out.println("Registro Eliminado");
				RequestDispatcher requestDispatcher=request.getRequestDispatcher("/index.jsp");
				requestDispatcher.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String opcion = request.getParameter("opcion");
		Date fechaActual=new Date();
		
		if(opcion.equals("guardar")){
			ProductoDAO productoDAO= new ProductoDAO();
			Producto producto= new Producto();
			
			producto.setNombre(request.getParameter("txtName"));
			producto.setCantidad(Double.parseDouble(request.getParameter("txtCantidad")));
			producto.setPrecio(Double.parseDouble(request.getParameter("txtPrecio")));
			producto.setFechaCrear(new java.sql.Date(fechaActual.getTime()));
			
			try {
				productoDAO.guardar(producto);
				System.out.println("Registro Guardado");
				RequestDispatcher requestDispatcher=request.getRequestDispatcher("/index.jsp");
				requestDispatcher.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(opcion.equals("editar")){
			Producto producto= new Producto();
			ProductoDAO productoDAO= new ProductoDAO();
			producto.setId(Integer.parseInt(request.getParameter("id")));
			producto.setNombre(request.getParameter("txtName"));
			producto.setCantidad(Double.parseDouble(request.getParameter("txtCantidad")));
			producto.setPrecio(Double.parseDouble(request.getParameter("txtPrecio")));
			producto.setFechaActualizar(new java.sql.Date(fechaActual.getTime()));
			try {
				productoDAO.editar(producto);
				System.out.println("Registro Actualizado");
				RequestDispatcher requestDispatcher=request.getRequestDispatcher("/index.jsp");
				requestDispatcher.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		//doGet(request, response);
	}

}
