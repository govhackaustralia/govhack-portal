package org.govhack.portal.security;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Objects;
import java.util.stream.Collectors;

public class GovhackUser<T extends IGovhackUser> extends User {

    private final T user;

    public GovhackUser(T user) {
        super(user.getUsername(), user.getPassword(), user.getRoles().stream().map(x -> new SimpleGrantedAuthority(x.getNiceName())).collect(Collectors.toList()));
        this.user = user;
    }

    public T getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GovhackUser)) return false;
        if (!super.equals(o)) return false;
        GovhackUser govhackUser = (GovhackUser) o;
        return Objects.equals(user, govhackUser.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), user);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("user", user)
                .toString();
    }
}
