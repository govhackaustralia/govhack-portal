package org.govhack.portal.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserDetailsService<T extends IGovhackUser> implements org.springframework.security.core.userdetails.UserDetailsService {

    private static final Logger LOG = LoggerFactory.getLogger(UserDetailsService.class);

    private IGovhackUserService<T> userRepository;

    public UserDetailsService(IGovhackUserService<T> userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public GovhackUser<T> loadUserByUsername(String username) {
        T c = userRepository.findByUsername(username);
        return new GovhackUser<>(c);
    }
}
