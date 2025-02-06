package com.example.service;

import com.example.entity.Account;
import org.springframework.stereotype.Service;
import com.example.repository.*;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public Account createAccount(Account account){
        if(findAccountByUsername(account.getUsername()) !=null)
            return null;
        return accountRepository.save(account);
    }
    public Account findAccountByUsername(String username){
        //Optional<Account> optional accountRepository.findAccountByUsername(username
        return accountRepository.findByUsername(username.trim()).orElse(null);
    }
    public Account findAccountById(int id){
        //Optional<Account> optional accountRepository.findAccountByUsername(username
        return accountRepository.findById(id).orElse(null);
    }
    public Account loginUser(Account account){
        Account currentAccount  = findAccountByUsername(account.getUsername());
        if(currentAccount == null)
        return null;

        if(currentAccount.getPassword().equals(account.getPassword()) && 
        currentAccount.getUsername().equals(account.getUsername())){
            return currentAccount;
        }
        return null;

    }
    
}
