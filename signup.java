package com.org;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Signup")
public class Signup extends Httpservlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signup() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String name = request.getParameter("Name");
		String city =  request.getParameter("City");
		String country = request.getParameter("Country");
		String username = request.getParameter("Uname");
		String password = request.getParameter("Password");
		System.out.println("Username : "+ username);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			  System.out.println("Connection Ready");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3305/login","root","root");
			System.out.println(con);
			
			PreparedStatement ps = con.prepareStatement("insert into register(Name,City,Country,Username,Password) values(?,?,?,?,?)");
			ps.setString(1, Name);
			ps.setString(2, City);
			ps.setString(3, Country);
			ps.setString(4, Username);
			ps.setString(5, Password);
			int row = ps.executeUpdate();
			if(row>=1) {
			out.print("Successfullly registered");
			RequestDispatcher rd = request.getRequestDispatcher("signup.jsp");
			rd.include(request, response);
			System.out.println("Registered!");
			}
			else {
				System.out.println("Not yet registered");
			}
		}catch(Exception e){
			System.out.println(e);
		}
		out.close();
		
	}

}