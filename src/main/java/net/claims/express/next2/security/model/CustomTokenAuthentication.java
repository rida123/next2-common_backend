package net.claims.express.next2.security.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class CustomTokenAuthentication implements Authentication {

    private final boolean authentication_status;
    private final String token;
    private List<GrantedAuthority> authorityList;
    private String username;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return this.authorityList;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return username;
    }

    @Override
    public boolean isAuthenticated() {
        return authentication_status;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
         return username;
    }
}
