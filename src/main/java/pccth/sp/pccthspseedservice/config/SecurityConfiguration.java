//package pccth.sp.pccthspseedservice.config;
//
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//    private static final String[] AUTH_WHITELIST = {
//            "/actuator/**",
//            "/favicon.ico",
//            "/v2/api-docs",
//            "/swagger-resources/**",
//            "/swagger-resources/configuration/**",
//            "/swagger-ui.html",
//            "/webjars/springfox-swagger-ui/**"
//    };
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.cors().and()
//            .csrf().disable()
//            .authorizeRequests()
//            .antMatchers().permitAll()
//            .anyRequest().authenticated()
//            .and()
//           // .addFilter(new JwtAuthenticationFilter(authenticationManager()))
//            .addFilter(new JwtAuthorizationFilter(authenticationManager()))
//            .sessionManagement()
//            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().mvcMatchers(AUTH_WHITELIST);
//    }
//}
