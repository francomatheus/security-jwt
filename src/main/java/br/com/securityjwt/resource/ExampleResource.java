package br.com.securityjwt.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ExampleResource {

    @GetMapping("/example")
    public ResponseEntity<String> getHelloWorld(){
        return ResponseEntity.status(HttpStatus.OK).body("Hello World");
    }

}
