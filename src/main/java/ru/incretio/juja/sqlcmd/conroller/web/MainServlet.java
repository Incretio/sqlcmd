package ru.incretio.juja.sqlcmd.conroller.web;

import ru.incretio.juja.sqlcmd.service.Service;
import ru.incretio.juja.sqlcmd.service.ServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Service service = new ServiceImpl();

        String requestURI = req.getRequestURI();
        String action = requestURI.substring(req.getContextPath().length(), requestURI.length());

        if (action.startsWith("/menu")){
            req.setAttribute("items", service.commandsList());
            req.getRequestDispatcher("menu.jsp").forward(req, resp);
        } else if (action.startsWith("/help")){
            req.getRequestDispatcher("help.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("missingPage.jsp").forward(req, resp);
        }
    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
//    }
}
