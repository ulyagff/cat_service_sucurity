package org.example.services;

import org.example.exceptions.UserException;
import org.example.repositories.CatRepository;
import org.example.repositories.UserRepository;
import org.example.securityEntity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCheckService {
    @Autowired
    UserService userService;

    public void IsUserHaveAuthorityCat(String username, Cat cat) {
        User currentUser = userService.findByUsername(username);
        if (!currentUser.getRoles().contains(Role.RoleType.ROLE_ADMIN)) {
            if (cat.getOwner().getId() != currentUser.getOwner().getId()) {
                throw UserException.OwnerHasNotAuthorityCat();
            }
        }
    }

    public boolean IsUserHaveAuthorityCatFilter(String username, Cat cat) {
        User currentUser = userService.findByUsername(username);
        if (!currentUser.getRoles().contains(Role.RoleType.ROLE_ADMIN)){
            return cat.getOwner().getId() == currentUser.getOwner().getId();
        }
        return true;
    }

    public Owner ownerOfUser(String username) {
        User currentUser = userService.findByUsername(username);
        return currentUser.getOwner();
    }

}
