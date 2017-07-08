package ru.incretio.juja.sqlcmd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.incretio.juja.sqlcmd.service.Service;
import ru.incretio.juja.sqlcmd.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@Controller
public class MainController {

    private Service service;
    @Autowired
    private ServiceFactory serviceFactory;

    @RequestMapping(value = {"/", "/menu"}, method = RequestMethod.GET)
    public String menu(HttpServletRequest request) {
        configureRequest(request);
        return "menu";
    }

    @RequestMapping(value = "/help", method = RequestMethod.GET)
    public String help(HttpServletRequest request) {
        configureRequest(request);
        request.setAttribute("helpText", service.help());
        return "help";
    }

    @RequestMapping(value = "/clear", method = RequestMethod.GET)
    public String clearGet(HttpServletRequest request) {
        configureRequest(request);
        return "clear";
    }

    @RequestMapping(value = "/clear", method = RequestMethod.POST)
    public String clearPost(@RequestParam("tableName") String tableName, HttpServletRequest request) {
        configureRequest(request);
        try {
            String message = service.clear(tableName);
            return openMenuPage(request, message);
        } catch (Exception e) {
            return toProcessServiceException(request, e);
        }
    }

    @RequestMapping(value = "/closeConnection", method = RequestMethod.GET)
    public String closeConnection(HttpServletRequest request) {
        configureRequest(request);
        try {
            String message = service.closeConnection();
            return openMenuPage(request, message);
        } catch (Exception e) {
            return toProcessServiceException(request, e);
        }
    }

    @RequestMapping(value = "/connect", method = RequestMethod.GET)
    public String connectGet(HttpServletRequest request) {
        configureRequest(request);
        return "connect";
    }

    @RequestMapping(value = "/connect", method = RequestMethod.POST)
    public String connectPost(
            @RequestParam("serverName") String serverName,
            @RequestParam("dbName") String dbName,
            @RequestParam("userName") String userName,
            @RequestParam("password") String password,
            HttpServletRequest request) {
        configureRequest(request);
        request.getSession().setAttribute("service", service);
        String message;
        try {
            message = service.connect(serverName, dbName, userName, password);
            return openMenuPage(request, message);
        } catch (Exception e) {
            return toProcessServiceException(request, e);
        }
    }

    @RequestMapping(value = "/createTable", method = RequestMethod.GET)
    public String createTableGet(HttpServletRequest request) {
        configureRequest(request);
        return "createTable";
    }

    @RequestMapping(value = "/createTable", method = RequestMethod.POST)
    public String createTablePost(
            @RequestParam("tableName") String tableName,
            @RequestParam("columns") List<String> columns,
            HttpServletRequest request) {
        configureRequest(request);
        try {
            columns.removeIf(Objects::isNull);
            String message = service.createTable(tableName, columns);
            return openMenuPage(request, message);
        } catch (Exception e) {
            return toProcessServiceException(request, e);
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteGet(HttpServletRequest request) {
        configureRequest(request);
        return "delete";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deletePost(
            @RequestParam("tableName") String tableName,
            @RequestParam("whereColumnName") String whereColumnName,
            @RequestParam("whereColumnValue") String whereColumnValue,
            HttpServletRequest request) {
        configureRequest(request);
        try {
            String message = service.delete(tableName, whereColumnName, whereColumnValue);
            return openMenuPage(request, message);
        } catch (Exception e) {
            return toProcessServiceException(request, e);
        }
    }

    @RequestMapping(value = "/dropTable", method = RequestMethod.GET)
    public String dropTableGet(HttpServletRequest request) {
        configureRequest(request);
        return "dropTable";
    }

    @RequestMapping(value = "/dropTable", method = RequestMethod.POST)
    public String dropTablePost(
            @RequestParam("tableName") String tableName,
            HttpServletRequest request) {
        configureRequest(request);
        try {
            String message = service.dropTable(tableName);
            return openMenuPage(request, message);
        } catch (Exception e) {
            return toProcessServiceException(request, e);
        }
    }

    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public String insertGet(HttpServletRequest request) {
        configureRequest(request);
        return "insert";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insertPost(
            @RequestParam("tableName") String tableName,
            @RequestParam("columns") List<String> columns,
            @RequestParam("values") List<String> values,
            HttpServletRequest request) {
        configureRequest(request);
        columns.removeIf(Objects::isNull);
        values.removeIf(Objects::isNull);
        try {
            String message = service.insert(tableName, columns, values);
            return openMenuPage(request, message);
        } catch (Exception e) {
            return toProcessServiceException(request, e);
        }
    }

    @RequestMapping(value = "/select", method = RequestMethod.GET)
    public String select(@RequestParam("tableName") String tableName, HttpServletRequest request) {
        configureRequest(request);
        try {
            request.setAttribute("table", service.select(tableName));
            return "select";
        } catch (Exception e) {
            return toProcessServiceException(request, e);
        }
    }

    @RequestMapping(value = "/takeTablesList", method = RequestMethod.GET)
    public String takeTablesList(HttpServletRequest request) {
        configureRequest(request);
        try {
            request.setAttribute("tablesList", service.takeTablesList());
            return "takeTablesList";
        } catch (Exception e) {
            return toProcessServiceException(request, e);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String updateGet(HttpServletRequest request) {
        configureRequest(request);
        return "update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updatePost(
            @RequestParam("tableName") String tableName,
            @RequestParam("whereColumnName") String whereColumnName,
            @RequestParam("whereColumnValue") String whereColumnValue,
            @RequestParam("setColumnName") String setColumnName,
            @RequestParam("setColumnValue") String setColumnValue,
            HttpServletRequest request) {
        configureRequest(request);
        try {
            String message = service.update(tableName, whereColumnName, whereColumnValue, setColumnName, setColumnValue);
            return openMenuPage(request, message);
        } catch (Exception e) {
            return toProcessServiceException(request, e);
        }
    }

    private void configureRequest(HttpServletRequest req) {
        updateService(req);
        setMenuToAttribute(req);
    }

    protected void updateService(HttpServletRequest req) {
        Service sessionService = (Service) req.getSession().getAttribute("service");
        if (sessionService != null) {
            service = sessionService;
        } else {
            service = serviceFactory.makeService();
        }
    }

    private void setMenuToAttribute(HttpServletRequest request) {
        request.setAttribute("items", service.commandsList());
    }

    protected String openMenuPage(HttpServletRequest request, String message) {
        request.setAttribute("message", message);
        return "menu";
    }

    protected String toProcessServiceException(HttpServletRequest request, Exception e) {
        request.setAttribute("message", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
        return "errorPage";
    }
}
