package com.nt.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Servlet1 extends HttpServlet {
	private static final String verifyQuery="SELECT COUNT(*) AS count FROM admin Where user=? AND password=?";
	

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		String name=req.getParameter("name");
		String pass=req.getParameter("password");
		pw.println("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css'>");

		pw.println("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css'>");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
			if(con!=null) {
				System.out.println("connected");
			}
			PreparedStatement ps=con.prepareStatement(verifyQuery);
			ps.setString(1, name);
			ps.setString(2, pass);
			
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				int x=rs.getInt("count");
				if(x==1) {
					HttpSession ses=req.getSession();
					ses.setAttribute("admin-name", name);
					ses.setAttribute("admin-pass", pass);
					pw.println(" <style>\r\n"
							+ "      .verification{\r\n"
							+ "        height: 100px;\r\n"
							+ "        width: 800px;\r\n"
							+ "        background-color: brown;\r\n"
							+ "        border:2px red solid;\r\n"
							+ "        border-radius: 25px;\r\n"
							+ "        margin-left: 350px;\r\n"
							+ "        margin-top: 100px;\r\n"
							+ "        padding: 20px;\r\n"
							+ "        \r\n"
							+ "       \r\n"
							+ "        color: white;\r\n"
							+ "\r\n"
							+ "      }\r\n"
							+ "      .verification span {\r\n"
							+ "        font-size: 30px;\r\n"
							+ "        margin-left: 150px;\r\n"
							+ "\r\n"
							+ "      }\r\n"
							+ "      a{\r\n"
							+ "        font-size: 50px;\r\n"
							+ "        text-decoration: none;\r\n"
							+ "        color: brown;\r\n"
							+ "        text-align:centre;"
							+ "\r\n"
							+ "      }\r\n"
							+ "      \r\n"
							+ "    </style>");
					pw.println(" <div class=\"verification\">\r\n"
							+ "    <span >USER VERIFIED SUCCESSFULLY <span class=\"bi bi-check\"></span>  </span>\r\n"
							+ "        \r\n"
							+ "\r\n"
							+ "  </div>");
					
					
					pw.println("<a style='text-align:centre; text-decoration:none font-size:50px' href="+res.encodeUrl("Servlet2")+">CLICK TO GO FOR PROFILE </a>");
				}
				else {
					pw.println("  <marquee style=\"color:red\">INVALID USERID OR PASSWORD</marquee>\r\n");
					RequestDispatcher rd=req.getRequestDispatcher("adminlogin.html");
					rd.include(req, res);


				}
			}
			
			
			
		}catch(Exception e) {
			RequestDispatcher rd=req.getRequestDispatcher("/ErrorServlet");
			rd.forward(req, res);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
