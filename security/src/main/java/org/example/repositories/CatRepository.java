package org.example.repositories;

import org.example.securityEntity.Cat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CatRepository extends JpaRepository<Cat, Integer> {
    public List<Cat> getAllByOwnerId(Integer id);
    public List<Cat> getAllByName(String name);
    public List<Cat> getAllByBirthday(LocalDate birthday);
    public List<Cat> getAllByBreed(String breed);
    public List<Cat> getAllByColor(Cat.ColorType colorType);
}
