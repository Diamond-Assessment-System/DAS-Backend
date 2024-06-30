package com.project.DASBackend.filter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.project.DASBackend.util.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class TokenFilter extends OncePerRequestFilter {

    private final UserDetailsService jwtUserDetailsService;
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public TokenFilter(UserDetailsService jwtUserDetailsService, JwtTokenUtil jwtTokenUtil) {
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String idToken = authorizationHeader.substring(7);
            String typeToken = null;
            FirebaseToken decodedToken = null;

            try {
                decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
                typeToken = "firebase";
            } catch (FirebaseAuthException e) {
                typeToken = "jwt";
            }

            if ("firebase".equals(typeToken)) {
                String uid = decodedToken.getUid();

                User principal = new User(uid, "", Collections.emptyList());
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(principal, idToken, Collections.emptyList());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
                chain.doFilter(request, response);
            } else {
                String jwtToken = null;
                String uid = null;

                try {
                    uid = jwtTokenUtil.getUidFromToken(idToken);
                    jwtToken = idToken;

                    if (uid != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                        UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(uid);

                        if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
                            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                                    userDetails, null, userDetails.getAuthorities());
                            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                            SecurityContextHolder.getContext().setAuthentication(authentication);
                        }
                    }
                } catch (IllegalArgumentException e) {
                    logger.error("Unable to get JWT Token");
                } catch (ExpiredJwtException e) {
                    logger.error("JWT Token has expired");
                } catch (Exception e) {
                    logger.error("Unexpected error while extracting UID from JWT Token");
                }
                chain.doFilter(request, response);
            }
        } else {
            chain.doFilter(request, response);
        }
    }
}
