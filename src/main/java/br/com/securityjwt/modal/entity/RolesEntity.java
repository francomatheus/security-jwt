package br.com.securityjwt.modal.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class RolesEntity implements GrantedAuthority {

    @Id
    private Long id;
    private String role;

    @Override
    public String getAuthority() {
        return this.role;
    }
}
