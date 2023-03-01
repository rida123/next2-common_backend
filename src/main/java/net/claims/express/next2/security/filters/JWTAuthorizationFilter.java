package net.claims.express.next2.security.filters;

import net.claims.express.next2.security.model.CustomTokenAuthentication;
import net.claims.express.next2.security.services.CustomAuthManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private CustomAuthManager authenticationManager;

    public JWTAuthorizationFilter(AuthenticationManager authManager) {
        super(authManager);
        this.authenticationManager = (CustomAuthManager)authManager;
    }

    //    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
      //if no bearer in authorization header => continue filter chain
        //this filter is only interested in requests with Bearer in the Authorization header
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer")) {
            chain.doFilter(request, response);
            return;
        }

        //the next step is to get an object of type UsernamePasswordAuthenticationToken
        //and that what we will use to store information about our user
        //{we want to store username + his roles in this authentication token}
        //after that we will put this authentication object in the SecurityContextHolder
        var authObject = new CustomTokenAuthentication(false, header, null, null);
        var authenticationResult = this.authenticationManager.authenticate(authObject);
        if(authenticationResult.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authenticationResult);
            //carry on doing the processing...
            //lastly what we want to do is to propagate to the next filter in the filter chain
            chain.doFilter(request, response);//ONLY WHEN  THE AUTHENTICATION WORKS
        }

    }


}
