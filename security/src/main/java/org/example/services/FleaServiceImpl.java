package org.example.services;

import org.example.exceptions.UserException;
import org.example.securityEntity.Cat;
import org.example.securityEntity.Flea;
import org.example.repositories.CatRepository;
import org.example.repositories.FleaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FleaServiceImpl implements FleaService{

    @Autowired
    FleaRepository fleaRepository;

    @Autowired
    CatRepository catRepository;
    @Autowired
    private UserCheckService userCheckService;
    @Override
    public Flea addFlea(String name, int catId, String username) {
        Cat cat = catRepository.getReferenceById(catId);
        Flea flea = new Flea(name, cat);
        userCheckService.IsUserHaveAuthorityCat(username, cat);
        cat.addFlea(flea);
        return fleaRepository.save(flea);
    }

    @Override
    public void deleteFlea(int id, String username) {
        Flea flea = fleaRepository.getReferenceById(id);
        userCheckService.IsUserHaveAuthorityCat(username, flea.getCat());
        fleaRepository.deleteById(id);
    }

    @Override
    public Flea getFleaById(int id, String username) {
        Flea flea = fleaRepository.getReferenceById(id);
        userCheckService.IsUserHaveAuthorityCat(username, flea.getCat());
        return flea;
    }

    @Override
    public List<Flea> getAllByCatId(int id, String username) {
        Cat cat = catRepository.getReferenceById(id);
        userCheckService.IsUserHaveAuthorityCat(username, cat);
        return fleaRepository.getAllByCatId(id);
    }

    @Override
    public void deleteAll(String username) {
        var listFleaForDelete = getAll(username);
        listFleaForDelete.forEach(flea -> deleteFlea(flea.getId(), username));

    }

    @Override
    public List<Flea> getAll(String username) {
        var rawList = fleaRepository.findAll();
        List<Flea> resultList = rawList.stream()
                .filter(x -> userCheckService.IsUserHaveAuthorityCatFilter(username,x.getCat()))
                .toList();
        if (resultList.isEmpty()) {
            throw UserException.ResultIsEmpty();
        }
        return resultList;
    }
}
