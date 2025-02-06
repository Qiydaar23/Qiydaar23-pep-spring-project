package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.repository.MessageRepository;
import com.example.entity.Message;

@Service
public class MessageService {

    private MessageRepository messageRepository;
    private AccountService accountService;

    public MessageService(MessageRepository messageRepository,
                            AccountService accountService){
        this.messageRepository = messageRepository;
        this.accountService = accountService;
    }

    public Message createMessage(Message message){
        if(message.getMessageText().isEmpty()){
            return null;
        }if(message.getMessageText().length() > 255){
            return null;
        }
        if(accountService.findAccountById(message.getPostedBy()) == null){
            return null;
        }
        return messageRepository.save(message);

    }

    public List<Message> RetrieveAllMessagesForUser(Integer postedBy){
    
        return messageRepository.findByPostedBy(postedBy);
    } 

    public List<Message> getAllMessagesMessagesAvailable(){
        Iterable<Message> messages = messageRepository.findAll();
        List<Message> allMessages = new ArrayList<>();
        messages.forEach(allMessages::add);
        return allMessages;
    }

    public Message getMessageById(int messageId){
       return messageRepository.findById(messageId).orElse(null);
    }
    public int deleteMessage(int messageId){
        return messageRepository.deleteMessageById(messageId);
    }

    public int updateMesage(int messageId, String messageText){
        if(getMessageById(messageId) == null){
            return -1;
        }
        if(messageText.isEmpty()){
            return -1;
        }
        if(messageText.length()> 255){
            return -1;
        }
        return messageRepository.updateMessage(messageId, messageText);
    }
}
