package net.claims.express.next2.security.providers;

import lombok.AllArgsConstructor;
import net.claims.express.next2.security.model.UsernamePasswordAuthentication;
import net.claims.express.next2.security.services.JpaUserDetailsService;
import net.claims.express.next2.security.services.PwdEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class UsernamePwdProvider implements AuthenticationProvider {

    @Autowired
    private JpaUserDetailsService userDetailsService;

    @Autowired
    private PwdEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
       String username = authentication.getName();
       String password = (String) authentication.getCredentials();


        UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
        String user_details_pwd = userDetails.getPassword();
        String encoded_input_password = this.passwordEncoder.passwordEncoder().encode(password);

        if(this.passwordEncoder.passwordEncoder().matches(encoded_input_password, userDetails.getPassword())) {
            return new UsernamePasswordAuthentication(userDetails, password, userDetails.getAuthorities());
        }
        throw new BadCredentialsException("Bad Credentials: password encoder failed");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthentication.class.equals((authentication));
    }
}
