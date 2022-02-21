package com.iut.servlets;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class FileLoad extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //response.setContentType("image/jpeg");
        ServletOutputStream out = response.getOutputStream();
        FileInputStream in = null;
        String str = request.getParameter("file");
        int cursor;


        if (str != null && str.length() > 0) {
            try {
                in = new FileInputStream(str);
                while ((cursor = in.read()) != -1) {
                    out.write(cursor);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            }
        }

    }
}
