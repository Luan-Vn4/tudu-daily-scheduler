package br.upe.tudu.data.daos;

import java.util.List;

public interface DAO <Entity, Key>{
    void save (Entity entity);

    Entity uptade (Entity entity);

    Entity uptade(List<Entity> entityList);

    void deleteById (Key key);

    void delete(List<Entity> entities);

    Entity findById (Entity entity);

    Entity findAll (List<Entity> entities);
}
