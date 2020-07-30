package br.com.securityjwt.resource;

import br.com.securityjwt.modal.DTO.TokenDTO;
import br.com.securityjwt.modal.request.UserRequest;
import br.com.securityjwt.security.jwt.JwtTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("/auth/token")
public class UserResource {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenDTO> getUser(@RequestBody UserRequest userRequest){

        log.info("Login user");

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userRequest.getEmail(),userRequest.getPassword());
        try{
            Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            String token = tokenService.createToken(authenticate);

            return ResponseEntity.status(HttpStatus.OK).body(new TokenDTO("Bearer",token));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }


    }

}
