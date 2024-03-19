package br.upe.tudu.data.daos;

import java.util.List;

public interface DAO <Entity, Key> {

    void save (Entity entity);

    Entity update (Entity entity);

    List<Entity> update(List<Entity> entityList);

    void deleteById (Key key);

    void deleteById(List<Key> keys);

    void delete(Entity entity);

    void delete(List<Entity> entities);

    Entity findById (Key key);

    List<Entity> findAll ();

}
