package org.example.services;

import org.example.securityEntity.Flea;

import java.util.List;

public interface FleaService {
    public Flea addFlea(String name, int catId, String username);
    public void deleteFlea(int id, String username);
    public Flea getFleaById(int id, String username);
    public List<Flea> getAllByCatId(int id, String username);
    public void deleteAll(String username);
    public List<Flea> getAll(String username);
}
