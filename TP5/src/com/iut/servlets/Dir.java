package com.iut.servlets;

import java.io.*;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.Date;
import java.text.*;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;

import org.json.simple.*;


public class Dir extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();

        String str = request.getParameter("file");

        String returnPath = FileSystems.getDefault().getPath(str).normalize().toAbsolutePath().toString();

        String[] returnPathTab = returnPath.split("/");
        String[] returnPathTab2 = new String[returnPathTab.length - 1];



        if (returnPathTab.length > 1) {
            for (int i = 0; i < returnPathTab.length - 1; i++) {
                returnPathTab2[i] = returnPathTab[i];
            }
            returnPath = String.join("/", returnPathTab2);

            jsonObject.put("path", returnPath);
            jsonObject.put("nom", "..");
            jsonObject.put("dir", true);
            jsonArray.add(jsonObject);
        }
        if (str != null && str.length() > 0) {
            File[] listeFichiers = new File(str).listFiles();


            for (File f : listeFichiers) {
                JSONObject json = new JSONObject();

                json.put("nom", f.getName());
                json.put("date", f.lastModified());
                json.put("taille", f.length());
                json.put("type", f.getName().substring(f.getName().lastIndexOf(".") + 1));
                json.put("path", f.getAbsolutePath());
                json.put("dir", f.isDirectory());
                jsonArray.add(json);
            }
        } else {
            File[] listeFichiers = new File("./").listFiles();
            assert listeFichiers != null;
            for (File f : listeFichiers) {
                JSONObject json = new JSONObject();

                json.put("nom", f.getName());
                json.put("date", f.lastModified());
                json.put("taille", f.length());
                json.put("type", f.getName().substring(f.getName().lastIndexOf(".") + 1));
                json.put("path", f.getAbsolutePath());
                json.put("dir", f.isDirectory());
                jsonArray.add(json);
            }
        }
        PrintWriter out = response.getWriter();
        out.println(jsonArray.toJSONString());
        out.flush();
    }




}