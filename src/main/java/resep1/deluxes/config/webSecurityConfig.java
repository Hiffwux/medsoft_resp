package resep1.deluxes.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import resep1.deluxes.domain.user;
import resep1.deluxes.repo.userDetailRepo;

import java.time.LocalDateTime;

@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
public class webSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .mvcMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
    }

    @Bean
    public PrincipalExtractor principalExtractor(userDetailRepo userDetailRepo) {
        return map -> {
          String id = (String) map.get("sub");
          user user = userDetailRepo.findById(id).orElseGet(() -> {
                  user newUser = new user();
                  newUser.setId(id);
                  newUser.setName((String) map.get("name"));
                  newUser.setEmail((String) map.get("email"));
                  newUser.setGender((String) map.get("gender"));
                  newUser.setLocale((String) map.get("locale"));
                  newUser.setPic((String) map.get("pic"));

                   return newUser;
                  });
            user.setLastVisit(LocalDateTime.now());
             return userDetailRepo.save(user);
        };
    }
}
