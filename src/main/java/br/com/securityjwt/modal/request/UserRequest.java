package br.com.securityjwt.modal.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

    private String email;
    private String password;

}
