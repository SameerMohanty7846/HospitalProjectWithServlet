package com.nt.patientStatus;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

public class PatientStatus extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		pw.println("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css'>");

		pw.println("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css'>");

		HttpSession ses = req.getSession();

		String name = (String) ses.getAttribute("name");
		int age = (Integer)ses.getAttribute("age");
		String city = (String) ses.getAttribute("city");
		String gender = (String) ses.getAttribute("gender");
		String issue = (String) ses.getAttribute("issue");
		String roomno = (String) ses.getAttribute("roomno");
		String regno = (String) ses.getAttribute("regno");
		String time = (String) ses.getAttribute("time");
		
		pw.println(" <div class=\"border border-3 rounded-3 border-warning w-50 p-4 m-4\">\r\n"
				+ "    <span class=\"p-3 m-3\">NAME:-</span> <span class=\"text-success p-1 m-1\"><b> "+name+" </b><br>"
						+ "</span><span class=\"p-3 m-3\">AGE:-</span> <span class=\"text-info p-1 m-1\"> <b>"+age+" </b></span><br>\r\n"
				+ "    <span class=\"p-3 m-3\">CITY:-</span> <span class=\"text-success p-1 m-1\"> <b>"+city+" </b><br>"
						+ "</span><span class=\"p-3 m-3\">GENDER:-</span> <span class=\"text-info p-1 m-1\"> <b>"+gender+" </b></span><br>\r\n"
				+ "    <span class=\"p-3 m-3\">ISSUE:-</span> <span class=\"text-success p-1 m-1\"> <b>"+issue+" </b><br>"
						+ "</span><span class=\"p-3 m-3\">ROOM NO:-</span> <span class=\"text-info p-1 m-1\"> <b>"+roomno+" </b><br>"
								+ "</span><br>\r\n"
				+ "    <span class=\"p-3 m-3\">REG NO:-</span> <span class=\"text-success p-1 m-1\"> <b>"+regno+" </b><br>"
						+ "</span><span class=\"p-3 m-3\">TIME OF REG:-</span> <span class=\"text-info p-1 m-1\"> <b>"+time+" </b></span><br>\r\n"
				+ "\r\n"
				+ "    \r\n"
				+ "\r\n"
				+ "  </div> <br> <br> " 
				+"<a href='index.html'>HOME</a>");
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
