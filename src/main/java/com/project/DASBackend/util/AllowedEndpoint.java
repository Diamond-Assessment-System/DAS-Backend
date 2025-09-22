package com.project.DASBackend.util;

public class AllowedEndpoint {
    public static final String[] PUBLIC_URLS = {
            "/oauth2/authorization/google",
            "/oauth2/authorization/github",
            "/login/oauth2/code/*",
            "/grantcode",
            "/api/v1/login/non-type",
            "/api/auth/**",
            "/api/auth/*",
            "/api/user/v1/**",
            "/api/v1/feedback/all",
            "/v2/api-docs/**",
            "/v3/api-docs",
            "/v3/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/swagger-resources",
            "/swagger-resources/**",
            "/swagger-ui/index.html#/**",
            "/swagger-ui/index.html/**"
    };
}
