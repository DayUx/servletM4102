package com.iut.servlets;

import java.io.*;
import java.util.Date;
import java.text.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.json.simple.*;



public class Action extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        JSONObject json = new JSONObject();

        if (request.getParameter("entier") != null) {
            PrintWriter pw = response.getWriter();
            int val = Integer.parseInt(request.getParameter("entier"));
            json.put("entier", Math.sqrt(val));
            response.setContentType("text/json");
            pw.println(json.toJSONString());
        }
    }
}