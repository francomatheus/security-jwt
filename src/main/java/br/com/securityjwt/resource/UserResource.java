package br.com.securityjwt.resource;

import br.com.securityjwt.modal.request.UserRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/token")
public class UserResource {

    @PostMapping
    public ResponseEntity<?> getUser(@RequestBody UserRequest userRequest){

        System.out.println(userRequest.getEmail() + userRequest.getPassword());

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
