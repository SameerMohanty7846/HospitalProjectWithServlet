package com.nt.patientStatus;

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
import java.sql.Timestamp;

public class PatientServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String checkStatus="SELECT * FROM regPatient Where name=? AND regno=?";

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		String fname=req.getParameter("name");//not needed
		String fregno=req.getParameter("reg");
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
			System.out.println("Connection Created");
			PreparedStatement ps=con.prepareStatement(checkStatus);
			ps.setString(1, fname);
			ps.setString(2, fregno);
			
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				String name=rs.getString("name");
				int age =Integer.parseInt(rs.getString("age"));
				String city=rs.getString("city");
				String gender=rs.getString("gender");
				String issue=rs.getString("issue");
				String roomno=rs.getString("roomno");
				String regno=rs.getString("regno");
				String time=rs.getString("time");
				
				HttpSession ses=req.getSession();
				ses.setAttribute("name", name);
				ses.setAttribute("age", age);
				ses.setAttribute("city", city);
				ses.setAttribute("gender", gender);
				ses.setAttribute("issue", issue);
				ses.setAttribute("roomno", roomno);
				ses.setAttribute("regno", regno);
				ses.setAttribute("time", time);
				
				pw.println("<p>THE PATIENT EXISTS </p>");
				pw.println("<a href='"+res.encodeUrl("PatientStatus")+"'>CHECK STATUS</a>");

				


				
			}
			
		}catch(Exception e) {
			
		}
				//generate a report using the data
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
