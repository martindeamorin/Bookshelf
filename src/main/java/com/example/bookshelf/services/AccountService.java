package com.example.bookshelf.services;

import com.example.bookshelf.dao.AccountDAO;
import com.example.bookshelf.models.Account;
import com.example.bookshelf.models.Rol;
import com.example.bookshelf.utils.ResponseHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {
    @Autowired
    private AccountDAO accountDAO;
    @Autowired 
    private RolService rolService;
    @Autowired
    private JwtService jwtService;

    @Transactional    
    public Account createUser(Account account){
        return accountDAO.save(account);
    }
    
    @Transactional
    public Account getUserByUsername(String username){
        return accountDAO.findByUsername(username);
    }
    @Transactional    
    public List<GrantedAuthority> getUserRolesAsGranthedAuthority(List<Rol> roles) {
        
        ArrayList<GrantedAuthority> grantedRoles = new ArrayList();
        
        roles.stream()
                .forEach(e -> grantedRoles.add(new SimpleGrantedAuthority(e.getName())));
        
        return grantedRoles;
        
    }
    
    private String encryptPassword(String originalPassword){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(originalPassword);
    }
    
    private boolean comparePasswords(String rawPassword, String encodedPassword){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(rawPassword, encodedPassword);
    }
    
    public boolean areLoginCredentialsValid (Account account) {
        Account bdUser = this.getUserByUsername(account.getUsername());
        if(bdUser != null){
            if(this.comparePasswords(account.getPassword(), bdUser.getPassword())){
                return true;
            }
        }
        
        return false;
    }
    
    public ResponseEntity<Object> register(Account user) throws JsonProcessingException{
        try{
            Account newUser = new Account();
            newUser.setUsername(user.getUsername());
            newUser.setPassword(this.encryptPassword(user.getPassword()));
            newUser.setRoles(rolService.findOrCreate(user.getRoles()));
            Account createdUser = this.createUser(newUser);
            return ResponseHandler.generateResponse("Usuario registrado con exito", HttpStatus.CREATED, createdUser);
       
        } catch(JsonProcessingException e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }

    }
    
    public ResponseEntity<Object> login(Account user) throws JsonProcessingException{
        if(this.areLoginCredentialsValid(user)){
            Account account = this.getUserByUsername(user.getUsername());
            String token = this.jwtService.getJWTToken(account);
            return ResponseHandler.generateResponse(null, HttpStatus.OK, token);
        } else{
            return ResponseHandler.generateResponse("Las credenciales son incorrectas", HttpStatus.UNAUTHORIZED, null);
        }
    }
    
}
