package org.govhack.portal.security;

import org.govhack.portal.data.model.User;

import java.util.Optional;

public interface IGovhackUserService<T extends IGovhackUser> {
    Optional<T> findByUsernameCaseInsensitive(String name);
    T findByUsername(String name);
    T save(T entity);
}
