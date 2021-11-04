package com.example.bookshelf.services;

import com.example.bookshelf.dao.RolDAO;
import com.example.bookshelf.models.Rol;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RolService {
    @Autowired
    private RolDAO rolDAO;
    
    @Transactional    
    public Rol getRolByName(String name){
        return rolDAO.getRolByName(name);
    }
    
    @Transactional
    public Rol createRol(Rol rol){
        return rolDAO.save(rol);
    }
    
    @Transactional
    public List<Rol> findOrCreate(List<Rol> accountRoles){
        ArrayList<Rol> roles = new ArrayList();
        for(Rol r: accountRoles){
            Rol findRol = this.getRolByName(r.getName());
            if(findRol == null){
                roles.add(this.createRol(r));
            } else{
                roles.add(findRol);
            }
        }
        return roles;

    }
}
