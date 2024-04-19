package hkmu.comps380f.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)
            throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/user/create").hasRole("ADMIN")
                        .requestMatchers("/user").hasRole("ADMIN")
                        .requestMatchers("/user/").hasRole("ADMIN")
                        .requestMatchers("/user/delete/**").hasRole("ADMIN")
                        .requestMatchers("/user/deleteComment/**").hasRole("ADMIN")
                        .requestMatchers("/user/edituser/**").hasRole("ADMIN")
                        .requestMatchers("/user/list").hasRole("ADMIN")
                        .requestMatchers("/book/create/**").hasRole("ADMIN")
                        .requestMatchers("/book/edit/**").hasRole("ADMIN")
                        .requestMatchers("/book/delete/**").hasRole("ADMIN")
                        .requestMatchers("/book/*/delete/**").hasRole("ADMIN")

                        .requestMatchers("/user/selfEdit").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/book/checkout").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/book/order").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/book/shop").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/book/viewCart").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/book/*/comment").hasAnyRole("USER", "ADMIN")
                        .anyRequest().permitAll()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .failureUrl("/login?error")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                )
                .rememberMe(remember -> remember
                        .key("uniqueAndSecret")
                        .tokenValiditySeconds(86400)
                )
                .httpBasic(withDefaults());
        return http.build();
    }

}
