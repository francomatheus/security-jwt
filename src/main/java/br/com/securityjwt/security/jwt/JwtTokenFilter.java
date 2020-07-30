package br.com.securityjwt.security.jwt;

import br.com.securityjwt.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {

    JwtTokenService tokenService;

    public JwtTokenFilter(JwtTokenService tokenService){
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("Filter request");
        String authorization = request.getHeader("Authorization");
        Boolean tokenValid = tokenService.isTokenValid(authorization);
        if(tokenValid){

            UsernamePasswordAuthenticationToken userByToken = tokenService.getUserByToken(authorization);

            SecurityContextHolder.getContext().setAuthentication(userByToken);

        }

        filterChain.doFilter(request, response);
        log.info("Filter finish");
    }
}
