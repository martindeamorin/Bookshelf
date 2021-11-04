
package com.example.bookshelf.services;

import com.example.bookshelf.models.Account;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    
    @Autowired
    private AccountService accountService;
    
    
    public String getJWTToken(Account account) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = this.accountService.getUserRolesAsGranthedAuthority(account.getRoles());

        String token = Jwts
            .builder()
            .setId("softtekJWT")
            .setSubject(account.getUsername())
            .claim("user", account.getUsername())
            .claim("authorities",
                            grantedAuthorities.stream()
                                            .map(GrantedAuthority::getAuthority)
                                            .collect(Collectors.toList()))
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 600000))
            .signWith(SignatureAlgorithm.HS512,
                            secretKey.getBytes()).compact();

        return token;
    }
}
