package br.com.securityjwt.resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class ExampleResource {

    @GetMapping("/example")
    public ResponseEntity<String> getHelloWorld(){
        log.info("Get Example");
        return ResponseEntity.status(HttpStatus.OK).body("Hello World");
    }

}
