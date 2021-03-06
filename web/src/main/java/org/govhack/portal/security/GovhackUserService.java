package org.govhack.portal.security;

import org.govhack.portal.data.model.User;
import org.govhack.portal.data.repo.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class GovhackUserService implements IGovhackUserService<User> {

    private final UserRepository userRepo;

    public GovhackUserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public Optional<User> findByUsernameCaseInsensitive(String name) {
        return userRepo.findByUsernameCaseInsensitive(name);
    }

    @Override
    public User findByUsername(String name) {
        return userRepo.findByUsername(name);
    }

    @Override
    public User save(User entity) {
        return userRepo.save(entity);
    }

    public User createNew(String username, String displayName, List<UserRoles> roles) {
        return new User(username, displayName, roles);
    }

}
