package org.example.services;

import org.example.securityEntity.Cat;
import org.example.securityEntity.Owner;

import java.time.LocalDate;
import java.util.List;

public interface CatService {
    public Cat addCat(String name, LocalDate birthday, String breed, String color, int ownerId, int tailLength, String ownerUsername);
    public void deleteCat(int id, String ownerUsername);
    public Cat getCatById(int id, String ownerUsername);
    public List<Cat> getAllByName(String name, String ownerUsername);
    public List<Cat> getAllByBirthday(LocalDate birthday, String ownerUsername);
    public List<Cat> getAllByBreed(String breed, String ownerUsername);
    public List<Cat> getAllByColor(String color, String ownerUsername);
    public List<Cat> getAllByOwnerId(int id, String ownerUsername);
    public void deleteAll(String ownerUsername);
    public List<Cat> getAll(String ownerUsername);
}
