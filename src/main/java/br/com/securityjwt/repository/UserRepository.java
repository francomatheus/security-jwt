package br.com.securityjwt.repository;

import br.com.securityjwt.modal.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Long, UserEntity> {

    Optional<UserEntity> findByEmail(String email);
}