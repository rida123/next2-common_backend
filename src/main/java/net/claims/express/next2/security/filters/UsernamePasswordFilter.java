package net.claims.express.next2.security.filters;


import com.fasterxml.jackson.databind.ObjectMapper;
import net.claims.express.next2.security.model.UsernamePasswordAuthentication;
import net.claims.express.next2.security.services.CustomAuthManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class UsernamePasswordFilter extends OncePerRequestFilter {

    CustomAuthManager authManager;

    public void setAuthenticationManager(CustomAuthManager manager){
        this.authManager = manager;
    }

    public void setAuthManager(CustomAuthManager manager) {
        this.authManager = manager;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String username;
        String password;

        try {
            System.out.println("we are in filter to be debugged");
            filterChain.doFilter(request, response);
            /*
             ServletInputStream is =  request.getInputStream();
            Map<String, String> requestMap = new ObjectMapper().readValue(is, Map.class);
            username = requestMap.get("username");
            password = requestMap.get("password");

            var a = new UsernamePasswordAuthentication(username, password);

            var authentication_object = this.authManager.authenticate(a);

            if(authentication_object.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(authentication_object);
                filterChain.doFilter(request, response);
            }
//catch (IOException e)
        */} catch (Exception e) {
            System.out.println("we are in filter but catch blcok: " + e.getMessage());
            filterChain.doFilter(request, response);
//            e.printStackTrace();
//            throw new AuthenticationServiceException(e.getMessage(), e);
        }
    }
}
