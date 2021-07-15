package ru.veqveq.conference.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.veqveq.conference.models.Role;

import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<Role, Byte> {
    List<Role> findAll();
}
