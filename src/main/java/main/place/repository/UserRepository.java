package main.place.repository;

import main.place.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByCpf(String cpf);
    User findByMail(String mail);
}
