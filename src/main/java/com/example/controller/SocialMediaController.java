package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private MessageService messageService;

    //Account Endpoint
    @PostMapping("/register")
    public ResponseEntity<Account> createAccount(@RequestBody Account account){
        Account accountCreated = accountService.createAccount(account);
        if( accountCreated== null)
            return  ResponseEntity.status(409).body(null);

        return  ResponseEntity.ok(account);
    }

    @PostMapping("/login")
    public ResponseEntity<Account> login(@RequestBody Account account){
        Account user = accountService.loginUser(account);
        if(user== null){
            return  ResponseEntity.status(401).body(null);
        }
        return ResponseEntity.ok(user);
    }


    //Messages Endpoint

    
    @PostMapping("/messages")
    public ResponseEntity<Message> createMessage(@RequestBody Message message){
        Message messageCreated = messageService.createMessage(message);
        if(messageCreated == null){
            return ResponseEntity.status(400).body(null);
        }
        return ResponseEntity.ok(messageCreated);
    }
    @GetMapping("/accounts/{postedBy}/messages")
    public List<Message> getAllMessageForUser(@PathVariable Integer postedBy){
        return messageService.RetrieveAllMessagesForUser(postedBy);
    }
    @GetMapping("/messages")
    public List<Message> getAllMessagesMessagesAvailable(){

        return messageService.getAllMessagesMessagesAvailable();
    }
    @GetMapping("/messages/{messageId}")
    public Message getMessage(@PathVariable int messageId){
        return messageService.getMessageById(messageId);
    }

    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<?> deleteMessage(@PathVariable int messageId){
       int found = messageService.deleteMessage(messageId);
       if(found>0){
        return ResponseEntity.ok(found);

       }
       return ResponseEntity.ok("");
    }
    @PatchMapping("/messages/{messageId}")
    public ResponseEntity<?> updateMessage(@PathVariable int messageId, @RequestBody Message  message){
            int found = messageService.updateMesage(messageId, message.getMessageText());
            if(found > 0){
                return ResponseEntity.ok(found);

            }
            return ResponseEntity.status(400).body("");
    }

}
