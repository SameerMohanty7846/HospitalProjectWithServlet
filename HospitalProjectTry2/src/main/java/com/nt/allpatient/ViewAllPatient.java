package com.nt.allpatient;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;


public class ViewAllPatient extends HttpServlet {
	private static final String viewAllPatient ="select * from regpatient";

	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=res.getWriter();
		pw.println("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css'>");

		pw.println("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css'>");

		res.setContentType("text/html");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
			System.out.println("Connection Created");
//			private static final String registerQuery = "INSERT INTO regpatient(name,age,city,gender,issue,roomno,regno,time) VALUES(?,?,?,?,?,?,?,?)";

			PreparedStatement ps=con.prepareStatement(viewAllPatient);
			ResultSet rs=ps.executeQuery();
			
			pw.println(" <table class=\"p-3 m-3 border border-3 border-dark w-100 h-75 m-4\">\r\n"
					+ "    <tr>\r\n"
					+ "      <th class=\"p-1 m-2 border border-3 border-dark p-4 bg-warning \">NAME</th>\r\n"
					+ "      <th class=\"p-1 m-2 border border-3 border-dark p-4 bg-warning\">AGE</th>\r\n"
					+ "      <th class=\"p-1 m-2 border border-3 border-dark p-4 bg-warning\">GENDER</th>\r\n"
					+ "      <th class=\"p-1 m-2 border border-3 border-dark p-4 bg-warning\">CITY</th>\r\n"
					+ "      <th class=\"p-1 m-2 border border-3 border-dark p-4 bg-warning\">ROOM NO</th>\r\n"
					+ "      <th class=\"p-1 m-2 border border-3 border-dark p-4 bg-warning\">REG NO</th>\r\n"
					+ "      <th class=\"p-1 m-2 border border-3 border-dark p-4 bg-warning\">TIME </th>\r\n"
					+ "\r\n"
					+ "\r\n"
					+ "     \r\n"
					+ "\r\n"
					+ "      \r\n"
					+ "\r\n"
					+ "    </tr>\r\n"
					+ "");
			while(rs.next()) {
				String name=rs.getString("name");
				int age=Integer.parseInt(rs.getString("age"));
				String gender=rs.getString("gender");
				String city=rs.getString("city");
				String roomno=rs.getString("roomno");
				String regno=rs.getString("regno");
				Timestamp time=rs.getTimestamp("time");
				pw.println(" <tr>\r\n"
						+ "      <td  class=\"p-1 m-2 border border-3 border-dark p-4 bg-success text-white\"> "+name+" </td>\r\n"
						+ "      <td  class=\"p-1 m-2 border border-3 border-dark p-4 bg-success text-white\"> "+age+" </td>\r\n"
						+ "      <td  class=\"p-1 m-2 border border-3 border-dark p-4 bg-success text-white\"> "+gender+" </td>\r\n"
						+ "      <td  class=\"p-1 m-2 border border-3 border-dark p-4 bg-success text-white\"> "+city+" </td>\r\n"
						+ "      <td  class=\"p-1 m-2 border border-3 border-dark p-4 bg-success text-white\"> "+roomno+"</td>\r\n"
						+ "      <td  class=\"p-1 m-2 border border-3 border-dark p-4 bg-success text-white\">"+regno+" </td>\r\n"
						+ "      <td  class=\"p-1 m-2 border border-3 border-dark p-4 bg-success text-white\"> "+time+" </td>\r\n"
						+ "\r\n"
						+ "\r\n"
						+ "\r\n"
						+ "    </tr>\r\n"
						+ "");

				
			}
			pw.println("\r\n"
					+ "  </table>");
			
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
