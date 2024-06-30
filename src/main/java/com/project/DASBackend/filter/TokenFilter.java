//package com.project.DASBackend.filter;
//
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseAuthException;
//import com.google.firebase.auth.FirebaseToken;
//import com.project.DASBackend.util.JwtTokenUtil;
//import io.jsonwebtoken.ExpiredJwtException;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.util.Collections;
//
//@Component
//public class TokenFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private UserDetailsService jwtUserDetailsService;
//
//    @Autowired
//    private JwtTokenUtil jwtTokenUtil;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
//            throws ServletException, IOException {
//        String authorizationHeader = request.getHeader("Authorization");
//        String typeToken = null;
//        String idToken = null;
//        idToken = authorizationHeader.substring(7);
//
//        try{
//            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
//            typeToken = "firebase";
//            logger.info(typeToken);
//        } catch (FirebaseAuthException e) {
//            typeToken = "jwt";
//            logger.info(typeToken);
//        } finally {
//            if(typeToken.equals("firebase")){
//                logger.info(typeToken);
//                if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//                    try {
//                        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
//                        String uid = decodedToken.getUid();
//
//                        User principal = new User(uid, "", Collections.emptyList());
//                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(principal, idToken, Collections.emptyList());
//                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//                        SecurityContextHolder.getContext().setAuthentication(authentication);
//                    } catch (FirebaseAuthException e) {
//                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                        return;
//                    }
//                }
//
//                chain.doFilter(request, response);
//            }else{
//                if (typeToken.equals("jwt")){
//                    // thực hiện theo jwt
//                    logger.info("jwt");
//                    String uid = null;
//                    String jwtToken = null;
//                    if (idToken != null && idToken.startsWith("Bearer ")) {
//                        jwtToken = idToken.substring(7);
//                        try {
//                            logger.info("JWT Token: " + jwtToken);
//                            uid = jwtTokenUtil.getUidFromToken(jwtToken);
//                            logger.info("Extracted Username: " + uid);
//                        } catch (IllegalArgumentException e) {
//                            logger.error("Unable to get JWT Token");
//                        } catch (ExpiredJwtException e) {
//                            logger.error("JWT Token has expired");
//                        } catch (Exception e) {
//                            logger.error("Unexpected error while extracting UID from JWT Token");
//                        }
//                    } else {
//                        logger.warn("JWT Token does not begin with Bearer String ");
////                if (jwtToken != null) {
////                    logger.info("JWT: " + jwtToken.substring(7));
////                    logger.warn("Username: " + uid);
////                }
//                    }
//
//                    // Once we get the token validate it.
//                    if (uid != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//
//                        UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(uid);
//
//                        // if token is valid configure Spring Security to manually set
//                        // authentication
//                        if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
//
//                            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
//                                    userDetails, null, userDetails.getAuthorities());
//                            usernamePasswordAuthenticationToken
//                                    .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                            // After setting the Authentication in the context, we specify
//                            // that the current user is authenticated. So it passes the
//                            // Spring Security Configurations successfully.
//                            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//                        }
//                    }
//                    chain.doFilter(request, response);
//                }
//
//            }
//        }
//
//
//
//
//    }
//}
