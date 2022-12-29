package net.claims.express.next2.security.services;

import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Configuration
public class PwdEncoder {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new  BCryptPasswordEncoder();
        //in this example the pwd is stored un hashed in the db => use NoOpPasswordEncoder
//        return NoOpPasswordEncoder.getInstance();
    }
}
