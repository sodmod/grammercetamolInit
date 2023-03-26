package com.grammercetamol.securities.jwt;

import com.grammercetamol.implementation.UserDetailsServicesImpl;
import com.grammercetamol.implementation.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter extends OncePerRequestFilter {

    @Autowired
    private JWTService jwtService;
    @Autowired
    private UserDetailsServicesImpl userDetailsServices;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        try {
            String token = parseToken(request);
            String username = jwtService.extractUsername(token);
            if (username != null) {
                UserServices userServices = (UserServices) userDetailsServices.loadUserByUsername(username);
                if (jwtService.isTokenValid(token, userServices)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(
                                    userServices,
                                    null,
                                    userServices.getAuthorities()
                            );
                    usernamePasswordAuthenticationToken.setDetails(request);
                    SecurityContextHolder.getContext()
                            .setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        } catch (Exception e) {

        }
        filterChain.doFilter(request, response);
    }

    private String parseToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (StringUtils.hasText(header) && header.startsWith("Bearer ")) {
            return header.substring(7, header.length());
        }
        return null;
    }
}
