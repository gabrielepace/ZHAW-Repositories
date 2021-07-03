package com.example.employees.access;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

public class TestLoginFilter {

    @Test
    public void testRedirectLogin() throws IOException, ServletException {
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getContextPath()).thenReturn("/empolyees-app");
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain chain = mock(FilterChain.class);
        LoginFilter filter = new LoginFilter();        
        filter.doFilter(request, response, chain);
        verify(response).sendRedirect(captor.capture());
        String path = request.getContextPath() + "/login.jsp";
        assertEquals(path, captor.getValue());
    }
    
    @Test
    public void testNoRedirectLogin() throws IOException, ServletException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getContextPath()).thenReturn("/empolyees-app");
        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("userid")).thenReturn("fooUserID");
        when(request.getSession(false)).thenReturn(session);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain chain = mock(FilterChain.class);
        LoginFilter filter = new LoginFilter();        
        filter.doFilter(request, response, chain);
        verifyZeroInteractions(response);
    }
}
