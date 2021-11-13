package com.example.bookshelf.services;

import com.example.bookshelf.dao.RolDAO;
import com.example.bookshelf.models.Rol;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RolService implements ICommonService<Rol> {
    @Autowired
    private RolDAO rolDAO;
    
    @Transactional    
    public Rol getRolByName(String name){
        return rolDAO.getRolByName(name);
    }
    
    @Override
    public Rol save(Rol entity) {
        return this.rolDAO.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        this.rolDAO.deleteById(id);
    }

    @Override
    public List<Rol> getAll() {
        return this.rolDAO.findAll();
    }

    @Override
    public Rol getById(Long id) {
        return this.rolDAO.getById(id);
    }
    
    @Override
    public List<Rol> findOrCreate(List<Rol> accountRoles) {
        ArrayList<Rol> roles = new ArrayList();
        accountRoles.forEach(r -> {
            Rol findRol = this.getRolByName(r.getName());
            if(findRol == null){
                roles.add(this.save(r));
            } else{
                roles.add(findRol);
            }
        });
        return roles;
    }

  
}
