package org.example.services;

import org.example.exceptions.UserException;
import org.example.securityEntity.Cat;
import org.example.securityEntity.Owner;
import org.example.repositories.CatRepository;
import org.example.repositories.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CatServiceImpl implements CatService{
    @Autowired
    CatRepository catRepository;
    @Autowired
    OwnerRepository ownerRepository;
    @Autowired
    private UserCheckService userCheckService;
    @Override
    public Cat addCat(String name, LocalDate birthday, String breed,
                      String color, int ownerId, int tailLength, String ownerUsername) {
        Owner owner = ownerRepository.getReferenceById(ownerId);
        Cat cat = new Cat(name, birthday, breed, Cat.ColorType.valueOf(color), owner, tailLength);
        userCheckService.IsUserHaveAuthorityCat(ownerUsername, cat);
        owner.addCat(cat);
        return catRepository.save(cat);
    }

    @Override
    public void deleteCat(int id, String ownerUsername) {
        userCheckService.IsUserHaveAuthorityCat(ownerUsername, catRepository.getReferenceById(id));
        catRepository.deleteById(id);
    }

    @Override
    public Cat getCatById(int id, String ownerUsername) {
        Cat cat = catRepository.getReferenceById(id);
        userCheckService.IsUserHaveAuthorityCat(ownerUsername, cat);
        return cat;
    }

    @Override
    public List<Cat> getAllByName(String name, String ownerUsername) {
        var rawList = catRepository.getAllByName(name);
        List<Cat> resultList = rawList.stream()
                .filter(x -> userCheckService.IsUserHaveAuthorityCatFilter(ownerUsername,x))
                .toList();
        if (resultList.isEmpty()) {
            throw UserException.ResultIsEmpty();
        }
        return resultList;
    }

    @Override
    public List<Cat> getAllByBirthday(LocalDate birthday, String ownerUsername) {
        var rawList = catRepository.getAllByBirthday(birthday);
        List<Cat> resultList = rawList.stream()
                .filter(x -> userCheckService.IsUserHaveAuthorityCatFilter(ownerUsername,x))
                .toList();
        if (resultList.isEmpty()) {
            throw UserException.ResultIsEmpty();
        }
        return resultList;
    }

    @Override
    public List<Cat> getAllByBreed(String breed, String ownerUsername) {
        var rawList = catRepository.getAllByBreed(breed);
        List<Cat> resultList = rawList.stream()
                .filter(x -> userCheckService.IsUserHaveAuthorityCatFilter(ownerUsername,x))
                .toList();
        if (resultList.isEmpty()) {
            throw UserException.ResultIsEmpty();
        }
        return resultList;
    }

    @Override
    public List<Cat> getAllByColor(String color, String ownerUsername) {
        var rawList = catRepository.getAllByColor(Cat.ColorType.valueOf(color));
        List<Cat> resultList = rawList.stream()
                .filter(x -> userCheckService.IsUserHaveAuthorityCatFilter(ownerUsername,x))
                .toList();
        if (resultList.isEmpty()) {
            throw UserException.ResultIsEmpty();
        }
        return resultList;
    }

    @Override
    public List<Cat> getAllByOwnerId(int id, String ownerUsername) {
        var rawList = catRepository.getAllByOwnerId(id);
        List<Cat> resultList = rawList.stream()
                .filter(x -> userCheckService.IsUserHaveAuthorityCatFilter(ownerUsername,x))
                .toList();
        if (resultList.isEmpty()) {
            throw UserException.ResultIsEmpty();
        }
        return resultList;
    }

    @Override
    public void deleteAll(String ownerUsername) {
        Owner ownerOfUser = userCheckService.ownerOfUser(ownerUsername);
        var listCatForDelete = getAllByOwnerId(ownerOfUser.getId(), ownerUsername);
        listCatForDelete.forEach(x -> deleteCat(x.getId(), ownerUsername));
    }

    @Override
    public List<Cat> getAll(String ownerUsername) {
        var rawList = catRepository.findAll();
        List<Cat> resultList = rawList.stream()
                .filter(x -> userCheckService.IsUserHaveAuthorityCatFilter(ownerUsername,x))
                .toList();
        if (resultList.isEmpty()) {
            throw UserException.ResultIsEmpty();
        }
        return resultList;
    }

}
