package org.govhack.portal.data.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.govhack.portal.data.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends Repository<User, UUID> {
    @Query("select x from User x where lower(x.username) = lower(?1)")
    Optional<User> findByUsernameCaseInsensitive(String name);

    @Query("select x from User x where lower(x.username) = lower(?1)")
    Optional<User> findById(String name);

    @Query("select x from User x")
    Optional<List<User>> findAll();

    User findByUsername(String username);

    User save(User entity);
}
