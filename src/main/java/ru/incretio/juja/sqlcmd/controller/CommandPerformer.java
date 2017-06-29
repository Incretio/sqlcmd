package ru.incretio.juja.sqlcmd.controller;

import ru.incretio.juja.sqlcmd.service.Service;
import ru.incretio.juja.sqlcmd.service.actions.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandPerformer {

    private Service service;

    public CommandPerformer(Service service) {
        this.service = service;
    }

    public void performGetFunction(HttpServletRequest req, HttpServletResponse resp) {
        switch (getActionName(req)) {
            case "/menu":
                new Menu(service, req, resp).doGet();
                break;
            case "/closeConnection":
                new CloseConnection(service, req, resp).doGet();
                break;
            case "/help":
                new Help(service, req, resp).doGet();
                break;
            case "/connect":
                new Connect(service, req, resp).doGet();
                break;
            case "/select":
                new SelectTable(service, req, resp).doGet();
                break;
            case "/takeTablesList":
                new TablesList(service, req, resp).doGet();
                break;
            case "/createTable":
                new CreateTable(service, req, resp).doGet();
                break;
            case "/dropTable":
                new DropTable(service, req, resp).doGet();
                break;
            case "/insert":
                new InsertRecord(service, req, resp).doGet();
                break;
            case "/update":
                new UpdateRecord(service, req, resp).doGet();
                break;
            case "/delete":
                new DeleteRecord(service, req, resp).doGet();
                break;
            case "/clear":
                new ClearTable(service, req, resp).doGet();
                break;
            case "/errorPage":
                new ErrorPage(service, req, resp).doGet();
                break;
            default:
                new DefaultAction(service, req, resp).doGet();
        }
    }

    public void performPostFunction(HttpServletRequest req, HttpServletResponse resp) {
        switch (getActionName(req)) {
            case "/connect":
                new Connect(service, req, resp).doPost();
                break;
            case "/createTable":
                new CreateTable(service, req, resp).doPost();
                break;
            case "/insert":
                new InsertRecord(service, req, resp).doPost();
                break;
            case "/update":
                new UpdateRecord(service, req, resp).doPost();
                break;
            case "/delete":
                new DeleteRecord(service, req, resp).doPost();
                break;
            case "/clear":
                new ClearTable(service, req, resp).doPost();
                break;
            case "/dropTable":
                new DropTable(service, req, resp).doPost();
                break;
        }
    }


    private Action getAction(HttpServletRequest request) {
        // ToDo: implement method
//        List<Action> actionList = new ArrayList<>();
//        actionList.addAll(Arrays.asList(
//            new ClearTable()
//        ));
//        for (:
//             ){
//
//        }

        return null;
    }

    private String getActionName(HttpServletRequest req) {
        String requestURI = req.getRequestURI();
        return requestURI.substring(req.getContextPath().length(), requestURI.length());
    }


}
