package com.amit.job.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Set;

@Component
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        String returnUrl = request.getParameter("returnUrl");

        // Only allow relative (internal) URLs to prevent open redirects
        if (returnUrl != null && returnUrl.startsWith("/") && !returnUrl.startsWith("//") && !returnUrl.startsWith("/http")) {
            response.sendRedirect(returnUrl);
            return;
        }

        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        if (roles.contains("ROLE_EMPLOYER")) {
            response.sendRedirect("/employer/employer_dashboard");
        } else if (roles.contains("ROLE_APPLICANT")) {
            response.sendRedirect("/applicant/applicant_dashboard");
        } else {
            response.sendRedirect("/applicant/applicant_dashboard");
        }
    }
}
