package br.com.securityjwt.service.impl;

import br.com.securityjwt.modal.entity.UserEntity;
import br.com.securityjwt.repository.UserRepository;
import br.com.securityjwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Optional<UserEntity> byEmail = userRepository.findByEmail(s);
        if (byEmail.isPresent()) {
            return byEmail.get();
        }

        throw new UsernameNotFoundException("Wrong user!");
    }
}
