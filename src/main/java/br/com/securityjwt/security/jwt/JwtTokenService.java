package br.com.securityjwt.security.jwt;

import br.com.securityjwt.modal.entity.UserEntity;
import br.com.securityjwt.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class JwtTokenService {
    @Value("${jwt.duration}")
    private String expirationToken;

    @Value("${jwt.secret}")
    private String secret;

    @Autowired
    private UserRepository userRepository;


    public String createToken(Authentication authenticate){
        log.info("Creating token");
        UserEntity user = (UserEntity) authenticate.getPrincipal();

        Date today = new Date();


        log.info("Token created");
        return Jwts.builder()
                .setIssuer("API Security")
                .setSubject(user.getEmail())
                .setIssuedAt(today)
                .setExpiration(new Date(today.getTime() + Long.parseLong(expirationToken)))
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();

    }

    public Boolean isTokenValid(String authorization) {

        log.info("Verify if token is valid");

        String tokenFromHeader = getTokenFromHeader(authorization);
        try{
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secret).parseClaimsJws(tokenFromHeader);
            log.info("Token valid");
            return true;
        }catch (Exception e){
            log.error("Token invalid");
            return false;
        }

    }

    private String getTokenFromHeader(String authorization) {
        log.info("Get token from header");
        if( authorization == null || !authorization.startsWith("Bearer ") || authorization.isEmpty() ){
            return null;
        }
        log.info("Return token");
        return authorization.substring(7);
    }


    public UsernamePasswordAuthenticationToken getUserByToken(String authorization) {
        log.info("Get user from token");

        String token = getTokenFromHeader(authorization);

        Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        String email = body.getSubject();
        UserEntity userEntity = userRepository.findByEmail(email).get();

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userEntity.getEmail(),null,userEntity.getAuthorities());

        return usernamePasswordAuthenticationToken;
    }
}
