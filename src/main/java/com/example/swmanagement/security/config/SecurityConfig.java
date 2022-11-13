package com.example.swmanagement.security.config;

import com.example.swmanagement.security.metadatasource.FilterInvocationMetaDataSource;
import com.example.swmanagement.security.TokenProvider;
import com.example.swmanagement.security.handler.JwtAccessDeniedHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

import java.util.ArrayList;
import java.util.List;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenProvider tokenProvider;

    private final CorsFilter corsFilter;

    private final JwtAuthenticationEntryFilter jwtAuthenticationEntryFilter;

    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/h2/**")
                .antMatchers("/swagger-ui/**")
                .antMatchers("/swagger-resources/**")
                .antMatchers("/error");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryFilter)// 우리가 만든 클래스로 인증 실패 핸들링
                .accessDeniedHandler(jwtAccessDeniedHandler)// 커스텀 인가 실패 핸들링
                .and()// embedded h2를 위한 설정
                .headers()
                .frameOptions()
                .sameOrigin()
                .and()
                // 세션을 사용하지 않기 때문에 STATELESS로 설정
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/signup").permitAll()
                .anyRequest().authenticated()
                .and()
                .apply(new JwtSecurityConfig(tokenProvider));

//        http.authorizeHttpRequests()
//                .antMatchers("/login").permitAll()
//                .antMatchers("/signup").permitAll()
//                .antMatchers("/users").permitAll()
//                .and()
//                .apply(new JwtSecurityConfig(tokenProvider));
//
//        http.csrf().disable()
//                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
//                .exceptionHandling()
//                .authenticationEntryPoint(jwtAuthenticationEntryFilter)
//                .accessDeniedHandler(jwtAccessDeniedHandler)
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
////                .and()
////                .addFilterAfter(getFilterSecurityInterceptor(), FilterSecurityInterceptor.class);
    }

//    @Bean
//    public FilterSecurityInterceptor getFilterSecurityInterceptor() {
//        FilterSecurityInterceptor interceptor = new FilterSecurityInterceptor();
//        interceptor.setAccessDecisionManager(affirmativeBased());
//        interceptor.setSecurityMetadataSource(getReloadableFilterInvocationSecurityMetadataSource());
//        return interceptor;
//    }
//
//    @Bean
//    public FilterInvocationSecurityMetadataSource getReloadableFilterInvocationSecurityMetadataSource() {
//        return new FilterInvocationMetaDataSource();
//    }
//
//    private AccessDecisionManager affirmativeBased() {
//        AffirmativeBased affirmativeBased = new AffirmativeBased(getAccessDecisionVoters());
//        return affirmativeBased;
//    }
//
//    @Bean
//    public List<AccessDecisionVoter<?>> getAccessDecisionVoters() {
//        List<AccessDecisionVoter<? extends Object>> accessDecisionVoters = new ArrayList<>();
////        accessDecisionVoters.add(roleVoter());
//        return accessDecisionVoters;
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
