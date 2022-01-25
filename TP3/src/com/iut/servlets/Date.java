package com.iut.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

public class Date extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        PrintWriter pw;

        response.setContentType("text/html");
        pw=response.getWriter();
        pw.println("<h1>"+ dateFormat.format(new java.util.Date())+"<h2>");
    }
}