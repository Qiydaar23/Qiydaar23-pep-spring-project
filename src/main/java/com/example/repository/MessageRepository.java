package com.example.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Message;

@Repository
public interface MessageRepository extends CrudRepository<Message, Integer> {
    List<Message> findByPostedBy(int postedBy);
    @Transactional
    @Modifying
    @Query("DELETE FROM Message m WHERE m.messageId = :messageId")
    int deleteMessageById(int messageId);
    @Transactional
    @Modifying
    @Query("UPDATE Message m SET m.messageText = :messageText WHERE m.messageId = :messageId")
    int updateMessage(int messageId, String messageText);
    

}
