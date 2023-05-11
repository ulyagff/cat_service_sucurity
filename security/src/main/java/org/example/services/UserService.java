package org.example.services;

import org.example.securityEntity.Owner;
import org.example.securityEntity.Role;
import org.example.securityEntity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService extends UserDetailsService {
    public User findByUsername(String username);
    public User addUser( Owner owner, String password, List<Role> roles);
    public void deleteUser(int id);
    public void deleteAll();
}
