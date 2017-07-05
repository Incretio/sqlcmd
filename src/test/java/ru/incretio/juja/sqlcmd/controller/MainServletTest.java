package ru.incretio.juja.sqlcmd.controller;

import org.junit.Test;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MainServletTest extends MainServlet {

    @Test
    public void getActionName_correct_test(){
        MainServlet mainServlet = new MainServlet();
        String uri = "/sqlcmd/help";
        HttpServletRequest req = mock(HttpServletRequest.class);
        when(req.getRequestURI()).thenReturn(uri);
        when(req.getContextPath()).thenReturn("/sqlcmd");

        String actual = mainServlet.getActionName(req);

        assertEquals("/help", actual);
    }

    @Test
    public void test(){
        MainServlet mainServlet = new MainServlet();
        String uri = "/sqlcmd/help";
        HttpServletRequest req = mock(HttpServletRequest.class);
        when(req.getRequestURI()).thenReturn(uri);
        when(req.getContextPath()).thenReturn("/sqlcmd");

        String actual = mainServlet.getActionName(req);

        assertEquals("/help", actual);
    }

}