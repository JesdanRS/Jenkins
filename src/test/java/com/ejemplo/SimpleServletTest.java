package com.ejemplo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class SimpleServletTest {

    private SimpleServlet servlet;
    
    @Mock
    private HttpServletRequest request;
    
    @Mock
    private HttpServletResponse response;
    
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        servlet = new SimpleServlet();
    }
    
    @Test
    public void testDoGet() throws Exception {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        
        Mockito.when(response.getWriter()).thenReturn(writer);
        
        servlet.doGet(request, response);
        
        writer.flush();
        String result = stringWriter.toString();
        
        assertTrue(result.contains("<!DOCTYPE html>"));
        assertTrue(result.contains("<title>Aplicación Simple para Jenkins y Tomcat</title>"));
        assertTrue(result.contains("¡Hola desde mi aplicación Java desplegada con Jenkins en Tomcat!"));
    }
}