package main.java.emlakburada.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import emlakburada.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

    User findById(int id);

}
