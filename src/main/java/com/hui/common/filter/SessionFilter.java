package com.hui.common.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;


public class SessionFilter extends OncePerRequestFilter {


    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String clientSessionId = request.getParameter("ssid");
        String serverSessionId = request.getSession().getId();
        if (serverSessionId.equals(clientSessionId)) {
        	filterChain.doFilter(request, response);
        } else {
            response.sendRedirect("/common/dataError");
        }
    }
}
