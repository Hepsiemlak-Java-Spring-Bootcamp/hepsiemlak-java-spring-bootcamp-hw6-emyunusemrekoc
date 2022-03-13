package main.java.emlakburada.repository;

import emlakburada.model.Advert;
import emlakburada.model.Message;
import emlakburada.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

    Message findById(int id);


}
