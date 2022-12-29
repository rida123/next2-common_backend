package net.claims.express.next2.security.model;

import lombok.AllArgsConstructor;
import net.claims.express.next2.entities.Next2Authority;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
public class SecurityAuthority implements GrantedAuthority {

    private final Next2Authority authority;

    @Override
    public String getAuthority() {
        return authority.getName();
    }
}
