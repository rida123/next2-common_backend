package net.claims.express.next2.security.services;

import lombok.NoArgsConstructor;
import net.claims.express.next2.repositories.CoreUserRepository;
import net.claims.express.next2.repositories.DB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.token.Sha512DigestUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.security.MessageDigest;

@Configuration
public class PwdEncoder {

    @Autowired
    DB db;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                String passwordToHash = String.valueOf(rawPassword);
                String generatedPassword = null;
                try {
                    MessageDigest md = this.getMessageDigest();
                    String value = getConfigurationValue("saltSha");
                    Assert.hasLength(value);
                    md.update(value.getBytes());
                    byte[] bytes = md.digest(passwordToHash.getBytes());
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < bytes.length; i++) {
                        sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                    }
                    generatedPassword = sb.toString();
                    return generatedPassword;
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    return null;
                }

            }

            public String getConfigurationValue(String key) {
                String value = null;
                value= db.userRepository.findConfigByKey(key);
                return value;
            }

            public MessageDigest getMessageDigest() throws  Exception {
                return  MessageDigest.getInstance("SHA-512");
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                String plainPassword = String.valueOf(rawPassword);
                return    (!encode(plainPassword).matches(encodedPassword) );
            }
        };
    }


    public  String getConfigurationValue(String key) {
        String value = null;
        value = this.db.userRepository.findConfigByKey(key);
        return value;
    }

}
