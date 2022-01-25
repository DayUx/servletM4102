package com.iut.servlets;

import java.io.*;
import java.util.Date;
import java.text.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Action extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("entier") != null) {
            HttpSession session = request.getSession();

            if (session.getAttribute("nb") == null) {
                session.setAttribute("nb", 1);
            }
            PrintWriter pw = response.getWriter();
            int val = Integer.parseInt(request.getParameter("entier"));
            pw.println("Requête n°"+ session.getAttribute("nb") +"La racine carré de " + val + " est : " + Math.sqrt(val));
            session.setAttribute("nb", (int)session.getAttribute("nb") + 1);
        }
    }
}