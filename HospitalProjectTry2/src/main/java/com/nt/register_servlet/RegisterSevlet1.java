package com.nt.register_servlet;

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
import java.sql.Timestamp;
import java.util.Random;

public class RegisterSevlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String registerQuery = "INSERT INTO regpatient(name,age,city,gender,issue,roomno,regno,time) VALUES(?,?,?,?,?,?,?,?)";

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=res.getWriter();
		pw.println("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css'>");

		pw.println("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css'>");
		
		res.setContentType("text/html");
		String name=req.getParameter("name");
		String Aage=req.getParameter("age");
		int age=Integer.parseInt(Aage);
		String city=req.getParameter("city");
		String gender=req.getParameter("gender");
		String issue=req.getParameter("disease");
		String roomNo="NOT";
		Timestamp time=new Timestamp(System.currentTimeMillis());
		Random rm=new Random();
		int min=10000;
		int max=30000;
        int gen=rm.nextInt(max-min+1)+min;
		String reg="REGAK"+String.valueOf(gen);
		

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
			System.out.println("Connection Created");
//			private static final String registerQuery = "INSERT INTO regpatient(name,age,city,gender,issue,roomno,regno,time) VALUES(?,?,?,?,?,?,?,?)";

			PreparedStatement ps=con.prepareStatement(registerQuery);
			ps.setString(1, name);
			ps.setInt(2, age);
			ps.setString(3, city);
			ps.setString(4, gender);
			ps.setString(5, issue);
			ps.setString(6, roomNo);
			ps.setString(7, reg);
			ps.setTimestamp(8, time);
			int x=ps.executeUpdate();
			if(x==1) {
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
						+ "    <span >INFORMATION SUBMITTED  SUCCESSFULLY   </span>\r\n<br> <br> <br>"
					    +"<a href='index.html'>HOME</a>"	
						+ "        \r\n"
						+ "\r\n"
						+ "  </div>");
			}else {
				pw.println("<p>ISSUE WITH DATABASE</p>");
			}
			
		}catch(Exception e) {
			RequestDispatcher rd=req.getRequestDispatcher("/ErrorServlet");
			rd.forward(req, res);
		}

	
		//now insert the info to database with current time if it returns 1 then inserted and show successful message
		//else use error servlet


		

		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
