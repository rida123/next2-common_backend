package net.claims.express.next2.security.configuration;

import net.claims.express.next2.security.filters.JWTAuthorizationFilter;
import net.claims.express.next2.security.filters.UsernamePasswordFilter;
import net.claims.express.next2.security.services.CustomAuthManager;
import net.claims.express.next2.security.services.JpaUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.token.Sha512DigestUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JpaUserDetailsService jpaUserDetailsService;
    @Autowired
    private CustomAuthManager authManager;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        var usernamePwdFilter = new UsernamePasswordFilter();

       /*  rs: 15-2-23 basicAuth(old way it was custom filter having bugs)
        UsernamePasswordFilter up_authentication_filter = new UsernamePasswordFilter();
        up_authentication_filter.setAuthenticationManager(this.authManager);*/

        //Requirements for BASIC AUTHENTICATION part
        http.cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .httpBasic().and() //rs: 15-2-23(added)
                .authenticationManager(authManager)
//                .userDetailsService(jpaUserDetailsService) todo check later
                .authorizeRequests().
                antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/api/basicAuth/**").permitAll();
//     rs: 15-2-23:::.and().httpBasic().and().addFilterAfter(up_authentication_filter, UsernamePasswordAuthenticationFilter.class);
        //cors configuration didn't work, solution add configuration file CORSConfig:
        /*http.cors(c -> {
            CorsConfigurationSource cs = r -> {
                CorsConfiguration cc = new CorsConfiguration();
                cc.setAllowedOrigins(List.of("http://localhost:3000", "http://localhost:4200"));
                cc.setAllowedMethods(List.of("OPTIONS", "GET", "POST"));
                cc.setAllowCredentials(false);
                cc.setAllowedHeaders(List.of("*"));
                return cc;
            };
        });*/
        //end cors
        //a          .mvcMatchers(HttpMethod.OPTIONS, "/api/basicAuth/**").permitAll()
        //a           .mvcMatchers("/api/basicAuth/validate").hasAnyAuthority("dmReception", "dmCeUsr");
//               .and().build();

        //Requirements for JWT part
        //commenting out jwt part: (temporary) //
        // jwt part:
        http.csrf().disable().authorizeRequests()
                .mvcMatchers("/hello").hasAuthority("dmDataEntry") //for demo
                .mvcMatchers("/demo").hasAuthority("dmDataEntry_test") //for demo
                .mvcMatchers("/getNotificationSearch").hasAuthority("dmSaveDataEntry") //for demo
                .and().addFilter(new JWTAuthorizationFilter(this.authManager));
//         return http.build();
        return http.build();
    }

}