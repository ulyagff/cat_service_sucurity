package org.example.services;


import org.example.securityEntity.Owner;

import java.time.LocalDate;
import java.util.List;

public interface OwnerService {
    public Owner addOwner(String name, LocalDate birthday);
    public void deleteOwner(int id);
    public void addCatToOwner(int catId, int ownerId);
    public Owner getOwnerById(int id);
    public List<Owner> getAllByName(String name);
    public List<Owner> getAllByBirthday(LocalDate birthday);
    public void deleteAll();
    public List<Owner> getAll();
}
