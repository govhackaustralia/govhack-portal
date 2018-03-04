package org.govhack.portal.service;

import org.govhack.portal.service.model.UserRoles;
import org.govhack.portal.web.model.UserCreateModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.govhack.portal.data.model.User;
import org.govhack.portal.data.repo.UserRepository;

@Component
@Transactional(rollbackFor = Exception.class)
public class UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public User create(UserCreateModel model) {
        User user = new User(
                model.getUsername(),
                passwordEncoder.encode(model.getPassword()),
                model.getEmail(),
                model.getFirstName(),
                model.getLastName(),
                UserRoles.fromString(model.getType())
        );
        userRepo.save(user);
        return user;
    }

    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

}
