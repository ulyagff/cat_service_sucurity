package org.example.services;

import org.example.securityEntity.Cat;
import org.example.securityEntity.Owner;
import org.example.repositories.CatRepository;
import org.example.repositories.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OwnerServiceImpl implements OwnerService {
    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private CatRepository catRepository;

    @Override
    public Owner addOwner(String name, LocalDate birthday) {
        Owner owner = new Owner(name, birthday);
        return ownerRepository.save(owner);
    }
    @Override
    public void deleteOwner(int id) {
        ownerRepository.deleteById(id);
    }
    @Override
    public void addCatToOwner(int catId, int ownerId) {
        Cat cat = catRepository.getReferenceById(catId);
        Owner owner = ownerRepository.getReferenceById(ownerId);
        owner.addCat(cat);
    }

    @Override
    public Owner getOwnerById(int id) {
        return ownerRepository.getReferenceById(id);
    }

    @Override
    public List<Owner> getAllByName(String name) {
        return ownerRepository.getAllByName(name);
    }

    @Override
    public List<Owner> getAllByBirthday(LocalDate birthday) {
        return ownerRepository.getAllByBirthday(birthday);
    }

    @Override
    public void deleteAll() {
        ownerRepository.deleteAll();
    }

    @Override
    public List<Owner> getAll() {
        return ownerRepository.findAll();
    }
}
