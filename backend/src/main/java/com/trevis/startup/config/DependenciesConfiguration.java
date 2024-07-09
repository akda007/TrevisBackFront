package com.trevis.startup.config;

import com.trevis.startup.interfaces.*;
import com.trevis.startup.services.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.context.WebApplicationContext;

import com.trevis.startup.sessions.UserSession;


@Configuration
public class DependenciesConfiguration {

    @Bean @Scope() @Primary
    protected BCryptPasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    @Bean @Scope() @Primary
    protected JwtTokenManager jwtTokenManager() {
        return new JwtTokenManagerService();
    }

    @Bean @Scope() @Primary
    protected LoginService loginService() { return new LoginServiceDefault(); }

    @Bean @Scope() @Primary
    protected UserDataService userDataService() { return new UserDataServiceDefault(); }

    @Bean @Scope() @Primary
    protected DepartmentDataService departmentDataService() { return new DepartmentDataServiceDefault(); }

    @Bean @Scope() @Primary
    protected ServiceDataService serviceDataService() { return new ServiceDataServiceDefault(); }

    @Bean @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS) @Primary
    protected UserSession userSession() { return new UserSession(); }
}