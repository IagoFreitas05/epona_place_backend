package main.place.securityjwt;

import main.place.services.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthFilter extends OncePerRequestFilter {

    private JwtService jwtService;
    private UserService userService;

    public JwtAuthFilter(JwtService jwtService, UserService userService){
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

       String authorization = httpServletRequest.getHeader("Authorization");
       if(authorization != null && authorization.startsWith("Bearer")){
           String token = authorization.split(" ")[1];
           boolean isValid = jwtService.isValidToken(token);
           if(isValid){
             String loginUser =   jwtService.getLoggedUser(token);
             UserDetails user =  userService.loadUserByUsername(loginUser);
               UsernamePasswordAuthenticationToken userToken = new
                       UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
               userToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
               SecurityContextHolder.getContext().setAuthentication(userToken);
           }
       }
       filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
